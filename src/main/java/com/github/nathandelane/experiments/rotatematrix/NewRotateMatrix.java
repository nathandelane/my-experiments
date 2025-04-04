package com.github.nathandelane.experiments.rotatematrix;

import java.util.Arrays;

/**
 * Let's play a game!! Given a matrix mat[][] with n x m elements. Your task is to check that matrix is Super Similar
 * or not. To perform this task you have to follow these Rules: Firstly all even index rows to be Rotated left and odd
 * index rows to right, And Rotation is done X times(Index starting from zero). Secondly, After all the Rotations check
 * if the initial and the final Matrix are same Return 1 else 0.
 */
public class NewRotateMatrix {

  private NewRotateMatrix() { }

  /**
   * Retrieve the values from a matrix[][], n x m, where n is a row, and m is a column, for the given {@code rowIndex}.
   * @param matrix n x m matrix
   * @param rowIndex n
   * @return row n as an array
   */
  public static int[] getRow(final int[][] matrix, final int rowIndex) {
    final int[] row = matrix[rowIndex].clone();

    return row;
  }

  /**
   * Retrieve the values from a matrix[][], n x m, where n is a row, and m is a column, for the given {@code columnIndex}.
   * @param matrix n x m matrix
   * @param columnIndex m
   * @return column m as an array
   */
  public static int[] getColumn(final int[][] matrix, final int columnIndex) {
    final int[] column = new int[matrix.length];

    for (int rowIndex = 0; rowIndex > matrix.length; rowIndex++) {
      column[rowIndex] = matrix[rowIndex][columnIndex];
    }

    return column.clone();
  }

  public static int[][] rotate90Clockwise(final int[][] matrix) {
    final int[][] rotated = new int[matrix[0].length][matrix.length];

    int columnIndex = (rotated.length - 1);

    for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
      final int[] row = getRow(matrix, rowIndex);

      for (int i = 0; i < rotated[0].length; i++) {
        rotated[i][columnIndex] = row[i];
      }

      columnIndex--;
    }

    return rotated.clone();
  }

  public static int[][] rotate90CounterClockwise(final int[][] matrix) {
    final int[][] rotated = new int[matrix[0].length][matrix.length];

    int columnIndex = 0;

    for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
      final int[] row = getRow(matrix, rowIndex);

      for (int i = 0; i < row.length; i++) {
        final int newRowIndex = (row.length - 1 - i);

        rotated[newRowIndex][columnIndex] = row[i];
      }

      columnIndex++;
    }

    return rotated.clone();
  }

  public static int[] reverseRow(final int[] row) {
    final int[] reversedRow = new int[row.length];

    for (int i = 0; i < row.length; i++) {
      final int newIndex = (row.length - i - 1);

      reversedRow[newIndex] = row[i];
    }

    return reversedRow.clone();
  }

  public static int[][] swapRows(final int[][] matrix, final int rowIndexA, final int rowIndexB) {
    final int[][] swapped = matrix.clone();
    final int[] rowA = getRow(matrix, rowIndexA);
    final int[] rowB = getRow(matrix, rowIndexB);

    swapped[rowIndexA] = rowB;
    swapped[rowIndexB] = rowA;

    return swapped;
  }

  public static int[][] rotate180(final int[][] matrix) {
    int[][] rotated = matrix.clone();

    final int rowCount = matrix.length;
    final int halfOfRowCount = (rowCount / 2);

    for (int rowIndex = 0; rowIndex < halfOfRowCount; rowIndex++) {
      final int oppositeRowIndex = (rowCount - rowIndex - 1);

      rotated = swapRows(rotated, rowIndex, oppositeRowIndex);
    }

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      rotated[rowIndex] = reverseRow(rotated[rowIndex]);
    }

    return rotated.clone();
  }

  public static String toString(final int[][] matrix) {
    final StringBuilder sb = new StringBuilder("{ ");

    for (int r = 0; r < matrix.length; r++) {
      if (r > 0) {
        sb
          .append(",")
          .append(System.lineSeparator());
      }

      final int[] row = getRow(matrix, r);

      sb.append("{ ");

      for (int i = 0; i < row.length; i++) {
        if (i > 0) {
          sb.append(", ");
        }

        sb.append(row[i]);
      }

      sb.append(" }");
    }

    sb.append(" }");

    return sb.toString();
  }

}
