package com.github.nathandelane.experiments.linkedlists;

import java.util.Arrays;

public class LinkedLists {

	private static final class LinkedListDataStructure<T> {
		
		private ListNode<T> root;
		
		public LinkedListDataStructure(final Iterable<T> values) {
			if (values != null) {
				addAll(values);
			}
		}
		
		public void push(final T value) {
			if (root == null) {
				root = new ListNode<T>(value);
			}
			else {
				ListNode<T> previous = root;
				
				while (previous.getNext() != null) {
					previous = previous.getNext();
				}
				
				final ListNode<T> newNode = new ListNode<T>(value);
				
				previous.setChild(newNode);
			}
		}
		
		public void addAll(final Iterable<T> values) {
			for (final T nextValue : values) {
				push(nextValue);
			}
		}
		
		public int size() {
			int size = 0;
			
			ListNode<T> previous = root;
			
			if (root != null) {
				size = 1;
				
				while (previous.getNext() != null) {
					previous = previous.getNext();
					size++;
				}
			}
			
			return size;
		}
		
		public T get(final int index) {
			int indexCounter = 0;
			
			if (root == null) {
				throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
			}
			else {
				ListNode<T> currentNode = root;
				
				while (indexCounter < index && currentNode.getNext() != null) {
					currentNode = currentNode.getNext();
					indexCounter++;
				}
				
				if (indexCounter == index) {
					return currentNode.getValue();
				}
				else {
					throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
				}
			}
		}
		
		public void remove(final int index) {
			int indexCounter = 0;
			
			if (root == null) {
				throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
			}
			else {
				ListNode<T> lastNode = null;
				ListNode<T> currentNode = root;
				
				while (indexCounter < index && currentNode.getNext() != null) {
					lastNode = currentNode;
					currentNode = lastNode.getNext();
					indexCounter++;
				}
				
				if (indexCounter == index) {
					if (currentNode.getNext() != null) {
						lastNode.setChild(currentNode.getNext());
					}
					else {
						lastNode.setChild(null);
					}
				}
				else {
					throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
				}
			}
		}
		
		public void set(final int index, final T value) {
			int indexCounter = 0;
			
			if (root == null) {
				throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
			}
			else {
				ListNode<T> lastNode = null;
				ListNode<T> currentNode = root;
				
				while (indexCounter < index && currentNode.getNext() != null) {
					lastNode = currentNode;
					currentNode = lastNode.getNext();
					indexCounter++;
				}
				
				if (indexCounter == index && currentNode != null) {
					final ListNode<T> newCurrentNode = new ListNode<T>(value);
					newCurrentNode.setChild(currentNode.getNext());
					
					lastNode.setChild(newCurrentNode);
				}
				else {
					throw new IndexOutOfBoundsException(String.format("Index %s is out of bounds!", index));
				}
			}
		}
		
		public LinkedListDataStructure<T> reverse() {
			final LinkedListDataStructure<T> newList = new LinkedListDataStructure<>(null);
			
			if(root == null) {
				return newList;
			}
			else {
				if (root != null) {
					reverseNextNode(root, newList);
				}
			}
			
			return newList;
		}
		
		private void reverseNextNode(final ListNode<T> nextNode, final LinkedListDataStructure<T> newList) {
			if (nextNode.getNext() != null) {
				reverseNextNode(nextNode.getNext(), newList);
			}
			
  		newList.push(nextNode.value);
		}
		
		@Override
		public boolean equals(final Object other) {
			if (other != null && (other instanceof LinkedListDataStructure)) {
				try {
					final LinkedListDataStructure<?> otherLinkedList = (LinkedListDataStructure<?>) other;
				
					if (this.size() == otherLinkedList.size()) {
						ListNode<?> thisNode = root;
						ListNode<?> otherNode = otherLinkedList.root;
						
						while ((thisNode != null && otherNode != null) && thisNode.value.equals(otherNode.value)) {
							thisNode = thisNode.next;
							otherNode = otherNode.next;
						}
						
						if (thisNode == null && otherNode == null) {
							return true;
						}
					}
				}
				catch (final Exception e) {
					// No-op
				}
			}
		
			return false;
		}
		
	}
	
	private static final class ListNode<T> {
		
		private final T value;
		
		private ListNode<T> next;
		
		public ListNode(final T value) {
			this.value = value;
			this.next = null;
		}
		
		public void setChild(final ListNode<T> next) {
			this.next = next;
		}
		
		public T getValue() {
			return value;
		}
		
		public ListNode<T> getNext() {
			return next;
		}
		
		@Override
		public boolean equals(final Object other) {
			return (
				other != null &&
				(other instanceof ListNode<?>) &&
				this.value != null &&
				((ListNode<?>) other).value != null &&
				this.value.equals(((ListNode<?>) other).value)
			);
		}
		
	}
	
	public static void runTests(String[] args) {
		final Integer[] arrayOfInts = { 13, 1, 12, 2, 11, 3, 10, 4, 9, 5, 8, 6, 7 };
		final LinkedListDataStructure<Integer> list = new LinkedListDataStructure<>(Arrays.asList(arrayOfInts));
		
		runTest1(list, arrayOfInts);
		runTest2(list, arrayOfInts);		
		runTest3();
		runTest4(list, arrayOfInts);
	}
	
	private static void runTest1(final LinkedListDataStructure<Integer> list, final Integer[] arrayOfInts) {
		final int size = list.size();
		
		System.out.format("Expected size: %s, actual size: %s%n", arrayOfInts.length, size);
		
		for (int i = 0; i < size; i++) {
			final Integer nextInt = list.get(i);
			
			System.out.format("Element value at index %d: %s, expected %s%n", i, nextInt, arrayOfInts[i]);
		}
	}
	
	private static void runTest2(final LinkedListDataStructure<Integer> list, final Integer[] arrayOfInts) {
		list.remove(5);

		final int sizeAfterNodeRemoval = list.size();
		
		System.out.format("Expected size after node removal: %s, actual size: %s%n", arrayOfInts.length, sizeAfterNodeRemoval);
		
		for (int i = 0; i < sizeAfterNodeRemoval; i++) {
			final Integer nextInt = list.get(i);
			
			System.out.format("Element value at index %d: %s, expected %s%n", i, nextInt, arrayOfInts[i]);
		}
	}
	
	private static void runTest3() {
		final Integer[] arrayOfInts = { 13, 1, 12, 2, 11, 3, 10, 4, 9, 5, 8, 6, 7 };
		final LinkedListDataStructure<Integer> list = new LinkedListDataStructure<>(Arrays.asList(arrayOfInts));
		
		final LinkedListDataStructure<Integer> list2 = new LinkedListDataStructure<>(null);
		final int size = list2.size();
		
		System.out.format("Expected size: %s, actual size: %s%n", 0, size);
		
		for (int i = 0; i < arrayOfInts.length; i++) {
			list2.push(arrayOfInts[i]);
		}
		
		final int sizeAfterAddingElements = list2.size();
		
		System.out.format("Expected size: %s, actual size: %s%n", arrayOfInts.length, sizeAfterAddingElements);
		
		final boolean list2EqualsList = list2.equals(list);
		
		System.out.format("List is equal to list2: %s%n", list2EqualsList);
		
		for (int i = 0; i < list2.size(); i++) {
			final Integer nextInt = list.get(i);
			final Integer nextInt2 = list2.get(i);
			
			System.out.format("Element value at index %d: %s, expected %s%n", i, nextInt2, nextInt);
		}
		
		list2.set(12, 14);
		
		final boolean updatedList2EqualsList = list2.equals(list);

		System.out.format("List is equal to list2: %s%n", updatedList2EqualsList);
		
		for (int i = 0; i < list2.size(); i++) {
			final Integer nextInt = list.get(i);
			final Integer nextInt2 = list2.get(i);
			
			System.out.format("Element value at index %d: %s, expected %s%n", i, nextInt2, nextInt);
		}
	}
	
	private static void runTest4(final LinkedListDataStructure<Integer> list, final Integer[] arrayOfInts) {
		final LinkedListDataStructure<Integer> listReversed = list.reverse();
		
		for (int i = 0; i < list.size(); i++) {
			final Integer nextInt = list.get(i);
			final Integer revNextInt = listReversed.get(i);
			
			System.out.format("Element value at index %d: %s, reversed %s ==> is reversed: %s%n", i, nextInt, revNextInt, list.get(i).equals(listReversed.get(12 - i)));
		}
	}

}
