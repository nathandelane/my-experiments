package com.github.nathandelane.experiments.binary;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConvertTests {

  @Test
  public void testSomething() {
    final int left = -30;
    final int right = 30;
    final int sum = (left + right);
    final int result = ((left - 1) & right); // because of 2's compliment

    System.out.format("%d: %s%n%d: %s%nMAX: %s%nMIN: %s%n",
      left, Integer.toBinaryString(left),
      right, Integer.toBinaryString(right),
      Integer.toBinaryString(Integer.MAX_VALUE),
      Integer.toBinaryString(Integer.MIN_VALUE));

    assertTrue(Integer.compare(sum, result) == 0);
  }

}
