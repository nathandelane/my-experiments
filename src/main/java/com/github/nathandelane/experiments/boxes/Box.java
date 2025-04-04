package com.github.nathandelane.experiments.boxes;

/**
 * An AABB axis-aligned box that cannot be rotated.
 */
public final class Box {

	private Point min;

	private Point max;

	public Box() {
		min = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
		max = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	public Box extend(final Point p) {
		min.x = (min.x == Double.NEGATIVE_INFINITY) ? p.x : Math.min(min.x, p.x);
		min.y = (min.y == Double.NEGATIVE_INFINITY) ? p.y : Math.min(min.y, p.y);
		max.x = (max.x == Double.POSITIVE_INFINITY) ? p.x : Math.max(max.x, p.x);
		max.y = (max.y == Double.POSITIVE_INFINITY) ? p.y : Math.max(max.y, p.y);

		return this;
	}

	public static Box boundingBox(final Point ... points) {
		Box result = new Box();

		for (final Point next : points) {
			result = result.extend(next);
		}

		return result;
	}

}
