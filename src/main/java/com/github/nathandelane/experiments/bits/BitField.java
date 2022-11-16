package com.github.nathandelane.experiments.bits;

public class BitField {

  private final byte[] bytes;

  public BitField(final int length) {
    this.bytes = new byte[((length / 8) + (length % 8))];
  }

  public BitField(final int numberOfElements, final int sizeOfElement) {
    this(numberOfElements * sizeOfElement);
  }

}
