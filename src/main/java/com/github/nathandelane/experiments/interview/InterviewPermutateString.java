package com.github.nathandelane.experiments.interview;

public class InterviewPermutateString {
	
	public static void permutateString(final String input) {
		final char[] newValue = new char[input.length()];
		
		for (int i = 0; i < input.length(); i++) {
			newValue[0] = input.charAt(i);
			
			for (int j = 1; j < input.length(); j++) {
				String s = removeCharAtIndex(input, i);
				
				if (j % 2 == 0) {
					s = swap(s, 0, j - 1);
				}
				
				for (int k = 0; k < s.length(); k++) {
					newValue[k + 1] = s.charAt(k);
				}
				
				System.out.println(newValue);
			}
		}
	}
	
	public static String removeCharAtIndex(final String input, final int characterIndex) {
		final char[] arr = new char[input.length() - 1];
		
		for (int i = 0, j = 0; i < input.length(); i++) {
			if (i != characterIndex) {
				arr[j] = input.charAt(i);
				j++;
			}
		}
		
		return new String(arr);
	}
	
	public static String swap(final String input, final int left, final int right) {
		final char[] arr = input.toCharArray();
		final char t = arr[left];
		
		arr[left] = arr[right];
		arr[right] = t;
		
		return new String(arr);
	}
	
	public static void main(final String[] args) {
		final String input = "abc";
		
		permutateString(input);
	}

}
