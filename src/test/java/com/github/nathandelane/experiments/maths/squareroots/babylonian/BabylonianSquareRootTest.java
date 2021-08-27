package com.github.nathandelane.experiments.maths.squareroots.babylonian;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.Assert.assertTrue;

public class BabylonianSquareRootTest {

  @Test
  public void testPerfectSquare() {
    final BigDecimal expected = BigDecimal.valueOf(10);
    expected.setScale(2, RoundingMode.FLOOR);

    final BigDecimal sqrt = BabylonianSquareRoot.sqrt(100, 2);

    assertTrue(sqrt.compareTo(expected) == 0);
  }

  @Test
  public void testQuareRootOfTwo() {
    final MathContext mathContext = new MathContext(3, RoundingMode.FLOOR);
    final BigDecimal expected = BigDecimal.valueOf(2).sqrt(mathContext);

    final BigDecimal sqrt = BabylonianSquareRoot.sqrt(2, 3);

    assertTrue(sqrt.compareTo(expected) == 0);
  }

  @Test
  public void testLargeNumber() {
    final MathContext mathContext = new MathContext(3, RoundingMode.FLOOR);
    final BigDecimal expected = BigDecimal.valueOf(391).sqrt(mathContext);

    final BigDecimal sqrt = BabylonianSquareRoot.sqrt(391, 3);

    assertTrue(sqrt.compareTo(expected) == 0);
  }

}
