package com.github.nathandelane.experiments.search;

public class AddressSearch {

  public static class PostalCodeRange {

    public final String low;
    public final String high;

    public PostalCodeRange(final String low, final String high) {
      if (low.compareTo(high) > 0) {
        this.low = high;
        this.high = low;
      }
      else {
        this.low = low;
        this.high = high;
      }
    }

    public boolean contains(final String postalCode) {
      final boolean postalCodeGreaterThanOrEqualToLow = postalCode.compareTo(low) >= 0;
      final boolean postalCodeLessThanOrEqualToHigh = postalCode.compareTo(high) <= 0;

      return postalCodeGreaterThanOrEqualToLow && postalCodeLessThanOrEqualToHigh;
    }

  }

  public static int hashTwoCharCountryCode(final String twoCharCountryCode) {
    final int char1 = twoCharCountryCode.charAt(0);
    final int char2 = twoCharCountryCode.charAt(1);

    //final String newNumber = String.format("%d%d", char1, char2);
    final int result = ((char1 << 5) + char2) - 2000;//Integer.parseInt(newNumber);

    return result;
  }

}
