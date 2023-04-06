package com.github.nathandelane.experiments.interview;

import com.github.nathandelane.experiments.linkedlists.LinkedListDataStructure;
import com.github.nathandelane.experiments.linkedlists.ListNode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrintLinkedListReverseOrder {

  private static void printNodeLastFirst(final ListNode<Character> listNode) {
    if (listNode == null) return;

    printNodeLastFirst(listNode.getNext());

    System.out.print(listNode.getValue());
  }

  public static void main(final String[] args) {
    final char[] alphabetArray = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    final List<Character> characterList = IntStream
      .range(0, alphabetArray.length)
      .mapToObj(i -> alphabetArray[i])
      .collect(Collectors.toList());
    final LinkedListDataStructure<Character> linkedListAlphabet = new LinkedListDataStructure<>(characterList);

    final ListNode<Character> first = linkedListAlphabet.getRoot();

    printNodeLastFirst(first);
  }

}
