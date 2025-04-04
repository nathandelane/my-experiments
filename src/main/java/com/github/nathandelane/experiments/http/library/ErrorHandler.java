package com.github.nathandelane.experiments.http.library;

import com.github.nathandelane.experiments.http.library.model.HttpHeaders;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpRequest;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpResponse;

import java.util.Map;

import static com.github.nathandelane.experiments.http.library.model.CommonHttpResponses.HTTP_404_NOT_FOUND;
import static com.github.nathandelane.experiments.http.library.model.ContentTypes.TEXT_PLAIN;
import static com.github.nathandelane.experiments.http.library.model.HttpConstants.HEADER_CONTENT_LENGTH;
import static com.github.nathandelane.experiments.http.library.model.HttpConstants.HEADER_CONTENT_TYPE;

public final class ErrorHandler {

  private ErrorHandler() { }

  public static RequestHandlerContainer getNotFoundHandlerForRequest(final SimpleHttpRequest simpleHttpRequest) {
    final RequestHandler requestHandler = new RequestHandler() {
      @Override
      public SimpleHttpResponse handle(final SimpleHttpRequest simpleHttpRequest, final Map<String, String> variableMapping) {
        final String responseBody = String.format("No handler found for method: %s and path: %s%n", simpleHttpRequest.getHttpMethod(), simpleHttpRequest.getPath());
        final Integer length = responseBody.getBytes().length;
        final HttpHeaders headers = new HttpHeaders();
        headers.putValue(HEADER_CONTENT_LENGTH, length.toString());
        headers.putValue(HEADER_CONTENT_TYPE, TEXT_PLAIN);

        return new SimpleHttpResponse(
          HTTP_404_NOT_FOUND,
          TEXT_PLAIN,
          headers,
          responseBody
        );
      }
    };

    return new RequestHandlerContainer(requestHandler, null);
  }

}
