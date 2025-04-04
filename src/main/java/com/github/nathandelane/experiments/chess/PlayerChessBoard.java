package com.github.nathandelane.experiments.chess;

import static com.github.nathandelane.experiments.chess.Matrices.getReferenceIndex;

public class PlayerChessBoard {

  private static final int MIN_X = 0, MIN_Y = 0;
  private static final int MAX_X = 7, MAX_Y = 7;


  private final ChessPiece[] spaces = new ChessPiece[] {
    null, null, null, null, null, null, null, null, // 0, [0-7]
    null, null, null, null, null, null, null, null, // 1, [0-7]
    null, null, null, null, null, null, null, null, // 2, [0-7]
    null, null, null, null, null, null, null, null, // 3, [0-7]
    null, null, null, null, null, null, null, null, // 4, [0-7]
    null, null, null, null, null, null, null, null, // 5, [0-7]
    null, null, null, null, null, null, null, null, // 6, [0-7]
    null, null, null, null, null, null, null, null  // 7, [0-7]
  };

  public String color;

  public PlayerChessBoard(final String color) {
    this.color = color;
  }

  private PlayerChessBoard(final ChessPiece[] other) {
    for (int index = 0; index < other.length; index++) {
      spaces[index] = other[index];
    }
  }

  /**
   * Get {@link ChessPiece} at location (X, Y).
   * @param x horizontal location, 0-7
   * @param y vertical location, 0-7
   * @return chess piece or null
   */
  public ChessPiece getPieceAt(final int x, final int y) {
    if (x < MIN_X || x > MAX_X) throw new IllegalStateException(String.format("X (%d) must be between 0 and 7, inclusive.", x));
    if (y < MIN_Y || y > MAX_Y) throw new IllegalStateException(String.format("Y (%d) must be between 0 and 7, inclusive.", y));

    final int referenceIndex = getReferenceIndex(x, y);
    final ChessPiece chessPiece = spaces[referenceIndex];

    return chessPiece;
  }

  /**
   * Places {@link ChessPiece} at location (X, Y).
   * @param x horizontal location, 0-7
   * @param y vertical location, 0-7
   * @param chessPiece chess piece to be placed
   * @return placement result in the form {@link ChessPiecePlacementResult} with success status and chess piece that was
   * located there.
   */
  public ChessPiecePlacementResult setChessPieceAt(final int x, final int y, final ChessPiece chessPiece) {
    final ChessPiecePlacementResult chessPiecePlacementResult;
    final ChessPiece currentChessPiece = getPieceAt(x, y);

    if (currentChessPiece == null || !currentChessPiece.getColor().equalsIgnoreCase(chessPiece.getColor())) {
      final int referenceIndex = getReferenceIndex(x, y);

      spaces[referenceIndex] = chessPiece;

      chessPiecePlacementResult = new ChessPiecePlacementResult(true, currentChessPiece);
    } else {
      chessPiecePlacementResult = new ChessPiecePlacementResult(false, currentChessPiece);
    }

    return chessPiecePlacementResult;
  }

  public ChessPiece[] getSpaces() {
    return spaces;
  }

  public static class ChessPiecePlacementResult {

    public final boolean success;

    public final ChessPiece otherChessPiece;

    public ChessPiecePlacementResult(final boolean success, final ChessPiece otherChessPiece) {
      this.success = success;
      this.otherChessPiece = otherChessPiece;
    }

  }

}
