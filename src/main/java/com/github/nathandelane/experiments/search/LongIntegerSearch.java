package com.github.nathandelane.experiments.search;

import com.github.nathandelane.experiments.arrays.LongInclusiveRange;

import java.util.LinkedList;
import java.util.List;

public class LongIntegerSearch {

  private final List<LongInclusiveRange> listOfRanges;

  public LongIntegerSearch() {
    listOfRanges = new LinkedList<>();
  }

  public void addInteger(final long value) {
    if (listOfRanges.size() == 0) {
      listOfRanges.add(new LongInclusiveRange(value));
    }
    else {
      for (int i = 0; i < listOfRanges.size(); i++) {
        final LongInclusiveRange nextRange = listOfRanges.get(i);
      }
    }
  }

  public void addIntegers(final long[] values) {
    if (values == null || values.length == 0) {
      return;
    }
  }

}
