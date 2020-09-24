package com.github.nathandelane.experiments.rotatematrix;


public final class DoubleFunctionMatrices {

  private DoubleFunctionMatrices() {
    // No-op
  }
  
  /**
   * Apply functions in a {@link DoubleFunctionMatrix} to a {@link DoubleMatrix}.
   * @param doubleMatrix non-null {@link DoubleMatrix}
   * @param doubleFunctionMatrix non-null {@link DoubleFunctionMatrix}
   * @return results of applying function matrix
   */
  public static DoubleMatrix apply(final DoubleMatrix doubleMatrix, final DoubleFunctionMatrix doubleFunctionMatrix) {
    if (doubleMatrix == null) {
      throw new IllegalStateException("DoubleMatrix may not be null.");
    }
    if (doubleFunctionMatrix == null) {
      throw new IllegalStateException("DoubleFunctionMatrix may not be null.");
    }
    if (!areAppliable(doubleMatrix, doubleFunctionMatrix)) {
      throw new MatricesNotMultipliableException(String.format("DoubleFunctionMatrix cannot be applied to doubleMatrix: %s", DoubleMatrices.asString(doubleMatrix)));
    }
    
    final int numberOfRows = doubleFunctionMatrix.numberOfRows;
    final int numberOfColumns = doubleMatrix.numberOfColumns;
    final double[][] newMatrix = new double[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final DoubleFunction[] functionVector = doubleFunctionMatrix.getRow(rowIndex);
        final double[] doubleVector = doubleMatrix.getColumn(columnIndex);
        
        for (int i = 0; i < functionVector.length; i++) {
          newMatrix[rowIndex][columnIndex] = functionVector[i].apply(doubleVector[i]);
        }
      }
    }
    
    return new DoubleMatrix(newMatrix);
  }
  
  /**
   * Determines if a function matrix may be applied to a double matrix.
   * @param matrix matrix to apply function matrix to
   * @param functionMatrix list of other matrices to multiply, in order
   * @return whether or not function matrix can be applied to double matrix
   */
  public static boolean areAppliable(final DoubleMatrix matrix, final DoubleFunctionMatrix functionMatrix) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    if (functionMatrix == null) {
      throw new IllegalStateException("FunctionMatrix cannot be null.");
    }
    
    final boolean areAppliable = (functionMatrix.numberOfRows == matrix.numberOfColumns);
    
    return areAppliable;
  }

}
