package com.github.nathandelane.experiments.interview;

public class Palindrome {

  private static boolean isPalindrome(final String phrase) {
    boolean result = false;

    if (phrase != null && !phrase.trim().equals("")) {
      if (phrase.length() == 1) {
        result = true;
      }
      else {
        final String updatedPhrase = phrase.replace(" ", "");
        final int remainingHalfLength = updatedPhrase.length() / 2;

        int d = 0;
        while (d <= remainingHalfLength) {
          final int index1 = (remainingHalfLength - d);
          final char c1 = updatedPhrase.charAt(index1);
          final int index2 = (remainingHalfLength + d);
          final char c2 = updatedPhrase.charAt(index2);

          if (c1 != c2) {
            break;
          }

          d++;
        }

        result = (d == remainingHalfLength);
      }
    }

    return result;
  }

  public static void main(final String[] args) {
    final String phrase1 = "this is not a palindrome";
    final boolean result1 = isPalindrome(phrase1);

    final String phrase2 = "never odd or even";
    final boolean result2 = isPalindrome(phrase2);

    System.out.format("Phrase 1: \"%s\": is a palindrome? %s%n", phrase1, result1);
    System.out.format("Phrase 2: \"%s\": is a palindrome? %s%n", phrase2, result2);
  }

}
