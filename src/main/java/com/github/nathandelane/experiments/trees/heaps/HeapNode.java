package com.github.nathandelane.experiments.trees.heaps;

import java.util.Objects;

public final class HeapNode {

	public final int value;

	HeapNode left;

	HeapNode right;

	public HeapNode(final int value) {
		this.value = value;

		left = null;
		right = null;
	}

	public HeapNode(final HeapNode oldNode, final int newValue) {
		this.value = newValue;
		this.left = oldNode.left;
		this.right = oldNode.right;
	}

	@Override
	public String toString() {
		return new StringBuilder(value)
			.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HeapNode heapNode = (HeapNode) o;
		return value == heapNode.value && Objects.equals(left, heapNode.left) && Objects.equals(right, heapNode.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, left, right);
	}

}
