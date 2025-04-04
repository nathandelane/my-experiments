package com.github.nathandelane.experiments.misc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {

  @Test
  public void test() {
    final Integer maxCoins = Solution.maxCoins(8, new int[][] {{11,29,7},{11,25,7},{12,24,3},{10,25,6},{13,23,10},{13,26,1},{17,17,3},{16,20,1}});

    assertEquals(Integer.valueOf(10), maxCoins);
  }

  @Test
  public void test22() {
    final int[][] arr = new int[][] {{1, 3, 4}, {2, 3, 5}, {3, 4, 2}};
    final int n = 3;
    final Integer maxCoins = Solution.maxCoins(n, arr);

    assertEquals(Integer.valueOf(7), maxCoins);
  }

  @Test
  public void test3() {
    final Integer maxCoins = Solution.maxCoins(7, new int[][] {{12,13,1},{16,19,2},{16,16,8},{10,19,1},{12,29,4},{13,22,2},{16,16,9}});

    assertEquals(Integer.valueOf(17), maxCoins);
  }

  @Test
  public void test4() {
    final Integer maxCoins = Solution.maxCoins(7, new int[][] {{17,26,3},{15,18,10},{16,26,3},{18,19,5},{15,15,10},{12,29,9},{10,25,10}});

    assertEquals(Integer.valueOf(20), maxCoins);
  }

}
