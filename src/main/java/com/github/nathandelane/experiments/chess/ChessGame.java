package com.github.nathandelane.experiments.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.github.nathandelane.experiments.chess.Constants.PLAYER_COLOR_BLACK;
import static com.github.nathandelane.experiments.chess.Constants.PLAYER_COLOR_WHITE;

public class ChessGame {

  private final ChessBoard chessBoard;

  private final AtomicBoolean isWhitesTurn;

  private ChessGame() {
    chessBoard = new ChessBoard();
    isWhitesTurn = new AtomicBoolean(true);
  }

  private String getUserInput(final String prompt) {
    final StringBuilder sb = new StringBuilder();

    System.out.format("%s ", prompt);

    try {
      final InputStreamReader isr = new InputStreamReader(System.in);
      final BufferedReader br = new BufferedReader(isr);
      final String userInput = br.readLine();

      sb.append(userInput);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }

    return sb.toString().trim().toUpperCase();
  }

  private String generatePrompt() {
    if (isWhitesTurn.get()) {
      return "WHITE:>";
    }
    else {
      return "BLACK:>";
    }
  }

  private void changePlayer() {
    isWhitesTurn.set(!isWhitesTurn.get());
  }

  private String getCurrentPlayerPerspective() {
    if (isWhitesTurn.get()) {
      return PLAYER_COLOR_WHITE;
    }
    else {
      return PLAYER_COLOR_BLACK;
    }
  }

  private boolean isValidSyntax(final String command) {
    boolean isValid = true;

    final String[] parts = command.split("\\s+");

    if (
      (isWhitesTurn.get() && !parts[0].startsWith("W"))
      || (!isWhitesTurn.get() && !parts[0].startsWith("B"))
    ) {
      isValid = false;
    }

    return isValid;
  }

  private void output(final String formatString, final Object ... args) {
    System.out.format(formatString, args);
  }

  private void getHelp(final String subject) {
    if (subject == null || subject.isBlank()) {

    }
  }

  private void runGame() {
    while (true) {
      chessBoard.setPerspective(getCurrentPlayerPerspective());

      output("%s%n%n", chessBoard);

      final String userInput = getUserInput(generatePrompt());

      if (userInput.isBlank()) continue;
      if (userInput.equalsIgnoreCase("Q")) break;
      if (userInput.equalsIgnoreCase("?") || userInput.equalsIgnoreCase("help")) getHelp(null);

      output("You entered: %s%n%n", userInput);

      if (!isValidSyntax(userInput)) {
        output("This is not valid for your player: %s%n%n", userInput);
      }

      changePlayer();
    }
  }

  public static void main(final String[] args) {
    final ChessGame chessGame = new ChessGame();
    chessGame.runGame();
  }

}
