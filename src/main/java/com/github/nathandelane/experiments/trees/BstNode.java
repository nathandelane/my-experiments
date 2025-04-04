package com.github.nathandelane.experiments.trees;

import java.util.Objects;

public class BstNode<T extends Comparable<T>> {

  public final T value;

  private int heightLeft;

  private int heightRight;

  private BstNode<T> left;

  private BstNode<T> right;

  public BstNode(final T value) {
    this.value = value;
    this.heightLeft = 0;
    this.heightRight = 0;
    this.left = null;
    this.right = null;
  }

  public int heightLeft() {
    return heightLeft;
  }

  public void heightLeftIs(int heightLeft) {
    this.heightLeft = heightLeft;
  }

  public int heightRight() {
    return heightRight;
  }

  public void heightRightIs(int heightRight) {
    this.heightRight = heightRight;
  }

  public BstNode<T> left() {
    return left;
  }

  public void leftIs(BstNode<T> left) {
    this.left = left;
  }

  public BstNode<T> right() {
    return right;
  }

  public void rightIs(BstNode<T> right) {
    this.right = right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BstNode<?> bstNode = (BstNode<?>) o;
    return Objects.equals(value, bstNode.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "BstNode{" +
      "value=" + value +
      ", left=" + left +
      ", right=" + right +
      '}';
  }

}
