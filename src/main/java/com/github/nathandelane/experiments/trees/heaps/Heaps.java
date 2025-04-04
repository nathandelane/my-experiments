package com.github.nathandelane.experiments.trees.heaps;

import com.github.nathandelane.experiments.trees.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public final class Heaps {

	private Heaps() { }

	public static HeapNode heapify(final int[] values) {
		if (values.length == 0) return null;

		HeapNode root = new HeapNode(values[0]);

		for (int index = 1; index < values.length; index++) {
			HeapNode newNode = new HeapNode(values[index]);

			root = push(root, newNode);
		}

		return root;
	}

	public static HeapNode push(final HeapNode root, final HeapNode node) {
		HeapNode newRoot;

		final Queue<HeapNode> queue = new LinkedList<HeapNode>();
		queue.add(root);

		if (root != null) {
			newRoot = root;

			while (!queue.isEmpty()) {
				final HeapNode temp = queue.peek();

				queue.remove();

				if (temp.left == null) {
					if (node.value > temp.value) {
						temp.left = node;
					}
					else {
						node.left = temp;
					}
					break;
				}
				else {
					queue.add(temp.left);
				}

				if (temp.right == null) {
					temp.right = node;
					break;
				}
				else {
					queue.add(temp.right);
				}
			}
		}
		else {
			newRoot = node;
		}

		return newRoot;
	}

}
