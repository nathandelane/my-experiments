package com.github.nathandelane.experiments.chess;

import java.util.Objects;

import static com.github.nathandelane.experiments.chess.Constants.PLAYER_COLOR_BLACK;
import static com.github.nathandelane.experiments.chess.Constants.PLAYER_COLOR_WHITE;

public class ChessPiece {

  private final String name;

  private final String symbol;

  private final String color;

  public ChessPiece(final String name, final String symbol, final String color) {
    this.name = name;
    this.symbol = symbol;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChessPiece that = (ChessPiece) o;
    return Objects.equals(name, that.name) && Objects.equals(symbol, that.symbol) && Objects.equals(color, that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, symbol, color);
  }

  @Override
  public String toString() {
    return "ChessPiece{" +
      "name='" + name + '\'' +
      ", symbol='" + symbol + '\'' +
      ", color='" + color + '\'' +
      '}';
  }

  public static boolean isBlack(final String playerColor) {
    return PLAYER_COLOR_BLACK.equalsIgnoreCase(playerColor.trim());
  }

  public static boolean isWhite(final String playerColor) {
    return PLAYER_COLOR_WHITE.equalsIgnoreCase(playerColor.trim());
  }

}
