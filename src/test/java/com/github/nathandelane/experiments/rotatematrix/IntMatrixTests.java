package com.github.nathandelane.experiments.rotatematrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.Test;

public class IntMatrixTests {
  
  @Test
  public void testAssertEquals() {
    final IntMatrix m = new IntMatrix(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
    final IntMatrix expectedM = IntMatrices.matrixOf(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 3, 4);
    
    assertTrue(String.format("Expected 3, but got %s", m.numberOfRows), 3 == m.numberOfRows);
    assertTrue(String.format("Expected 4, but got %s", m.numberOfColumns), 4 == m.numberOfColumns);
    assertTrue(m.equals(expectedM));
  }
  
  @Test
  public void testAssertNotEquals() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } });
    
    assertFalse(m1.numberOfRows == m2.numberOfRows);
    assertFalse(m1.numberOfColumns == m2.numberOfColumns);
    assertFalse(m1.equals(m2));
  }
  
  @Test
  public void testMultiplyByIntScalar() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
    final int scalar = 5;
    final IntMatrix expectedM1 = new IntMatrix(new int[][] { { 5, 10, 15 }, { 20, 25, 30 } });
    
    final IntMatrix m2 = IntMatrices.multiply(m1, scalar);
    
    assertEquals(expectedM1, m2);
  }
  
  @Test
  public void testMultipleByDoubleScalar() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
    final double scalar = 1.5;
    final IntMatrix expectedM1 = new IntMatrix(new int[][] { { 1, 3, 4 }, { 6, 7, 9 } });
    
    final IntMatrix m2 = IntMatrices.multiply(m1, scalar);
    
    assertEquals(expectedM1, m2);
  }
  
  @Test
  public void testMultipleByDoubleScalarWithRoundingModeHalfUp() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
    final double scalar = 1.5;
    final IntMatrix expectedM1 = new IntMatrix(new int[][] { { 2, 3, 5 }, { 6, 8, 9 } });
    
    final IntMatrix m2 = IntMatrices.multiply(m1, scalar, RoundingMode.HALF_UP);
    
    assertEquals(expectedM1, m2);
  }
  
  @Test
  public void testMultipleByDoubleScalarWithRoundingModeHalfDown() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
    final double scalar = 1.5;
    final IntMatrix expectedM1 = new IntMatrix(new int[][] { { 1, 3, 4 }, { 6, 7, 9 } });
    
    final IntMatrix m2 = IntMatrices.multiply(m1, scalar, RoundingMode.HALF_DOWN);
    
    assertEquals(expectedM1, m2);
  }
  
  @Test
  public void testMultipliableMatricesSquare() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2 }, { 3, 4 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 5, 6 }, { 7, 8 } });
    
    assertTrue(IntMatrices.areMultipliable(m1, m2));
    assertTrue(IntMatrices.areMultipliable(m2, m1));
    assertTrue(IntMatrices.areMultipliable(m1, m1));
    assertTrue(IntMatrices.areMultipliable(m2, m2));
  }
  
  @Test
  public void testMultipliableMatricesDifferentDimsTrue() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2 }, { 3, 4}, { 5, 6 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 5, 6, 7 }, { 8, 9, 10 } });
    
    final boolean areMultipliable = IntMatrices.areMultipliable(m1, m2);
    
    assertTrue(areMultipliable);
  }
  
  @Test
  public void testMultipliableMatricesDifferentDimsFalse() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2 }, { 3, 4}, { 5, 6 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 5, 6, 7 }, { 8, 9, 10 }, { 11, 12, 13 } });
    
    final boolean areMultipliable = IntMatrices.areMultipliable(m1, m2);
    
    assertFalse(areMultipliable);
  }
  
  @Test
  public void testMultiplyTwoMatrices() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2 }, { 3, 4}, { 5, 6 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 5, 6, 7 }, { 8, 9, 10 } });
    final IntMatrix expectedResult = new IntMatrix(new int[][] { { 21, 24, 27 }, { 47, 54, 61 }, { 73, 84, 95 } });
    
    final IntMatrix result = IntMatrices.multiply(m1, m2);
    
    assertEquals(expectedResult, result);
  }
  
  @Test
  public void testAsTwoDimensionalArray() {
    final int[][] matrix = new int[][] { { 1, 2 }, { 3, 4}, { 5, 6 } };
    final IntMatrix m1 = new IntMatrix(matrix);
    
    assertTrue(Arrays.deepEquals(matrix, m1.asTwoDimensionalArray()));
  }
  
  @Test
  public void testAddableMatricesSameDimsTrue() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } });
    
    assertTrue(IntMatrices.areAddable(m1, m2));
  }
  
  @Test
  public void testAddTwoMatrices() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } });
    final IntMatrix expectedResult = new IntMatrix(new int[][] { { 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 10 } });
    
    final IntMatrix result = IntMatrices.add(m1, m2);
    
    assertEquals(expectedResult, result);
  }
  
  @Test
  public void testSubtractTwoMatrices() {
    final IntMatrix m1 = new IntMatrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
    final IntMatrix m2 = new IntMatrix(new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } });
    final IntMatrix expectedResult = new IntMatrix(new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } });
    
    final IntMatrix result = IntMatrices.subtract(m1, m2);
    
    assertEquals(expectedResult, result);
  }
  
  @Test
  public void testSwapRows() {
    final IntMatrix m = new IntMatrix(new int[][] { { -3, 2, 3, -2 }, { 3, 3, 1, 1 } });
    final IntMatrix expectedResult = new IntMatrix(new int[][] { { 3, 3, 1, 1 }, { -3, 2, 3, -2 } });
    
    final IntMatrix r = IntMatrices.swapRows(m, 0, 1);
    
    assertEquals(expectedResult, r);
  }

}
