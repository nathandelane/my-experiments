package com.github.nathandelane.experiments.interview;

/**
 * Given an n x m array where all rows and columns are in sorted order, write a function to determine whether the array contains an element x.
 */
public class MatrixSearch {

  private static boolean contains(final int[][] array, final int n, final int m, final int x) {
    boolean xIsFound = false;

    if (array != null) {
      while (true) {
        if (n == 1 && m == 1 && array[0][0] == x) break;
        if (x < array[0][0] || x > array[n-1][m-1]) break;

        for (int i = 0; i < n && !xIsFound; i++) {
          if (array[i][0] <= x && array[i][m-1] > x) {
            for (int j = 0; j < m && !xIsFound; j++) {
              xIsFound = (array[i][j] == x);
            }
          }
        }

        break;
      }
    }

    return xIsFound;
  }

  private static String arrayToString(final int[][] array, final int n, final int m) {
    final StringBuilder sb = new StringBuilder("[ ");

    for (int i = 0; i < 3; i++) {
      if (i > 0) sb.append(", ");

      sb.append("{ ");

      for (int j = 0; j < m; j++) {
        if (j > 0) sb.append(", ");

        sb.append(array[i][j]);
      }

      sb.append(" }");
    }

    return sb.append(" ]").toString();
  }

  public static void main(final String[] args) {
    final int[][] array = new int[][] {
      { 1, 2, 3, 4},
      { 5, 6, 7, 8},
      { 9, 10, 11, 12}
    };
    final int n = 3;
    final int m = 4;
    final int x = 13;

    final boolean xExists = contains(array, n, m, x);

    System.out.format("Search for %d in %s is %s%n", x, arrayToString(array, n, m), xExists);
  }

}
