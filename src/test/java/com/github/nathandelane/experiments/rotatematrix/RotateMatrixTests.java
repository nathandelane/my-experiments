package com.github.nathandelane.experiments.rotatematrix;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class RotateMatrixTests {

	@Test
	public void testNumberOfRings1x1() {
		final int[][] matrix = new int[][] { { 1 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 1);
	}
	
	@Test
	public void testNumberOfRings1x2() {
		final int[][] matrix = new int[][] { { 1, 2 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 1);
	}
	
	@Test
	public void testNumberOfRings2x1() {
		final int[][] matrix = new int[][] { { 1 }, { 2 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 1);
	}
	
	@Test
	public void testNumberOfRings2x2() {
		final int[][] matrix = new int[][] { { 1, 2 }, { 3, 4 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 1);
	}
	
	@Test
	public void testNumberOfRings2x3() {
		final int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 1);
	}
	
	@Test
	public void testNumberOfRings3x2() {
		final int[][] matrix = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 1);
	}
	
	@Test
	public void testNumberOfRings3x3() {
		final int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 2);
	}
	
	@Test
	public void testNumberOfRings3x4() {
		final int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 2);
	}
	
	@Test
	public void testNumberOfRings4x3() {
		final int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 2);
	}
	
	@Test
	public void testNumberOfRings4x4() {
		final int[][] matrix = new int[][] { { 1, 2, 3, 0 }, { 4, 5, 6, 0 }, { 7, 8, 9, 0 }, { 10, 11, 12, 0 } };
		final int numberOfRings = RotateMatrix.numberOfRings(matrix);
		
		assertTrue(numberOfRings == 2);
	}
	
	@Test
	public void testNextSpaceInMatrixClockwise2x2_0_0() {
		final int ringWidth = 2;
		final int ringHeight = 2;
		final int n = 0;
		final int m = 0;
		
		final int[] newPoint = RotateMatrix.nextSpaceInMatrixClockwise(ringWidth, ringHeight, n, m);
		
		assertTrue(Arrays.equals(new int[] { 1, 0 }, newPoint));
	}
	
	@Test
	public void testNextSpaceInMatrixClockwise2x2_1_0() {
		final int ringWidth = 2;
		final int ringHeight = 2;
		final int n = 1;
		final int m = 0;
		
		final int[] newPoint = RotateMatrix.nextSpaceInMatrixClockwise(ringWidth, ringHeight, n, m);
		
		assertTrue(Arrays.equals(new int[] { 1, 1 }, newPoint));
	}
	
	@Test
	public void testNextSpaceInMatrixClockwise2x2_1_1() {
		final int ringWidth = 2;
		final int ringHeight = 2;
		final int n = 1;
		final int m = 1;
		
		final int[] newPoint = RotateMatrix.nextSpaceInMatrixClockwise(ringWidth, ringHeight, n, m);
		
		assertTrue(Arrays.equals(new int[] { 0, 1 }, newPoint));
	}
	
	@Test
	public void testNextSpaceInMatrixClockwise2x2_0_1() {
		final int ringWidth = 2;
		final int ringHeight = 2;
		final int n = 0;
		final int m = 1;
		
		final int[] newPoint = RotateMatrix.nextSpaceInMatrixClockwise(ringWidth, ringHeight, n, m);
		
		assertTrue(Arrays.equals(new int[] { 0, 0 }, newPoint));
	}
	
	@Test
	public void testRotateMatrixClockwise1x1() {
		final int[][] matrix = new int[][] { { 1 } };
		final int[][] rotatedMatrix = RotateMatrix.rotateMatrixClockwise(matrix);
		
		assertTrue(Arrays.equals(matrix, rotatedMatrix));
	}
	
	@Test
	public void testRotateMatrixClockwise2x2() {
		final int[][] matrix = new int[][] { { 1, 2 }, { 4, 3 } };
		final int[][] expectedMatrix = new int[][] { { 4, 1 }, { 3, 2 } };
		final int[][] rotatedMatrix = RotateMatrix.rotateMatrixClockwise(matrix);
		
		for (int i = 0; i < rotatedMatrix.length; i++) {
			assertTrue(Arrays.equals(expectedMatrix[i], rotatedMatrix[i]));
		}
	}
	
	@Test
	public void testRotateMatrixClockwise2x1() {
		final int[][] matrix = new int[][] { { 1, 2 } };
		final int[][] expectedMatrix = new int[][] { { 2, 1 } };
		final int[][] rotatedMatrix = RotateMatrix.rotateMatrixClockwise(matrix);
		
		for (int i = 0; i < rotatedMatrix.length; i++) {
			assertTrue(Arrays.equals(expectedMatrix[i], rotatedMatrix[i]));
		}
	}
	
	@Test
	public void testRotateMatrixClockwise1x2() {
		final int[][] matrix = new int[][] { { 1 }, { 2 } };
		final int[][] expectedMatrix = new int[][] { { 2 }, { 1 } };
		final int[][] rotatedMatrix = RotateMatrix.rotateMatrixClockwise(matrix);
		
		for (int i = 0; i < rotatedMatrix.length; i++) {
			assertTrue(Arrays.equals(expectedMatrix[i], rotatedMatrix[i]));
		}
	}
	
	@Test
	public void testRotateMatrixClockwise4x4() {
		final int[][] matrix = new int[][] { { 1, 2, 3, 4}, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 12, 14, 15, 16 } };
		final int[][] expectedMatrix = new int[][] { { 5, 1, 2, 3 }, { 9, 10, 6, 4 }, { 13, 11, 7 , 8 }, { 14, 15, 16, 12 } };
		final int[][] rotatedMatrix = RotateMatrix.rotateMatrixClockwise(matrix);
		
		assertTrue(RotateMatrix.matricesAreEqual(expectedMatrix, rotatedMatrix));
	}
	
	@Test
	public void testRotateMatrixClockwise10x2() {
		final int[][] matrix = new int[][] {{3, 6}, {2, 5}, {1, 5}, {2, 4}, {1, 9}, {8, 9}, {6, 1}, {8, 9}, {6, 6}, {6, 7}};
		final int[][] expectedMatrix = new int[][] {{3, 6}, {5, 5}, {1, 5}, {4, 4}, {1, 9}, {9, 9}, {6, 1}, {9, 9}, {6, 6}, {7, 7}};
		
		int[][] rotatedMatrix = RotateMatrix.deepCopyMatrix(matrix);
		
		for (int x = 0; x < 5; x++) {
			rotatedMatrix = RotateMatrix.deepCopyMatrix(RotateMatrix.rotateMatrixClockwise(rotatedMatrix));
		}
		
		final boolean areEqual = RotateMatrix.matricesAreEqual(expectedMatrix, rotatedMatrix);
		
		assertTrue(areEqual);
	}

}
