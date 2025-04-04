package com.github.nathandelane.experiments.numbers;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Longs {

  @Test
  public void testStreamEquals() {
    final List<Long> listOfLongs = Arrays.asList(new Long[] { 1234567L, 2345678L, 3456778L, 128L, 999_999_999L });
    final long search =  999_999_999;

    assertTrue(listOfLongs.stream().anyMatch(x -> x == search));
  }

}
