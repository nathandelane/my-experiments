package com.github.nathandelane.experiments.rotatematrix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdjacencyList<T> {

  private final List<AdjacencyNode<T>> roots;

  private final Comparator<T> valueComparator;

  public AdjacencyList(final Comparator<T> valueComparator) {
    this.roots = new ArrayList<>();
    this.valueComparator = valueComparator;
  }

  public void addNode(final T rootValue, final T adacentValue) {
    final int indexOfRoot = roots.indexOf(rootValue);

    if (indexOfRoot == -1) {

    }
  }

  public AdjacencyNode<T> find(final T rootValue) {
    return null;
  }

  public static class AdjacencyNode<T> {

    public final T value;

    private AdjacencyNode<T> parent;

    private AdjacencyNode<T> child;

    public AdjacencyNode(final T value) {
      this.value = value;
    }

    public AdjacencyNode<T> getParent() {
      return parent;
    }

    public void setParent(AdjacencyNode<T> parent) {
      this.parent = parent;
    }

    public AdjacencyNode<T> getChild() {
      return child;
    }

    public void setChild(AdjacencyNode<T> child) {
      this.child = child;
    }

  }

}
