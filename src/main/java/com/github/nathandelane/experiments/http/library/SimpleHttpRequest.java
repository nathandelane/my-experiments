package com.github.nathandelane.experiments.http.library;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.github.nathandelane.experiments.http.library.HttpConstants.*;

public class SimpleHttpRequest {

  private final String httpMethod;

  private final String path;

  private final String protocolAndVersion;

  private final String host;

  private final Map<String, String> queryStringParameters;

  private final HttpHeaders headers;

  private SimpleHttpRequest(
    final String httpMethod,
    final String path,
    final String protocolAndVersion,
    final String host,
    final Map<String, String> queryStringParameters,
    final HttpHeaders headers
  ) {
    this.httpMethod = httpMethod;
    this.path = path;
    this.protocolAndVersion = protocolAndVersion;
    this.host = host;
    this.queryStringParameters = Collections.unmodifiableMap(queryStringParameters);
    this.headers = headers;
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

  public String getHost() {
    return host;
  }

  public Map<String, String> getQueryStringParameters() {
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

  @Override
  public String toString() {
    return "SimpleHttpRequest{" +
      "httpMethod='" + httpMethod + '\'' +
      ", path='" + path + '\'' +
      ", protocolAndVersion='" + protocolAndVersion + '\'' +
      ", host='" + host + '\'' +
      ", queryStringParameters=" + queryStringParameters +
      ", headers=" + headers +
      '}';
  }

  public static SimpleHttpRequest parse(final String request) {
    final String[] requestLines = request.split(HTTP_NEW_LINE);
    final String[] firstLine = requestLines[0].split(" ");
    final String httpMethod = firstLine[0];
    final String pathAndQueryString = firstLine[1];
    final String protocolAndVersion = firstLine[2];
    final String host = requestLines[1].split(" ")[1];

    final String[] pathAndQueryStringParts = pathAndQueryString.split("\\?");
    final Map<String, String> queryStringParameters = new HashMap<>();

    if (pathAndQueryStringParts.length > 1) {
      final String queryString = pathAndQueryStringParts[1];
      final String[] queryStringParams = queryString.split("&");
      final int numberOfParams = queryStringParams.length;

      for (int queryStringParameterIndex = 0; queryStringParameterIndex < numberOfParams; queryStringParameterIndex++) {
        final String parameter = queryStringParams[queryStringParameterIndex];
        final String[] parameterParts = parameter.split("=");

        queryStringParameters.put(parameterParts[0], parameterParts[1]);
      }
    }

    final int numberOfRequestLines = requestLines.length;
    final HttpHeaders headers = new HttpHeaders();

    for (int lineIndex = 2; lineIndex < numberOfRequestLines; lineIndex++) {
      final String nextLine = requestLines[lineIndex];
      final String[] nextLineParts = nextLine.split(": ");

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

    final SimpleHttpRequest simpleHttpRequest = new SimpleHttpRequest(
      httpMethod,
      pathAndQueryStringParts[0],
      protocolAndVersion,
      host,
      queryStringParameters,
      headers
    );

    return simpleHttpRequest;
  }

}
