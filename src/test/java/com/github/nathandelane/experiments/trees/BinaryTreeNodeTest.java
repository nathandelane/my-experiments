package com.github.nathandelane.experiments.trees;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeNodeTest {
	
	private BinaryTreeNode integerTree;
	
	@Before
	public void setupTree() {
		integerTree = new BinaryTreeNode(0);
	}
	
	@Test
	public void test() {
		BinaryTrees.push(integerTree, new BinaryTreeNode(1));
		BinaryTrees.push(integerTree, new BinaryTreeNode(2));
		BinaryTrees.push(integerTree, new BinaryTreeNode(3));
		BinaryTrees.push(integerTree, new BinaryTreeNode(4));
		BinaryTrees.push(integerTree, new BinaryTreeNode(5));
		BinaryTrees.push(integerTree, new BinaryTreeNode(6));
		
		BinaryTrees.traverseInOrder(integerTree);
	}

}
