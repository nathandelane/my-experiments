package com.github.nathandelane.experiments.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class Utility {

  private Utility() { }

  public static List<Long> generateListOfLongs(final int size) {
    final List<Long> listOfLongs = new ArrayList<>();
    final Random random = new Random(System.currentTimeMillis());

    while (listOfLongs.size() < 100) {
      final Long l = random.nextLong();

      if (listOfLongs.contains(l)) continue;

      listOfLongs.add(l);
    }

    return Collections.unmodifiableList(listOfLongs);
  }

}
