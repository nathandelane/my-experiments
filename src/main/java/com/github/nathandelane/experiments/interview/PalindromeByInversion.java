package com.github.nathandelane.experiments.interview;

public class PalindromeByInversion {

  private static boolean isPalindrome(final String str) {
    if (str == null || str.length() <= 1) return true;

    final String omitSpaces = str.replaceAll("\\s", "");
    final int halfOfLength = omitSpaces.length() / 2;
    final String firstHalf = omitSpaces.substring(0, halfOfLength);
    final String lastHalf = omitSpaces.substring(omitSpaces.length() - halfOfLength);
    final StringBuilder lastHalfInverted = new StringBuilder();

    for (int index = lastHalf.length() - 1; index >= 0; index--)
      lastHalfInverted.append(lastHalf.charAt(index));

    return firstHalf.equalsIgnoreCase(lastHalfInverted.toString());
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
