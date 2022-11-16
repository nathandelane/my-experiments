package com.github.nathandelane.experiments.search;

import static java.lang.System.out;

public final class SquareRoot {

  public static double find(final double square, final double precision) {
    double squareRoot = square;
    double guess = (square / 2.0);

    if (Math.pow(guess, 2.0) == square) {
      squareRoot = guess;
    }
    else {

    }

    return squareRoot;
  }

}
