package com.github.nathandelane.experiments.http.library.model;

import java.util.*;

public class WebForm {

  private final Map<String, Set<String>> fields;

  public WebForm(final String body) {
    fields = new HashMap<>();

    if (!body.isBlank()) {
      final String[] formParts = body.split("&");

      for (final String nextPart : formParts) {
        final String[] pair = nextPart.split("=");

        Set<String> listOfValues = fields.get(pair[0]);

        if (listOfValues == null) listOfValues = new HashSet<>();

        if (pair.length == 2) {
          listOfValues.add(pair[1]);
        }
        else {
          listOfValues.add("true");
        }

        fields.put(pair[0], listOfValues);
      }
    }
  }

  public Set<String> getField(final String fieldName) {
    Set<String> values = fields.get(fieldName);

    if (values == null) values = new HashSet<>();

    return values;
  }

  @Override
  public String toString() {
    return super.toString();
  }

}
