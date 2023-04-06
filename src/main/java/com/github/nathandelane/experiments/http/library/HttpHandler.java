package com.github.nathandelane.experiments.http.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static com.github.nathandelane.experiments.http.library.CommonHttpResponses.HTTP_404_NOT_FOUND;
import static com.github.nathandelane.experiments.http.library.ContentTypes.TEXT_PLAIN;
import static com.github.nathandelane.experiments.http.library.HttpConstants.*;

public class HttpHandler implements Runnable {

  private static final Map<RequestMethodAndPath, RequestHandler> REQUEST_MAPPING = new HashMap<>();

  private final Socket socket;

  public HttpHandler(final Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    handleRequest();
  }

  public void handleRequest() {
    System.out.format("Handling request...%n%n");

    try (final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      final StringBuilder sb = new StringBuilder();

      String line = null;

      while (!(line = br.readLine()).isBlank()) {
        sb.append(line).append(HTTP_NEW_LINE);
      }

      final String request = sb.toString();
      final SimpleHttpRequest simpleHttpRequest = SimpleHttpRequest.parse(request);

      System.out.format("------ REQUEST ------%n%n%s%n", simpleHttpRequest);

      final RequestMethodAndPath requestMethodAndPath = new RequestMethodAndPath(simpleHttpRequest.getHttpMethod(), simpleHttpRequest.getPath());

      RequestHandler requestHandler = REQUEST_MAPPING.get(requestMethodAndPath);
      if (requestHandler == null) requestHandler = getNotFoundHandlerForRequest(simpleHttpRequest);

      final SimpleHttpResponse simpleHttpResponse = requestHandler.handle(simpleHttpRequest);

      System.out.format("------ RESPONSE ------%n%n%s%n", simpleHttpResponse);

      handleResponse(simpleHttpResponse);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void handleResponse(final SimpleHttpResponse simpleHttpResponse) throws IOException {
    final OutputStream clientOutput = socket.getOutputStream();
    clientOutput.write(simpleHttpResponse.toBytes());
    clientOutput.flush();
    socket.close();
  }

  private RequestHandler getNotFoundHandlerForRequest(final SimpleHttpRequest simpleHttpRequest) {
    return new RequestHandler() {
      @Override
      public SimpleHttpResponse handle(final SimpleHttpRequest simpleHttpRequest) {
        final String responseBody = String.format("No handler found for method: %s and path: %s%n", simpleHttpRequest.getHttpMethod(), simpleHttpRequest.getPath());
        final Integer length = responseBody.getBytes().length;
        final Map<String, String> headers = new HashMap<>();
        headers.put(HEADER_CONTENT_LENGTH, length.toString());
        headers.put(HEADER_CONTENT_TYPE, "text/plain");

        return new SimpleHttpResponse(
          HTTP_404_NOT_FOUND,
          TEXT_PLAIN,
          headers,
          responseBody
        );
      }
    };
  }

  public static void mapRequest(final String httpMethod, final String path, final RequestHandler requestHandler) {
    final RequestMethodAndPath requestMethodAndPath = new RequestMethodAndPath(httpMethod, path);

    REQUEST_MAPPING.put(requestMethodAndPath, requestHandler);
  }

}
