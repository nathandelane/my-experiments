package com.github.nathandelane.experiments.numbers;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RationalsTests {

  @Test
  public void testConstructor1() {
    final Rational r = new Rational(3, -4);
    final Rational expected = new Rational(-3, 4);

    assertEquals(expected, r);
  }

  @Test
  public void testConstructor2() {
    final Rational r = new Rational(-3, -4);
    final Rational expected = new Rational(3, 4);

    assertEquals(expected, r);
  }

  @Test(expected = ArithmeticException.class)
  public void testConstructorZeroInDivisor() {
    final Rational r = new Rational(1, 0);
  }

  @Test
  public void testFromDouble() {
    final double d = 25.0 / 100.0;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(1, 4);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testFromDouble2() {
    final double d = 50.0 / 100.0;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(1, 2);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testFromDouble3() {
    final double d = 1.0 / 7.0;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(1, 7);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testFromDouble4() {
    final double d = 4.0 / 5.0;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(4, 5);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testFromDouble5() {
    final double d = 3.1415;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(6283, 2000);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testFromDouble6() {
    final double d = 1.0 + (1.0 / 6.0);
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(7, 6);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testFromDoubleWholeNumber() {
    final double d = 24.0;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(24, 1);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testFromDoubleWholeAndDecimal() {
    final double d = 3.125;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(25, 8);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testAdd1() {
    final Rational r1 = Rational.fromLong(2);
    final Rational r2 = Rational.fromLong(3);
    final Rational r = r1.add(r2);
    final Rational expected = new Rational(5, 1);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testAdd2() {
    final Rational r1 = new Rational(1, 3);
    final Rational r2 = new Rational(2, 3);
    final Rational r = r1.add(r2);
    final Rational expected = new Rational(1, 1);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testAdd3() {
    final Rational r1 = new Rational(1, 4);
    final Rational r2 = new Rational(2, 4);
    final Rational r = r1.add(r2);
    final Rational expected = new Rational(3, 4);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testAdd4() {
    final Rational r1 = new Rational(1, 3);
    final Rational r2 = new Rational(2, 6);
    final Rational r = r1.add(r2);
    final Rational expected = new Rational(2, 3);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testSubtract1() {
    final Rational r1 = Rational.fromLong(1);
    final Rational r2 = new Rational(1, 3);
    final Rational r = r1.subtract(r2);
    final Rational expected = new Rational(2, 3);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testSubtract2() {
    final Rational r1 = Rational.fromLong(2);
    final Rational r2 = new Rational(5, 6);
    final Rational r = r1.subtract(r2);
    final Rational expected = new Rational(7, 6);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testDivide1() {
    final Rational r1 = new Rational(3, 4);
    final Rational r2 = new Rational(1, 2);
    final Rational r = r1.divide(r2);
    final Rational expected = new Rational(3, 2);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testDivide2() {
    final Rational r1 = Rational.fromLong(4);
    final Rational r2 = new Rational(3, 4);
    final Rational r = r1.divide(r2);
    final Rational expected = new Rational(16, 3);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testDivide3() {
    final Rational r1 = new Rational(1, 2);
    final Rational r2 = new Rational(1, 4);
    final Rational r = r1.divide(r2);
    final Rational expected = new Rational(2, 1);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testMultiply1() {
    final Rational r1 = new Rational(3, 5);
    final Rational r2 = new Rational(1, 2);
    final Rational r = r1.multiply(r2);
    final Rational expected = new Rational(3, 10);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testMultiply2() {
    final Rational r1 = Rational.fromLong(22);
    final Rational r2 = new Rational(1, 3);
    final Rational r = r1.multiply(r2);
    final Rational expected = new Rational(22, 3);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testMultiply3() {
    final Rational r1 = new Rational(1, 2);
    final Rational r2 = new Rational(1, 4);
    final Rational r = r1.multiply(r2);
    final Rational expected = new Rational(1, 8);

    assertNotNull(r);
    assertEquals(expected, r);
  }

  @Test
  public void testAsDouble1() {
    final Rational r = new Rational(3, 4);
    final double d = r.asDouble();
    final double expected = 0.75;

    assertTrue(Double.compare(expected, d) == 0);
  }

  @Test
  public void testAsDouble2() {
    final Rational r = new Rational(3, 3);
    final double d = r.asDouble();
    final double expected = 1.0;

    assertTrue(Double.compare(expected, d) == 0);
  }

  @Test
  public void testToString1() {
    final Rational r = new Rational(3, 4);
    final String s = r.toString();
    final String expected = "3/4";

    assertEquals(expected, s);
  }

  @Ignore
  @Test
  public void createCache() {
    final Set<String> keys = new HashSet<>();

    int count = 0;

    for (int i = 1; i <= 10; i++) {
      for (int j = 1; j <= 10; j++) {
        if (i > j) continue;

        final double resultOfDivision = ((double) i / (double) j);
        final String resultAsString = Double.toString(resultOfDivision);
        final int indexOfDecimal = resultAsString.indexOf('.');
        final String decimalPart = resultAsString.substring(indexOfDecimal + 1);

        if (decimalPart.equals("0")) continue;
        if (keys.contains(decimalPart)) continue;

        System.out.format(
          "Map.entry(\"%s\", new Rational(%d, %d)),%n",
          decimalPart, i, j);

        keys.add(decimalPart);
        count++;
      }
    }

    System.out.format("%nCalculated %d entries.%n", count);
  }

}
