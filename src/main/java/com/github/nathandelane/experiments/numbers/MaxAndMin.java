package com.github.nathandelane.experiments.numbers;

public final class MaxAndMin {

  private MaxAndMin() { }

  public static int max(final int first, final int second) {
    if (first < second) return second;

    return first;
  }

  public static int mathMax(final int first, final int second) {
    return Math.max(first, second);
  }

}
