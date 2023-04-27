package com.github.nathandelane.experiments.regexp;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CsvMatchTest {

  @Test
  public void test() {
    final String csv = "12234,3456,91022";

    assertTrue(CsvMatch.matches(csv, 91022));
    assertTrue(CsvMatch.matches(csv, 3456));
    assertTrue(CsvMatch.matches(csv, 12234));
    assertFalse(CsvMatch.matches(csv, 12));
    assertFalse(CsvMatch.matches(csv, 22));
    assertFalse(CsvMatch.matches(csv, 45));
    assertFalse(CsvMatch.matches(csv, 56));
  }

}
