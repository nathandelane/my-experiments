package com.github.nathandelane.experiments.rawcollection;

import java.util.Iterator;

public final class RawLinkedList {

  private RawLinkedList() {
    // No instance
  }

  public static <T> int sizeOfList(final ListNode<T> list) {
    if (list == null) return 0;

    int size = 0;
    ListNode<T> childNode = list;

    do {
      size++;
      childNode = childNode.getChild();
    } while (childNode != null);

    return size;
  }

  public static <T> ListNode<T> newList(final int size) {
    ListNode<T> root = null;

    int count = 0;

    while (count < size) {
      final ListNode<T> nextNode;

      if (root == null) {
        nextNode = new ListNode<>(null);
      }
      else {
        nextNode = new ListNode<>(null, root);
      }

      root = nextNode;
      count++;
    }

    return root;
  }

  public static <T> ListNode<T> newListOf(final T[] arrayOfT) {
    if (arrayOfT == null) throw new NullPointerException("Array is null.");

    final int arrayLength = arrayOfT.length;

    ListNode<T> result = null;
    int index = (arrayLength - 1);

    while (index >= 0) {
      final T value = arrayOfT[index];
      final ListNode<T> newChildNode = new ListNode<>(value);

      if (result != null) {
        newChildNode.setChild(result);
      }

      result = newChildNode;
      index--;
    }

    return result;
  }

  public static <T> ListNode<T> newListOf(final Iterable<T> iterable) {
    if (iterable == null) throw new NullPointerException("Iterable is null.");

    final Iterator<T> iterator = iterable.iterator();

    ListNode<T> root = null;
    ListNode<T> nextChild = null;

    while (iterator.hasNext()) {
      final T next = iterator.next();
      final ListNode<T> newChildNode = new ListNode<>(next);

      if (root == null) {
        root = newChildNode;
        nextChild = newChildNode;
      }
      else {
        nextChild.setChild(newChildNode);
        nextChild = nextChild.getChild();
      }
    }

    return root;
  }

  public static <T> ListNode<T> setValue(final ListNode<T> root, final int index, final T newValue) {
    if (root == null) throw new NullPointerException("ListNode is null.");

    final int size = sizeOfList(root);

    if (index < 0 || index >= size) throw new IndexOutOfBoundsException(String.format("Index %d is greater than or equal to list size.", index));

    ListNode<T> currentNode = root;
    int count = 0;

    while (count < index) {
      currentNode = currentNode.getChild();
      count++;
    }

    currentNode.setValue(newValue);

    return root;
  }

  public static <T> T getValue(final ListNode<T> root, final int index) {
    if (root == null) throw new NullPointerException("ListNode is null.");
    if (index < 0 || index >= sizeOfList(root)) throw new IndexOutOfBoundsException(String.format("Index %d is greater than or equal to list size.", index));

    ListNode<T> childNode = root;
    int count = 0;

    while (count < index) {
      childNode = childNode.getChild();

      count++;
    }

    final T value = childNode.getValue();

    return value;
  }

  public static <T> ListNode<T> addToEnd(final ListNode<T> root, final T value) {
    if (root == null) throw new NullPointerException("ListNode is null.");

    ListNode<T> childNode = root;

    while (childNode.getChild() != null) {
      childNode = childNode.getChild();
    }

    final ListNode<T> newChildNode = new ListNode<>(value);

    childNode.setChild(newChildNode);

    return root;
  }

  public static <T> ListNode<T> addToFront(final ListNode<T> root, final T value) {
    if (root == null) throw new NullPointerException("ListNode is null.");

    final ListNode<T> newRoot = new ListNode<>(value, root);

    return newRoot;
  }

  public static <T> ListNode<T> clear(final ListNode<T> root) {
    final int size = sizeOfList(root);

    return newList(size);
  }

}
