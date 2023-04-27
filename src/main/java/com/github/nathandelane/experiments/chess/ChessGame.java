package com.github.nathandelane.experiments.chess;

public class ChessGame {

  public static void main(final String[] args) {
    final ChessBoard chessBoard = new ChessBoard();
    chessBoard.setPerspective("white");

    System.out.format("%s%n%n", chessBoard);
  }

}
