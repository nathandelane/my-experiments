package com.github.nathandelane.experiments.linkedlists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoublyLinkedListTests {

  @Test
  public void testEmptyDoublyLinkedList() {
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>();

    assertEquals(0, l.size());
  }

  @Test
  public void testAppendSingleNode() {
    final Integer value = 256;
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    l.append(value);

    assertEquals(1, l.size());
    assertEquals(value, l.get(0));
  }

  @Test
  public void testConstructWithArray() {
    final Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 10 };
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>(a);

    assertEquals(a.length, l.size());
  }

  @Test
  public void testInsert() {
    final Integer[] values = new Integer[] { 28, 72, 196 };
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    l.append(values[0]);
    l.append(values[2]);
    l.insert(1, values[1]);

    assertEquals(3, l.size());
    assertEquals(values[0], l.get(0));
    assertEquals(values[1], l.get(1));
    assertEquals(values[2], l.get(2));
  }

  @Test
  public void testToString1() {
    final Integer value = 256;
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    l.append(value);

    final String toStringValue = l.toString();

    assertEquals("{ 256 }", toStringValue);
  }

  @Test
  public void testToString3WithInsert() {
    final Integer[] values = new Integer[] { 28, 72, 196 };
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    l.append(values[0]);
    l.append(values[2]);
    l.insert(1, values[1]);

    final String toStringValue = l.toString();

    assertEquals("{ 28, 72, 196 }", toStringValue);
  }

  @Test
  public void testRemoveByOne() {
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    l.append(256);
    l.remove(0);

    assertEquals(0, l.size());
  }

  @Test
  public void testRemoveByIndex() {
    final Integer[] values = new Integer[] { 28, 72, 196 };
    final DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    l.append(values[0]);
    l.append(values[1]);
    l.append(values[2]);
    l.remove(1);

    assertEquals(2, l.size());
    assertEquals(values[0], l.get(0));
    assertEquals(values[2], l.get(1));
  }

}
