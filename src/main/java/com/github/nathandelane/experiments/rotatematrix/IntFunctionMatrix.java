package com.github.nathandelane.experiments.rotatematrix;

import java.util.Arrays;

/**
 * Matrix of {@link IntFunction}s.
 * @author nathandelane
 *
 */
public class IntFunctionMatrix implements FunctionMatrix<IntFunction> {

  private final IntFunction[] internalRepresentation;
  
  public final int numberOfRows;
  
  public final int numberOfColumns;
  
  /**
   * Copy constructor. Creates a new {@link IntFunctionMatrix} from another {@code Matrix}.
   * @param other non-null {@link IntFunctionMatrix}
   */
  public IntFunctionMatrix(final IntFunctionMatrix other) {
    this.numberOfRows = other.numberOfRows;
    this.numberOfColumns = other.numberOfColumns;
    
    final int size = (this.numberOfRows * this.numberOfColumns);
    
    this.internalRepresentation = Arrays.copyOf(other.internalRepresentation, size);
  }
  
  /**
   * Create new matrix using a two-dimensional {@code IntFunction} array.
   * @param values values in matrix
   */
  public IntFunctionMatrix(final IntFunction[][] values) {
    this.numberOfRows = values.length;
    
    if (this.numberOfRows > 0) {
      this.numberOfColumns = values[0].length;
    }
    else {
      this.numberOfColumns = 0;
    }
    
    final int size = (this.numberOfRows * this.numberOfColumns);
    
    this.internalRepresentation = new IntFunction[size];
    
    for (int rowIndex = 0; rowIndex < this.numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < this.numberOfColumns; columnIndex++) {
        final IntFunction value = values[rowIndex][columnIndex];
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
  @Override
  public IntFunction get(final int rowIndex, final int columnIndex) {
    return internalRepresentation[(rowIndex * numberOfColumns) + columnIndex];
  }
  
  /**
   * Gets a specific row of the matrix.
   * @param rowIndex index of row
   * @return array of values contained in row
   */
  public IntFunction[] getRow(final int rowIndex) {
    final IntFunction[] row = Arrays.copyOfRange(internalRepresentation, ((rowIndex * numberOfColumns) + 0), ((rowIndex * numberOfColumns) + numberOfColumns));
    
    return row;
  }
  
  /**
   * Gets a specific column of the matrix.
   * @param columnIndex index of column
   * @return array of values contained in column
   */
  public IntFunction[] getColumn(final int columnIndex) {
    final IntFunction[] row = new IntFunction[numberOfRows];
    
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
  public IntFunction[][] asTwoDimensionalArray() {
    final IntFunction[][] representation = new IntFunction[numberOfRows][numberOfColumns];
    
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
        representation[rowIndex][colIndex] = get(rowIndex, colIndex);
      }
    }
    
    return representation;
  }

}
