package com.github.nathandelane.experiments.http.library;

public interface RequestHandler {

  SimpleHttpResponse handle(SimpleHttpRequest simpleHttpRequest);

}
