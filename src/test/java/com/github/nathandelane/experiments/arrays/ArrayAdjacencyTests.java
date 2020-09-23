package com.github.nathandelane.experiments.arrays;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayAdjacencyTests {
	
	@Test
	public void testCreateArraySingeleimension1() {
		final int[] arrayWithOneElement = ArrayAdjacency.createTwoDimensionalArray(1, 1);
		
		assertTrue(arrayWithOneElement.length == 1);
	}

	@Test
	public void testCreateArraySingleDimension10() {
		final int[] arrayWithTenElements = ArrayAdjacency.createTwoDimensionalArray(10, 1);
		
		assertTrue(arrayWithTenElements.length == 10);
	}
	
	@Test
	public void testCreateArrayWithTwoDimensions_4x6() {
		final int[] array = ArrayAdjacency.createTwoDimensionalArray(4,  6);
		final int expectedSize = (4 * 6);
		
		assertTrue(array.length == expectedSize);
	}
	
	@Test
	public void testCreateArrayWithTwoDimensions_1x1() {
		final int[] array = ArrayAdjacency.createTwoDimensionalArray(1, 1);
		
		assertTrue(array.length == 1);
	}
	
	@Test
	public void testGetIndex() {
		final int[] array = ArrayAdjacency.createTwoDimensionalArray(4, 6);
		final int index = ArrayAdjacency.getIndex(array, 4, 6, 1, 1);
		
 		assertTrue(5 == index);
	}
	
	@Test
	public void testGetCoordinates() {
		final int[] array = ArrayAdjacency.createTwoDimensionalArray(4, 6);
		final int index = 5;
		final int[] coordinates = ArrayAdjacency.getCoordinates(array, 4, 6, index);
		
		assertTrue(coordinates.length == 2);
		assertTrue(coordinates[0] == 1);
		assertTrue(coordinates[1] == 1);
	}

}
