package com.github.nathandelane.experiments.arrays;

import org.junit.Test;

import java.util.stream.IntStream;

public class LoopsTest {

  @Test
  public void testForeach() {
    final int quantity = 4;

    IntStream.rangeClosed(1, quantity)
      .forEach(i -> {
        System.out.println("I don't really care about the value i.");
      });
  }

}
