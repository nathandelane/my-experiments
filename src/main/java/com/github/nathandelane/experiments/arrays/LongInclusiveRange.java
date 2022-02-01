package com.github.nathandelane.experiments.arrays;

/**
 * Range of {@code long} values from {@link Long#MIN_VALUE} to {@link Long#MAX_VALUE}. The range is a continuous
 * sequence of {@code long} values with no breaks.
 */
public class LongInclusiveRange implements Range<Long> {

  private final long low;

  private final long high;

  public LongInclusiveRange(final long value) {
    this.low = this.high = value;
  }

  public LongInclusiveRange(final long start, final long end) {
    this.low = Math.min(start, end);
    this.high = Math.max(start, end);
  }

  public Long getLow() {
    return low;
  }

  public Long getHigh() {
    return high;
  }

  @Override
  public boolean isInRange(final Long value) {
    return Long.compare(value,low) >= 0 && Long.compare(value, high) <= 0;
  }

  @Override
  public <U extends Range<Long>> boolean isInRange(final U range) {
    return isInRange(range.getLow()) && isInRange(range.getHigh());
  }

  public boolean isNextToRangeLow(final long value) {
    return !isInRange(value) && ((low - value) == 1);
  }

  public boolean isNextToRangeHigh(final long value) {
    return !isInRange(value) && ((value - high) == 1);
  }

  public LongInclusiveRange addToEnd(final long addend) {
    return new LongInclusiveRange(low, (high + addend));
  }

  public LongInclusiveRange addToStart(final long subtrahend) {
    return new LongInclusiveRange((low - subtrahend), high);
  }

  public LongInclusiveRange concatenate(final LongInclusiveRange other) {
    if (isInRange(other)) {
      return this;
    }
    else {
      long newStart = this.low;
      long newEnd = this.high;

      if ((isInRange(other.low) && (other.high > this.high)) || isNextToRangeHigh(other.low)) {
        newEnd = other.high;
      }
      else if ((isInRange(other.high) && (other.low < this.low)) || isNextToRangeLow(other.high)) {
        newStart = other.low;
      }
      else {
        throw new IllegalStateException(String.format("There is a gap between this (%s) and the other range (%s).", this, other));
      }

      return new LongInclusiveRange(newStart, newEnd);
    }
  }

  @Override
  public String toString() {
    return String.format("{ \"start\": %s, \"end\": %s }", low, high);
  }

}
