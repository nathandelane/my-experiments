package com.github.nathandelane.experiments.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvMatch {

  public static boolean matches(final String csv, final long identifier) {
    final String regexp = String.format("(^%1$d$|%1$d$|%1$d,|,\\s+%1$d)", identifier);
    final Pattern pattern = Pattern.compile(regexp);
    final Matcher matcher = pattern.matcher(csv);

    return matcher.find();
  }

}
