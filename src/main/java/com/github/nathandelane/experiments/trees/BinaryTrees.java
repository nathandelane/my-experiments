package com.github.nathandelane.experiments.trees;

import java.util.LinkedList;
import java.util.Queue;

public final class BinaryTrees {

	public static BinaryTreeNode push(final BinaryTreeNode root, final BinaryTreeNode node) {
		final BinaryTreeNode newRoot;
		
		final Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.add(root);
		
		if (root != null) {
			newRoot = root;
			
			while (!queue.isEmpty()) {
				final BinaryTreeNode temp = queue.peek();
				
				queue.remove();
				
				if (temp.getLeft() == null) {
					temp.setLeft(node);
					break;
				}
				else {
					queue.add(temp.getLeft());
				}
				
				if (temp.getRight() == null) {
					temp.setRight(node);
					break;
				}
				else {
					queue.add(temp.getRight());
				}
			}
		}
		else {
			newRoot = node;
		}
		
		return newRoot;
	}
	
	public static BinaryTreeNode delete(final BinaryTreeNode root, final BinaryTreeNode node) {
		
		
		return root;
	}
	
	public static void traverseInOrder(final BinaryTreeNode node) {
		if (node == null) {
			return;
		}
		
		traverseInOrder(node.getLeft());
		
		System.out.format("%s ", node.getValue());
		
		traverseInOrder(node.getRight());
	}
	
}
