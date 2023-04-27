package com.github.nathandelane.experiments.chess;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

  private final ChessPiece[] spaces;

  private final Map<ChessPiece, ChessPieceLocation> chessPieceLocations;

  private String perspective;

  public ChessBoard() {
    spaces = new ChessPiece[64];
    chessPieceLocations = new HashMap<>();
    perspective = "white";

    initializeBoard(chessPieceLocations);
    fillBoard(spaces, chessPieceLocations);
  }

  public String getPerspective() {
    return perspective;
  }

  public void setPerspective(final String perspective) {
    if (!(perspective.equalsIgnoreCase("white") || perspective.equalsIgnoreCase("black")))
      throw new IllegalStateException("Perspective must be either \"white\" or \"black\".");

    this.perspective = perspective;
  }

  static void initializeBoard(final Map<ChessPiece, ChessPieceLocation> chessPieceLocations) {
    int verticalWhiteLocation = 0;
    int horizontalWhiteLocation = 0;

    for (final ChessPiece nextChessPiece : ChessPieces.ALL_WHITE_CHESS_PIECES) {
      final int boardY = (verticalWhiteLocation + 1);
      final int boardX = (horizontalWhiteLocation + 1);
      final ChessPieceLocation chessPieceLocation = new ChessPieceLocation(boardX, boardY, "white");

      chessPieceLocations.put(nextChessPiece, chessPieceLocation);

      horizontalWhiteLocation += 1;

      if (horizontalWhiteLocation >= 8) {
        horizontalWhiteLocation = 0;
        verticalWhiteLocation += 1;
      }
    }

    int verticalBlackLocation = 0;
    int horizontalBlackLocation = 0;

    for (final ChessPiece nextChessPiece : ChessPieces.ALL_BLACK_CHESS_PIECES) {
      final int boardY = (verticalBlackLocation + 1);
      final int boardX = (horizontalBlackLocation + 1);
      final ChessPieceLocation chessPieceLocation = new ChessPieceLocation(boardX, boardY, "black");

      chessPieceLocations.put(nextChessPiece, chessPieceLocation);

      horizontalBlackLocation += 1;

      if (horizontalBlackLocation >= 8) {
        horizontalBlackLocation = 0;
        verticalBlackLocation += 1;
      }
    }
  }

  public static void fillBoard(final ChessPiece[] spaces, final Map<ChessPiece, ChessPieceLocation> chessPieceLocations) {
    for (final Map.Entry<ChessPiece, ChessPieceLocation> entry : chessPieceLocations.entrySet()) {
      final ChessPiece chessPiece = entry.getKey();
      final ChessPieceLocation chessPieceLocation = entry.getValue();

      if (chessPiece.getColor().equals("white") && chessPieceLocation.getColor().equals("white")) {
        final int x = chessPieceLocation.getHorizontal() - 1;
        final int y = chessPieceLocation.getVertical() - 1;
        final int renderIndex = 63 - ((y * 8) + x);

        spaces[renderIndex] = chessPiece;
      }
      else if (chessPiece.getColor().equals("black") && chessPieceLocation.getColor().equals("black")) {
        final int x = chessPieceLocation.getHorizontal() - 1;
        final int y = chessPieceLocation.getVertical() - 1;
        final int renderIndex = ((y * 8) + x);

        spaces[renderIndex] = chessPiece;
      }
      else {
        throw new IllegalStateException("Chess piece and location must contain the same color, either \"white\" or \"black\".");
      }
    }
  }

  String getPerspectiveTop() {
    if (perspective.equalsIgnoreCase("white")) {
      return "          BLACK          ";
    }
    else {
      return "          WHITE          ";
    }
  }

  String getPerspectiveBottom() {
    if (perspective.equalsIgnoreCase("white")) {
      return "          WHITE          ";
    }
    else {
      return "          BLACK          ";
    }
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(String.format("%s%n  1   2   3   4   5   6   7   8  %n", getPerspectiveTop()));

    for (int y = 0; y < 8; y++) {
      sb
        .append(y + 1)
        .append(" ");

      for (int x = 0; x < 8; x++) {
        final int renderIndex;

        if (perspective.equalsIgnoreCase("white")) {
          renderIndex = (y * 8 + x);
        } else {
          renderIndex = 63 - (y * 8 + x);
        }

        final ChessPiece chessPiece = spaces[renderIndex];

        if (chessPiece != null) {
          sb
            .append(chessPiece.getColor().equals("white") ? "W" : "B")
            .append(String.format("%-3s", chessPiece.getSymbol()));
        }
        else {
          sb.append("   ");
        }
      }

      sb.append(String.format("%n"));
    }

    sb.append(String.format("%s%n", getPerspectiveBottom()));

    return sb.toString();
  }

}
