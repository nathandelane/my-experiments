package com.github.nathandelane.experiments.maths.logarithms;

import java.io.IOException;

// All of these are wrong :/
public class LogBase2 {

	private static final int logTable64[] = new int[]{
		63, 0, 58, 1, 59, 47, 53, 2,
		60, 39, 48, 27, 54, 33, 42, 3,
		61, 51, 37, 40, 49, 18, 28, 20,
		55, 30, 34, 11, 43, 14, 22, 4,
		62, 57, 46, 52, 38, 26, 32, 41,
		50, 36, 17, 19, 29, 10, 13, 21,
		56, 45, 25, 31, 35, 16, 9, 12,
		44, 24, 15, 8, 23, 7, 6, 5
	};

	public static int log2_long(final long value) {
		long log2 = value;

		log2 |= (log2 >> 1);
		log2 |= (log2 >> 2);
		log2 |= (log2 >> 4);
		log2 |= (log2 >> 8);
		log2 |= (log2 >> 16);
		log2 |= (log2 >> 32);

		final int index = ((int) ((log2 - (log2 >> 1)) * 0x07EDD5E59A4E28C2L)) >> 58;
		final int result = logTable64[index];

		return result;
	}

	private static final int[] b = new int[] { 0x2, 0xC, 0xF0, 0xFF00, 0xFFFF0000 };
	private static final int[] S = new int[] { 1, 2, 4, 8, 16 };

	public static int log2_int(final int value) {
		int log2 = value;

		for (int i = 4; i >= 0; i--) // unroll for speed...
		{
			if ((log2 & b[i]) != 0) {
				log2 >>= S[i];
				log2 |= S[i];
			}
		}

		return log2;
	}

	public static void main(final String[] args) throws IOException {
		final int log2_of_21 = log2_int(21);

		System.in.read();
	}

}
