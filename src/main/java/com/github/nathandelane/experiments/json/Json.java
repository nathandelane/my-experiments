package com.github.nathandelane.experiments.json;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Json {

  public static List<String> parse(final String jsonString) {
    final List<String> tokens = new ArrayList<>();
    final int lengthOfJson = jsonString.length();
    final AtomicInteger currentCharIndex = new AtomicInteger(0);

    while (currentCharIndex.get() < lengthOfJson) {
      final int index = currentCharIndex.get();
      final char nextChar = jsonString.charAt(index);

      
    }

    return tokens;
  }

  private static boolean isOpenObjectBrace(final char c) {
    return c == '{';
  }

  private static boolean isCloseObjectBrace(final char c) {
    return c == '}';
  }

  private static boolean isQuote(final char c) {
    return c == '"' || c == '\'';
  }

  private static boolean isWhiteSpace(final char c) {
    return c == ' ' || c == '\t' || c == '\r' || c == '\n';
  }

  private static boolean isComma(final char c) {
    return c == ',';
  }

  private static boolean isOpenArrayBrace(final char c) {
    return c == '[';
  }

  private static boolean isCloseArrayBrace(final char c) {
    return c == ']';
  }

  private static boolean isSemiColon(final char c) {
    return c == ';';
  }

  private static boolean isColon(final char c) {
    return c == ':';
  }

}
