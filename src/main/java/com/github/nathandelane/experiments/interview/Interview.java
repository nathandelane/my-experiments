package com.github.nathandelane.experiments.interview;

public class Interview {

	public static void reverseCharArray(char[] input) {
		int distanceFromStart = 0;
		
		final int midPoint = (input.length / 2) + (input.length % 2);
		
		while ((distanceFromStart + 1) < midPoint) {
			final char front = input[distanceFromStart];
			final char back = input[(input.length - distanceFromStart - 1)];
			
			if (front != back) {
				input[distanceFromStart] = back;
				input[(input.length - distanceFromStart - 1)] = front;
			}
			
			distanceFromStart++;
		}
	}
	
	/**
	 * Assume a word is separated by a single space.
	 * @param input
	 */
	public static void reverseWords(char[] input) {
		int currentIndex = 0;
		int startOfWord = 0;
		
		while (currentIndex <= input.length) {
			if (currentIndex == input.length || input[currentIndex] == ' ') {
				final int midPoint = (((currentIndex - startOfWord - 1) / 2) + ((currentIndex - startOfWord - 1) % 2));
				
				for (int i = 0; i < midPoint; i++) {
					final char front = input[(startOfWord + i)];
					final char back = input[(currentIndex - 1 - i)];
					
					if (front != back) {
						input[(startOfWord + i)] = back;
						input[(currentIndex - 1 - i)] = front;
					}
				}
				
				startOfWord = currentIndex + 1;
			}
			
			currentIndex++;
		}
	}
	
	public static void test() {
		String[] grid = new String[] { "....", "....", "...." };
		
		int numberOfWays = 0;
    int gridWidth = grid[0].length();
    int gridHeight = grid.length;

    String linearGrid = "";

    for (final String next : grid) {
        linearGrid += next;
    }

    if (linearGrid.indexOf(".") > -1) {
        
    }
    else {
        numberOfWays = 1;
    }

    System.out.println(numberOfWays);
	}
	
	private static final int[][] UPRIGHT_L = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 }};
	private static final int[][] TURNED_90 = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 }};
	private static final int[][] TURNED_180 = new int[][] { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 }};
	private static final int[][] TURNED_270 = new int[][] { { 2, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 }};
	
	static String drawL(int orientation, String linearGrid, int width) {
	    char[] arr = linearGrid.toCharArray();
	
	    if (orientation == 1) { // upright L
	    	arr = applyPattern(arr, UPRIGHT_L, width);
	    }
	    else if (orientation == 2) { // turned 90 degrees
	    	arr = applyPattern(arr, TURNED_90, width);
	    }
	    else if (orientation == 3) { // turned 180 degrees
	    	arr = applyPattern(arr, TURNED_180, width);
	    }
	    else { // turned 270 degrees
	    	arr = applyPattern(arr, TURNED_270, width);
	    }
	
	    return new String(arr);
	}
	
	static char[] applyPattern(char[] arr, int[][] pattern, int width) {
	    final char[] copy = new char[arr.length];
	
	    System.arraycopy(arr, 0, copy, 0, arr.length);
	
	    boolean completed = true;
	
	    for (final int[] next : pattern) {
	        int x = next[0];
	        int y = next[1];
	
	        if (copy[(y * width) + x] == '.') {
	            copy[(y * width) + x] = 'L';
	        }
	        else {
	            completed = false;
	            break;
	        }
	    }
	    
	    if (completed) {
	    	return copy;
	    }
	    else {
	    	return arr;
	    }
	}
	
	public static void main(String[] args) {
		String input = "hello";
		
		char[] charArrayOfInput = input.toCharArray();
		
		reverseCharArray(charArrayOfInput);
		
		String input2 = "hello how are you today";
		
		char[] charArrayOfInput2 = input2.toCharArray();
		
		reverseCharArray(charArrayOfInput2);
		reverseWords(charArrayOfInput2);
	}
	
}
