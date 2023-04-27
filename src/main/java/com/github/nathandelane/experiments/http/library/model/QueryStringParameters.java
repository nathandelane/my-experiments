package com.github.nathandelane.experiments.http.library.model;

import java.util.*;

public class QueryStringParameters {

  private final Map<String, Set<String>> parameters;

  public QueryStringParameters() {
    this.parameters = new HashMap<>();
  }

  public void putValue(final String parameterName, final Object value) {
    Set<String> values = parameters.get(parameterName);

    if (values == null) values = new HashSet<>();

    values.add(value.toString());

    parameters.put(parameterName, values);
  }

  public Set<String> getValues(final String parameterName) {
    Set<String> values = parameters.get(parameterName);

    if (values == null) values = new HashSet<>();

    return Collections.unmodifiableSet(values);
  }

  public Set<Map.Entry<String, Set<String>>> entrySet() {
    return parameters.entrySet();
  }

  @Override
  public String toString() {
    return "QueryStringParameters{" +
      "parameters=" + parameters +
      '}';
  }
}
