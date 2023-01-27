package com.github.nathandelane.experiments.numbers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RationalsTests {

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
  public void testFromDoubleWholeNumber() {
    final double d = 24.0;
    final Rational r = Rational.fromDouble(d);
    final Rational expected = new Rational(24, 1);

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

}
