package com.github.nathandelane.experiments.rotatematrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Collection of functions for manipulating {@link IntMatrix}.
 * @author nathandelane
 *
 */
public final class IntMatrices {

  private IntMatrices() {
    // No-op
  }
  
  /**
   * Creates a new {@link IntMatrix} copy based on an int arrat with dimensions.
   * @param newInternalRepresentation new integer array representing new matrix.
   * @param rows number of rows
   * @param columns number of columns
   */
  public static IntMatrix matrixOf(final int[] newInternalRepresentation, final int numberOfRows, final int numberOfColumns) {
    if (newInternalRepresentation == null) {
      throw new IllegalStateException("NewInternalRepresentation cannot be null.");
    }
    if (!(numberOfRows> 0)) {
      throw new IllegalStateException("NumberOfRows must be greater then zero.");
    }
    if (!(numberOfColumns> 0)) {
      throw new IllegalStateException("NumberOfColumns must be greater then zero.");
    }
    
    final int[][] representation = new int[numberOfRows][numberOfColumns];
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
    
    return new IntMatrix(representation);
  }
  
  /**
   * Creates a new zero matrix.
   * @param rows number of rows
   * @param columns number of columns
   */
  public static IntMatrix zeroMatrix(final int numberOfRows, final int numberOfColumns) {
    final int size = (numberOfRows * numberOfColumns);
    final int[] representation = new int[size];
    
    return matrixOf(representation, numberOfRows, numberOfColumns);
  }

  /**
   * Gets matrix as two-dimensional array as string.
   * @param matrix {@link IntMatrix}
   * @return String representation of matrix
   */
  public static String asString(final IntMatrix matrix) {
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
   * @param matrix instance of {@link IntMatrix}
   * @param scalar {@code int} scalar value
   * @return new {@link IntMatrix} containing new values
   */
  public static IntMatrix multiply(final IntMatrix matrix, final int scalar) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    
    final int[][] newMatrixDef = new int[matrix.numberOfRows][matrix.numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < matrix.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < matrix.numberOfColumns; columnIndex++) {
        final int value = matrix.get(rowIndex, columnIndex);
        final int result = (value * scalar);
        
        newMatrixDef[rowIndex][columnIndex] = result;
      }
    }
    
    return new IntMatrix(newMatrixDef);
  }
  
  /**
   * Multiply matrix by {@code double} scalar. {@link IntMatrix} will cast double results to {@code int} values.
   * @param matrix instance of {@link IntMatrix}
   * @param scalar {@code double} scalar value
   * @return new {@link IntMatrix} containing new values
   */
  public static IntMatrix multiply(final IntMatrix matrix, final double scalar) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    
    final int[][] newMatrixDef = new int[matrix.numberOfRows][matrix.numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < matrix.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < matrix.numberOfColumns; columnIndex++) {
        final double value = (double) matrix.get(rowIndex, columnIndex);
        final double result = (value * scalar);
        
        newMatrixDef[rowIndex][columnIndex] = (int) result;
      }
    }
    
    return new IntMatrix(newMatrixDef);
  }
  
  /**
   * Multiply matrix by {@code double} scalar. {@link IntMatrix} will round results to {@code int} values using the given {@code roundingMode}.
   * @param matrix instance of {@link IntMatrix}
   * @param scalar {@code double} scalar value
   * @param roundingMode rounding mode
   * @return new {@link IntMatrix} containing new values
   */
  public static IntMatrix multiply(final IntMatrix matrix, final double scalar, final RoundingMode roundingMode) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    
    final BigDecimal bdScalar = BigDecimal.valueOf(scalar);
    
    final int[][] newMatrixDef = new int[matrix.numberOfRows][matrix.numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < matrix.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < matrix.numberOfColumns; columnIndex++) {
        final int intValue = matrix.get(rowIndex, columnIndex);
        final BigDecimal value = BigDecimal.valueOf(intValue);
        final BigDecimal bdResult = value.multiply(bdScalar, MathContext.DECIMAL128);
        final BigDecimal bdResultRounded = bdResult.setScale(0, roundingMode);
        
        final int result = bdResultRounded.intValueExact();
        
        newMatrixDef[rowIndex][columnIndex] = result;
      }
    }
    
    return new IntMatrix(newMatrixDef);
  }
  
  /**
   * Multiply two matrices. Matrices are multipliable if the number of columns in the {@code first} matrix is equal to the 
   * number of rows in the {@code second} matrix. Otherwise a {@link MatricesNotMultipliableException} can be thrown.
   * @param first non-null {@link IntMatrix}
   * @param second non-null {@link IntMatrix}
   * @return result of multiplication as {@link IntMatrix}
   * @throws {@link MatricesNotMultipliableException} if matrices are not multipliable.
   */
  public static IntMatrix multiply(final IntMatrix first, final IntMatrix second) throws MatricesNotMultipliableException {
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
    final int[][] newMatrix = new int[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final int[] firstVector = first.getRow(rowIndex);
        final int[] secondVector = second.getColumn(columnIndex);
        
        newMatrix[rowIndex][columnIndex] = dotProduct(firstVector, secondVector);
      }
    }
    
    return new IntMatrix(newMatrix);
  }
  
  /**
   * Add two matrices. Matrices are addable if the number of columns and rows match acress the {@code first} and {@code second} 
   * matrices. Otherwise a {@link MatricesNotAddableException} can be thrown.
   * @param non-null {@link IntMatrix}
   * @param second non-null {@link IntMatrix}
   * @return result of addition as {@link IntMatrix}
   * @throws {@link MatricesNotAddableException}
   */
  public static IntMatrix add(final IntMatrix first, final IntMatrix second) throws MatricesNotAddableException {
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
    final int[][] newMatrix = new int[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final int firstValue = first.get(rowIndex, columnIndex);
        final int secondValue = second.get(rowIndex, columnIndex);
        final int newValue = (firstValue + secondValue);
        
        newMatrix[rowIndex][columnIndex] = newValue;
      }
    }
    
    return new IntMatrix(newMatrix);
  }
  
  /**
   * Subtracts {@code second} from {@code first}, by first multiplying the second matrix by {@code -1} and then adding the matrices.
   * @param non-null {@link IntMatrix}
   * @param second non-null {@link IntMatrix}
   * @return result of subtraction as {@link IntMatrix}
   * @throws MatricesNotSubtractableException
   */
  public static IntMatrix subtract(final IntMatrix first, final IntMatrix second) throws MatricesNotSubtractableException {
    if (first == null) {
      throw new IllegalStateException("First cannot be null.");
    }
    if (second == null) {
      throw new IllegalStateException("Second cannot be null.");
    }
    if (!areAddable(first, second)) {
      throw new MatricesNotSubtractableException(String.format("These matrices are not subtractable: 1:%s, 2%s", asString(first), asString(second)));
    }
    
    final IntMatrix negatedSecond = multiply(second, -1);
    final int numberOfRows = first.numberOfRows;
    final int numberOfColumns = first.numberOfColumns;
    final int[][] newMatrix = new int[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final int firstValue = first.get(rowIndex, columnIndex);
        final int secondValue = negatedSecond.get(rowIndex, columnIndex);
        final int newValue = (firstValue + secondValue);
        
        newMatrix[rowIndex][columnIndex] = newValue;
      }
    }
    
    return new IntMatrix(newMatrix);
  }
  
  /**
   * Calculate the dot product of two vectors.
   * @param firstVector array of {@code int} values
   * @param secondVector array of {@code int} values
   * @return dot product of {@code firstVector} and {@code secondVector}
   */
  public static int dotProduct(final int[] firstVector, final int[] secondVector) {
    if (firstVector == null) {
      throw new IllegalStateException("FirstVector cannot be null.");
    }
    if (secondVector == null) {
      throw new IllegalStateException("SecondVector cannot be null.");
    }
    if (firstVector.length != secondVector.length) {
      throw new IllegalStateException("FirstVector and secondVector must be the same length.");
    }
    
    final int[] values = new int[firstVector.length];
    
    for (int i = 0; i < values.length; i++) {
      values[i] = (firstVector[i] * secondVector[i]);
    }
    
    int result = values[0];
    
    for (int i = 1; i < values.length; i++) {
      final int nextValue = values[i];
      
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
  public static boolean areMultipliable(final IntMatrix firstMatrix, final IntMatrix ... intMatrices) {
    if (firstMatrix == null) {
      throw new IllegalStateException("FirstMatrix cannot be null.");
    }
    if (intMatrices == null) {
      throw new IllegalStateException("IntMatrices cannot be null.");
    }
    
    boolean areMultipliable = true;
    
    int lastMatrixNumCols = firstMatrix.numberOfColumns;
    
    for (int index = 0; index < intMatrices.length; index++) {
      final IntMatrix next = intMatrices[index];
      
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
  public static boolean areAddable(final IntMatrix firstMatrix, final IntMatrix ... intMatrices) {
    if (firstMatrix == null) {
      throw new IllegalStateException("FirstMatrix cannot be null.");
    }
    if (intMatrices == null) {
      throw new IllegalStateException("IntMatrices cannot be null.");
    }
    
    boolean areAddable = true;
    
    for (int index = 0; index < intMatrices.length; index++) {
      final IntMatrix next = intMatrices[index];
      
      if (next.numberOfRows != firstMatrix.numberOfRows || next.numberOfColumns != firstMatrix.numberOfColumns) {
        areAddable = false;
        
        break;
      }
    }
    
    return areAddable;
  }
  
}
