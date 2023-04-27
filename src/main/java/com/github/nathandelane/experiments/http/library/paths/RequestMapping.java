package com.github.nathandelane.experiments.http.library.paths;

import com.github.nathandelane.experiments.http.library.RequestHandler;
import com.github.nathandelane.experiments.http.library.RequestHandlerContainer;

import java.util.*;

import static com.github.nathandelane.experiments.http.library.model.ContentTypes.WILDCARD;

public final class RequestMapping {

  private static final List<String> defaultAccepts;
  static {
    defaultAccepts = new ArrayList<>();
    defaultAccepts.add(WILDCARD);
  }

  private static final List<PathNode> roots = new ArrayList<>();

  private RequestMapping() { }

  public static void addRequestMapping(final String httpMethod, final String path, final RequestHandler requestHandler) {
    addRequestMapping(httpMethod, path, defaultAccepts, requestHandler);
  }

  public static void addRequestMapping(final String httpMethod, final String path, final List<String> accepts, final RequestHandler requestHandler) {
    if (path.isBlank()) throw new IllegalStateException("Path cannot be null.");
    if (requestHandler == null) throw new IllegalStateException("RequestHandler cannot be null.");

    final String[] pathParts = path.split("/");
    final int numberOfPathElements = pathParts.length;

    int pathIndex = 0;
    PathNode node = null;

    while (pathIndex < numberOfPathElements) {
      final String pathPart = pathParts[pathIndex];

      if (pathPart.isBlank()) {
        pathIndex++;
        continue;
      }

      PathNode newNode = null;

      if (pathIndex == (numberOfPathElements - 1)) {
        if (pathPart.startsWith(":")) {
          newNode = new PathNode(pathPart.substring(1), PathNodeType.VARIABLE_AND_HANDLER, requestHandler);
        }
        else {
          newNode = new PathNode(pathPart, PathNodeType.HANDLER, requestHandler);
        }
      }
      else if (pathPart.startsWith(":")) {
        final String newPathPart = pathPart.substring(1);

        newNode = new PathNode(newPathPart, PathNodeType.VARIABLE, null);
      }
      else {
        newNode = new PathNode(pathPart, PathNodeType.PATH, null);
      }

      if (node == null) {
        newNode = new PathNode(httpMethod, newNode.value, newNode.type, newNode.requestHandler);

        roots.add(newNode);
      }
      else {
        node.addChild(newNode);
      }

      node = newNode;
      pathIndex++;
    }
  }

  public static RequestHandlerContainer resolvePath(final String httpMethod, final String path) {
    if (path.isBlank()) return null;

    RequestHandler requestHandler = null;

    final Map<String, String> variableMapping = new HashMap<>();
    final String[] pathParts = path.split("/");
    final int numberOfPathParts = pathParts.length;
    final PathNodeType searchPathNodeType = ((numberOfPathParts == 2) ? PathNodeType.HANDLER : PathNodeType.PATH);
    final String searchPathPart = (pathParts[0].isBlank() ? pathParts[1] : pathParts[0]);
    final PathNode expectedRootNode = new PathNode(httpMethod, searchPathPart, searchPathNodeType, null);
    final int indexOfRoot = roots.indexOf(expectedRootNode);

    if (indexOfRoot == -1) return null;

    PathNode nextNode = roots.get(indexOfRoot);
    int pathPartIndex = 2;

    if (expectedRootNode.type == PathNodeType.HANDLER) {
      requestHandler = nextNode.requestHandler;
    } else {
      while (pathPartIndex < numberOfPathParts) {
        final String pathPart = pathParts[pathPartIndex];

        nextNode = nextNode.findByPathNodeValue(pathPart);

        if (pathPartIndex == (numberOfPathParts - 1) && nextNode.type == PathNodeType.HANDLER) {
          requestHandler = nextNode.requestHandler;
        } else if (nextNode.type == PathNodeType.VARIABLE) {
          variableMapping.put(nextNode.value, pathPart);
        } else if (nextNode.type == PathNodeType.VARIABLE_AND_HANDLER) {
          variableMapping.put(nextNode.value, pathPart);
          requestHandler = nextNode.requestHandler;
        }

        pathPartIndex++;
      }
    }

    return new RequestHandlerContainer(requestHandler, variableMapping);
  }



}
