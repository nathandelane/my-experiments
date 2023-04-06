package com.github.nathandelane.experiments.rawcollection;

import java.util.Objects;

public class ListNode<T> {

  private T value;

  private ListNode<T> child;

  public ListNode(final T value) {
    this.value = value;
    this.child = null;
  }

  public ListNode(final T value, final ListNode<T> child) {
    this.value = value;
    this.child = child;
  }

  public T getValue() {
    return value;
  }

  public void setValue(final T value) {
    this.value = value;
  }

  public ListNode<T> getChild() {
    return child;
  }

  public void setChild(final ListNode<T> child) {
    this.child = child;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ListNode<?> node = (ListNode<?>) o;
    return Objects.equals(value, node.value) && Objects.equals(child, node.child);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, child);
  }

  @Override
  public String toString() {
    return "Node{" +
      "value=" + value +
      ", child=" + child +
      '}';
  }

}
