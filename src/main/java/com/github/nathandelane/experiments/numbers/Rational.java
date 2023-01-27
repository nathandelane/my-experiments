package com.github.nathandelane.experiments.numbers;

import java.math.BigDecimal;
import java.util.Map;

public class Rational {

  private static final Map<String, Rational> RATIONAL_CACHE = Map.ofEntries(
    Map.entry("0", new Rational(1, 1)),
    Map.entry("5", new Rational(1, 2)),
    Map.entry("3333333333333333", new Rational(1, 3)),
    Map.entry("25", new Rational(1, 4)),
    Map.entry("2", new Rational(1, 5)),
    Map.entry("16666666666666666", new Rational(1, 6)),
    Map.entry("14285714285714285", new Rational(1, 7)),
    Map.entry("125", new Rational(1, 8)),
    Map.entry("1111111111111111", new Rational(1, 9)),
    Map.entry("1", new Rational(1, 10)),
    Map.entry("09090909090909091", new Rational(1, 11)),
    Map.entry("08333333333333333", new Rational(1, 12)),
    Map.entry("07692307692307693", new Rational(1, 13)),
    Map.entry("07142857142857142", new Rational(1, 14)),
    Map.entry("06666666666666667", new Rational(1, 15)),
    Map.entry("0625", new Rational(1, 16)),
    Map.entry("058823529411764705", new Rational(1, 17)),
    Map.entry("05555555555555555", new Rational(1, 18)),
    Map.entry("05263157894736842", new Rational(1, 19)),
    Map.entry("05", new Rational(1, 20)),
    Map.entry("047619047619047616", new Rational(1, 21)),
    Map.entry("045454545454545456", new Rational(1, 22)),
    Map.entry("043478260869565216", new Rational(1, 23)),
    Map.entry("041666666666666664", new Rational(1, 24)),
    Map.entry("04", new Rational(1, 25)),
    Map.entry("038461538461538464", new Rational(1, 26)),
    Map.entry("037037037037037035", new Rational(1, 27)),
    Map.entry("03571428571428571", new Rational(1, 28)),
    Map.entry("034482758620689655", new Rational(1, 29)),
    Map.entry("03333333333333333", new Rational(1, 30)),
    Map.entry("03225806451612903", new Rational(1, 31)),
    Map.entry("03125", new Rational(1, 32)),
    Map.entry("030303030303030304", new Rational(1, 33)),
    Map.entry("029411764705882353", new Rational(1, 34)),
    Map.entry("02857142857142857", new Rational(1, 35)),
    Map.entry("027777777777777776", new Rational(1, 36)),
    Map.entry("02702702702702703", new Rational(1, 37)),
    Map.entry("02631578947368421", new Rational(1, 38)),
    Map.entry("02564102564102564", new Rational(1, 39)),
    Map.entry("025", new Rational(1, 40)),
    Map.entry("024390243902439025", new Rational(1, 41)),
    Map.entry("023809523809523808", new Rational(1, 42)),
    Map.entry("023255813953488372", new Rational(1, 43)),
    Map.entry("022727272727272728", new Rational(1, 44)),
    Map.entry("022222222222222223", new Rational(1, 45)),
    Map.entry("021739130434782608", new Rational(1, 46)),
    Map.entry("02127659574468085", new Rational(1, 47)),
    Map.entry("020833333333333332", new Rational(1, 48)),
    Map.entry("02040816326530612", new Rational(1, 49)),
    Map.entry("02", new Rational(1, 50)),
    Map.entry("0196078431372549", new Rational(1, 51)),
    Map.entry("019230769230769232", new Rational(1, 52)),
    Map.entry("018867924528301886", new Rational(1, 53)),
    Map.entry("018518518518518517", new Rational(1, 54)),
    Map.entry("01818181818181818", new Rational(1, 55)),
    Map.entry("017857142857142856", new Rational(1, 56)),
    Map.entry("017543859649122806", new Rational(1, 57)),
    Map.entry("017241379310344827", new Rational(1, 58)),
    Map.entry("01694915254237288", new Rational(1, 59)),
    Map.entry("016666666666666666", new Rational(1, 60)),
    Map.entry("01639344262295082", new Rational(1, 61)),
    Map.entry("016129032258064516", new Rational(1, 62)),
    Map.entry("015873015873015872", new Rational(1, 63)),
    Map.entry("015625", new Rational(1, 64)),
    Map.entry("015384615384615385", new Rational(1, 65)),
    Map.entry("015151515151515152", new Rational(1, 66)),
    Map.entry("014925373134328358", new Rational(1, 67)),
    Map.entry("014705882352941176", new Rational(1, 68)),
    Map.entry("014492753623188406", new Rational(1, 69)),
    Map.entry("014285714285714285", new Rational(1, 70)),
    Map.entry("014084507042253521", new Rational(1, 71)),
    Map.entry("013888888888888888", new Rational(1, 72)),
    Map.entry("0136986301369863", new Rational(1, 73)),
    Map.entry("013513513513513514", new Rational(1, 74)),
    Map.entry("013333333333333334", new Rational(1, 75)),
    Map.entry("013157894736842105", new Rational(1, 76)),
    Map.entry("012987012987012988", new Rational(1, 77)),
    Map.entry("01282051282051282", new Rational(1, 78)),
    Map.entry("012658227848101266", new Rational(1, 79)),
    Map.entry("0125", new Rational(1, 80)),
    Map.entry("012345679012345678", new Rational(1, 81)),
    Map.entry("012195121951219513", new Rational(1, 82)),
    Map.entry("012048192771084338", new Rational(1, 83)),
    Map.entry("011904761904761904", new Rational(1, 84)),
    Map.entry("011764705882352941", new Rational(1, 85)),
    Map.entry("011627906976744186", new Rational(1, 86)),
    Map.entry("011494252873563218", new Rational(1, 87)),
    Map.entry("011363636363636364", new Rational(1, 88)),
    Map.entry("011235955056179775", new Rational(1, 89)),
    Map.entry("011111111111111112", new Rational(1, 90)),
    Map.entry("01098901098901099", new Rational(1, 91)),
    Map.entry("010869565217391304", new Rational(1, 92)),
    Map.entry("010752688172043012", new Rational(1, 93)),
    Map.entry("010638297872340425", new Rational(1, 94)),
    Map.entry("010526315789473684", new Rational(1, 95)),
    Map.entry("010416666666666666", new Rational(1, 96)),
    Map.entry("010309278350515464", new Rational(1, 97)),
    Map.entry("01020408163265306", new Rational(1, 98)),
    Map.entry("010101010101010102", new Rational(1, 99)),
    Map.entry("01", new Rational(1, 100))
  );

  private final long numerator;

  private final long denominator;

  public Rational(final long numerator, final long denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public double asDouble() {
    final double doubleNumerator = Double.longBitsToDouble(numerator);
    final double doubleDenominator = Double.longBitsToDouble(denominator);

    return (doubleNumerator / doubleDenominator);
  }

  public Rational invert() {
    return new Rational(denominator, numerator);
  }

  /**
   * Add two Rational values.
   * @param other
   * @return
   */
  public Rational add(final Rational other) {
    if (other == null) throw new NullPointerException("Other Rational cannot be null.");

    final Rational newThis = this.reduce();
    final Rational newOther = other.reduce();

    final long lcm = leastCommonMultiple(newThis.denominator, newOther.denominator);
    final long leftNumerator = (newThis.numerator * (lcm / newThis.denominator));
    final long rightNumerator = (newOther.numerator * (lcm / newOther.denominator));
    final long numeratorSum = (leftNumerator + rightNumerator);

    final Rational result = new Rational(numeratorSum, lcm).reduce();

    return result;
  }

  public Rational subtract(final Rational other) {
    if (other == null) throw new NullPointerException("Other Rational cannot be null.");

    final Rational newThis = this.reduce();
    final Rational newOther = other.reduce();

    final long lcm = leastCommonMultiple(newThis.denominator, newOther.denominator);
    final long leftNumerator = (newThis.numerator * (lcm / newThis.denominator));
    final long rightNumerator = (newOther.numerator * (lcm / newOther.denominator));
    final long numeratorDifference = (leftNumerator - rightNumerator);

    final Rational result = new Rational(numeratorDifference, lcm).reduce();

    return result;
  }

  public Rational multiply(final Rational other) {
    if (other == null) throw new NullPointerException("Other Rational cannot be null.");

    final long newNumerator = (this.numerator * other.numerator);
    final long newDenominator = (this.denominator * other.denominator);

    final Rational result = new Rational(newNumerator, newDenominator).reduce();

    return result;
  }

  public Rational divide(final Rational other) {
    if (other == null) throw new NullPointerException("Other Rational cannot be null.");

    final Rational invertedOther = other.invert();

    final long newNumerator = (this.numerator * invertedOther.numerator);
    final long newDenominator = (this.denominator * invertedOther.denominator);

    final Rational result = new Rational(newNumerator, newDenominator).reduce();

    return result;
  }

  public Rational reduce() {
    final long gcf = greatestCommonFactor(numerator, denominator);
    final long newNumerator = (numerator / gcf);
    final long newDenominator = (denominator / gcf);

    return new Rational(newNumerator, newDenominator);
  }

  @Override
  public String toString() {
    return new StringBuilder()
      .append(numerator)
      .append("/")
      .append(denominator)
      .toString();
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;

    final Rational otherRational = ((Rational) other).reduce();
    final Rational thisRational = this.reduce();

    if (thisRational.numerator != otherRational.numerator) return false;
    return thisRational.denominator == otherRational.denominator;
  }

  @Override
  public int hashCode() {
    int result = (int) (numerator ^ (numerator >>> 32));
    result = 31 * result + (int) (denominator ^ (denominator >>> 32));
    return result;
  }

  public static Rational fromDouble(final double value) {
    final String[] parts = Double.toString(value).split("[\\.]{1}");
    final long wholePart = Long.parseLong(parts[0]);
    final String decimalPart = parts[1];
    final long longDecimalPart = Long.parseLong(decimalPart);

    if (longDecimalPart > 0) {
      final Rational fractionalPart = RATIONAL_CACHE.get(decimalPart);

      if (fractionalPart != null) {
        if (wholePart == 0) {
          return fractionalPart;
        }
        else {
          final long wholePartTimesDenominator = (wholePart * fractionalPart.denominator);

          return new Rational(wholePartTimesDenominator, fractionalPart.denominator);
        }
      }
      else {
        final int lengthOfDecimalPart = decimalPart.length();
        final long denominator = Math.round(Math.pow(10.0, (double) lengthOfDecimalPart));

        return new Rational(longDecimalPart, denominator);
      }
    }

    return new Rational(wholePart, 1);
  }

  public static Rational fromLong(final long value) {
    return new Rational(value, 1);
  }

  static long greatestCommonFactor(final long left, final long right) {
    long first = Long.compare(left, right) < 0 ? right : left;
    long second = Long.compare(left, right) < 0 ? left : right;
    long remainder = 0L;

    while (true) {
      remainder = (first % second);

      if (remainder == 0) break;

      first = second;
      second = remainder;
    }

    return second;
  }

  static long leastCommonMultiple(final long left, final long right) {
    return ((left * right) / (greatestCommonFactor(left, right)));
  }

}
