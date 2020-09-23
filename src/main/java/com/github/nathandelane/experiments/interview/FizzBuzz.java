package com.github.nathandelane.experiments.interview;

import static java.lang.System.out;

public class FizzBuzz {
  
  private static void print(final Object obj) {
    out.format("%s%n", obj);
  }
  
  private static boolean isMultipleOf3(final int value) {
    return value % 3 == 0;
  }
  
  private static boolean isMultipleOf5(final int value) {
    return value % 5 == 0;
  }
  
  private static Object get(final int value) {
    return isMultipleOf3(value) && isMultipleOf5(value) ? "FizzBuzz" :
      isMultipleOf3(value) ? "Fizz" :
        isMultipleOf5(value) ? "Buzz" :
          value;
  }
  
  public static void main(String[] args) {
    for (int i = 1; i <= 100; i++) {
      print(get(i));
    }
  }

}
