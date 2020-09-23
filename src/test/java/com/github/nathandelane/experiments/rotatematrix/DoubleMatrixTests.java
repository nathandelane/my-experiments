package com.github.nathandelane.experiments.rotatematrix;

import org.junit.Test;

public class DoubleMatrixTests {

  @Test
  public void test() {
    final DoubleMatrix m1 = new DoubleMatrix(new double[][] { {-4, 8, 8, -4 }, { 4, 8, -4, -8 } });
    final DoubleMatrix m2 = new DoubleMatrix(new double[][] { { 0.25, 0 }, { 0, -0.25 } });
    
    final DoubleMatrix r = DoubleMatrices.multiply(m2, m1);
    
    System.out.println(DoubleMatrices.asString(r));
  }

}
