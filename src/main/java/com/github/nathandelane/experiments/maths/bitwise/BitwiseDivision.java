package com.github.nathandelane.experiments.maths.bitwise;

public final class BitwiseDivision {

	private static boolean isDivisible(final byte a, final byte b) {
		byte quotient = 0;
		byte n = a;
		byte d = b;

		for (byte i = 7; i >= 0; --i) {

			// Check if (divisor << i) <= dividend
			if ((d << i) <= n) {
				n -= (b << i);
				quotient |= (1L << i);
			}
		}

		return n == 0;
	}

	public static void main(final String[] args) {
		for (byte x = 1; x <= 100; x++) {
			if(isDivisible(x, (byte) 15)) System.out.println("FizzBuzz");
			else if(isDivisible(x, (byte) 3)) System.out.println("Fizz");
			else if(isDivisible(x, (byte) 5)) System.out.println("Buzz");
			else System.out.format("%d%n", x);
		}
	}

}
