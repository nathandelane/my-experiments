package com.github.nathandelane.experiments.maths.squareroots.babylonian;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

public class BabylonianSquareRoot {

  /**
   * Calculate a square root of a number using the Babylonian method.
   * @param s number to get the square root of
   * @param precision number of decimal places
   */
  public static BigDecimal sqrt(final int s, final int precision) {
    final RoundingMode roundingMode = RoundingMode.FLOOR;
    final BigDecimal oneHalf = BigDecimal.valueOf(0.5);
    final BigDecimal two = BigDecimal.valueOf(2);
    final MathContext mathContext = new MathContext(precision, roundingMode);
    final BigDecimal bigDecimalS = BigDecimal.valueOf(s);
    bigDecimalS.setScale(precision, roundingMode);

    final BigDecimal root0 = bigDecimalS.divide(two, precision, roundingMode);
    BigDecimal rootN = oneHalf.multiply(root0.add(bigDecimalS.divide(root0, precision, roundingMode)), mathContext);
    BigDecimal rootNLast = new BigDecimal(rootN.toString());

    while (true) {
      rootN = oneHalf.multiply(rootNLast.add(bigDecimalS.divide(rootNLast, precision, roundingMode)), mathContext);

      if (rootNLast.compareTo(rootN) != 0) {
        rootNLast = new BigDecimal(rootN.toString());
      }
      else {
        break;
      }
    }

    return rootNLast;
  }

}
