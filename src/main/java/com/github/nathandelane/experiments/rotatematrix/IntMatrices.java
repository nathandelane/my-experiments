package com.github.nathandelane.experiments.rotatematrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;

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
   * Multiplies a single row by a scalar.
   * @param matrix instance of {@link IntMatrix}
   * @param rowIndex index of row to operate on
   * @param scalar scalar value to multiply the row by
   * @return new instance of {@link IntMatrix} containing updated values
   */
  public static IntMatrix multiply(final IntMatrix matrix, final int rowIndex, final int scalar) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    if (rowIndex < 0) {
      throw new IllegalStateException("RowIndex must be positive.");
    }
    
    final int[][] newRepresentation = matrix.asTwoDimensionalArray();
    
    final int[] row = matrix.getRow(rowIndex);
    final int[] newRow = new int[matrix.numberOfColumns];
    
    for (int i = 0; i < matrix.numberOfColumns; i++) {
      final int value = row[i];
      final int newValue = (value * scalar);
      
      newRow[i] = newValue;
    }
    
    newRepresentation[rowIndex] = newRow;
    
    return new IntMatrix(newRepresentation);
  }
  
  /**
   * Multiplies a single row by a scalar.
   * @param matrix instance of {@link IntMatrix}
   * @param rowIndex index of row to operate on
   * @param scalar scalar value to multiply the row by
   * @return new instance of {@link IntMatrix} containing updated values
   */
  public static IntMatrix multiply(final IntMatrix matrix, final int rowIndex, final double scalar) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    if (rowIndex < 0) {
      throw new IllegalStateException("RowIndex must be positive.");
    }
    
    final int[][] newRepresentation = matrix.asTwoDimensionalArray();
    
    final int[] row = matrix.getRow(rowIndex);
    final int[] newRow = new int[matrix.numberOfColumns];
    
    for (int i = 0; i < matrix.numberOfColumns; i++) {
      final double value = (double) row[i];
      final double newValue = (value * scalar);
      final int newValueTruncated = (int) newValue;
      
      newRow[i] = newValueTruncated;
    }
    
    newRepresentation[rowIndex] = newRow;
    
    return new IntMatrix(newRepresentation);
  }
  
  /**
   * Multiplies a single row by a scalar
   * @param matrix instance of {@link IntMatrix}
   * @param rowIndex index of row to operate on
   * @param scalar scalar value to multiply the row by
   * @param roundingMode {@link RoundingMode}
   * @return new instance of {@link IntMatrix} containing updated values
   */
  public static IntMatrix multiply(final IntMatrix matrix, final int rowIndex, final double scalar, final RoundingMode roundingMode) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    if (rowIndex < 0) {
      throw new IllegalStateException("RowIndex must be positive.");
    }
    
    final int[][] newRepresentation = matrix.asTwoDimensionalArray();
    
    final int[] row = matrix.getRow(rowIndex);
    final int[] newRow = new int[matrix.numberOfColumns];
    
    for (int i = 0; i < matrix.numberOfColumns; i++) {
      final BigDecimal bdScalar = BigDecimal.valueOf(scalar);
      final BigDecimal bdValue = BigDecimal.valueOf(row[i]);
      final BigDecimal bdResult = bdValue.multiply(bdScalar, MathContext.DECIMAL128);
      final BigDecimal bdResultRounded = bdResult.setScale(0, roundingMode);
      
      final int result = bdResultRounded.intValueExact();
      
      newRow[i] = result;
    }
    
    newRepresentation[rowIndex] = newRow;
    
    return new IntMatrix(newRepresentation);
  }
  
  /**
   * Adds one row to another row, and updates the destination row, returning a new instance of {@link IntMatrix} containing the new data.
   * @param matrix instance of {@link IntMatrix}
   * @param firstRowIndex index of first row to add
   * @param secondRowIndex index of second row to add
   * @param destinationRowIndex index of row to add into
   * @return new instance of {@link IntMatrix} containing the new data
   */
  public static IntMatrix add(final IntMatrix matrix, final int firstRowIndex, final int secondRowIndex, final int destinationRowIndex) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    if (firstRowIndex < 0) {
      throw new IllegalStateException("FirstRowIndex must be greater than zero.");
    }
    if (secondRowIndex < 0) {
      throw new IllegalStateException("SecondRowIndex must be greater than zero.");
    }
    if (destinationRowIndex < 0) {
      throw new IllegalStateException("DestinationRowIndex must be greater than zero.");
    }
    if (destinationRowIndex != firstRowIndex || destinationRowIndex != secondRowIndex) {
      throw new IllegalStateException("DestinationRowIndex must be the same as either firstRowIndex or secondRowIndex.");
    }
    if (firstRowIndex == secondRowIndex) {
      throw new IllegalStateException("FirstRowIndex cannot be the same as secondRowIndex.");
    }
    
    final int[][] newRepresentation = matrix.asTwoDimensionalArray();
    
    final int[] firstRow = matrix.getRow(firstRowIndex);
    final int[] secondRow = matrix.getRow(secondRowIndex);
    final int[] newRow = new int[matrix.numberOfColumns];
    
    for (int columnIndex = 0; columnIndex < matrix.numberOfColumns; columnIndex++) {
      final int left = firstRow[columnIndex];
      final int right = secondRow[columnIndex];
      final int result = (left + right);
      
      newRow[columnIndex] = result;
    }
    
    newRepresentation[destinationRowIndex] = newRow;
    
    return new IntMatrix(newRepresentation);
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
   * Applies a single {@link Function} {@code f} to all values of the matrix and returns a new matrix containing the updated values.
   * @param matrix instance of {@link IntMatrix}
   * @param f function to apply
   * @return new instance of {@link IntMatrix} containing updated values
   */
  public static IntMatrix applyFunction(final IntMatrix matrix, final Function<Integer, Integer> f) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    if (f == null) {
      throw new IllegalStateException("Function f cannot be null.");
    }
    
    final int numberOfRows = matrix.numberOfRows;
    final int numberOfColumns = matrix.numberOfColumns;
    final int[][] newMatrix = new int[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        final int value = matrix.get(rowIndex, columnIndex);
        final int newValue = f.apply(value);
        
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
  
  /**
   * Swaps two rows in a matrix with each other.
   * @param matrix instance of {@link IntMatrix}
   * @param firstRowIndex index of first row to swap
   * @param secondRowIndex index of second row to swap
   * @return new instance of {@link IntMatrix} containing updated data
   */
  public static IntMatrix swapRows(final IntMatrix matrix, final int firstRowIndex, final int secondRowIndex) {
    if (matrix == null) {
      throw new IllegalStateException("Matrix cannot be null.");
    }
    if (firstRowIndex < 0) {
      throw new IllegalStateException("FirstRowIndex cannot be negative.");
    }
    if (secondRowIndex < 0) {
      throw new IllegalStateException("SecondRowIndex cannot be negative.");
    }
    
    final int[][] newRepresentation = matrix.asTwoDimensionalArray();
    
    final int[] firstRow = matrix.getRow(firstRowIndex);
    final int[] secondRow = matrix.getRow(secondRowIndex);
    
    newRepresentation[firstRowIndex] = secondRow;
    newRepresentation[secondRowIndex] = firstRow;
    
    return new IntMatrix(newRepresentation);
  }
  
  /**
   * Converts a {@link IntMatrix} to an {@link DoubleMatrix}.
   * @param matrix instance of {@link IntMatrix}
   * @return instance of {@link DoubleMatrix}
   */
  public static DoubleMatrix asDoubleMatrix(final IntMatrix matrix) {
    final double[][] doubleMatrix = new double[matrix.numberOfRows][matrix.numberOfColumns];
    
    for (int rI = 0; rI < matrix.numberOfRows; rI++) {
      for (int cI = 0; cI <matrix.numberOfColumns; cI++) {
        doubleMatrix[rI][cI] = matrix.get(rI, cI).doubleValue();
      }
    }
    
    return new DoubleMatrix(doubleMatrix);
  }
  
}
