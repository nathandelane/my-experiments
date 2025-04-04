package com.github.nathandelane.experiments.collect;

import java.util.ArrayList;
import java.util.List;

public class FlatRangeList {

  private static final int DEFAULT_LENGTH = 100;

  private final List<Long> rangeStart;

  private final List<Long> rangeEnd;

  public FlatRangeList() {
    this.rangeStart = new ArrayList<>(DEFAULT_LENGTH);
    this.rangeEnd = new ArrayList<>(DEFAULT_LENGTH);
  }

  public FlatRangeList(final int startingSize) {
    this.rangeStart = new ArrayList<>(startingSize);
    this.rangeEnd = new ArrayList<>(startingSize);
  }

  public FlatRangeList add(final long start, final long end) {
    final long actualStart = Math.min(start, end);
    final long actualEnd = Math.max(end, start);

    if (rangeStart.isEmpty() && rangeEnd.isEmpty()) {
      rangeStart.add(actualStart);
      rangeEnd.add(actualEnd);
    }
    else {
      final int indexOfClosestRangeLeft = findClosestRangeLeft(actualStart);

      if (true
//        rangeEnd.get(indexOfClosestRangeLeft) < actualStart
      ) {
        rangeEnd.set(indexOfClosestRangeLeft, actualEnd);
      }
    }

    return this;
  }

  public int findClosestRangeLeft(final long actualStart) {
    int index = 0;

    while (rangeStart.get(0).compareTo(actualStart) > 0) {
      index += 1;
    }

    return index;
  }

}
