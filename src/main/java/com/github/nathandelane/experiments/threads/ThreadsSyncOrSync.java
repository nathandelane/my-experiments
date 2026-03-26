package com.github.nathandelane.experiments.threads;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ThreadsSyncOrSync {

  private static final int MAX_THREADS = 10_000;

  public static void main(final String[] args) throws InterruptedException {
    System.out.println("CPU Core: " + Runtime.getRuntime().availableProcessors());
    System.out.println("CommonPool Parallelism: " + ForkJoinPool.commonPool().getParallelism());
    System.out.println("CommonPool Common Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

    final Random r = new Random();
    final int[] random = r.ints(MAX_THREADS, 5, 25).toArray();

    for (final int i : random) {
      CompletableFuture.runAsync(() -> {
        doSomethingHard(i);
      });
    }

    ForkJoinPool.commonPool().awaitTermination(5_000, TimeUnit.SECONDS);
  }

  public static void doSomethingHard(final int whatToFactorial) {
    BigInteger p = BigInteger.valueOf(whatToFactorial);

    for (int t = whatToFactorial; t > 1; t--) {
      p = p.multiply(BigInteger.valueOf(t));
    }

    System.out.format("What to factorial? %d; result=%s%n", whatToFactorial, p);
  }

}
