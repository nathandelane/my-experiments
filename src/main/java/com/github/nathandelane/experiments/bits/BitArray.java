package com.github.nathandelane.experiments.bits;

public class BitArray {

  private static final long[] MASK_CACHE = new long[] {
    0b0000000000000001, // 1
    0b0000000000000010, // 2
    0b0000000000000100, // 4
    0b0000000000001000, // 8
    0b0000000000010000, // 16
    0b0000000000100000, // 32
    0b0000000001000000, // 64
    0b0000000010000000, // 128
    0b0000000100000000, // 256
    0b0000001000000000, // 512
    0b0000010000000000, // 1024
    0b0000100000000000, // 1048
    0b0001000000000000  // 4096, 12 bits
  };

  private final long[] bits;

  private final long mask;

  public BitArray(final int numberOfElements, final int sizeOfElement) {
    final int length = numberOfElements * sizeOfElement;
    final int fullLongs = (length / 64);
    final int additionalBits = (length % 64) == 0 ? 0 : 1;

    this.bits = new long[(fullLongs + additionalBits)];
    this.mask = mask(sizeOfElement);
  }

  public long mask(final int power) {
    long mask = 0;
    return -1;
  }

  public int get(final int index) {
    return -1;
  }

}
