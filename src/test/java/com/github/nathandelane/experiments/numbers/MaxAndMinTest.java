package com.github.nathandelane.experiments.numbers;

import org.junit.Test;

import java.io.*;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MaxAndMinTest {

  @Test
  public void bothReturnCorrectlyForPositiveValues() {
    final int first = 203;
    final int second = 900_312;

    assertTrue(Integer.compare(MaxAndMin.max(first, second), MaxAndMin.mathMax(first, second)) == 0);
  }

  @Test
  public void generateTestData() throws IOException {
    final int min = 0;
    final int max = 9_000_000;
    final Random random = new Random();
    random.setSeed(System.currentTimeMillis());

    final File f = new File("MaxAndMinTest.dat");
    f.createNewFile();

    try (
      final FileOutputStream fos = new FileOutputStream(f);
      final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
      ) {
      for (int i = 0; i < 9_000_000; i++) {
        final int first = random.nextInt(max - min) + min;
        final int second = random.nextInt(max - min) + min;
        final String nextLine = String.format("%d,%d%n", first, second);

        bw.write(nextLine);
      }
    } catch (final FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }

    final long start1 = System.nanoTime();

    try (
      final FileInputStream fis = new FileInputStream(f);
      final BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    ) {
      String next = null;

      while ((next = br.readLine()) != null) {
        final String[] parts = next.split(",");
        final int first = Integer.parseInt(parts[0]);
        final int second = Integer.parseInt(parts[1]);

        MaxAndMin.max(first, second);
      }
    }

    final long end1 = System.nanoTime();

    final long start2 = System.nanoTime();

    try (
      final FileInputStream fis = new FileInputStream(f);
      final BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    ) {
      String next = null;

      while ((next = br.readLine()) != null) {
        final String[] parts = next.split(",");
        final int first = Integer.parseInt(parts[0]);
        final int second = Integer.parseInt(parts[1]);

        MaxAndMin.mathMax(first, second);
      }
    }

    final long end2 = System.nanoTime();

    System.out.format("Max test: %,d%nMathMax test: %,d%n", (end1 - start1), (end2 - start2));
  }

}
