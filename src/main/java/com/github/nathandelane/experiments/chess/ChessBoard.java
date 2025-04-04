package com.github.nathandelane.experiments.chess;

import java.util.HashMap;
import java.util.Map;

import static com.github.nathandelane.experiments.chess.Constants.PLAYER_COLOR_BLACK;
import static com.github.nathandelane.experiments.chess.Constants.PLAYER_COLOR_WHITE;
import static com.github.nathandelane.experiments.chess.Matrices.rotate;

public class ChessBoard {

  private final PlayerChessBoard whiteBoard;
  private final PlayerChessBoard blackBoard;

  private final Map<ChessPiece, ChessPieceLocation> chessPieceLocations;

  private String perspective;

  public ChessBoard() {
    whiteBoard = new PlayerChessBoard(PLAYER_COLOR_WHITE);
    blackBoard = new PlayerChessBoard(PLAYER_COLOR_BLACK);
    chessPieceLocations = new HashMap<>();
    perspective = PLAYER_COLOR_WHITE;

    initializePieceLocations(chessPieceLocations);
  }

  public String getPerspective() {
    return perspective;
  }

  public void setPerspective(final String perspective) {
    if (!(perspective.equalsIgnoreCase(PLAYER_COLOR_WHITE) || perspective.equalsIgnoreCase(PLAYER_COLOR_BLACK)))
      throw new IllegalStateException("Perspective must be either \"white\" or \"black\".");

    this.perspective = perspective;
  }

  /**
   * Setup board anew, used in constructor.
   * @param chessPieceLocations
   */
  static void initializePieceLocations(final Map<ChessPiece, ChessPieceLocation> chessPieceLocations) {
    int verticalWhiteLocation = 0;
    int horizontalWhiteLocation = 0;

    for (final ChessPiece nextChessPiece : ChessPieces.ALL_WHITE_CHESS_PIECES) {
      final int boardY = (verticalWhiteLocation + 1);
      final int boardX = (horizontalWhiteLocation + 1);
      final ChessPieceLocation chessPieceLocation = new ChessPieceLocation(boardX, boardY, PLAYER_COLOR_WHITE);

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
      final ChessPieceLocation chessPieceLocation = new ChessPieceLocation(boardX, boardY, PLAYER_COLOR_BLACK);

      chessPieceLocations.put(nextChessPiece, chessPieceLocation);

      horizontalBlackLocation += 1;

      if (horizontalBlackLocation >= 8) {
        horizontalBlackLocation = 0;
        verticalBlackLocation += 1;
      }
    }
  }

  /**
   * Call before rendering in toString.
   * @param whiteBoard
   * @param blackBoard
   * @param chessPieceLocations
   */
  public static void fillBoard(final PlayerChessBoard whiteBoard, final PlayerChessBoard blackBoard, final Map<ChessPiece, ChessPieceLocation> chessPieceLocations) {
    for (final Map.Entry<ChessPiece, ChessPieceLocation> entry : chessPieceLocations.entrySet()) {
      final ChessPiece chessPiece = entry.getKey();
      final ChessPieceLocation chessPieceLocation = entry.getValue();

      if (chessPiece.getColor().equals(PLAYER_COLOR_WHITE) && chessPieceLocation.getColor().equals(PLAYER_COLOR_WHITE)) {
        final int x = 8 - chessPieceLocation.getHorizontal();
        final int y = chessPieceLocation.getVertical() - 1;

        whiteBoard.setChessPieceAt(x, y, chessPiece);
      }
      else if (chessPiece.getColor().equals(PLAYER_COLOR_BLACK) && chessPieceLocation.getColor().equals(PLAYER_COLOR_BLACK)) {
        final int x = 8 - chessPieceLocation.getHorizontal();
        final int y = chessPieceLocation.getVertical() - 1;

        blackBoard.setChessPieceAt(x, y, chessPiece);
      }
      else {
        throw new IllegalStateException("Chess piece and location must contain the same color, either \"white\" or \"black\".");
      }
    }
  }

  String getPerspectiveTop() {
    if (perspective.equalsIgnoreCase(PLAYER_COLOR_WHITE)) {
      return "              BLACK          ";
    }
    else {
      return "              WHITE          ";
    }
  }

  String getPerspectiveBottom() {
    if (perspective.equalsIgnoreCase(PLAYER_COLOR_WHITE)) {
      return "              WHITE          ";
    }
    else {
      return "              BLACK          ";
    }
  }

  ChessPiece[] mergeBoards(final String perspective) {
    fillBoard(whiteBoard, blackBoard, chessPieceLocations);

    ChessPiece[] newSpaces = new ChessPiece[64];
    final ChessPiece[] newWhiteSpaces = whiteBoard.getSpaces();
    final ChessPiece[] blackSpaces = rotate(blackBoard.getSpaces());

    for (int i = 0; i < newSpaces.length; i++) {
      final ChessPiece whiteChessPiece = newWhiteSpaces[i];
      final ChessPiece blackChessPiece = blackSpaces[i];

      if (whiteChessPiece != null && blackChessPiece == null) {
        newSpaces[i] = whiteChessPiece;
      } else if (whiteChessPiece == null && blackChessPiece != null) {
        newSpaces[i] = blackChessPiece;
      } else if (whiteChessPiece != null && blackChessPiece != null) {
        System.err.format("Space %d is occupied by both %s and %s.%n", i, whiteChessPiece, blackChessPiece);
      } else {
        newSpaces[i] = null;
      }
    }

    if (perspective.equalsIgnoreCase(PLAYER_COLOR_BLACK)) {
      newSpaces = rotate(newSpaces);
    }

    return newSpaces;
  }

  @Override
  public String toString() {
    final ChessPiece[] spaces = mergeBoards(getPerspective());
    final StringBuilder sb = new StringBuilder(String.format("%s%n  1   2   3   4   5   6   7   8  %n", getPerspectiveTop()));

    for (int y = 7; y > -1; y--) {
      sb
        .append(y + 1)
        .append(" ");

      for (int x = 7; x > -1; x--) {
        final int renderIndex;

        renderIndex = (y * 8 + x);

        final ChessPiece chessPiece = spaces[renderIndex];

        if (chessPiece != null) {
          sb
            .append(chessPiece.getColor().equals(PLAYER_COLOR_WHITE) ? "W" : "B")
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
