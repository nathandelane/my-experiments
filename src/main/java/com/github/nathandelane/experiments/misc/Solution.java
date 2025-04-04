package com.github.nathandelane.experiments.misc;

import java.util.Comparator;

class Solution {

  private static final Comparator<int[]> COMPARE_INT_ARRAYS_BY_FIRST_TWO = new Comparator<int[]>() {
    @Override
    public int compare(int[] o1, int[] o2) {
      final int resultOf0 = Integer.compare(o1[0], o2[0]);
      final int resultOf1 = Integer.compare(o1[1], o2[1]);

      final int result;

      if (resultOf0 == 0) {
        result = resultOf1;
      }
      else {
        result = resultOf0;
      }

      return result;
    }
  };

  public static int maxCoins(int n,int ranges[][])
  {
    final int START_INDEX = 0;
    final int END_INDEX = 1;
    final int COINS_INDEX = 2;

    int maxCoins = 0;

    java.util.Arrays.sort(ranges, COMPARE_INT_ARRAYS_BY_FIRST_TWO);

    for (int tupleIndex = 0; tupleIndex < ranges.length; tupleIndex++) {
      int intervals = 1;
      int amount = ranges[tupleIndex][COINS_INDEX];

      for (int compareIndex = (tupleIndex + 1); compareIndex < ranges.length; compareIndex++) {
        if (ranges[tupleIndex][END_INDEX] > ranges[compareIndex][START_INDEX]) continue;

        amount += ranges[compareIndex][COINS_INDEX];
        intervals++;

        if (intervals == 2) {
          if (amount > maxCoins) maxCoins = amount;

          intervals = 1;
          amount = ranges[tupleIndex][COINS_INDEX];
        }
      }

      if (amount > maxCoins) maxCoins = amount;
    }

    return maxCoins;
  }
}
