package com.github.nathandelane.experiments.linkedlists;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class DoubleListNode<T> {

  public static final AtomicInteger ids = new AtomicInteger(0);

  final int id;

  final T value;

  DoubleListNode<T> next;

  DoubleListNode<T> previous;

  public DoubleListNode(final T value) {
    this.id = ids.getAndIncrement();
    this.value = value;

    next = null;
    previous = null;
  }

  public void setNext(final DoubleListNode<T> next) {
    this.next = next;
  }

  public void setPrevious(final DoubleListNode<T> previous) {
    this.previous = previous;
  }

  public T getValue() {
    return value;
  }

  public DoubleListNode<T> getNext() {
    return next;
  }

  public DoubleListNode<T> getPrevious() {
    return previous;
  }

  @Override
  public String toString() {
    return String.format("%s", value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoubleListNode<?> that = (DoubleListNode<?>) o;
    return id == that.id && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, value);
  }
}
