package com.github.nathandelane.experiments.arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DimensionalArrayTest {

	@Test
	public void testFirstDimension() {
		final int dims[] = new int[] { 10 };
		final int x = 3;
		final int result = DimensionalArray.LIST_OF_FUNCTIONS.get(0).apply(dims, x);

		assertEquals("Incorrect result", 3, result);
	}

	@Test
	public void testSecondDimension() {
		final int dims[] = new int[] { 10, 10 };
		final int y = 5;
		final int expected = (y * 10);
		final int result = DimensionalArray.LIST_OF_FUNCTIONS.get(1).apply(dims, y);

		assertEquals("Incorrect result", expected, result);
	}

	@Test
	public void testThirdDimension() {
		final int dims[] = new int[] { 10, 10, 10 };
		final int z = 5;
		final int expected = (z * 10 * 10);
		final int result = DimensionalArray.LIST_OF_FUNCTIONS.get(2).apply(dims, z);

		assertEquals("Incorrect result", expected, result);
	}

	@Test
	public void testFourthDimension() {
		final int dims[] = new int[] { 10, 10, 10, 10 };
		final int k = 3;
		final int expected = (k * 10 * 10 * 10);
		final int result = DimensionalArray.LIST_OF_FUNCTIONS.get(3).apply(dims, k);

		assertEquals("Incorrect result", expected, result);
	}

	@Test
	public void testFifthDimension() {
		final int dims[] = new int[] { 10, 10, 10, 10, 10 };
		final int l = 2;
		final int expected = (l * 10 * 10 * 10 * 10);
		final int result = DimensionalArray.LIST_OF_FUNCTIONS.get(4).apply(dims, l);

		assertEquals("Incorrect result", expected, result);
	}

	@Test
	public void testOneDimensionalFive() {
		final int dims[] = new int[] { 5 };
		final int x = 4;
		final int expected = 30;

		final DimensionalArray<Integer> dimensionalArray = new DimensionalArray<>(dims);
		dimensionalArray.fill(Integer.valueOf(0));
		dimensionalArray.set(expected, x);

		final int value = dimensionalArray.get(x);

		assertEquals(expected, value);
	}

	@Test
	public void testTwoDimensionalFiveByFive() {
		final int dims[] = new int[] { 5, 5 };
		final int x = 4;
		final int y = 3;
		final int expected = 30;

		final DimensionalArray<Integer> dimensionalArray = new DimensionalArray<>(dims);
		dimensionalArray.fill(Integer.valueOf(0));
		dimensionalArray.set(expected, x, y);

		final int value = dimensionalArray.get(x, y);

		assertEquals(String.format("Value was: %d", value), expected, value);
	}

	@Test
	public void testThreeDimensionalFiveByFiveByFive() {
		final int dims[] = new int[] { 5, 5, 5 };
		final int x = 3;
		final int y = 2;
		final int z = 4;
		final int expected = 30;

		final DimensionalArray<Integer> dimensionalArray = new DimensionalArray<>(dims);
		dimensionalArray.fill(Integer.valueOf(0));
		dimensionalArray.set(expected, x, y, z);

		final int value = dimensionalArray.get(x, y, z);

		assertEquals(String.format("Value was: %d", value), expected, value);
	}

}
