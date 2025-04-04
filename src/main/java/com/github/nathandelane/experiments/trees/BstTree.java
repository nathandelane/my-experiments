package com.github.nathandelane.experiments.trees;

public class BstTree<T extends Comparable<T>> {

  private BstNode<T> root;

  private int size;

  public BstTree() {
    this.root = null;
    this.size = 0;
  }

  public BstTree(final T rootValue) {
    this.root = new BstNode<>(rootValue);
  }

  public BstTree(final BstTree<T> other) {
    this.root = other.root;
  }

  public int size() {
    return size;
  }

  public BstTree<T> add(final T value) {
    boolean notFound = true;
    BstNode<T> currentNode = root;

    while (notFound) {
      if (currentNode == null) {
        currentNode = new BstNode<>(value);
        notFound = false;
        size += 1;
      } else if (value.compareTo(currentNode.value) < 0) {
        currentNode = currentNode.left();
      } else if (value.compareTo(currentNode.value) > 0) {
        currentNode = currentNode.right();
      } else if (value.compareTo(currentNode.value) == 0) {
        notFound = false;
      }
    }

    return this;
  }

}
