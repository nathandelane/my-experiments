package com.github.nathandelane.experiments.bits;

public class BitArray {

  private static final long[] MASK_CACHE = new long[] {
    1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096 // 12
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
