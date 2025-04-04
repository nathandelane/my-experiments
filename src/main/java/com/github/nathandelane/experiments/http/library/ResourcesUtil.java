package com.github.nathandelane.experiments.http.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class ResourcesUtil {

  private ResourcesUtil() { }

  public static String loadResource(final String resourceFileName) throws IOException {
    final StringBuilder sb = new StringBuilder();

    try (
      final InputStream is = ResourcesUtil.class.getClassLoader().getResourceAsStream(resourceFileName);
      final InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      final BufferedReader reader = new BufferedReader(streamReader)
    ) {
      String line;

      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (final IOException e) {
      e.printStackTrace();

      throw e;
    }

    return sb.toString();
  }

}
