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
