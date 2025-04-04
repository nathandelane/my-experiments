package com.github.nathandelane.experiments.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GeneralTree<T> {

	public final GeneralTreeNode<T> root;

	public GeneralTree() {
		this.root = new GeneralTreeNode<>(null);
	}

	public static class GeneralTreeNode<T> {

		public final T value;

		private final List<GeneralTreeNode<T>> children;

		public GeneralTreeNode(final T value) {
			this.value = value;
			this.children = new ArrayList<>();
		}

		public List<GeneralTreeNode<T>> getChildren() {
			return Collections.unmodifiableList(children);
		}

	}

}
