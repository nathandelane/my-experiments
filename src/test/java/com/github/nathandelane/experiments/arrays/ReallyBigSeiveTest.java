package com.github.nathandelane.experiments.arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReallyBigSeiveTest {

  @Test
  public void createZeroElementSieve() {
    final ReallyBigSieve rbs = new ReallyBigSieve();

    assertNotNull(rbs);
    assertEquals(0L, rbs.size());
  }

  @Test
  public void createSmallSieve() {
    final long expectedSize = 1_000_000L;
    final ReallyBigSieve rbs = new ReallyBigSieve(expectedSize);

    assertNotNull(rbs);
    assertEquals(expectedSize, rbs.size());
  }

  /**
   * Probably don't have enough memory for this is my guess.
   */
  @Test
  public void createGiantSieve() {
    final long expectedSize = Long.valueOf(Integer.MAX_VALUE) + (Long.valueOf(Integer.MAX_VALUE) / 2L);

    final ReallyBigSieve rbs = new ReallyBigSieve(expectedSize);

    assertNotNull(rbs);
    assertEquals(expectedSize, rbs.size());
  }

}
