package com.github.nathandelane.experiments.collect;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CircularLinkedListTest {

  @Test
  public void addTest() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(2);
    circularLinkedList.add(3);

    assertTrue(circularLinkedList.size() == 3);
    assertTrue(circularLinkedList.get(0).equals(1));
    assertTrue(circularLinkedList.get(1).equals(2));
    assertTrue(circularLinkedList.get(2).equals(3));
  }

  @Test
  public void indexOfTest() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(2);
    circularLinkedList.add(3);

    assertTrue(circularLinkedList.size() == 3);
    assertTrue(circularLinkedList.indexOf(1) == 0);
    assertTrue(circularLinkedList.indexOf(2) == 1);
    assertTrue(circularLinkedList.indexOf(3) == 2);
  }

  @Test
  public void removeTest() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(2);
    circularLinkedList.add(3);
    circularLinkedList.removeAt(0);

    assertTrue(circularLinkedList.size() == 2);
    assertTrue(circularLinkedList.get(0).equals(2));
    assertTrue(circularLinkedList.get(1).equals(3));
  }

  @Test
  public void removeTest2() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(2);
    circularLinkedList.add(3);
    circularLinkedList.removeAt(0);
    circularLinkedList.removeAt(0);

    assertTrue(circularLinkedList.size() == 1);
    assertTrue(circularLinkedList.get(0).equals(3));
  }

  @Test
  public void removeTest3() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(2);
    circularLinkedList.add(3);
    circularLinkedList.removeAt(0);
    circularLinkedList.removeAt(0);

    assertTrue(circularLinkedList.size() == 1);
    assertTrue(circularLinkedList.get(0).equals(3));
  }

  @Test
  public void removeTest4() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(2);
    circularLinkedList.add(3);
    circularLinkedList.remove(1);
    circularLinkedList.remove(2);

    assertTrue(circularLinkedList.size() == 1);
    assertTrue(circularLinkedList.get(0).equals(3));
  }

  @Test
  public void removeTest5() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(1);
    circularLinkedList.add(1);
    circularLinkedList.remove(1);

    assertTrue(circularLinkedList.size() == 2);
    assertTrue(circularLinkedList.get(0).equals(1));
  }

  @Test
  public void removeTest6() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(1);
    circularLinkedList.add(1);
    circularLinkedList.remove(1);
    circularLinkedList.remove(1);
    circularLinkedList.remove(1);

    assertTrue(circularLinkedList.size() == 0);
  }

  @Test
  public void createWithListTest() {
    final List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>(list);

    assertTrue(circularLinkedList.size() == 3);
    assertTrue(circularLinkedList.get(0).equals(1));
    assertTrue(circularLinkedList.get(1).equals(2));
    assertTrue(circularLinkedList.get(2).equals(3));
  }

  @Test
  public void iteratorTest() {
    final CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    circularLinkedList.add(1);
    circularLinkedList.add(2);
    circularLinkedList.add(3);
    circularLinkedList.add(4);
    circularLinkedList.add(5);
    final CircularLinkedList.CircularIterator<Integer> iter = circularLinkedList.iterator();

    assertTrue(iter.isHead());

    final int loopingMax = (2 * circularLinkedList.size());

    int loopingCounter = 0;

    while (loopingCounter < loopingMax) {
      assertTrue(iter.hasNext());

      final int val = iter.next();
      final int expectedVal = (loopingCounter % circularLinkedList.size() + 1);

      assertTrue(val == expectedVal);

      loopingCounter += 1;
    }
  }

}
