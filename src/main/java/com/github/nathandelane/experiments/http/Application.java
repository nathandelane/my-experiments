package com.github.nathandelane.experiments.http;

import com.github.nathandelane.experiments.http.library.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.github.nathandelane.experiments.http.library.CommonHttpResponses.HTTP_200_OK;
import static com.github.nathandelane.experiments.http.library.ContentTypes.TEXT_HTML;
import static com.github.nathandelane.experiments.http.library.HttpConstants.HEADER_CONTENT_LENGTH;

public class Application {

  private final SimpleHttpWebApplication simpleHttpWebApplication;

  private Application() {
    final RequestHandler testHandler = simpleHttpRequest -> {
      final String responseBody = "<b>It works!</b>";
      final HttpHeaders headers = new HttpHeaders();
      headers.putValue(HEADER_CONTENT_LENGTH, Integer.valueOf(responseBody.getBytes().length));

      return new SimpleHttpResponse(HTTP_200_OK, TEXT_HTML, headers, responseBody);
    };

    HttpHandler.mapRequest("GET", "/this/is/a/test/12345", testHandler);

    simpleHttpWebApplication = new SimpleHttpWebApplication(20930);;
  }

  private void startApp() {
    simpleHttpWebApplication.listen();
  }

  public static void main(final String[] args) {
    final Application application = new Application();
    application.startApp();
  }

}
