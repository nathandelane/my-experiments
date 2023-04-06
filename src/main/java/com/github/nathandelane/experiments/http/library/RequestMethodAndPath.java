package com.github.nathandelane.experiments.http.library;

import java.util.Objects;

public final class RequestMethodAndPath {

  public final String method;

  public final String path;

  public RequestMethodAndPath(String method, String path) {
    this.method = method;
    this.path = path;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RequestMethodAndPath that = (RequestMethodAndPath) o;
    return Objects.equals(method, that.method) && Objects.equals(path, that.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, path);
  }

  @Override
  public String toString() {
    return "RequestMethodAndPath{" +
      "method='" + method + '\'' +
      ", path='" + path + '\'' +
      '}';
  }

}
