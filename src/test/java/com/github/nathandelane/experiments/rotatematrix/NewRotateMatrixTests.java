package com.github.nathandelane.experiments.rotatematrix;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class NewRotateMatrixTests {

  @Test
  public void testRotate3x3By90DegreesClockwise() {
    final int[][] matrix = new int[][] {
      { 1, 2, 3 },
      { 4, 5, 6 },
      { 7, 8, 9 }
    };
    final int[][] rotated90Clockwise = NewRotateMatrix.rotate90Clockwise(matrix);
    final int[][] expected = new int[][] {
      { 7, 4, 1 },
      { 8, 5, 2 },
      { 9, 6, 3 }
    };

    assertTrue(
      String.format("Expected:%n%n%s%n%nBut got:%n%n%s%n", NewRotateMatrix.toString(expected), NewRotateMatrix.toString(rotated90Clockwise)),
      Arrays.deepEquals(expected, rotated90Clockwise)
    );
  }

  @Test
  public void testRotate3x3By90DegreesCounterClockwise() {
    final int[][] matrix = new int[][] {
      { 1, 2, 3 },
      { 4, 5, 6 },
      { 7, 8, 9 }
    };
    final int[][] rotated90CounterClockwise = NewRotateMatrix.rotate90CounterClockwise(matrix);
    final int[][] expected = new int[][] {
      { 3, 6, 9 },
      { 2, 5, 8 },
      { 1, 4, 7 }
    };

    assertTrue(
      String.format("Expected:%n%n%s%n%nBut got:%n%n%s%n", NewRotateMatrix.toString(expected), NewRotateMatrix.toString(rotated90CounterClockwise)),
      Arrays.deepEquals(expected, rotated90CounterClockwise)
    );
  }

  @Test
  public void testRotate3x3By180Degrees() {
    final int[][] matrix = new int[][] {
      { 1, 2, 3 },
      { 4, 5, 6 },
      { 7, 8, 9 }
    };
    final int[][] rotated180Clockwise = NewRotateMatrix.rotate180(matrix);
    final int[][] expected = new int[][] {
      { 9, 8, 7 },
      { 6, 5, 4 },
      { 3, 2, 1 }
    };

    assertTrue(
      String.format("Expected:%n%n%s%n%nBut got:%n%n%s%n", NewRotateMatrix.toString(expected), NewRotateMatrix.toString(rotated180Clockwise)),
      Arrays.deepEquals(expected, rotated180Clockwise)
    );
  }

  @Test
  public void testRotate2x4CounterClockwise() {
    final int[][] matrix = new int[][] {
      { 1, 2, 1, 2 },
      { 2, 1, 2, 1 }
    };
    final int[][] rotated90CounterClockwise = NewRotateMatrix.rotate90CounterClockwise(matrix);
    final int[][] expected = new int[][] {
      { 2, 1 },
      { 1, 2 },
      { 2, 1 },
      { 1, 2 }
    };

    assertTrue(
      String.format("Expected:%n%n%s%n%nBut got:%n%n%s%n", NewRotateMatrix.toString(expected), NewRotateMatrix.toString(rotated90CounterClockwise)),
      Arrays.deepEquals(expected, rotated90CounterClockwise)
    );
  }

}
