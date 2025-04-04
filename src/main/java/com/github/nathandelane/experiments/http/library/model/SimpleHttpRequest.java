package com.github.nathandelane.experiments.http.library.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.github.nathandelane.experiments.http.library.model.HttpConstants.*;

public class SimpleHttpRequest {

  private final String httpMethod;

  private final String path;

  private final String protocolAndVersion;

  private final QueryStringParameters queryStringParameters;

  private final HttpHeaders headers;

  private final String body;

  private SimpleHttpRequest(
    final String httpMethod,
    final String path,
    final String protocolAndVersion,
    final QueryStringParameters queryStringParameters,
    final HttpHeaders headers,
    final String body
  ) {
    this.httpMethod = httpMethod;
    this.path = path;
    this.protocolAndVersion = protocolAndVersion;
    this.queryStringParameters = queryStringParameters;
    this.headers = headers;
    this.body = body;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public String getPath() {
    return path;
  }

  public String getProtocolAndVersion() {
    return protocolAndVersion;
  }

  public QueryStringParameters getQueryStringParameters() {
    return queryStringParameters;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

  public String getUserAgentString() {
    return headers.getValues(HEADER_USER_AGENT).iterator().next();
  }

  public String getContentType() {
    return headers.getValues("Content-Type").iterator().next();
  }

  public String getAccept() {
    return headers.getValues(HEADER_ACCEPT).iterator().next();
  }

  public String getBody() {
    return body;
  }

  public WebForm getBodyAsWebForm() {
    return new WebForm(body);
  }

  @Override
  public String toString() {
    return "SimpleHttpRequest{" +
      "httpMethod='" + httpMethod + '\'' +
      ", path='" + path + '\'' +
      ", protocolAndVersion='" + protocolAndVersion + '\'' +
      ", queryStringParameters=" + queryStringParameters +
      ", headers=" + headers +
      ", body=" + body +
      '}';
  }

  public static SimpleHttpRequest parse(final String request, final int contentLength) {
    final AtomicReference<String> outBody = new AtomicReference<>();
    final List<String> requestTokens = tokenizeRequest(request, outBody);
    final int requestTokensLength = requestTokens.size();
    final String[] firstLine = requestTokens.get(0).split(" ");
    final String httpMethod = firstLine[0];
    final String pathAndQueryString = firstLine[1];
    final String protocolAndVersion = firstLine[2];

    final String body = outBody.get();

    final String[] pathAndQueryStringParts = pathAndQueryString.split("\\?");
    final QueryStringParameters queryStringParameters = new QueryStringParameters();

    if (pathAndQueryStringParts.length > 1) {
      final String queryString = pathAndQueryStringParts[1];
      final String[] queryStringParams = queryString.split("&");
      final int numberOfParams = queryStringParams.length;

      for (int queryStringParameterIndex = 0; queryStringParameterIndex < numberOfParams; queryStringParameterIndex++) {
        final String parameter = queryStringParams[queryStringParameterIndex];
        final String[] parameterParts = parameter.split("=");

        queryStringParameters.putValue(parameterParts[0], parameterParts[1]);
      }
    }

    final int numberOfRequestLines = requestTokens.size();
    final HttpHeaders headers = new HttpHeaders();

    for (int lineIndex = 1; lineIndex < (numberOfRequestLines - 1); lineIndex++) {
      final String nextLine = requestTokens.get(lineIndex);

      if (nextLine.equals("")) break;

      final String[] nextLineParts = nextLine.split(":\\s+");

      if (nextLineParts.length == 1) {
        headers.putValue(nextLineParts[0], null);
      }
      else if (nextLineParts.length == 2) {
        headers.putValue(nextLineParts[0], nextLineParts[1]);
      }
      else {
        System.out.format("Cannot understand line %s%n", nextLine);
      }
    }

    // Get content by length from end of request.

    final SimpleHttpRequest simpleHttpRequest = new SimpleHttpRequest(
      httpMethod,
      pathAndQueryStringParts[0],
      protocolAndVersion,
      queryStringParameters,
      headers,
      body
    );

    return simpleHttpRequest;
  }

  static List<String> tokenizeRequest(final String request, final AtomicReference<String> outBody) {
    final List<String> tokens = new ArrayList<>();
    final char[] reqChars = request.toCharArray();
    final int reqCharsLength = reqChars.length;

    final StringBuilder tokenBuilder = new StringBuilder();

    for (int index = 0; index < reqCharsLength; index++) {
      final char next = reqChars[index];

      if (next == '\r') {
        final String strTok = tokenBuilder.toString();

        tokens.add(strTok);
        tokenBuilder.setLength(0);

        if (reqChars[index + 1] == '\n') {
          if (reqChars[index + 2] == '\r' && reqChars[index + 3] == '\n') {
            index += 4;

            final String body = request.substring(index);

            outBody.set(body);

            index += body.length();
          }
          else {
            index++;
          }
        }
      }
      else {
        tokenBuilder.append(next);
      }
    }

    return Collections.unmodifiableList(tokens);
  }

}
