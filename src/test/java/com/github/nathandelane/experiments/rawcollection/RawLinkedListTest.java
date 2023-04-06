package com.github.nathandelane.experiments.rawcollection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RawLinkedListTest {

  @Test
  public void testNewListOfSize() {
    final ListNode<Integer> listOfInt = RawLinkedList.newList(10);
    final int size = RawLinkedList.sizeOfList(listOfInt);

    assertTrue(size == 10);
  }

  @Test
  public void testSetValueGetValue() {
    ListNode<Integer> listOfInt = RawLinkedList.newList(10);
    listOfInt = RawLinkedList.setValue(listOfInt, 0, 24);

    final int value = RawLinkedList.getValue(listOfInt, 0);
    final int size = RawLinkedList.sizeOfList(listOfInt);

    assertTrue(value == 24);
    assertTrue(size == 10);
  }

  @Test
  public void testFillList() {
    ListNode<Integer> listOfInt = RawLinkedList.newList(10);

    for (int i = 0; i < 10; i++) {
      listOfInt = RawLinkedList.setValue(listOfInt, i, (i+1));
    }

    final int value = RawLinkedList.getValue(listOfInt, 9);
    final int size = RawLinkedList.sizeOfList(listOfInt);

    assertTrue(value == 10);
    assertTrue(size == 10);
  }

  @Test
  public void testNewListOfArray() {
    final Integer[] arrayOfInt = new Integer[] { 1, 3, 5, 7, 9, 11, 13, 15 };
    final ListNode<Integer> listOfInt = RawLinkedList.newListOf(arrayOfInt);

    final int lengthOfArray = arrayOfInt.length;
    final int lengthOfList = RawLinkedList.sizeOfList(listOfInt);

    assertTrue(lengthOfList == lengthOfArray);
  }

  @Test
  public void testNewListOfIterable() {
    final List<Integer> list = Arrays.asList(new Integer[] { 1, 3, 5, 7, 9, 11, 13, 15 });
    final ListNode<Integer> listOfInt = RawLinkedList.newListOf(list);

    final int lengthOfArray = list.size();
    final int lengthOfList = RawLinkedList.sizeOfList(listOfInt);

    assertTrue(lengthOfList == lengthOfArray);
  }

  @Test
  public void testAddToEnd() {
    final Integer[] arrayOfInt = new Integer[] { 1, 3, 5, 7, 9, 11, 13, 15 };
    final ListNode<Integer> listOfInt = new ListNode<>(1);
    final int lengthOfArray = arrayOfInt.length;

    for (int index = 1; index < lengthOfArray; index++) {
      final int nextInt = arrayOfInt[index];

      RawLinkedList.addToEnd(listOfInt, nextInt);
    }

    final int lengthOfList = RawLinkedList.sizeOfList(listOfInt);

    assertTrue(lengthOfList == lengthOfArray);

    for (int index = 0; index < lengthOfArray; index++) {
      final int arrayValue = arrayOfInt[index];
      final int listValue = RawLinkedList.getValue(listOfInt, index);

      assertTrue(Integer.compare(arrayValue, listValue) == 0);
    }
  }

}
