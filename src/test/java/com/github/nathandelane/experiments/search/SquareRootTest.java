package com.github.nathandelane.experiments.search;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SquareRootTest {

  @Test
  public void test2() {
    final double square = 2.0;
    final double precision = 0.001;
    final double squareRootOf2 = SquareRoot.find(square, precision);
    final double expectedMax = square + precision;
    final double expectedMin = square - precision;

    assertTrue(String.format("Actual: %s, Min: %s, Max: %s", (squareRootOf2 * squareRootOf2), expectedMin, expectedMax),(squareRootOf2 * squareRootOf2) < expectedMax && (squareRootOf2 * squareRootOf2) < expectedMin);
  }

}
