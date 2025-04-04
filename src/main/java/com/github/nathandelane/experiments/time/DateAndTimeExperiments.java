package com.github.nathandelane.experiments.time;

import java.util.Calendar;
import java.util.Date;

public final class DateAndTimeExperiments {

  private static void showCurrentTime() {
    final Date date = new Date();
    final Calendar calendar = Calendar.getInstance();
    final long systemTimeInMillis = System.currentTimeMillis();

    System.out.format("Date date: %s%n", date);
    System.out.format("Calendar calendar: %s%n", calendar);
    System.out.format("Current time millis: %d%n", systemTimeInMillis);
  }

  public static void main(final String[] args) {
    showCurrentTime();
  }

}
