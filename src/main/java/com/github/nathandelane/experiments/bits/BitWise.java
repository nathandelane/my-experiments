package com.github.nathandelane.experiments.bits;

public class BitWise {

  private long field64;

  public BitWise() {
    field64 = 0L;
  }

  public long set(final int n) {
    if (n >= 64) throw new IllegalStateException("n cannot be greater than 63.");
    if (n < 0) throw new IllegalStateException("n cannot be les than 0.");

    field64 |= (1 << n);

    return field64;
  }

  public long clear(final int n) {
    if (n >= 64) throw new IllegalStateException("n cannot be greater than 63.");
    if (n < 0) throw new IllegalStateException("n cannot be les than 0.");

    field64 &= ~(1 << n);

    return field64;
  }

  public long toggle(final int n) {
    if (n >= 64) throw new IllegalStateException("n cannot be greater than 63.");
    if (n < 0) throw new IllegalStateException("n cannot be les than 0.");

    field64 ^= (1 << n);

    return field64;
  }

  public int read(final int n) {
    if (n >= 64) throw new IllegalStateException("n cannot be greater than 63.");
    if (n < 0) throw new IllegalStateException("n cannot be les than 0.");

    long val = (field64 >> n) & 1;

    return (int) val;
  }

  public long all() {
    return field64;
  }

}
