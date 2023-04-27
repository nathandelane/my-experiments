package com.github.nathandelane.experiments.http.library.paths;

import com.github.nathandelane.experiments.http.library.model.HttpMethod;
import com.github.nathandelane.experiments.http.library.RequestHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.github.nathandelane.experiments.http.library.model.ContentTypes.WILDCARD;

public class PathNode implements Comparable<PathNode> {

  public final HttpMethod method;

  public final List<String> accepts;

  public final String value;

  public final PathNodeType type;

  public final RequestHandler requestHandler;

  private final List<PathNode> children;

  public PathNode(final String httpMethod, final String value, final List<String> accepts, final PathNodeType type, final RequestHandler requestHandler) {
    this.method = HttpMethod.valueOf(httpMethod);
    this.value = value;
    this.type = type;
    this.requestHandler = requestHandler;
    this.children = new ArrayList<>();
    this.accepts = new ArrayList<>(accepts);
  }

  public PathNode(final String httpMethod, final String value, final PathNodeType type, final RequestHandler requestHandler) {
    this.method = HttpMethod.valueOf(httpMethod);
    this.value = value;
    this.type = type;
    this.requestHandler = requestHandler;
    this.children = new ArrayList<>();
    this.accepts = new ArrayList<>();
    this.accepts.add(WILDCARD);
  }

  public PathNode(final String value, final PathNodeType type, final RequestHandler requestHandler) {
    this.method = HttpMethod.NULL;
    this.value = value;
    this.type = type;
    this.requestHandler = requestHandler;
    this.children = new ArrayList<>();
    this.accepts = new ArrayList<>();
  }

  public void addChild(final PathNode child) {
    if (children.indexOf(child) > -1) return;

    children.add(child);

    Collections.sort(children);
  }

  public void removeChild(final PathNode child) {
    children.remove(child);
  }

  public PathNode findByPathNodeValue(final String value) {
    PathNode pathNode = null;

    final int indexOfPathNode = children.indexOf(new PathNode(value, PathNodeType.UNKNOWN, null));

    if (indexOfPathNode > -1) {
      pathNode = children.get(indexOfPathNode);
    }
    else if (children.size() > 0) {
      pathNode = children.get(0);
    }

    return pathNode;
  }

  @Override
  public int compareTo(final PathNode other) {
    return this.value.compareTo(other.value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PathNode pathNode = (PathNode) o;
    return method == pathNode.method && Objects.equals(value, pathNode.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, value);
  }

  @Override
  public String toString() {
    return "PathNode{" +
      "method=" + method +
      ", value='" + value + '\'' +
      ", type=" + type +
      '}';
  }

}
