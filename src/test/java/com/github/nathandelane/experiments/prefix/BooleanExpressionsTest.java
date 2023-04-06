package com.github.nathandelane.experiments.prefix;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BooleanExpressionsTest {

  @Test
  public void testSomething() {
    final BooleanExpressions booleanExpressions = new BooleanExpressions();
    final List<String> tokens = booleanExpressions.parseExpression("(add 2 3)");

    assertTrue(tokens.size() == 3);
  }

}
