package com.github.nathandelane.experiments.http;

import com.github.nathandelane.experiments.http.library.*;
import com.github.nathandelane.experiments.http.library.model.HttpHeaders;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.github.nathandelane.experiments.http.library.model.CommonHttpResponses.HTTP_200_OK;
import static com.github.nathandelane.experiments.http.library.model.ContentTypes.*;
import static com.github.nathandelane.experiments.http.library.model.HttpConstants.HEADER_CONTENT_LENGTH;
import static com.github.nathandelane.experiments.http.library.model.HttpConstants.HEADER_CONTENT_TYPE;

public class Application {

  private final Gson gson;

  private final SimpleHttpWebApplication simpleHttpWebApplication;

  private Application() {
    gson = new Gson();

    HttpHandler.mapRequest("GET", "this/is/a/test/:number", (simpleHttpRequest, variableMapping) -> {
      final String responseBody = String.format("<b>It works!</b><span>%s</span>", variableMapping.get("number"));
      final HttpHeaders headers = new HttpHeaders();
      final Integer length = responseBody.getBytes().length;
      headers.putValue(HEADER_CONTENT_LENGTH, length);

      return new SimpleHttpResponse(HTTP_200_OK, TEXT_HTML, headers, responseBody);
    });
    HttpHandler.mapRequest("GET", "health", (simpleHttpRequest, variableMapping) -> {
      final String responseBody = String.format("OK", variableMapping.get("number"));
      final HttpHeaders headers = new HttpHeaders();
      final Integer length = responseBody.getBytes().length;
      headers.putValue(HEADER_CONTENT_LENGTH, length);
      return new SimpleHttpResponse(HTTP_200_OK, TEXT_PLAIN, headers, responseBody);
    });
    HttpHandler.mapRequest("HEAD", "health", (simpleHttpRequest, variableMapping) -> {
      return new SimpleHttpResponse(HTTP_200_OK, TEXT_PLAIN, new HttpHeaders(), "");
    });
    HttpHandler.mapRequest("POST", "json/getName", ((simpleHttpRequest, variableMapping) -> {
      if (simpleHttpRequest.getBody().isBlank()) throw new IllegalStateException("POST body cannot be null.");

      final JsonObject jsonObject = new JsonParser().parse(simpleHttpRequest.getBody()).getAsJsonObject();
      final JsonElement nameElement = jsonObject.get("name");

      if (nameElement == null) throw new IllegalStateException("Name not supplied in body.");

      final String name = nameElement.getAsString();
      final String responseBody = String.format("{ \"name\": \"%s\" }", name);
      final HttpHeaders headers = new HttpHeaders();
      headers.putValue(HEADER_CONTENT_TYPE, APPLICATION_JSON);
      headers.putValue(HEADER_CONTENT_LENGTH, responseBody.length());

      return new SimpleHttpResponse(HTTP_200_OK, TEXT_PLAIN, headers, responseBody);
    }));

    simpleHttpWebApplication = new SimpleHttpWebApplication(20930);
  }

  private void startApp() {
    simpleHttpWebApplication.listen();
  }

  public static void main(final String[] args) {
    final Application application = new Application();
    application.startApp();
  }

}
