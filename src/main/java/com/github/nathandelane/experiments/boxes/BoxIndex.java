package com.github.nathandelane.experiments.boxes;

public final class BoxIndex {

	public final int rowIndex;

	public final int columnIndex;

	private BoxIndex(final int rowIndex, final int columnIndex) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	public static final BoxIndex UPPER_LEFT = new BoxIndex(0, 0);

	public static final BoxIndex UPPER_RIGHT = new BoxIndex(0, 1);

	public static final BoxIndex LOWER_LEFT = new BoxIndex(1, 0);

	public static final BoxIndex LOWER_RIGHT = new BoxIndex(1, 1);

}
