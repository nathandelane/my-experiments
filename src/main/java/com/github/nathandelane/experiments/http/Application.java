package com.github.nathandelane.experiments.http;

import com.github.nathandelane.experiments.http.library.*;
import com.github.nathandelane.experiments.http.library.model.HttpHeaders;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpRequest;
import com.github.nathandelane.experiments.http.library.model.SimpleHttpResponse;
import com.github.nathandelane.experiments.http.model.JsonUtil;
import com.github.nathandelane.experiments.http.model.NameRequest;

import java.io.IOException;
import java.util.Map;

import static com.github.nathandelane.experiments.http.library.model.CommonHttpResponses.HTTP_200_OK;
import static com.github.nathandelane.experiments.http.library.model.CommonHttpResponses.HTTP_404_NOT_FOUND;
import static com.github.nathandelane.experiments.http.library.model.ContentTypes.*;
import static com.github.nathandelane.experiments.http.library.model.HttpConstants.HEADER_CONTENT_LENGTH;
import static com.github.nathandelane.experiments.http.library.model.HttpConstants.HEADER_CONTENT_TYPE;

public class Application {

  private final LeafWebApp leafWebApp;

  private Application() {
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

      final NameRequest nameRequest = JsonUtil.parseNameRequest(simpleHttpRequest.getBody());

      if (nameRequest == null || nameRequest.name.isBlank()) throw new IllegalStateException("Name not supplied in body.");

      final String name = nameRequest.name;
      final String responseBody = String.format("{ \"name\": \"%s\" }", name);
      final HttpHeaders headers = new HttpHeaders();
      headers.putValue(HEADER_CONTENT_TYPE, APPLICATION_JSON);
      headers.putValue(HEADER_CONTENT_LENGTH, responseBody.length());

      return new SimpleHttpResponse(HTTP_200_OK, TEXT_PLAIN, headers, responseBody);
    }));

    final RequestHandler htmlHandler = (simpleHttpRequest, variableMapping) -> {
      final String responseBody = ResourcesUtil.loadResource("html/index.html");
      final HttpHeaders headers = new HttpHeaders();
      headers.putValue(HEADER_CONTENT_TYPE, TEXT_HTML);
      headers.putValue(HEADER_CONTENT_LENGTH, responseBody.length());

      return new SimpleHttpResponse(HTTP_200_OK, TEXT_HTML, headers, responseBody);
    };
    HttpHandler.mapRequest("GET", ".", ((simpleHttpRequest, variableMapping) -> {
      return new SimpleHttpResponse(HTTP_404_NOT_FOUND, "text/plain", "Resource not found");
    }));
    HttpHandler.mapRequest("GET", "..", ((simpleHttpRequest, variableMapping) -> {
      return new SimpleHttpResponse(HTTP_404_NOT_FOUND, "text/plain", "Resource not found");
    }));
    HttpHandler.mapRequest("GET", "index", htmlHandler);
    HttpHandler.mapRequest("GET", "css/:fileName",  ((simpleHttpRequest, variableMapping) -> {
      final String fileName = variableMapping.get("fileName");

      if (fileName.startsWith(".") || fileName.startsWith("..")) {
        return new SimpleHttpResponse(HTTP_404_NOT_FOUND, "text/plain", "Resource not found");
      }

      final String resourcePath = String.format("css/%s", fileName);
      final String cssBody = ResourcesUtil.loadResource(resourcePath);
      final HttpHeaders headers = new HttpHeaders();
      headers.putValue(HEADER_CONTENT_TYPE, TEXT_CSS);
      headers.putValue(HEADER_CONTENT_LENGTH, cssBody.length());

      return new SimpleHttpResponse(HTTP_200_OK, TEXT_CSS, headers, cssBody);
    }));

    leafWebApp = new LeafWebApp(20930, 30_000);
  }

  private void startApp() {
    leafWebApp.start();
  }

  public static void main(final String[] args) {
    final Application application = new Application();
    application.startApp();
  }

}
