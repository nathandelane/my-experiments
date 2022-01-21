package com.github.nathandelane.experiments.arrays;

/**
 * Range of {@code long} values from {@link Long#MIN_VALUE} to {@link Long#MAX_VALUE}. The range is a continuous
 * sequence of {@code long} values wiith no breaks.
 */
public class LongInclusiveRange {

  private final long start;

  private final long end;

  public LongInclusiveRange(final long value) {
    this.start = this.end = value;
  }

  public LongInclusiveRange(final long start, final long end) {
    this.start = Math.min(start, end);
    this.end = Math.max(start, end);
  }

  public long getStart() {
    return start;
  }

  public long getEnd() {
    return end;
  }

  public boolean isInRange(final long value) {
    return Long.compare(value, start) >= 0 && Long.compare(value, end) <= 0;
  }

  public boolean isInRange(final LongInclusiveRange range) {
    return Long.compare(range.start, start) >= 0 && Long.compare(range.end, end) <= 0;
  }

  public boolean isNextToRangeStart(final long value) {
    return !isInRange(value) && ((value < start) && ((start - value) == 1));
  }

  public boolean isNextToRangeEnd(final long value) {
    return !isInRange(value) && ((value > end) && ((value - end) == 1));
  }

  public LongInclusiveRange addToEnd(final long addend) {
    return new LongInclusiveRange(start, (end + addend));
  }

  public LongInclusiveRange addToStart(final long subtrahend) {
    return new LongInclusiveRange((start - subtrahend), end);
  }

  public LongInclusiveRange conccatenate(final LongInclusiveRange other) {
    if (isInRange(other)) {
      return this;
    }
    else {
      long newStart = this.start;
      long newEnd = this.end;

      if ((isInRange(other.start) && (other.end > this.end)) || isNextToRangeEnd(other.start)) {
        newEnd = other.end;
      }
      else if ((isInRange(other.end) && (other.start < this.start)) || isNextToRangeStart(other.end)) {
        newStart = other.start;
      }
      else {
        throw new IllegalStateException(String.format("There is a gap between this (%s) and the other range (%s).", this, other));
      }

      return new LongInclusiveRange(newStart, newEnd);
    }
  }

  @Override
  public String toString() {
    return String.format("{ \"start\": %s, \"end\": %s }", start, end);
  }

}
