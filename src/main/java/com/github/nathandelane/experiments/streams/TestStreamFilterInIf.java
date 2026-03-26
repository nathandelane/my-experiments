package com.github.nathandelane.experiments.streams;

import java.util.List;

import static com.github.nathandelane.experiments.streams.Utility.generateListOfLongs;

public class TestStreamFilterInIf {

  public static void main(final String[] args) {
    final List<Long> listOfLongs = generateListOfLongs(100);

    if (listOfLongs.stream().anyMatch(l -> (l % 2) == 0)) {
      System.out.format("The list contains even numbers.");
    }
    else {
      System.out.format("The list only contains odd numbers.");
    }
  }

}
