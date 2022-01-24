package com.github.nathandelane.experiments.arrays;

import java.util.ArrayList;
import java.util.List;

public class ReallyBigSieve {

  public static final long MAX_SIZE = (Long.valueOf(Integer.MAX_VALUE) * Long.valueOf(Integer.MAX_VALUE));

  private final List<boolean[]> data;

  private long size;

  public ReallyBigSieve() {
    data = new ArrayList<>();
    size = 0;
  }

  public ReallyBigSieve(final long numberOfElements) {
    this();

    if (numberOfElements > MAX_SIZE) {
      throw new IllegalStateException(String.format("Cannot create a ReallyBigSieve larger than %s.", MAX_SIZE));
    }

    size = numberOfElements;

    final long numberOfArrays = (numberOfElements / Long.valueOf(Integer.MAX_VALUE));
    final long remainder = (numberOfElements % Long.valueOf(Integer.MAX_VALUE));

    if (numberOfArrays > 0) {
      final boolean[] ba = new boolean[Integer.MAX_VALUE];

      for (long l = 0; l < numberOfArrays; l++) {
        data.add(ba.clone());
      }
    }

    if (remainder > 0) {
      final boolean[] rest = new boolean[(int) remainder];

      data.add(rest);
    }
  }

  public long size() {
    return size;
  }

}
