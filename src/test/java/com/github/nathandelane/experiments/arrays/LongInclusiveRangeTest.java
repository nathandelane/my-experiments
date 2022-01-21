package com.github.nathandelane.experiments.arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongInclusiveRangeTest {

  @Test
  public void testSingleElementRange() {
    final LongInclusiveRange r = new LongInclusiveRange(23L);

    assertEquals(r.getStart(), r.getEnd());
  }

  @Test
  public void testCreation() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertEquals(12L, r.getStart());
    assertEquals(25L, r.getEnd());
  }

  @Test
  public void testReversedCreation() {
    final LongInclusiveRange r = new LongInclusiveRange(25L, 12L);

    assertEquals(12L, r.getStart());
    assertEquals(25L, r.getEnd());
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
  public void testIsNextToRangeStart() {
    final LongInclusiveRange r = new LongInclusiveRange(12L, 25L);

    assertTrue(r.isNextToRangeStart(11L));
  }

}
