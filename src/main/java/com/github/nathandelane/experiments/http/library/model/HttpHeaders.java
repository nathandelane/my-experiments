package com.github.nathandelane.experiments.http.library.model;

import java.util.*;

public class HttpHeaders {

  private final Map<String, Set<String>> headers;

  public HttpHeaders() {
    this.headers = new HashMap<>();
  }

  public void putValue(final String headerName, final Object value) {
    Set<String> values = headers.get(headerName);

    if (values == null) values = new HashSet<>();

    values.add(value.toString());

    headers.put(headerName, values);
  }

  public Set<String> getValues(final String headerName) {
    Set<String> values = headers.get(headerName);

    if (values == null) values = new HashSet<>();

    return Collections.unmodifiableSet(values);
  }

  public Set<Map.Entry<String, Set<String>>> entrySet() {
    return headers.entrySet();
  }

  @Override
  public String toString() {
    return "HttpHeaders{" +
      "headers=" + headers +
      '}';
  }
}
