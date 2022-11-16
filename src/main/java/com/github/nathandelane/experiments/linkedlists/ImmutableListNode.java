package com.github.nathandelane.experiments.linkedlists;

public class ImmutableListNode<T> {

  final T value;

  final ImmutableListNode<T> next;

  public ImmutableListNode(final T value) {
    this.value = value;
    this.next = null;
  }

  private ImmutableListNode(final T value, final ImmutableListNode<T> next) {
    this.value = value;
    this.next = next;
  }

  public ImmutableListNode<T> setChild(final ImmutableListNode<T> child) {
    return new ImmutableListNode<>(child.value, child.next);
  }

  public T getValue() {
    return value;
  }

  public ImmutableListNode<T> getNext() {
    return next;
  }

  @Override
  public boolean equals(final Object other) {
    return (
      other != null &&
        (other instanceof ListNode<?>) &&
        this.value != null &&
        ((ImmutableListNode<?>) other).value != null &&
        this.value.equals(((ListNode<?>) other).value)
    );
  }

}
