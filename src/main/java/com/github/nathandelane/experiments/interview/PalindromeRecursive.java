package com.github.nathandelane.experiments.interview;

public class PalindromeRecursive {

  public static boolean isPalindrome(final String phrase) {
    return checkIsPalindrome(phrase.replace(" ", ""), 0);
  }

  private static boolean checkIsPalindrome(final String phrase, final int distanceFromCenter) {
    if (phrase == null || phrase.length() == 0) return false;
    if (phrase.length() == 1) return true;
    if ((phrase.length() / 2) - 1 - distanceFromCenter < 0) return true;

    final char charLeft = phrase.charAt((phrase.length() / 2) - 1 - distanceFromCenter);
    final char charRight = phrase.charAt((phrase.length() / 2) + distanceFromCenter);

    if (charLeft != charRight)
      return false;

    checkIsPalindrome(phrase, distanceFromCenter + 1);

    return true;
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
