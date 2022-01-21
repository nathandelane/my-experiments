package com.github.nathandelane.experiments.linkedlists;

public class DoublyLinkedList<T> {

  private DoubleListNode<T> first;

  private DoubleListNode<T> current;

  public DoublyLinkedList() {
    first = null;
  }

  public DoublyLinkedList(final T ... values) {
    if (values != null) {
      DoubleListNode<T> l = null;

      for (final T nextValue : values) {
        append(nextValue);
      }
    }
  }

  public void append(final T value) {
    final DoubleListNode<T> n = new DoubleListNode<>(value);

    if (first == null) {
      first = n;
    }
    else {
      DoubleListNode<T> l = first;

      while (l.next != null) {
        l = l.next;
      };

      l.next = n;
      n.previous = l;
    }
  }

  public void insert(final int index, final T value) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index is not in range of existing list.");
    }

    final DoubleListNode<T> n = new DoubleListNode<>(value);

    DoubleListNode<T> l = null;

    for (int i = 0; i < index; i++) {
      if (l == null) {
        l = first;
      }
      else {
        l = l.next;
      }
    }

    n.previous = l;
    n.next = l.next;
    l.next = n;
  }

  public void remove(final int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index is not in range of existing list.");
    }

    DoubleListNode<T> l = null;

    int i = 0;
    do {
      if (l == null) {
        l = first;
      }
      else {
        l = l.next;
      }

      i++;
    } while (i <= index);

    if (l.previous == null && l.next == null) {
      first = null;
    }
    if (l.next != null) {
      if (l.previous == null) {
        first = l.next;
      }
      else {
        l.previous.next = l.next;
      }
    }
  }

  public int size() {
    int countedNodes = 0;

    if (first != null) {
      DoubleListNode<T> n = first;

      do {
        countedNodes++;
        n = n.next;
      } while (n != null);
    }

    return countedNodes;
  }

  public T get(final int index) {
    if (index < 0 || index > (size() - 1)) {
      throw new IndexOutOfBoundsException("Index is larger than size - 1.");
    }

    DoubleListNode<T> n = first;

    for (int i = 0; i < index; i++) {
      n = n.next;
    }

    return n.value;
  }

  public boolean hasNext() {
    if (current == null) {
      current = first;
    }

    return current != null && current.next != null;
  }

  public T next() {
    T val = null;

    if (current == null) {
      current = first;
      val = current.value;
    }
    else if (current.next != null) {
      current = current.next;
      val = current.value;
    }

    return val;
  }

  public boolean hasPrevious() {
    if (current == null) {
      current = first;
    }

    return current != null && current.previous != null;
  }

  public T previous() {
    T val = null;

    if (current == null) {
      current = first;
      val = current.value;
    }
    else if (current.previous != null) {
      current = current.previous;
      val = current.value;
    }

    return val;
  }

  public void resetCurrent() {
    current = null;
  }

  @Override
  public String toString() {
    DoubleListNode<T> l = first;

    final StringBuilder sb = new StringBuilder(String.format("{ %s", l.value));

    while (l.next != null) {
      sb.append(", ");

      l = l.next;
      final T val = l.value;

      sb.append(String.format("%s", val));
    }

    sb.append(" }");

    return sb.toString();
  }

}
