package com.github.nathandelane.experiments.interview;

public class InterviewFunctional {
	
	public static void reverse(final char[] c) {
		reverseRecursive(c, 0);
	}
	
	public static void reverseRecursive(final char[] c, final int left) {
		if (left >= (c.length / 2 + c.length % 2)) {
			return;
		}
		else {
			final char s = c[left];
			final int right = (c.length - 1) - left;
			
			c[left] = c[right];
			c[right] = s;
			
			reverseRecursive(c, left + 1);
		}
	}
	
	public static char[] reverseWords(char[] c) {
		final char[] r = new char[c.length];
		
		int wordLength = 0;
		int startOfWord = 0;
		
		int ci = 0;
		
		do {
			final char next = c[ci];
			
			if (next !=  ' ' && ci < (c.length - 1)) wordLength++;
			else {
				if (ci == (c.length - 1)) {
					wordLength++;
				}
				
				for (int i = 0; i < wordLength; i++) {
					final char nc = c[(startOfWord + i)];
					final int np = (c.length - (wordLength + startOfWord) + i);
					
					r[np] = nc;
				}
				
				final int sp = (c.length - (wordLength + startOfWord) - 1);
				
				if (sp >= 0) {
					r[sp] = ' ';
				}
				
				startOfWord += wordLength + 1;
				wordLength = 0;
			}
			
			ci++;
		} while (ci < c.length);
		
		return r;
	}

	public static void main(final String[] args) {
		final char[] c = "how are you today".toCharArray();
		
//		reverse(c);
//		
//		System.out.println(c);
		
		char[] x = new char[c.length];
		x = reverseWords(c);

		System.out.println(c);
		System.out.println(x);
	}
	
}
