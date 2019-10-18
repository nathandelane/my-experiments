package com.github.nathandelane.experiments.numberofdigits;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
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

	public static void runTests(String[] args) {
		final List<Long> oneHunderedThousandNumbers = new ArrayList<>();
		final Random random = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < 100_000; i++) {
			final BigInteger result = new BigInteger(63, random);
			final Long longFromDouble = result.longValue();
			
			oneHunderedThousandNumbers.add(longFromDouble);
		}
		
		final List<Integer> actualsBasedOnStrings = new ArrayList<>();
		
		int maxLength = 1;
		int minLength = 1;
		
		for (final Long n : oneHunderedThousandNumbers) {
			final String x = n.toString();
			final Integer length = x.length();
			
			actualsBasedOnStrings.add(length);
			
			if (length > maxLength) {
				maxLength = length;
			}
			if (length < minLength) {
				minLength = length;
			}
		}
		
		final List<Integer> lengths1 = new ArrayList<>();
		
		long totalMillis = 0;
		
		for (final Long n : oneHunderedThousandNumbers) {
			final long startMilliseconds = System.currentTimeMillis();
			final Integer num = NUMBER_OF_DIGITS.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths1.add(num);
		}
		
		System.out.println(String.format("Method from StackExchange - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));

		final List<Integer> lengths2 = new ArrayList<>();
		
		totalMillis = 0;
		
		for (final Long n : oneHunderedThousandNumbers) {
			final long startMilliseconds = System.currentTimeMillis();
			final Integer num = LOG_10_METHOD.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths2.add(num);
		}
		
		System.out.println(String.format("Log10 method from StackExchange - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));
		
		final List<Integer> lengths3 = new ArrayList<>();
		
		totalMillis = 0;
		
		for (final Long n : oneHunderedThousandNumbers) {
			final long startMilliseconds = System.currentTimeMillis();
			final Integer num = OTHER_METHOD.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths3.add(num);
		}
		
		System.out.println(String.format("Other method from StackExchange - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));
		
		final List<Integer> lengths4 = new ArrayList<>();
		
		totalMillis = 0;
		
		for (final Long n : oneHunderedThousandNumbers) {
			final long startMilliseconds = System.currentTimeMillis();
			final Integer num = YET_ANOTHER_METHOD.apply(n);
			final long total = System.currentTimeMillis() - startMilliseconds;
			
			totalMillis += total;
			
			lengths4.add(num);
		}
		
		System.out.println(String.format("Yet another method from StackExchange - total milliseconds: %s", NUMBER_FORMAT.format(totalMillis)));
		
		System.out.println(String.format("Min length: %s, Max length: %s", minLength, maxLength));
		System.out.println(String.format("1 == actualsBasedOnStrings: %s", lengths1.equals(actualsBasedOnStrings)));
		System.out.println(String.format("2 == actualsBasedOnStrings: %s", lengths2.equals(actualsBasedOnStrings)));
		System.out.println(String.format("3 == actualsBasedOnStrings: %s", lengths3.equals(actualsBasedOnStrings)));
		System.out.println(String.format("4 == actualsBasedOnStrings: %s", lengths4.equals(actualsBasedOnStrings)));
	}
}
