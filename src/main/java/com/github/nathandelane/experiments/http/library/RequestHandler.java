package com.github.nathandelane.experiments.http.library;

import com.github.nathandelane.experiments.http.library.model.SimpleHttpRequest;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpResponse;

import java.util.Map;

public interface RequestHandler {

  SimpleHttpResponse handle(SimpleHttpRequest simpleHttpRequest, Map<String, String> variableMapping);

}
