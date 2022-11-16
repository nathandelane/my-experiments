package com.github.nathandelane.experiments.rotatematrix;

import java.util.Arrays;

/**
 * Represents a matrix of {@code int} values.
 * @author nathandelane
 */
public class IntMatrix {
  
  private final int[] internalRepresentation;
  
  public final int numberOfRows;
  
  public final int numberOfColumns;
  
  /**
   * Copy constructor. Creates a new {@link IntMatrix} from another {@code Matrix}.
   * @param other non-null {@link IntMatrix}
   */
  public IntMatrix(final IntMatrix other) {
    this.numberOfRows = other.numberOfRows;
    this.numberOfColumns = other.numberOfColumns;
    
    final int size = (this.numberOfRows * this.numberOfColumns);
    
    this.internalRepresentation = Arrays.copyOf(other.internalRepresentation, size);
  }
  
  /**
   * Create new matrix using a two-dimensional {@code int} array.
   * @param values values in matrix
   */
  public IntMatrix(final int[][] values) {
    this.numberOfRows = values.length;
    
    if (this.numberOfRows > 0) {
      this.numberOfColumns = values[0].length;
    }
    else {
      this.numberOfColumns = 0;
    }
    
    final int size = (this.numberOfRows * this.numberOfColumns);
    
    this.internalRepresentation = new int[size];
    
    for (int rowIndex = 0; rowIndex < this.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < this.numberOfColumns; columnIndex++) {
        final int value = values[rowIndex][columnIndex];
        final int internalRepresentationIndex = ((rowIndex * numberOfColumns) + columnIndex);
        
        this.internalRepresentation[internalRepresentationIndex] = value;
      }
    }
  }
  
  /**
   * Retrieve value at {@code [rowIndex][columnIndex]}.
   * @param rowIndex row index
   * @param columnIndex column index
   * @return value at {@code [rowIndex][columnIndex]}
   */
  public Integer get(final int rowIndex, final int columnIndex) {
    return internalRepresentation[(rowIndex * numberOfColumns) + columnIndex];
  }
  
  /**
   * Gets a specific row of the matrix.
   * @param rowIndex index of row
   * @return array of values contained in row
   */
  public int[] getRow(final int rowIndex) {
    final int[] row = Arrays.copyOfRange(internalRepresentation, ((rowIndex * numberOfColumns)), ((rowIndex * numberOfColumns) + numberOfColumns));
    
    return row;
  }
  
  /**
   * Gets a specific column of the matrix.
   * @param columnIndex index of column
   * @return array of values contained in column
   */
  public int[] getColumn(final int columnIndex) {
    final int[] row = new int[numberOfRows];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      final int index = ((rowIndex * numberOfColumns) + columnIndex);
      
      row[rowIndex] = internalRepresentation[index];
    }
    
    return row;
  }
  
  /**
   * Gets the matrix as a two-dimensional array.
   * @return matrix as 2-D array.
   */
  public int[][] asTwoDimensionalArray() {
    final int[][] representation = new int[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
        representation[rowIndex][colIndex] = get(rowIndex, colIndex);
      }
    }
    
    return representation;
  }
  
  @Override
  public boolean equals(final Object other) {
    final boolean areEqual;
    
    if (other instanceof IntMatrix) {
      final IntMatrix o = (IntMatrix) other;
      
      areEqual = o.numberOfRows == this.numberOfRows &&
        o.numberOfColumns == this.numberOfColumns &&
        Arrays.equals(o.internalRepresentation, this.internalRepresentation);
    }
    else {
      areEqual = false;
    }
    
    return areEqual;
  }
  
  @Override
  public int hashCode() {
    return this.internalRepresentation.hashCode() + (this.numberOfRows * this.numberOfColumns);
  }
  
  @Override
  public String toString() {
    return super.toString();
  }
  
}
