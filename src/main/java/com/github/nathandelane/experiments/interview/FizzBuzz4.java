package com.github.nathandelane.experiments.interview;

import java.util.function.Function;

/**
 * Use a Java 1.7 style function and a compound ternary operator - this is not very optimized.
 */
public class FizzBuzz4 {

  private static final Function<Integer, String> MAP_INTEGER_TO_STRING = new Function<Integer, String>() {

    @Override
    public String apply(final Integer i) {
      return (i % 3) == 0 && (i % 5) == 0 ? "FizBuzz" :
        (i % 3) == 0 ? "Fizz" :
          (i % 5) == 0 ? "Buzz" :
            i.toString();
    }

  };

  public static void main(String[] args) {
    for (int i = 1; i <= 100; i++) {
      System.out.println(MAP_INTEGER_TO_STRING.apply(i));
    }
  }

}
