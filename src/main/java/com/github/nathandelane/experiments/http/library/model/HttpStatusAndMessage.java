package com.github.nathandelane.experiments.http.library.model;

public class HttpStatusAndMessage {

  public final int status;

  public final String message;

  public HttpStatusAndMessage(int status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public String toString() {
    return "HttpStatusAndMessage{" +
      "status=" + status +
      ", message='" + message + '\'' +
      '}';
  }

}
