package com.github.nathandelane.experiments.interview;

import java.util.Arrays;

public class FizzBuzz5 {

  private FizzBuzz5() { }

  private String[] setMultiplesOfThree(final String[] numbers) {
    for (int i = 3; i <= numbers.length; i += 3) {
      numbers[(i - 1)] = "Fizz";
    }

    return numbers;
  }

  private String[] setMultipleOfFive(final String[] numbers) {
    for (int i = 5; i <= numbers.length; i += 5) {
      if (numbers[(i - 1)].equals("Fizz")) {
        numbers[(i - 1)] = "FizzBuzz";
      }
      else {
        numbers[(i - 1)] = "Buzz";
      }
    }

    return numbers;
  }

  public void printFizzBuzz(final int max) {
    final String[] numbers = new String[max];

    for (int i = 1; i <= max; i++) {
      numbers[(i - 1)] = Integer.toString(i);
    }

    final String[] fizzBuzzedNumbers = setMultipleOfFive(setMultiplesOfThree(numbers));

    for (final String num : fizzBuzzedNumbers) {
      System.out.println(num);
    }
  }

  public static void main(final String[] args) {
    final FizzBuzz5 fizzBuzz5 = new FizzBuzz5();
    fizzBuzz5.printFizzBuzz(100);
  }

}
