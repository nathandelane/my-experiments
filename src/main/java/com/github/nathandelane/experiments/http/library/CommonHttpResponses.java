package com.github.nathandelane.experiments.http.library;

public final class CommonHttpResponses {

  private CommonHttpResponses() { }

  public static final HttpStatusAndMessage HTTP_200_OK = new HttpStatusAndMessage(200, "OK");

  public static final HttpStatusAndMessage HTTP_404_NOT_FOUND = new HttpStatusAndMessage(404, "NOT FOUND");

}
