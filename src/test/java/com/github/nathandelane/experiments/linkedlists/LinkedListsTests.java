package com.github.nathandelane.experiments.linkedlists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class LinkedListsTests {
	
	private Integer[] arrayOfInts;
	
	private LinkedListDataStructure<Integer> list;
	
	@Before
	public void setup() {
		arrayOfInts = new Integer[] { 13, 1, 12, 2, 11, 3, 10, 4, 9, 5, 8, 6, 7 };
		list = new LinkedListDataStructure<>(Arrays.asList(arrayOfInts));
	}
	
	@Test
	public void runTest1() {
		final int size = list.size();
		
		assertTrue(String.format("Expected size: %s, actual size: %s%n", arrayOfInts.length, size), arrayOfInts.length == size);
		
		for (int i = 0; i < size; i++) {
			final Integer nextInt = list.get(i);
			
			assertEquals(String.format("Element value at index %d: %s, expected %s%n", i, nextInt, arrayOfInts[i]), nextInt, arrayOfInts[i]);
		}
	}
	
	@Test
	public void runTest2() {
		final int indexToRemove = 5;
		
		list.remove(indexToRemove);

		final int sizeAfterNodeRemoval = list.size();
		
		assertTrue(String.format("Expected size after node removal: %s, actual size: %s%n", (arrayOfInts.length - 1), sizeAfterNodeRemoval), (arrayOfInts.length - 1) == sizeAfterNodeRemoval);
		
		for (int i = 0; i < sizeAfterNodeRemoval; i++) {
			final Integer nextInt = list.get(i);
			
			assertEquals(String.format("Element value at index %d: %s, expected %s%n", i, nextInt, arrayOfInts[((i >= indexToRemove) ? (i + 1) : i)]), nextInt, arrayOfInts[((i >= indexToRemove) ? (i + 1) : i)]);
		}
	}
	
	@Test
	public void runTest3() {
		final Integer[] arrayOfInts = { 13, 1, 12, 2, 11, 3, 10, 4, 9, 5, 8, 6, 7 };
		final LinkedListDataStructure<Integer> internalList = new LinkedListDataStructure<>(Arrays.asList(arrayOfInts));
		
		final LinkedListDataStructure<Integer> internalList2 = new LinkedListDataStructure<>(null);
		final int size = internalList2.size();
		
		assertTrue(String.format("Expected size: %s, actual size: %s%n", 0, size), 0 == size);
		
		for (int i = 0; i < arrayOfInts.length; i++) {
			internalList2.push(arrayOfInts[i]);
		}
		
		final int sizeAfterAddingElements = internalList2.size();
		
		assertTrue(String.format("Expected size: %s, actual size: %s%n", arrayOfInts.length, sizeAfterAddingElements), arrayOfInts.length == sizeAfterAddingElements);
		
		final boolean list2EqualsList = internalList2.equals(internalList);
		
		assertEquals(String.format("List is equal to list2: %s%n", list2EqualsList), internalList2, internalList);
		
		for (int i = 0; i < internalList2.size(); i++) {
			final Integer nextInt = internalList.get(i);
			final Integer nextInt2 = internalList2.get(i);
			
			assertEquals(String.format("Element value at index %d: %s, expected %s%n", i, nextInt2, nextInt), nextInt2, nextInt);
		}
		
		final int indexToChange = 12;
		
		internalList2.set(indexToChange, 14);
		
		final boolean updatedList2EqualsList = internalList2.equals(internalList);

		assertNotEquals(String.format("List is equal to list2: %s%n", updatedList2EqualsList), internalList2, internalList);
		
		for (int i = 0; i < internalList2.size(); i++) {
			final Integer nextInt = internalList.get(i);
			final Integer nextInt2 = internalList2.get(i);
			
			if (i != indexToChange) {
				assertEquals(String.format("Element value at index %d: %s, expected %s%n", i, nextInt2, nextInt), nextInt2, nextInt);	
			}
			else {
				assertNotEquals(String.format("Element value at index %d: %s, expected %s%n", i, nextInt2, nextInt), nextInt2, nextInt);
			}
		}
	}
	
	@Test
	public void runTest4() {
		final LinkedListDataStructure<Integer> listReversed = list.reverse();
		
		for (int i = 0; i < list.size(); i++) {
			final Integer nextInt = list.get(i);
			final Integer revNextInt = listReversed.get(i);
			
			assertEquals(String.format("Element value at index %d: %s, reversed %s ==> is reversed: %s%n", i, nextInt, revNextInt, list.get(i).equals(listReversed.get(12 - i))), list.get(i), listReversed.get(12 - i));
		}
	}

}
