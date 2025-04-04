package com.github.nathandelane.experiments.boxes;

import org.jetbrains.annotations.NotNull;

public final class Point implements Comparable<Point> {

	public double x;

	public double y;

	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public static Point middle(final Point p1, final Point p2) {
		return new Point(((p1.x + p2.x) / 2.0), ((p1.y + p2.y) / 2.0));
	}

	/**
	 * Compares two points. If:
	 * <ul>
	 *   <li>{@code this.x < o.x || this.y < o.y} then the result is {@code -1}, meaning this is to the left or above the other {@code Point}</li>
	 *   <li>{@code this.x > o.x || this.y > o.y} then the result is {@code 1}, meaning this is to the right or below the other {@code Point}</li>
	 *   <li>Otherwise the result is zero, meaning the points are the same in value</li>
	 * </ul>
	 *
	 * @param o the object to be compared.
	 * @return
	 */
	@Override
	public int compareTo(@NotNull final Point o) {
		final int result;

		if (this.x < o.x || this.y < o.y) {
			result = -1;
		}
		else if (this.x > o.x || this.y > o.y) {
			result = 1;
		}
		else {
			result = 0;
		}

		return result;
	}
}
