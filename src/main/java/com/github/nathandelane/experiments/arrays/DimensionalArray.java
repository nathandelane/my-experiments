package com.github.nathandelane.experiments.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>A single class for generating multidimensional arrays of generic objects, from single-dimension up to n-dimensional
 * arrays, once I figure out how to accommodate them. For now this is a prototype with a fixed height of dimensions, using
 * a single-dimensional array and mathematical formula to map coordinates onto it.</p>
 * <p>
 * For two dimensions the math for identifying a corresponding index in a single-dimensional array identifies a row-width,
 * a row-number, and a column-index in the row. For example, to find the index of coordination (4, 3), meaning x (column)
 * index is 4, and y (row) index is 3, in a zero-based indexed array with virtual dimensions of 5x5 the formula is:
 * <div>
 *   <pre>(5 * 3) + 4 = 19</pre>
 * </div>
 * Or generically defined as:
 * <div>
 *   <pre>(rowWidth * y) + x</pre>
 * </div>
 * </p><p>
 * Visually you could identify this like so, where {@code X} represents the location:
 * <pre>
 * [
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, X,
 *   0, 0, 0, 0, 0
 * ]
 * </pre>
 * </p>
 * <p>
 *   In three dimensions things begin to become much more complicated. A three-dimensional cube of indices could look like the following
 *   with a set of x, y, and z coordinates, of 5x5x5:
 * <pre>
 * [
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0
 * ]
 * </pre>
 * In this example, x and y coordinates are inner-most, and z coordinates are the outer-most, i.e. each two-dimensional array is
 * contained in a z-coordinate. Thus, finding the coordinate (3, 2, 4) as an (x, y, z) tuple, we invert the tuple so that z represents
 * a set of 25 elements, a 5x5 array in the representation, and then we locate within that set the x and y coordinates in the same
 * way as before:
 * <div>
 *   <pre>(rowWidth * y) + x</pre>
 * </div>
 * Thus, we might get the following:
 * <div>
 *   <pre>(z * (rowWidth * columnHeight)) + (y * rowWidth) + x</pre>
 * </div>
 * In our example above we find the actual index as:
 * <div>
 *   <pre>(4 * (25)) + (2 * 5) + 3 = 113</pre>
 * </div>
 * Located as:
 * <pre>
 * [ -- 0
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0, -- 24
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0, -- 49
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0, -- 74
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0, -- 99
 *   --
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, X, 0, -- [113]
 *   0, 0, 0, 0, 0,
 *   0, 0, 0, 0, 0 -- 124
 * ]
 * </pre>
 * </p>
 * <p>
 *   It therefore seems pertinent that each dimension will incur a similar structure of multiplying the size of the foregoing
 *   array.
 * </p>
 * <p>
 *   For two dimensions we simply have: <pre>(rowWidth * y) + x</pre>.
 * </p>
 * <p>
 *   For three dimensions: <pre>(z * (yWidth * xWidth)) + (y * yWidth) + x</pre>
 * </p>
 * <p>
 *   For four dimensions: <pre>(k * (zWidth * yWidth * xWidth)) + (z * (yWidth * xWidth)) + (y * yWidth) + x</pre>
 * </p>
 * <p>And so on ...</p>
 * @param <T>
 */
public final class DimensionalArray<T> {

	// Visible for testing
	final static List<NIntFunction> LIST_OF_FUNCTIONS;
	static {
		LIST_OF_FUNCTIONS = new ArrayList<>(10);
		// One dimension
		LIST_OF_FUNCTIONS.add((dims, coord) -> coord);
		// Second dimension
		LIST_OF_FUNCTIONS.add((dims, coord) -> Math.multiplyExact(coord, dims[0]));
		// Third dimension
		LIST_OF_FUNCTIONS.add((dims, coord) -> Math.multiplyExact(
			coord,
			Math.multiplyExact(
				dims[0],
				dims[1]
			)
		));
		// Fourth
		LIST_OF_FUNCTIONS.add((dims, coord) -> Math.multiplyExact(
			coord,
			Math.multiplyExact(
				Math.multiplyExact(
					dims[0],
					dims[1]
				),
				dims[2]
			)
		));
		// Fifth
		LIST_OF_FUNCTIONS.add((dims, coord) -> Math.multiplyExact(
			coord,
			Math.multiplyExact(
				Math.multiplyExact(
					Math.multiplyExact(
						dims[0],
						dims[1]
					),
					dims[2]
				),
				dims[3]
			)
		));
		// Sixth
		LIST_OF_FUNCTIONS.add((dims, coord) -> Math.multiplyExact(
			coord,
			Math.multiplyExact(
				Math.multiplyExact(
					Math.multiplyExact(
						Math.multiplyExact(
							dims[0],
							dims[1]
						),
						dims[2]
					),
					dims[3]
				),
				dims[4]
			)
		));
	}

	private final T[] internal;

	private int[] dimensions;

	public DimensionalArray(final int ... dimensions) {
		if (dimensions.length > LIST_OF_FUNCTIONS.size()) throw new IllegalStateException(String.format("The maximum number of dimensions supported it %1$d, but you attempted to create a %2$d-dimensional array.", LIST_OF_FUNCTIONS.size(), dimensions.length));

		this.dimensions = dimensions;

		int totalSize = 1;

		for (int next : dimensions) {
			totalSize *= next;
		}

		this.internal = (T[]) new Object[totalSize];
	}

	public void fill(T fillValue) {
		Arrays.fill(internal, fillValue);
	}

	public void clear() {
		Arrays.fill(internal, (T) null);
	}

	public T get(final int ... dimN) {
		if (dimN.length != dimensions.length) throw new IllegalStateException(String.format("DimensionalArray instance has %1$d dimensions, therefore %1$d indices must be provided", dimensions.length));
		if (dimN.length >= LIST_OF_FUNCTIONS.size()) throw new IllegalStateException(String.format("Dimensions only supported up to %d.", LIST_OF_FUNCTIONS.size()));

		final int index = getIndex(dimN);

		return internal[index];
	}

	public void set(final T value, final int ... dimN) {
		if (dimN.length != dimensions.length) throw new IllegalStateException(String.format("DimensionalArray instance has %1$d dimensions, therefore %1$d indices must be provided", dimensions.length));
		if (dimN.length >= LIST_OF_FUNCTIONS.size()) throw new IllegalStateException(String.format("Dimensions only supported up to %d.", LIST_OF_FUNCTIONS.size()));

		final int index = getIndex(dimN);

		internal[index] = value;
	}

	public int getIndex(final int ... dimN) {
		int index = 0;

		for (int dimensionIndex = 0; dimensionIndex < dimN.length; dimensionIndex++) {
			index += LIST_OF_FUNCTIONS.get(dimensionIndex).apply(dimensions, dimN[dimensionIndex]);
		}

		if (index >= internal.length) throw new IllegalStateException(String.format("Index %1$d is greater than array size: %2$d.", index, internal.length));

		return index;
	}

	/**
	 * Special internal function type for handling matrix linearization.
	 */
	interface NIntFunction {

		/**
		 *
		 * @param dims Actual dimensions of internal array
		 * @param coord coordinate, e.g. {@code x}-coordinate or {@code y}-coordinate
		 * @return product
		 */
		int apply(int[] dims, int coord);

	}

}
