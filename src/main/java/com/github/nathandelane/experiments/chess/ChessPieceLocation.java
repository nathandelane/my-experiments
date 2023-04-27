package com.github.nathandelane.experiments.chess;

import java.util.Objects;

public class ChessPieceLocation {

  private final int horizontal;

  private final int vertical;

  private final String color;

  public ChessPieceLocation(final int horizontal, final int vertical, final String color) {
    this.horizontal = horizontal;
    this.vertical = vertical;
    this.color = color;
  }

  public int getHorizontal() {
    return horizontal;
  }

  public int getVertical() {
    return vertical;
  }

  public String getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChessPieceLocation that = (ChessPieceLocation) o;
    return horizontal == that.horizontal && vertical == that.vertical && Objects.equals(color, that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(horizontal, vertical, color);
  }

  @Override
  public String toString() {
    return "ChessPieceLocation{" +
      "horizontal=" + horizontal +
      ", vertical=" + vertical +
      ", color='" + color + '\'' +
      '}';
  }

}
