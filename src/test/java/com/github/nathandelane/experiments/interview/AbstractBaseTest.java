package com.github.nathandelane.experiments.interview;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractBaseTest {
  
  @Before
  public final void baseSetUp() { // or any other meaningful name
    System.out.println("AbstractBaseTest.setUp");
  }

  @After
  public final void baseTearDown() { // or any other meaningful name
    System.out.println("AbstractBaseTest.tearDown");
  }
  
  public static class MyTest extends AbstractBaseTest {
    
    @Before
    public void setUp() {
      System.out.println("Test.setUp");
    }

    @After
    public void tearDown() {
      System.out.println("Test.tearDown");
    }

    @Test
    public void test1() throws Exception {
      System.out.println("test1");
    }

    @Test
    public void test2() throws Exception {
      System.out.println("test2");
    }

  }
  
}
