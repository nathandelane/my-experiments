package com.github.nathandelane.experiments.interview;

import java.util.stream.IntStream;

/**
 * A relatively standard way of doing FizzBuzz with concatenation, modulus, and negative check.
 */
public class FizzBuzz3 {

  private FizzBuzz3() { }

  boolean isMultipleOfThree(final int i) {
    return i % 3 == 0;
  }

  boolean isMultipleOfFive(final int i) {
    return i % 5 == 0;
  }

  boolean isMultipleOfThreeOrFive(final int i) {
    return isMultipleOfThree(i) || isMultipleOfFive(i);
  }

  String getOutputForI(final int i) {
    StringBuilder sb = new StringBuilder();

    if (isMultipleOfThree(i)) {
      sb.append("Fizz");
    }
    if (isMultipleOfFive(i)) {
      sb.append("Buzz");
    }
    if (!isMultipleOfThreeOrFive(i)) {
      sb.append(i);
    }

    return sb.toString();
  }

  void printForI(final int i) {
    System.out.println(getOutputForI(i));
  }

  public static void main(final String[] args) {
    final FizzBuzz3 main = new FizzBuzz3();

    IntStream.rangeClosed(1, 100).forEach(i -> main.printForI(i));
  }

}