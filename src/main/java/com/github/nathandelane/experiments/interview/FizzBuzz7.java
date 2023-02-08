package com.github.nathandelane.experiments.interview;

import java.util.function.Function;

public class FizzBuzz7 {

  private static final Function<Integer, String> IS_MULTIPLE_OF_THREE_OR_FIVE = new Function<>() {
    @Override
    public String apply(final Integer integer) {
      final String fizzBuzz = IS_MULTIPLE_OF_THREE.apply(integer);

      if (fizzBuzz.equalsIgnoreCase("")) return integer.toString();

      return fizzBuzz;
    }
  };

  private static final Function<Integer, String> IS_MULTIPLE_OF_THREE = new Function<>() {
    @Override
    public String apply(final Integer integer) {
      final StringBuilder sb = new StringBuilder();

      if (integer % 3 == 0) sb.append("Fizz");

      sb.append(IS_MULTIPLE_OF_FIVE.apply(integer));

      return sb.toString();
    }
  };

  private static final Function<Integer, String> IS_MULTIPLE_OF_FIVE = new Function<>() {
    @Override
    public String apply(final Integer integer) {
      final StringBuilder sb = new StringBuilder();

      if (integer % 5 == 0) sb.append("Buzz");

      return sb.toString();
    }
  };

  public static void main(final String[] args) {
    for (int i = 1; i <= 100; i++) {
      final String result = IS_MULTIPLE_OF_THREE_OR_FIVE.apply(i);

      System.out.println(result);
    }
  }

}
