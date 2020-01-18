package com.github.nathandelane.experiments.trees;


public class BinaryTreeNode {
	
	private final int value;
	
	private BinaryTreeNode left;
	
	private BinaryTreeNode right;
	
	public BinaryTreeNode(final int value) {
		this.value = value;
	}
	
	public BinaryTreeNode getLeft() {
		return left;
	}
	
	public void setLeft(final BinaryTreeNode left) {
		this.left = left;
	}
	
	public BinaryTreeNode getRight() {
		return right;
	}
	
	public void setRight(final BinaryTreeNode right) {
		this.right = right;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("%s", value);
	}

}
