package com.github.nathandelane.experiments.boxes;

/**
 * Tree of {@Box}es.
 */
public final class QuadTree {

	public final Node root;

	public QuadTree(final Node root) {
		this.root = root;
	}

	public QuadTree(final Box box) {
		this.root = new Node(box);
	}

	public QuadTree(final Point min, final Point max) {
		final Box box = Box.boundingBox(new Point[] { min, max });

		this.root = new Node(box);
	}

}
