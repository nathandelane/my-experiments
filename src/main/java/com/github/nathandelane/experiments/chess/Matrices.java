package com.github.nathandelane.experiments.chess;

public final class Matrices {

  private static final int WIDTH = 8, HEIGHT = 8;

  private Matrices() { }

  public static int getReferenceIndex(final int x, final int y) {
    return ((y * WIDTH) + x);
  }

  public static ChessPiece[] reverseRow(final ChessPiece[] row) {
    final ChessPiece[] newRow = new ChessPiece[row.length];

    for (int i = 0; i < row.length; i++) {
      newRow[i] = row[(row.length - 1 - i)];
    }

    return newRow.clone();
  }

  public static ChessPiece[] swapRows(final ChessPiece[] original, final int rowA, final int rowB) {
    final ChessPiece[] newSpaces = original.clone();

    for (int x = 0; x < WIDTH; x++) {
      final int referenceIndexA = getReferenceIndex(x, rowA);
      final int referenceIndexB = getReferenceIndex(x, rowB);
      final ChessPiece chessPieceA = original[referenceIndexA];
      final ChessPiece chessPieceB = original[referenceIndexB];

      newSpaces[referenceIndexA] = chessPieceB;
      newSpaces[referenceIndexB] = chessPieceA;
    }

    return newSpaces.clone();
  }

  public static ChessPiece[] rotate(final ChessPiece[] currentSpaces) {
    ChessPiece[] newSpaces = currentSpaces.clone();
    newSpaces = swapRows(newSpaces, 0, 7);
    newSpaces = swapRows(newSpaces, 1, 6);
    newSpaces = swapRows(newSpaces, 2, 5);
    newSpaces = swapRows(newSpaces, 3, 4);

    ChessPiece[] row = new ChessPiece[WIDTH];

    for(int rowIndex = 0; rowIndex < HEIGHT; rowIndex++) {
      for (int colIndex = 0; colIndex < WIDTH; colIndex++) {
        final int referenceIndex = getReferenceIndex(colIndex, rowIndex);

        row[colIndex] = newSpaces[referenceIndex];
      }

      row = reverseRow(row);

      for (int colIndex = 0; colIndex < WIDTH; colIndex++) {
        final int referenceIndex = getReferenceIndex(colIndex, rowIndex);

        newSpaces[referenceIndex] = row[colIndex];
      }
    }

    return newSpaces.clone();
  }

}
