package com.github.nathandelane.experiments.http.library;

import com.github.nathandelane.experiments.http.library.model.HttpHeaders;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpRequest;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpResponse;
import com.github.nathandelane.experiments.http.library.paths.RequestMapping;

import java.io.*;
import java.net.Socket;
import java.util.Map;

import static com.github.nathandelane.experiments.http.library.model.CommonHttpResponses.HTTP_404_NOT_FOUND;
import static com.github.nathandelane.experiments.http.library.model.CommonHttpResponses.HTTP_500_SERVER_ERROR;
import static com.github.nathandelane.experiments.http.library.model.ContentTypes.TEXT_PLAIN;
import static com.github.nathandelane.experiments.http.library.model.HttpConstants.*;

public class HttpHandler implements Runnable {

  private final Socket socket;

  public HttpHandler(final Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      handleRequest();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void handleRequest() throws IOException {
    System.out.format("Handling request...%n%n");

    InputStream br = null;

    try {
      br = socket.getInputStream();

      final StringBuilder sb = new StringBuilder();
      final StringBuilder lineBuilder = new StringBuilder();

      int contentLength = 0;
      int byteFromStream = 0;
      int newLineCount = 0;

      while ((byteFromStream = br.read()) >= 0) {
        final char ch = (char) byteFromStream;

        if (ch == '\n') newLineCount++;
        if (ch != '\r' && ch != '\n') newLineCount = 0;

        if (newLineCount > 0 && lineBuilder.length() > 0) {
          lineBuilder.append(ch);

          final String line = lineBuilder.toString();

          if (line.startsWith("Content-Length: ")) {
            final String length = line.substring("Content-Length: ".length()).trim();

            contentLength = Integer.parseInt(length);
          }

          sb.append(line);

          if (line.equals("\r\n")) break;

          lineBuilder.setLength(0);
        } else {
          lineBuilder.append(ch);
        }
      }

      if (contentLength > 0) {
        final byte[] content = br.readNBytes(contentLength);
        final String strFromBytes = new String(content);
        sb.append(strFromBytes);
      }

      final String request = sb.toString();
      final SimpleHttpRequest simpleHttpRequest = SimpleHttpRequest.parse(request, contentLength);

      System.out.format("------ REQUEST ------%n%n%s%n", simpleHttpRequest);

      RequestHandlerContainer requestHandlerContainer = RequestMapping.resolvePath(simpleHttpRequest.getHttpMethod(), simpleHttpRequest.getPath());

      if (requestHandlerContainer.requestHandler == null) requestHandlerContainer = getNotFoundHandlerForRequest(simpleHttpRequest);

      final SimpleHttpResponse simpleHttpResponse = requestHandlerContainer.apply(simpleHttpRequest);

      System.out.format("------ RESPONSE ------%n%n%s%n", simpleHttpResponse);

      handleResponse(simpleHttpResponse);
    } catch (final Exception e) {
      final SimpleHttpResponse response = getServerErrorHandlerForRequest(e).handle(null, null);

      try {
        handleResponse(response);
      } catch (final IOException ex) {
        throw new RuntimeException(ex);
      }
    }
    finally {
      if (br != null) {
        br.close();
      }
    }
  }

  public void handleResponse(final SimpleHttpResponse simpleHttpResponse) throws IOException {
    final OutputStream clientOutput = socket.getOutputStream();
    clientOutput.write(simpleHttpResponse.toBytes());
    clientOutput.flush();
    socket.close();
  }

  private RequestHandlerContainer getNotFoundHandlerForRequest(final SimpleHttpRequest simpleHttpRequest) {
    final RequestHandler requestHandler = new RequestHandler() {
      @Override
      public SimpleHttpResponse handle(final SimpleHttpRequest simpleHttpRequest, final Map<String, String> variableMapping) {
        final String responseBody = String.format("No handler found for method: %s and path: %s%n", simpleHttpRequest.getHttpMethod(), simpleHttpRequest.getPath());
        final Integer length = responseBody.getBytes().length;
        final HttpHeaders headers = new HttpHeaders();
        headers.putValue(HEADER_CONTENT_LENGTH, length.toString());
        headers.putValue(HEADER_CONTENT_TYPE, TEXT_PLAIN);

        return new SimpleHttpResponse(
          HTTP_404_NOT_FOUND,
          TEXT_PLAIN,
          headers,
          responseBody
        );
      }
    };

    return new RequestHandlerContainer(requestHandler, null);
  }

  private RequestHandler getServerErrorHandlerForRequest(final Exception e) {
    return new RequestHandler() {
      @Override
      public SimpleHttpResponse handle(final SimpleHttpRequest simpleHttpRequest, final Map<String, String> variablMapping) {
        final String responseBody = String.format("Unexpected server error occurred! %s%n%s", e.getMessage(),getStackTraceAsString(e));
        final Integer length = responseBody.getBytes().length;
        final HttpHeaders headers = new HttpHeaders();
        headers.putValue(HEADER_CONTENT_LENGTH, length.toString());
        headers.putValue(HEADER_CONTENT_TYPE, TEXT_PLAIN);

        return new SimpleHttpResponse(
          HTTP_500_SERVER_ERROR,
          TEXT_PLAIN,
          headers,
          responseBody
        );
      }

      private String getStackTraceAsString(final Exception e) {
        final StringBuilder stackTraceBuilder = new StringBuilder();
        final StackTraceElement[] stackTraceElements = e.getStackTrace();

        for(final StackTraceElement nextElement : stackTraceElements) {
          stackTraceBuilder.append("  at ").append(nextElement.toString()).append(String.format("%n"));
        }

        return stackTraceBuilder.toString();
      }

    };
  }

  public static void mapRequest(final String httpMethod, final String path, final RequestHandler requestHandler) {
    RequestMapping.addRequestMapping(httpMethod, path, requestHandler);
  }

}
