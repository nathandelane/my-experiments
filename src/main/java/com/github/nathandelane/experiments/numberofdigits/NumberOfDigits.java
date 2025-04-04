package com.github.nathandelane.experiments.numberofdigits;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class NumberOfDigits {
	
	private static NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.US);

	private static final Function<Long, Integer> NUMBER_OF_DIGITS = x -> {
		if (x == null) {
			return 0;
		}

		if (x >= 10000) {
			if (x >= 10000000) {
				if (x >= 100000000) {
					if (x >= 1000000000) {
						return 10;
					}
					
					return 9;
				}
				
				return 8;
			}
			
			if (x >= 100000) {
				if (x >= 1000000) {
					return 7;
				}
				
				return 6;
			}
			return 5;
		}
		
		if (x >= 100) {
			if (x >= 1000) {
				return 4;
			}
			
			return 3;
		}
		
		if (x >= 10) {
			return 2;
		}
		
		return 1;
	};
	
	private static final Function<Long, Integer> LOG_10_METHOD = x -> {
		return (int) Math.log10(x.doubleValue()) + 1;
	};
	
	private static final Function<Long, Integer> OTHER_METHOD = x -> {
		if (x == 0) return 1;
    
		int l;
    
    for (l = 0; x > 0; ++l) {
        x /= 10;
    }
    
    return l;
	};
	
	private static final Function<Long, Integer> YET_ANOTHER_METHOD = x -> {
		int length = 1;
//		if (x >= 10000000000000000L) {
//	    length += 16;
//	    x /= 10000000000000000L;
//		}
		if (x >= 100000000) {
	    length += 8;
	    x /= 100000000;
		}
		if (x >= 100000000) {
		    length += 8;
		    x /= 100000000;
		}
		if (x >= 10000) {
		    length += 4;
		    x /= 10000;
		}
		if (x >= 100) {
		    length += 2;
		    x /= 100;
		}
		if (x >= 10) {
		    length += 1;
		}
		return length;
	};

	private static final long[] LOOKUP_TABLE = new long[] {
		4294967296L,  8589934582L,  8589934582L,  8589934582L,  12884901788L,
		12884901788L, 12884901788L, 17179868184L, 17179868184L, 17179868184L,
		21474826480L, 21474826480L, 21474826480L, 21474826480L, 25769703776L,
		25769703776L, 25769703776L, 30063771072L, 30063771072L, 30063771072L,
		34349738368L, 34349738368L, 34349738368L, 34349738368L, 38554705664L,
		38554705664L, 38554705664L, 41949672960L, 41949672960L, 41949672960L,
		42949672960L, 42949672960L
	};

	public static int log2(final long l) {
		return (int) (Math.log10(l) / Math.log10(2));
	}

	private static final Function<Long, Integer> IDS_METHOD = x -> {
		final int index = log2(x);

		return (int) ((x + LOOKUP_TABLE[index]) >> 32);
	};

	public static void runTests(String[] args) {
		final int ONE_HUNDRED_THOUSAND = 100_000;

		System.out.println("Generating 100,000 large integers...");

		final long[] oneHunderedThousandNumbers = new long[ONE_HUNDRED_THOUSAND];

		int initIndex = 0;

		final Random rand61 = new Random(System.currentTimeMillis());
		for (; initIndex < (ONE_HUNDRED_THOUSAND / 4); initIndex++) {
			final BigInteger result = new BigInteger(61, rand61);
			long positiveLong = Math.abs(result.longValue());
			
			oneHunderedThousandNumbers[initIndex] = positiveLong;
		}
		final Random rand41 = new Random(System.currentTimeMillis());
		for (; initIndex < (ONE_HUNDRED_THOUSAND / 2); initIndex++) {
			final BigInteger result = new BigInteger(41, rand41);
			long positiveLong = Math.abs(result.longValue());

			oneHunderedThousandNumbers[initIndex] = positiveLong;
		}
		final Random rand21 = new Random(System.currentTimeMillis());
		for (; initIndex < ((ONE_HUNDRED_THOUSAND / 4) * 3); initIndex++) {
			final BigInteger result = new BigInteger(21, rand21);
			long positiveLong = Math.abs(result.longValue());

			oneHunderedThousandNumbers[initIndex] = positiveLong;
		}
		final Random rand32 = new Random(System.currentTimeMillis());
		for (; initIndex < ONE_HUNDRED_THOUSAND; initIndex++) {
			final BigInteger result = new BigInteger(32, rand32);
			long positiveLong = Math.abs(result.longValue());

			oneHunderedThousandNumbers[initIndex] = positiveLong;
		}

		final int[] actualsBasedOnStrings = new int[ONE_HUNDRED_THOUSAND];
		int maxLength = 1;
		int minLength = 1;

		System.out.println("Collecting string-based number of digits for each number to validate.");

		for (int i = 0; i < oneHunderedThousandNumbers.length; i++) {
			final long n = oneHunderedThousandNumbers[i];
			final String x = String.valueOf(n);
			final int lengthOfN = x.length();
			
			actualsBasedOnStrings[i] = lengthOfN;
			
			if (lengthOfN > maxLength) {
				maxLength = lengthOfN;
			}
			if (lengthOfN < minLength) {
				minLength = lengthOfN;
			}
		}

		final Map<Integer, AtomicInteger> lengthToCountMap = new HashMap<>();

		for (int i = 0; i < actualsBasedOnStrings.length; i++) {
			final Integer numDigits = actualsBasedOnStrings[i];

			AtomicInteger counter = lengthToCountMap.get(numDigits);

			if (counter == null) {
				counter = new AtomicInteger(0);
				lengthToCountMap.put(numDigits, counter);
			}

			counter.incrementAndGet();
		}

		System.out.format("Distribution: %s%n", lengthToCountMap);

		final int[] lengths1 = new int[ONE_HUNDRED_THOUSAND];
		
		long totalMillis = 0;
		
		for (int i = 0; i < oneHunderedThousandNumbers.length; i++) {
			final long n = oneHunderedThousandNumbers[i];
			final long startMilliseconds = System.currentTimeMillis();
			final int num = NUMBER_OF_DIGITS.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths1[i] = num;
		}
		
		System.out.println(String.format("%n1: Method from StackExchange (Lookup based on whole numbers: NUMBER_OF_DIGITS) - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));

		final int[] lengths2 = new int[ONE_HUNDRED_THOUSAND];
		
		totalMillis = 0;
		
		for (int i = 0; i < oneHunderedThousandNumbers.length; i++) {
			final long n = oneHunderedThousandNumbers[i];
			final long startMilliseconds = System.currentTimeMillis();
			final int num = LOG_10_METHOD.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths2[i] = num;
		}
		
		System.out.println(String.format("2: Log10 method from StackExchange (LOG_10_METHOD) - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));
		
		final int[] lengths3 = new int[ONE_HUNDRED_THOUSAND];
		
		totalMillis = 0;
		
		for (int i = 0; i < oneHunderedThousandNumbers.length; i++) {
			final long n = oneHunderedThousandNumbers[i];
			final long startMilliseconds = System.currentTimeMillis();
			final int num = OTHER_METHOD.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths3[i] = num;
		}
		
		System.out.println(String.format("3: Other method from StackExchange (for-loop diving by 10 - OTHER_METHOD) - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));
		
		final int[] lengths4 = new int[ONE_HUNDRED_THOUSAND];
		
		totalMillis = 0;
		
		for (int i = 0; i < oneHunderedThousandNumbers.length; i++) {
			final long n = oneHunderedThousandNumbers[i];
			final long startMilliseconds = System.currentTimeMillis();
			final int num = YET_ANOTHER_METHOD.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths4[i] = num;
		}
		
		System.out.println(String.format("4: Yet another method from StackExchange {iterative divide by constant number - YET_ANOTHER_METHOD) - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));


		final int[] lengths5 = new int[ONE_HUNDRED_THOUSAND];

		totalMillis = 0;

		for (int i = 0; i < oneHunderedThousandNumbers.length; i++) {
			final long n = oneHunderedThousandNumbers[i];
			final long startMilliseconds = System.currentTimeMillis();
			final int num = IDS_METHOD.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;

			totalMillis += total;

			lengths5[i] = num;
		}

		System.out.println(String.format("5: IDS_METHOD - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));

		System.out.println(String.format("%nMin length: %s, Max length: %s", minLength, maxLength));
		System.out.println(String.format("1 == actualsBasedOnStrings: %s", Arrays.compare(lengths1, actualsBasedOnStrings) == 0));
		System.out.println(String.format("2 == actualsBasedOnStrings: %s", Arrays.compare(lengths2, actualsBasedOnStrings) == 0));
		System.out.println(String.format("3 == actualsBasedOnStrings: %s", Arrays.compare(lengths3, actualsBasedOnStrings) == 0));
		System.out.println(String.format("4 == actualsBasedOnStrings: %s", Arrays.compare(lengths4, actualsBasedOnStrings) == 0));
		System.out.println(String.format("5 == actualsBasedOnStrings: %s", Arrays.compare(lengths5, actualsBasedOnStrings) == 0));
	}
}
