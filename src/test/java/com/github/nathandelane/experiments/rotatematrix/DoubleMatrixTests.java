package com.github.nathandelane.experiments.rotatematrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class DoubleMatrixTests {

  @Test
  public void testAssertEquals() {
    final DoubleMatrix m = new DoubleMatrix(new double[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
    final DoubleMatrix expectedM = DoubleMatrices.matrixOf(new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 3, 4);
    
    assertTrue(String.format("Expected 3, but got %s", m.numberOfRows), 3 == m.numberOfRows);
    assertTrue(String.format("Expected 4, but got %s", m.numberOfColumns), 4 == m.numberOfColumns);
    assertTrue(m.equals(expectedM));
  }
  
  @Test
  public void testAssertNotEquals() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } });
    
    assertFalse(m1.numberOfRows == m2.numberOfRows);
    assertFalse(m1.numberOfColumns == m2.numberOfColumns);
    assertFalse(m1.equals(m2));
  }
  
  @Test
  public void testMultiplyByIntScalar() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
    final int scalar = 5;
    final DoubleMatrix expectedM1 = new DoubleMatrix(new double[][] { { 5, 10, 15 }, { 20, 25, 30 } });
    
    final DoubleMatrix m2 = DoubleMatrices.multiply(m1, scalar);
    
    assertEquals(expectedM1, m2);
  }
  
  @Test
  public void testMultipleByDoubleScalar() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
    final double scalar = 1.5;
    final DoubleMatrix expectedM1 = new DoubleMatrix(new double[][] { { 1, 3, 4 }, { 6, 7, 9 } });
    
    final DoubleMatrix m2 = DoubleMatrices.multiply(m1, scalar);
    
    assertEquals(expectedM1, m2);
  }
  
  @Test
  public void testMultipliableMatricesSquare() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2 }, { 3, 4 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 5, 6 }, { 7, 8 } });
    
    assertTrue(DoubleMatrices.areMultipliable(m1, m2));
    assertTrue(DoubleMatrices.areMultipliable(m2, m1));
    assertTrue(DoubleMatrices.areMultipliable(m1, m1));
    assertTrue(DoubleMatrices.areMultipliable(m2, m2));
  }
  
  @Test
  public void testMultipliableMatricesDifferentDimsTrue() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2 }, { 3, 4}, { 5, 6 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 5, 6, 7 }, { 8, 9, 10 } });
    
    final boolean areMultipliable = DoubleMatrices.areMultipliable(m1, m2);
    
    assertTrue(areMultipliable);
  }
  
  @Test
  public void testMultipliableMatricesDifferentDimsFalse() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2 }, { 3, 4}, { 5, 6 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 5, 6, 7 }, { 8, 9, 10 }, { 11, 12, 13 } });
    
    final boolean areMultipliable = DoubleMatrices.areMultipliable(m1, m2);
    
    assertFalse(areMultipliable);
  }
  
  @Test
  public void testMultiplyTwoMatrices() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2 }, { 3, 4}, { 5, 6 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 5, 6, 7 }, { 8, 9, 10 } });
    final DoubleMatrix expectedResult = new DoubleMatrix(new double[][] { { 21, 24, 27 }, { 47, 54, 61 }, { 73, 84, 95 } });
    
    final DoubleMatrix result = DoubleMatrices.multiply(m1, m2);
    
    assertEquals(expectedResult, result);
  }
  
  @Test
  public void testAsTwoDimensionalArray() {
    final double[][] matrix = new double[][] { { 1, 2 }, { 3, 4}, { 5, 6 } };
    final DoubleMatrix m1 = new DoubleMatrix(matrix);
    
    assertTrue(Arrays.deepEquals(matrix, m1.asTwoDimensionalArray()));
  }
  
  @Test
  public void testAddableMatricesSameDimsTrue() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } });
    
    assertTrue(DoubleMatrices.areAddable(m1, m2));
  }
  
  @Test
  public void testAddTwoMatrices() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } });
    final DoubleMatrix expectedResult = new DoubleMatrix(new double[][] { { 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 10 } });
    
    final DoubleMatrix result = DoubleMatrices.add(m1, m2);
    
    assertEquals(expectedResult, result);
  }
  
  @Test
  public void testSubtractTwoMatrices() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } });
    final DoubleMatrix expectedResult = new DoubleMatrix(new double[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } });
    
    final DoubleMatrix result = DoubleMatrices.subtract(m1, m2);
    
    assertEquals(expectedResult, result);
  }
  
  @Test
  public void testSwapRows() {
    final DoubleMatrix m = new DoubleMatrix(new double[][] { { -3, 2, 3, -2 }, { 3, 3, 1, 1 } });
    final DoubleMatrix expectedResult = new DoubleMatrix(new double[][] { { 3, 3, 1, 1 }, { -3, 2, 3, -2 } });
    
    final DoubleMatrix r = DoubleMatrices.swapRows(m, 0, 1);
    
    assertEquals(expectedResult, r);
  }

}
