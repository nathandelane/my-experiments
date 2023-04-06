package com.github.nathandelane.experiments.http.library;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.github.nathandelane.experiments.http.library.HttpConstants.HEADER_CONTENT_TYPE;
import static com.github.nathandelane.experiments.http.library.HttpConstants.HTTP_NEW_LINE;

public class SimpleHttpResponse {

  private final int httpStatus;

  private final String message;

  private final String contentType;

  private final HttpHeaders headers;

  private final String body;

  public SimpleHttpResponse(
    final HttpStatusAndMessage httpStatusAndMessage,
    final String contentType,
    final String body
  ) {
    this(httpStatusAndMessage.status, httpStatusAndMessage.message, contentType, new HttpHeaders(), body);
  }

  public SimpleHttpResponse(
    final HttpStatusAndMessage httpStatusAndMessage,
    final String contentType,
    final HttpHeaders headers,
    final String body
  ) {
    this(httpStatusAndMessage.status, httpStatusAndMessage.message, contentType, headers, body);
  }

  public SimpleHttpResponse(
    final int httpStatus,
    final String message,
    final String contentType,
    final String body
  ) {
    this(httpStatus, message, contentType, new HttpHeaders(), body);
  }

  public SimpleHttpResponse(
    final int httpStatus,
    final String message,
    final String contentType,
    final HttpHeaders headers,
    final String body
  ) {
    this.httpStatus = httpStatus;
    this.message = message;
    this.contentType = contentType;
    this.headers = headers;
    this.body = body;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public String getMessage() {
    return message;
  }

  public String getContentType() {
    return contentType;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }

  public Set<String> getHeaderValues(final String headerKey) {
    Set<String> headerValues = headers.getValues(headerKey);

    if (headerValues == null) headerValues = new HashSet<>();

    return headerValues;
  }

  public String getBody() {
    return body;
  }

  @Override
  public String toString() {
    return "SimpleHttpResponse{" +
      "httpStatus=" + httpStatus +
      ", message='" + message + '\'' +
      ", contentType='" + contentType + '\'' +
      ", headers=" + headers +
      ", body='" + body + '\'' +
      '}';
  }

  public byte[] toBytes() {
    final StringBuilder sb = new StringBuilder();

    sb
      .append(String.format("HTTP/1.1 %d %s%s", httpStatus, message, HTTP_NEW_LINE))
      .append(String.format("%s: %s%s", HEADER_CONTENT_TYPE, contentType, HTTP_NEW_LINE));

    for (final Map.Entry<String, Set<String>> entry : headers.entrySet()) {
      if (entry.getKey().equals(HEADER_CONTENT_TYPE)) continue;

      final StringBuilder headerValues = new StringBuilder();

      boolean isFirst = true;

      for (final String nextValue : entry.getValue()) {
        if (!isFirst) headerValues.append(",");

        headerValues.append(nextValue);

        isFirst = false;
      }

      sb.append(String.format("%s: %s%s", entry.getKey(), entry.getValue(), HTTP_NEW_LINE));
    }

    sb.append(HTTP_NEW_LINE);
    sb.append(body);
    sb.append(HTTP_NEW_LINE).append(HTTP_NEW_LINE);

    return sb.toString().getBytes();
  }

}
