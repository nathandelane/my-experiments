package com.github.nathandelane.experiments.rotatematrix;

import java.util.Random;

import org.junit.Test;

public class MatrixPerformanceTests {

  private final Random random;
  
  public MatrixPerformanceTests() {
    random = new Random();
  }

  @Test
  public void testBigMatrixMultiplication() {
    final int max = 3000;
    
    System.out.format("Max=%s%n", max);
    
    for (int squareDim = 2; squareDim <= max; squareDim += (squareDim * 2)) {
      System.out.format("Next=%s%n", squareDim);
      
      final DoubleMatrix m1 = createMatrix(squareDim);
      final DoubleMatrix m2 = createMatrix(squareDim);
      
      final long startMillis = System.currentTimeMillis();
      
      DoubleMatrices.multiply(m1, m2);
      
      final long endMillis = System.currentTimeMillis();
      final long totalMillis = (endMillis - startMillis);
      
      System.out.format("%s - Total time: millis=%s, seconds=%s, minutes=%s%n", squareDim, totalMillis, (((double) totalMillis) / 1000d), ((((double) totalMillis) / 1000d) / 60d));
    }
  }
  
  private DoubleMatrix createMatrix(final int dim) {
    final double[][] result = new double[dim][dim];
    
    for (int row = 0; row < result.length; row++) {
        for (int col = 0; col < result[row].length; col++) {
            result[row][col] = random.nextDouble();
        }
    }
    
    return new DoubleMatrix(result);
  }
  
  private int power(final int base, final int n) {
    int value;
    
    if (n == 0) {
      value = 1;
    }
    else {
      value = base;
      
      for (int next = 1; next < n; next++) {
        value *= base;
      }
    }
    
    return value;
  }

}
