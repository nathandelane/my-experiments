package com.github.nathandelane.experiments.rotatematrix;

import java.util.Arrays;

/**
 * Matrix of {@link DoubleFunction}s.
 * @author nathandelane
 *
 */
public class DoubleFunctionMatrix {

  private final DoubleFunction[] internalRepresentation;
  
  public final int numberOfRows;
  
  public final int numberOfColumns;
  
  /**
   * Copy constructor. Creates a new {@link DoubleFunctionMatrix} from another {@code Matrix}.
   * @param other non-null {@link DoubleFunctionMatrix}
   */
  public DoubleFunctionMatrix(final DoubleFunctionMatrix other) {
    this.numberOfRows = other.numberOfRows;
    this.numberOfColumns = other.numberOfColumns;
    
    final int size = (this.numberOfRows * this.numberOfColumns);
    
    this.internalRepresentation = Arrays.copyOf(other.internalRepresentation, size);
  }
  
  /**
   * Create new matrix using a two-dimensional {@code IntFunction} array.
   * @param values values in matrix
   */
  public DoubleFunctionMatrix(final DoubleFunction[][] values) {
    this.numberOfRows = values.length;
    
    if (this.numberOfRows > 0) {
      this.numberOfColumns = values[0].length;
    }
    else {
      this.numberOfColumns = 0;
    }
    
    final int size = (this.numberOfRows * this.numberOfColumns);
    
    this.internalRepresentation = new DoubleFunction[size];
    
    for (int rowIndex = 0; rowIndex < this.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < this.numberOfColumns; columnIndex++) {
        final DoubleFunction value = values[rowIndex][columnIndex];
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
  public DoubleFunction get(final int rowIndex, final int columnIndex) {
    return internalRepresentation[(rowIndex * numberOfColumns) + columnIndex];
  }
  
  /**
   * Gets a specific row of the matrix.
   * @param rowIndex index of row
   * @return array of values contained in row
   */
  public DoubleFunction[] getRow(final int rowIndex) {
    final DoubleFunction[] row = Arrays.copyOfRange(internalRepresentation, ((rowIndex * numberOfColumns) + 0), ((rowIndex * numberOfColumns) + numberOfColumns));
    
    return row;
  }
  
  /**
   * Gets a specific column of the matrix.
   * @param columnIndex index of column
   * @return array of values contained in column
   */
  public DoubleFunction[] getColumn(final int columnIndex) {
    final DoubleFunction[] row = new DoubleFunction[numberOfRows];
    
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
  public DoubleFunction[][] asTwoDimensionalArray() {
    final DoubleFunction[][] representation = new DoubleFunction[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
        representation[rowIndex][colIndex] = get(rowIndex, colIndex);
      }
    }
    
    return representation;
  }

}
