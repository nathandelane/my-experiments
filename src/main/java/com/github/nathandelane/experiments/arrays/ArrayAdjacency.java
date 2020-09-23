package com.github.nathandelane.experiments.arrays;


public class ArrayAdjacency {
	
	public static int[] createTwoDimensionalArray(final int width, final int height) {
		final int total = (width * height);
		
		return new int[total];
	}
	
	public static int getIndex(final int[] array, final int width, final int height, final int x, final int y) {
		return (y * width + x);
	}
	
	public static int[] getCoordinates(final int[] array, final int width, final int height, final int index) {
		final int x = (index % width);
		final int y = ((index - (index % width)) / width);
		
		return new int[] { x, y };
	}

}
