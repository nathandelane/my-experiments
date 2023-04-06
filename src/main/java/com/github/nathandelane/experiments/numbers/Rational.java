package com.github.nathandelane.experiments.numbers;

import java.util.Map;

public class Rational extends Number implements Comparable<Rational> {

  private static final Map<String, Rational> DECIMAL_TO_RATIONAL_CACHE = Map.ofEntries(
    Map.entry("5", new Rational(1, 2)),
    Map.entry("3333333333333333", new Rational(1, 3)),
    Map.entry("25", new Rational(1, 4)),
    Map.entry("2", new Rational(1, 5)),
    Map.entry("16666666666666666", new Rational(1, 6)),
    Map.entry("1666666666666667", new Rational(1, 6)),
    Map.entry("14285714285714285", new Rational(1, 7)),
    Map.entry("1428571428571428", new Rational(1, 7)),
    Map.entry("125", new Rational(1, 8)),
    Map.entry("1111111111111111", new Rational(1, 9)),
    Map.entry("111111111111111", new Rational(1, 9)),
    Map.entry("1", new Rational(1, 10)),
    Map.entry("6666666666666666", new Rational(2, 3)),
    Map.entry("666666666666667", new Rational(2, 3)),
    Map.entry("4", new Rational(2, 5)),
    Map.entry("2857142857142857", new Rational(2, 7)),
    Map.entry("285714285714285", new Rational(2, 7)),
    Map.entry("2222222222222222", new Rational(2, 9)),
    Map.entry("222222222222222", new Rational(2, 9)),
    Map.entry("75", new Rational(3, 4)),
    Map.entry("6", new Rational(3, 5)),
    Map.entry("42857142857142855", new Rational(3, 7)),
    Map.entry("4285714285714285", new Rational(3, 7)),
    Map.entry("375", new Rational(3, 8)),
    Map.entry("3", new Rational(3, 10)),
    Map.entry("8", new Rational(4, 5)),
    Map.entry("5714285714285714", new Rational(4, 7)),
    Map.entry("571428571428571", new Rational(4, 7)),
    Map.entry("4444444444444444", new Rational(4, 9)),
    Map.entry("444444444444444", new Rational(4, 9)),
    Map.entry("8333333333333334", new Rational(5, 6)),
    Map.entry("833333333333333", new Rational(5, 6)),
    Map.entry("7142857142857143", new Rational(5, 7)),
    Map.entry("714285714285714", new Rational(5, 7)),
    Map.entry("625", new Rational(5, 8)),
    Map.entry("5555555555555555", new Rational(5, 9)),
    Map.entry("555555555555556", new Rational(5, 9)),
    Map.entry("8571428571428571", new Rational(6, 7)),
    Map.entry("875", new Rational(7, 8)),
    Map.entry("7777777777777777", new Rational(7, 9)),
    Map.entry("777777777777778", new Rational(7, 9)),
    Map.entry("7", new Rational(7, 10)),
    Map.entry("8888888888888888", new Rational(8, 9)),
    Map.entry("888888888888889", new Rational(8, 9)),
    Map.entry("9", new Rational(9, 10))
  );

  private final long numerator;

  private final long denominator;

  public Rational(final long numerator, final long denominator) {
    if (denominator == 0) throw new ArithmeticException("Division by zero not allowed.");

    if (denominator < 0) {
      this.denominator = (denominator * -1);
      this.numerator = (numerator * -1);
    }
    else {
      this.denominator = denominator;
      this.numerator = numerator;
    }
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

  public boolean isNegative() {
    return (Math.signum(numerator) * Math.signum(denominator)) < 0;
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
    final String valueAsString = Double.toString(value);
    final int indexOfDecimal = valueAsString.indexOf('.');
    final String wholePartAsString = valueAsString.substring(0, indexOfDecimal);
    final long wholePart = Long.parseLong(wholePartAsString);
    final String decimalPartAsString = valueAsString.substring(indexOfDecimal + 1);
    final long decimalPart = Long.parseLong(decimalPartAsString);

    if (decimalPart > 0) {
      final Rational fractionalPart = DECIMAL_TO_RATIONAL_CACHE.get(decimalPartAsString);

      if (fractionalPart != null) {
        if (wholePart == 0) {
          return fractionalPart;
        }
        else {
          final long wholePartTimesDenominator = (wholePart * fractionalPart.denominator);
          final Rational wholePartAsRational = new Rational(wholePartTimesDenominator, fractionalPart.denominator);

          return wholePartAsRational.add(fractionalPart);
        }
      }
      else {
        final int lengthOfDecimalPart = decimalPartAsString.length();
        final long denominator = Math.round(Math.pow(10.0, (double) lengthOfDecimalPart));
        final long wholePartTimesDenominator = (wholePart * denominator);
        final Rational wholePartAsRational = new Rational(wholePartTimesDenominator, denominator);
        final Rational decimalPartAsRational = new Rational(decimalPart, denominator).reduce();

        return wholePartAsRational.add(decimalPartAsRational).reduce();
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

  @Override
  public int intValue() {
    return (int) longValue();
  }

  @Override
  public long longValue() {
    return (long) doubleValue();
  }

  @Override
  public float floatValue() {
    return (float) doubleValue();
  }

  @Override
  public double doubleValue() {
    final double doubleNumerator = Double.longBitsToDouble(numerator);
    final double doubleDenominator = Double.longBitsToDouble(denominator);

    return (doubleNumerator / doubleDenominator);
  }

  @Override
  public int compareTo(final Rational other) {
    if (this.equals(other)) return 0;
    if ((this.subtract(other)).isNegative()) return -1;

    return 1;
  }
}
