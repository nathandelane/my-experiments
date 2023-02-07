package com.github.nathandelane.experiments.interview;

/**
 * This FizzBuzz implementation uses a static mask, which is a little bit better than a sieve.
 * The intent is to avoid using division (or modulus) to identify when a value is a multiple of
 * 3 (Fizz), 5 (Buzz), or both 3 and 5 (FizzBuzz). The mask is based on the idea that the values
 * will repeat in a predictable pattern, which they do. This idea is based on an article about
 * a SIMD (single instruction multiple data) FizzBuzz (https://www.morling.dev/blog/fizzbuzz-simd-style/)
 * that could use a new vectorization optimization for Java proposed in JEP 338
 * (https://openjdk.org/jeps/338).
 */
public class FizzBuzz6 {

  private static final byte NUMBER = 0x00;
  private static final byte FIZZ_BUZZ = -0x03;
  private static final byte FIZZ = -0x02;
  private static final byte BUZZ = -0x01;
  private static final byte[] MASK = new byte[] { // 15
    NUMBER /* 1 */, NUMBER, FIZZ /* 3 */, NUMBER, BUZZ /* 5 */,
    FIZZ /* 6 */, NUMBER, NUMBER, FIZZ /* 9 */, BUZZ /* 10 */,
    NUMBER, FIZZ /* 12 */, NUMBER, NUMBER, FIZZ_BUZZ /* 15 */
  };

  public static void main(final String[] args) {
    final int maxNumber = 100;

    int currentNumber = 1;
    int maskIndex = 0;

    while (currentNumber <= maxNumber) {
      if (maskIndex >= 15) maskIndex = 0;

      final byte maskValue = MASK[maskIndex];

      if (maskValue == NUMBER) System.out.println(currentNumber);
      if (maskValue == FIZZ_BUZZ) System.out.println("FizzBuzz");
      if (maskValue == FIZZ) System.out.println("Fizz");
      if (maskValue == BUZZ) System.out.println("Buzz");

      currentNumber++;
      maskIndex++;
    };
  }

}
