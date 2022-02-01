package com.github.nathandelane.experiments.arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongInclusiveRangeTest {

  @Test
  public void testSingleElementRange() {
    final LongInclusiveRange r = new LongInclusiveRange(23L);

    assertEquals(r.getLow(), r.getHigh());
  }

  @Test
  public void testCreation() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertTrue(Long.compare(12L, r.getLow()) == 0);
    assertTrue(Long.compare(25L, r.getHigh()) == 0);
  }

  @Test
  public void testReversedCreation() {
    final LongInclusiveRange r = new LongInclusiveRange(25L, 12L);

    assertTrue(Long.compare(12L, r.getLow()) == 0);
    assertTrue(Long.compare(25L, r.getHigh()) == 0);
  }

  @Test
  public void testIsInRangeLongStart() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertTrue(r.isInRange(12L));
  }

  @Test
  public void testIsInRangeLongEnd() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertTrue(r.isInRange(25L));
  }

  @Test
  public void testIsInRangeLong() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertTrue(r.isInRange(20L));
  }

  @Test
  public void testNotIsInRange() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertFalse(r.isInRange(11L));
  }

  @Test
  public void testIsNextToRangeLow() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertTrue(r.isNextToRangeLow(11L));
  }

  @Test
  public void testIsNotNextToRangeLow() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertFalse(r.isNextToRangeLow(10L));
  }

  @Test
  public void testIsNextToRangeHigh() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertTrue(r.isNextToRangeHigh(26L));
  }

  @Test
  public void testIsNotNextToRangeHigh() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertFalse(r.isNextToRangeHigh(27L));
  }

  @Test
  public void testRangeIsInRangeSingleElement() {
    final LongInclusiveRange r1 = new LongInclusiveRange(-3L, -3L);
    final LongInclusiveRange r2 = new LongInclusiveRange(-3L, -3L);

    assertTrue(r1.isInRange(r2));
  }

  @Test
  public void testRangeIsNotInRangeSingleElement() {
    final LongInclusiveRange r1 = new LongInclusiveRange(-3L, -3L);
    final LongInclusiveRange r2 = new LongInclusiveRange(-4L, -4L);

    assertFalse(r1.isInRange(r2));
  }

  @Test
  public void testRangeIsInRange() {
    final LongInclusiveRange r1 = new LongInclusiveRange(-99L, 3_001L);
    final LongInclusiveRange r2 = new LongInclusiveRange(0, -10L);

    assertTrue(r1.isInRange(r2));
  }

  @Test
  public void testRangeIsNotInRange() {
    final LongInclusiveRange r1 = new LongInclusiveRange(-99L, 3_001L);
    final LongInclusiveRange r2 = new LongInclusiveRange(0, -100L);

    assertFalse(r1.isInRange(r2));
  }

}
