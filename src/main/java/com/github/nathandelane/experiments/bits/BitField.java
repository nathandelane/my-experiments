package com.github.nathandelane.experiments.bits;

import java.util.HashMap;
import java.util.Map;

public class BitField {

  private final long[] bits;
  private final Map<String, Integer> fieldMap;

  public BitField(final int length) {
    final int fullLongs = (length / 64);
    final int additionalBits = (length % 64) == 0 ? 0 : 1;
    this.bits = new long[(fullLongs + additionalBits)];
    this.fieldMap = new HashMap<>();
  }

  public BitField(final int numberOfElements, final int sizeOfElement) {
    this(numberOfElements * sizeOfElement);
  }

}
