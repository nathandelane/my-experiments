package com.github.nathandelane.experiments.interview;

/**
 * This implementation of FIzzBuzz avoids division and modulus by essentially populating a sieve:
 * <ol>
 *   <li>Set all elements of array to index + 1 (arrays start with zero, but we want 1-100)</li>
 *   <li>Set all of the multiples of three by skipping elements three at a time, then setting to "Fizz"</li>
 *   <li>Set all of the multiples of five by skipping elements five at a time, check if the element already
 *   equals "Fizz", and if so, then set to "FizzBuzz", otherwise set to "Buzz"</li>
 * </ol>
 */
public class FizzBuzz5 {

  private FizzBuzz5() { }

  private String[] setMultiplesOfThree(final String[] numbers) {
    for (int i = 3; i <= numbers.length; i += 3) {
      numbers[(i - 1)] = "Fizz";
    }

    return numbers;
  }

  private String[] setMultiplesOfFive(final String[] numbers) {
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

    final String[] fizzBuzzedNumbers = setMultiplesOfFive(setMultiplesOfThree(numbers));

    for (final String num : fizzBuzzedNumbers) {
      System.out.println(num);
    }
  }

  public static void main(final String[] args) {
    final FizzBuzz5 fizzBuzz5 = new FizzBuzz5();
    fizzBuzz5.printFizzBuzz(100);
  }

}
