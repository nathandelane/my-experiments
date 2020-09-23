package com.github.nathandelane.experiments.rotatematrix;

/**
 * Collection of functions for manipulating {@link DoubleMatrix}.
 * @author nathandelane
 *
 */
public final class DoubleMatrices {

  private DoubleMatrices() {
    // No-op
  }
  
  /**
   * Creates a new {@link DoubleMatrix} copy based on an double array with dimensions.
   * @param newInternalRepresentation new integer array representing new matrix.
   * @param rows number of rows
   * @param columns number of columns
   */
  public static DoubleMatrix matrixOf(final double[] newInternalRepresentation, final int numberOfRows, final int numberOfColumns) {
    if (newInternalRepresentation == null) {
      throw new IllegalStateException("NewInternalRepresentation cannot be null.");
    }
    if (!(numberOfRows> 0)) {
      throw new IllegalStateException("NumberOfRows must be greater then zero.");
    }
    if (!(numberOfColumns> 0)) {
      throw new IllegalStateException("NumberOfColumns must be greater then zero.");
    }
    
    final double[][] representation = new double[numberOfRows][numberOfColumns];
    final int size = (numberOfRows * numberOfColumns);
    
    if (newInternalRepresentation.length != size) {
      throw new IllegalStateException("Size of newInternalRepresentation should match dimensions.");
    }
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
        final int index = ((rowIndex * numberOfRows) + colIndex);
        
        representation[rowIndex][colIndex] = newInternalRepresentation[index];
      }
    }
    
    return new DoubleMatrix(representation);
  }
  
  /**
   * Creates a new zero matrix.
   * @param rows number of rows
   * @param columns number of columns
   */
  public static DoubleMatrix zeroMatrix(final int numberOfRows, final int numberOfColumns) {
    final int size = (numberOfRows * numberOfColumns);
    final double[] representation = new double[size];
    
    return matrixOf(representation, numberOfRows, numberOfColumns);
  }

  /**
   * Gets matrix as two-dimensional array as string.
   * @param matrix {@link IntMatrix}
   * @return String representation of matrix
   */
  public static String asString(final DoubleMatrix matrix) {
    final StringBuilder sb = new StringBuilder("{ ");
    
    for (int r = 0; r < matrix.numberOfRows; r++) {
      if (r > 0) {
        sb.append(", ");
      }
      
      sb.append("{ ");
      
      for (int c = 0; c < matrix.numberOfColumns; c++) {
        if (c > 0) {
          sb.append(", ");
        }
        
        sb.append(matrix.get(r, c));
      }
      
      sb.append(" }");
    }
    
    return sb.append(" }").toString();
  }
  
  /**
   * Multiply matrix by {@code int} scalar.
   * @param matrix instance of {@link DoubleMatrix}
   * @param scalar {@code int} scalar value
   * @return new {@link DoubleMatrix} containing new values
   */
  public static DoubleMatrix multiply(final DoubleMatrix matrix, final int scalar) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    
    final double[][] newMatrixDef = new double[matrix.numberOfRows][matrix.numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < matrix.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < matrix.numberOfColumns; columnIndex++) {
        final double value = matrix.get(rowIndex, columnIndex);
        final double result = (value * scalar);
        
        newMatrixDef[rowIndex][columnIndex] = result;
      }
    }
    
    return new DoubleMatrix(newMatrixDef);
  }
  
  /**
   * Multiply matrix by {@code double} scalar.
   * @param matrix instance of {@link DoubleMatrix}
   * @param scalar {@code double} scalar value
   * @return new {@link DoubleMatrix} containing new values
   */
  public static DoubleMatrix multiply(final DoubleMatrix matrix, final double scalar) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    
    final double[][] newMatrixDef = new double[matrix.numberOfRows][matrix.numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < matrix.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < matrix.numberOfColumns; columnIndex++) {
        final double value = (double) matrix.get(rowIndex, columnIndex);
        final double result = (value * scalar);
        
        newMatrixDef[rowIndex][columnIndex] = (int) result;
      }
    }
    
    return new DoubleMatrix(newMatrixDef);
  }
  
  /**
   * Multiply two matrices. Matrices are multipliable if the number of columns in the {@code first} matrix is equal to the 
   * number of rows in the {@code second} matrix. Otherwise a {@link MatricesNotMultipliableException} can be thrown.
   * @param first non-null {@link DoubleMatrix}
   * @param second non-null {@link DoubleMatrix}
   * @return result of multiplication as {@link DoubleMatrix}
   * @throws {@link MatricesNotMultipliableException} if matrices are not multipliable.
   */
  public static DoubleMatrix multiply(final DoubleMatrix first, final DoubleMatrix second) throws MatricesNotMultipliableException {
    if (first == null) {
      throw new IllegalStateException("First cannot be null.");
    }
    if (second == null) {
      throw new IllegalStateException("Second cannot be null.");
    }
    if (!areMultipliable(first, second)) {
      throw new MatricesNotMultipliableException(String.format("These matrices are not multipliable: 1:%s, 2%s", asString(first), asString(second)));
    }
    
    final int numberOfRows = first.numberOfRows;
    final int numberOfColumns = second.numberOfColumns;
    final double[][] newMatrix = new double[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final double[] firstVector = first.getRow(rowIndex);
        final double[] secondVector = second.getColumn(columnIndex);
        
        newMatrix[rowIndex][columnIndex] = dotProduct(firstVector, secondVector);
      }
    }
    
    return new DoubleMatrix(newMatrix);
  }
  
  /**
   * Add two matrices. Matrices are addable if the number of columns and rows match across the {@code first} and {@code second} 
   * matrices. Otherwise a {@link MatricesNotAddableException} can be thrown.
   * @param non-null {@link DoubleMatrix}
   * @param second non-null {@link DoubleMatrix}
   * @return result of addition as {@link DoubleMatrix}
   * @throws {@link MatricesNotAddableException}
   */
  public static DoubleMatrix add(final DoubleMatrix first, final DoubleMatrix second) throws MatricesNotAddableException {
    if (first == null) {
      throw new IllegalStateException("First cannot be null.");
    }
    if (second == null) {
      throw new IllegalStateException("Second cannot be null.");
    }
    if (!areAddable(first, second)) {
      throw new MatricesNotAddableException(String.format("These matrices are not addable: 1:%s, 2%s", asString(first), asString(second)));
    }
    
    final int numberOfRows = first.numberOfRows;
    final int numberOfColumns = first.numberOfColumns;
    final double[][] newMatrix = new double[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final double firstValue = first.get(rowIndex, columnIndex);
        final double secondValue = second.get(rowIndex, columnIndex);
        final double newValue = (firstValue + secondValue);
        
        newMatrix[rowIndex][columnIndex] = newValue;
      }
    }
    
    return new DoubleMatrix(newMatrix);
  }
  
  /**
   * Subtracts {@code second} from {@code first}, by first multiplying the second matrix by {@code -1} and then adding the matrices.
   * @param non-null {@link DoubleMatrix}
   * @param second non-null {@link DoubleMatrix}
   * @return result of subtraction as {@link DoubleMatrix}
   * @throws MatricesNotSubtractableException
   */
  public static DoubleMatrix subtract(final DoubleMatrix first, final DoubleMatrix second) throws MatricesNotSubtractableException {
    if (first == null) {
      throw new IllegalStateException("First cannot be null.");
    }
    if (second == null) {
      throw new IllegalStateException("Second cannot be null.");
    }
    if (!areAddable(first, second)) {
      throw new MatricesNotSubtractableException(String.format("These matrices are not subtractable: 1:%s, 2%s", asString(first), asString(second)));
    }
    
    final DoubleMatrix negatedSecond = multiply(second, -1.0);
    final int numberOfRows = first.numberOfRows;
    final int numberOfColumns = first.numberOfColumns;
    final double[][] newMatrix = new double[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final double firstValue = first.get(rowIndex, columnIndex);
        final double secondValue = negatedSecond.get(rowIndex, columnIndex);
        final double newValue = (firstValue + secondValue);
        
        newMatrix[rowIndex][columnIndex] = newValue;
      }
    }
    
    return new DoubleMatrix(newMatrix);
  }
  
  /**
   * Calculate the dot product of two vectors.
   * @param firstVector array of {@code double} values
   * @param secondVector array of {@code double} values
   * @return dot product of {@code firstVector} and {@code secondVector}
   */
  public static double dotProduct(final double[] firstVector, final double[] secondVector) {
    if (firstVector == null) {
      throw new IllegalStateException("FirstVector cannot be null.");
    }
    if (secondVector == null) {
      throw new IllegalStateException("SecondVector cannot be null.");
    }
    if (firstVector.length != secondVector.length) {
      throw new IllegalStateException("FirstVector and secondVector must be the same length.");
    }
    
    final double[] values = new double[firstVector.length];
    
    for (int i = 0; i < values.length; i++) {
      values[i] = (firstVector[i] * secondVector[i]);
    }
    
    double result = values[0];
    
    for (int i = 1; i < values.length; i++) {
      final double nextValue = values[i];
      
      result += nextValue;
    }
    
    return result;
  }
  
  /**
   * Determines if an ordered set of matrices are multipliable in order.
   * @param firstMatrix first matrix the multiply
   * @param intMatrices list of other matrices to multiply, in order
   * @return whether or not all of the matrices are multipliable
   */
  public static boolean areMultipliable(final DoubleMatrix firstMatrix, final DoubleMatrix ... intMatrices) {
    if (firstMatrix == null) {
      throw new IllegalStateException("FirstMatrix cannot be null.");
    }
    if (intMatrices == null) {
      throw new IllegalStateException("IntMatrices cannot be null.");
    }
    
    boolean areMultipliable = true;
    
    int lastMatrixNumCols = firstMatrix.numberOfColumns;
    
    for (int index = 0; index < intMatrices.length; index++) {
      final DoubleMatrix next = intMatrices[index];
      
      if (lastMatrixNumCols != next.numberOfRows) {
        areMultipliable = false;
        
        break;
      }
      else {
        lastMatrixNumCols = next.numberOfColumns;
      }
    }
    
    return areMultipliable;
  }
  
  /**
   * Determines if an orderd set of matrices are addable in order.
   * @param firstMatrix first matrix to add
   * @param intMatrices list of other matrices to add, in order
   * @return whether or not all of the matrices are addable
   */
  public static boolean areAddable(final DoubleMatrix firstMatrix, final DoubleMatrix ... intMatrices) {
    if (firstMatrix == null) {
      throw new IllegalStateException("FirstMatrix cannot be null.");
    }
    if (intMatrices == null) {
      throw new IllegalStateException("IntMatrices cannot be null.");
    }
    
    boolean areAddable = true;
    
    for (int index = 0; index < intMatrices.length; index++) {
      final DoubleMatrix next = intMatrices[index];
      
      if (next.numberOfRows != firstMatrix.numberOfRows || next.numberOfColumns != firstMatrix.numberOfColumns) {
        areAddable = false;
        
        break;
      }
    }
    
    return areAddable;
  }
  
}
