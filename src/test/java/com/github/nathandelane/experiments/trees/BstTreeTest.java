package com.github.nathandelane.experiments.trees;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BstTreeTest {

  @Test
  public void addTestEmptyTree() {
    BstTree<Integer> bstTree = new BstTree<>();

    bstTree.add(10);

    assertTrue(false);
  }

}
