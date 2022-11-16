package com.github.nathandelane.experiments.lang;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static org.junit.Assert.assertTrue;

public class ArrayTests {

  @Test
  public void testArraySizes() {
    final int[] intArr = new int[1_000];
    final long[] longArr = new long[1_000];
    final Object[] objArr = new Object[1_000];

    System.out.println(VM.current().details());
    System.out.println("int[]: " + ClassLayout.parseInstance(intArr).toPrintable());
    System.out.println("long[]: " + ClassLayout.parseInstance(longArr).toPrintable());
    System.out.println("Object[]: " + ClassLayout.parseInstance(objArr).toPrintable());

    assertTrue(true);
  }

}
