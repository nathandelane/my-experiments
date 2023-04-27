package com.github.nathandelane.experiments.http.library;

import com.github.nathandelane.experiments.http.library.model.SimpleHttpRequest;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpResponse;

import java.util.HashMap;
import java.util.Map;

public class RequestHandlerContainer {

  final RequestHandler requestHandler;

  private final Map<String, String> variableMapping;

  public RequestHandlerContainer(final RequestHandler requestHandler, final Map<String, String> variableMapping) {
    this.requestHandler = requestHandler;
    this.variableMapping = variableMapping == null ? new HashMap<>() : variableMapping;
  }

  public SimpleHttpResponse apply(final SimpleHttpRequest simpleHttpRequest) {
    return requestHandler.handle(simpleHttpRequest, variableMapping);
  }

}
