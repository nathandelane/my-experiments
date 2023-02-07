package com.github.nathandelane.experiments.interview;

import java.util.Arrays;

public class PalindromePhrase {

  private PalindromePhrase() { }

  private static final char[] ALL_LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
  private static final char[] ALL_UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

  private boolean charactersMatch(final char left, final char right) {
    if (left == right) return true;

    int indexOfLeft = Arrays.binarySearch(ALL_LOWER_CASE_LETTERS, left);
    int indexOfRight = Arrays.binarySearch(ALL_LOWER_CASE_LETTERS, right);

    if (indexOfLeft > 0 && indexOfRight > 0) {
      return indexOfLeft == indexOfRight;
    }

    indexOfLeft = Arrays.binarySearch(ALL_UPPER_CASE_LETTERS, left);
    indexOfRight = Arrays.binarySearch(ALL_UPPER_CASE_LETTERS, right);

    if (indexOfLeft > 0 && indexOfRight > 0) {
      return indexOfLeft == indexOfRight;
    }

    indexOfLeft = Arrays.binarySearch(ALL_LOWER_CASE_LETTERS, left);
    indexOfRight = Arrays.binarySearch(ALL_UPPER_CASE_LETTERS, right);

    if (indexOfLeft > 0 && indexOfRight > 0) {
      return indexOfLeft == indexOfRight;
    }

    indexOfLeft = Arrays.binarySearch(ALL_UPPER_CASE_LETTERS, left);
    indexOfRight = Arrays.binarySearch(ALL_LOWER_CASE_LETTERS, right);

    if (indexOfLeft > 0 && indexOfRight > 0) {
      return indexOfLeft == indexOfRight;
    }

    return false;
  }

  public boolean isPalindrome(final String phrase) {
    if (phrase == null || phrase.trim().equalsIgnoreCase("")) return false;
    if (phrase.trim().length() == 1) return true;

    final int phraseLength = phrase.length();

    int leftCursor = 0;
    int rightCursor = (phraseLength - 1);

    final int middleIndex = (phraseLength / 2);

    do {
      while (!Character.isLetterOrDigit(phrase.charAt(leftCursor))) {
        leftCursor++;
      }

      final char leftChar = phrase.charAt(leftCursor);

      while (!Character.isLetterOrDigit(phrase.charAt(rightCursor))) {
        rightCursor--;
      }

      final char rightChar = phrase.charAt(rightCursor);

      if (!charactersMatch(leftChar, rightChar)) return false;

      leftCursor++;
      rightCursor--;
    } while (leftCursor < middleIndex);

    return true;
  }

  public static void main(final String[] args) {
    final PalindromePhrase pp = new PalindromePhrase();

    final boolean isPalindrome1 = pp.isPalindrome("a");
    final boolean isPalindrome2 = pp.isPalindrome("the");
    final boolean isPalindrome3 = pp.isPalindrome(null);
    final boolean isPalindrome4 = pp.isPalindrome("   ");
    final boolean isPalindrome5 = pp.isPalindrome("racecar");
    final boolean isPalindrome6 = pp.isPalindrome("Racecar");
    final boolean isPalindrome7 = pp.isPalindrome("Don’t nod");
    final boolean isPalindrome8 = pp.isPalindrome("Dammit, I’m mad!");
  }

}
