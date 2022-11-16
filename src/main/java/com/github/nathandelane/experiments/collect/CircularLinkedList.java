package com.github.nathandelane.experiments.collect;

import java.util.Iterator;
import java.util.Objects;

/**
 * A circular linked list is a linked list that has a variable number of elements, and the last added element links
 * back to the head of the linked list for its child.
 * @param <T> generic type of values
 */
public class CircularLinkedList<T> {

  private int size;

  private Element<T> head;

  public CircularLinkedList() {
    size = 0;
    head = null;
  }

  public CircularLinkedList(final Iterable<T> iterable) {
    if (iterable != null) {
      final Iterator<T> iter = iterable.iterator();

      while (iter.hasNext()) {
        add(iter.next());
      }
    }
  }

  /**
   * Current size of circular linked list.
   * @return
   */
  public int size() {
    return size;
  }

  /**
   * Add value to circular linked list. {@code null} is allowed. Multiple of the same value may be added.
   * @param value value to add
   */
  public void add(final T value) {
    final Element<T> e = new Element<>(value);

    if (size == 0) {
      e.setChild(e);
      head = e;
    }
    else {
      final Element<T> last = getElement(size - 1);

      e.setChild(head);
      last.setChild(e);
    }

    size += 1;
  }

  /**
   * Get the value at index.
   * @param index
   * @return
   */
  public T get(final int index) {
    final Element<T> e = getElement(index);

    return e.getValue();
  }

  /**
   * Index of first instance of value in circular linked list
   * @param value value to search for
   * @return index of value or -1 if not found
   */
  public int indexOf(final T value) {
    final int index = getElementIndexByValue(value);

    return index;
  }

  /**
   * Whether or not this circular linked list contains at least one element with the provided
   * value.
   * @param value value to search for
   * @return value was found
   */
  public boolean contains(final T value) {
    final int index = getElementIndexByValue(value);

    return index > -1;
  }

  /**
   * Remove the linked list element at the given index.
   * @param index index of element to remove
   * @return element removed
   */
  public T removeAt(final int index) throws IndexOutOfBoundsException {
    if (index >= size) {
      throw new IndexOutOfBoundsException(String.format("Index %d is out of range.", index));
    }

    final int parentIndex;
    if (index == 0) {
      parentIndex = (size - 1);
    }
    else {
      parentIndex = (index - 1);
    }

    final Element<T> parent = getElement(parentIndex);
    final Element<T> actualElement = parent.getChild();

    size -= 1;

    if (size == 0) {
      head = null;
    }
    else {
      final Element<T> newChild = actualElement.getChild();

      parent.setChild(newChild);

      if (actualElement == head) {
        head = newChild;
      }
    }

    return actualElement.getValue();
  }

  /**
   * Remove the first element with the value {@code value} from the linked list.
   * @param value value of element to remove
   * @return element removed
   */
  public T remove(final T value) {
    final int index = getElementIndexByValue(value);

    return removeAt(index);
  }

  /**
   * Get a circular iterator that will loop through all elements indefinitely.
   * @return CircularIterator instance
   */
  public CircularIterator<T> iterator() {
    return new CircularIterator<>(this);
  }

  private Element<T> getElement(final int index) throws IndexOutOfBoundsException {
    if (index >= size) {
      throw new IndexOutOfBoundsException(String.format("Index %d is not defined.", index));
    }

    int internalIndex = 0;
    Element<T> e = head;

    while (internalIndex < index) {
      if (e.getChild() != null) {
        final Element ec = e.getChild();

        e = ec;

        internalIndex += 1;
      }
    }

    return e;
  }

  /**
   * Get the index of an element based on the provided value
   * @param value value to search for
   * @return index of element or -1 when not found
   */
  private int getElementIndexByValue(final T value) {
    int index = -1;

    if (size > 0) {
      int internalIndex = 0;

      while (internalIndex < size) {
        final Element<T> x = getElement(internalIndex);

        if (Objects.equals(x.value, value)) {
          index = internalIndex;
          break;
        }

        internalIndex += 1;
      }
    }

    return index;
  }

  Element<T> getHead() {
    return head;
  }

  public static class Element<T> {

    private final T value;

    private Element<T> child;

    public Element(final T value) {
      this.value = value;
    }

    public T getValue() {
      return value;
    }

    private Element<T> getChild() {
      return child;
    }

    private void setChild(final Element<T> child) {
      this.child = child;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Element<?> element = (Element<?>) o;
      return Objects.equals(value, element.value)
        && Objects.equals(child, element.child);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, child);
    }

    @Override
    public String toString() {
      return "Element{" +
        "value=" + value +
        '}';
    }
  }

  /**
   * Circular iterator that can be used to iterator indefinitely through the circular linked list.
   * @param <T> type of values in circular linked list
   */
  public static class CircularIterator<T> implements Iterator<T> {

    private final Element<T> head;

    private Element<T> current;

    private CircularIterator(final CircularLinkedList<T> circularLinkedList) {
      this.head = circularLinkedList.getHead();
      this.current = head;
    }

    public boolean isHead() {
      return current == head;
    }

    @Override
    public boolean hasNext() {
      return current != null & current.getChild() != null;
    }

    @Override
    public T next() {
      final T valueOfCurrent = current.getValue();
      final Element<T> next = current.getChild();
      current = next;

      return valueOfCurrent;
    }

  }

}
