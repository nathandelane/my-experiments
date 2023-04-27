package com.github.nathandelane.experiments.chess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ChessPieces {

  private ChessPieces() { }

  public static final ChessPiece WHITE_ROOK_1 = new ChessPiece("Rook", "R1", "white");
  public static final ChessPiece WHITE_ROOK_2 = new ChessPiece("Rook", "R2", "white");

  public static final ChessPiece WHITE_KNIGHT_1 = new ChessPiece("Knight", "K1", "white");
  public static final ChessPiece WHITE_KNIGHT_2 = new ChessPiece("Knight", "K2", "white");

  public static final ChessPiece WHITE_BISHOP_1 = new ChessPiece("Bishop", "B1", "white");
  public static final ChessPiece WHITE_BISHOP_2 = new ChessPiece("Bishop", "B2", "white");

  public static final ChessPiece WHITE_KING = new ChessPiece("King", "K", "white");
  public static final ChessPiece WHITE_QUEEN = new ChessPiece("Queen", "Q", "white");

  public static final ChessPiece WHITE_PAWN_1 = new ChessPiece("Pawn", "P1", "white");
  public static final ChessPiece WHITE_PAWN_2 = new ChessPiece("Pawn", "P2", "white");
  public static final ChessPiece WHITE_PAWN_3 = new ChessPiece("Pawn", "P3", "white");
  public static final ChessPiece WHITE_PAWN_4 = new ChessPiece("Pawn", "P4", "white");
  public static final ChessPiece WHITE_PAWN_5 = new ChessPiece("Pawn", "P5", "white");
  public static final ChessPiece WHITE_PAWN_6 = new ChessPiece("Pawn", "P6", "white");
  public static final ChessPiece WHITE_PAWN_7 = new ChessPiece("Pawn", "P7", "white");
  public static final ChessPiece WHITE_PAWN_8 = new ChessPiece("Pawn", "P8", "white");

  public static final List<ChessPiece> ALL_WHITE_CHESS_PIECES = Collections.unmodifiableList(
    Arrays.asList(
      WHITE_ROOK_1, WHITE_KNIGHT_1, WHITE_BISHOP_1, WHITE_KING, WHITE_QUEEN, WHITE_BISHOP_2, WHITE_KNIGHT_2, WHITE_ROOK_2,
      WHITE_PAWN_1, WHITE_PAWN_2, WHITE_PAWN_3, WHITE_PAWN_4, WHITE_PAWN_5, WHITE_PAWN_6, WHITE_PAWN_7, WHITE_PAWN_8
    )
  );

  public static final ChessPiece BLACK_ROOK_1 = new ChessPiece("Rook", "R1", "black");
  public static final ChessPiece BLACK_ROOK_2 = new ChessPiece("Rook", "R2", "black");

  public static final ChessPiece BLACK_KNIGHT_1 = new ChessPiece("Knight", "K1", "black");
  public static final ChessPiece BLACK_KNIGHT_2 = new ChessPiece("Knight", "K2", "black");

  public static final ChessPiece BLACK_BISHOP_1 = new ChessPiece("Bishop", "B1", "black");
  public static final ChessPiece BLACK_BISHOP_2 = new ChessPiece("Bishop", "B2", "black");

  public static final ChessPiece BLACK_KING = new ChessPiece("King", "K", "black");
  public static final ChessPiece BLACK_QUEEN = new ChessPiece("Queen", "Q", "black");

  public static final ChessPiece BLACK_PAWN_1 = new ChessPiece("Pawn", "P1", "black");
  public static final ChessPiece BLACK_PAWN_2 = new ChessPiece("Pawn", "P2", "black");
  public static final ChessPiece BLACK_PAWN_3 = new ChessPiece("Pawn", "P3", "black");
  public static final ChessPiece BLACK_PAWN_4 = new ChessPiece("Pawn", "P4", "black");
  public static final ChessPiece BLACK_PAWN_5 = new ChessPiece("Pawn", "P5", "black");
  public static final ChessPiece BLACK_PAWN_6 = new ChessPiece("Pawn", "P6", "black");
  public static final ChessPiece BLACK_PAWN_7 = new ChessPiece("Pawn", "P7", "black");
  public static final ChessPiece BLACK_PAWN_8 = new ChessPiece("Pawn", "P8", "black");

  public static final List<ChessPiece> ALL_BLACK_CHESS_PIECES = Collections.unmodifiableList(
    Arrays.asList(
      BLACK_ROOK_1, BLACK_KNIGHT_1, BLACK_BISHOP_1, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP_2, BLACK_KNIGHT_2, BLACK_ROOK_2,
      BLACK_PAWN_1, BLACK_PAWN_2, BLACK_PAWN_3, BLACK_PAWN_4, BLACK_PAWN_5, BLACK_PAWN_6, BLACK_PAWN_7, BLACK_PAWN_8
    )
  );

}
