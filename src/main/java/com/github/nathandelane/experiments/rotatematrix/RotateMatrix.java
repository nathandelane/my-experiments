package com.github.nathandelane.experiments.rotatematrix;

import java.util.Scanner;

public class RotateMatrix {
	
	static int numberOfRings(final int[][] matrix) {
		final int minDim = Math.min(matrix.length, matrix[0].length);
		
		return ((minDim / 2) + (minDim % 2));
	}
	
	static int[] adjacentClockwise(final int width, final int height, final int n, final int m) {
		int newN = n;
		int newM = m;
		
		if (width > 1 && height > 1) {
			if (n + 1 == width) { // Right side of matrix
				if (m + 1 == height) { // Bottom of matrix
					newN -= 1;
				}
				else { // Before bottom right corner of matrix
					newM += 1;
				}
			}
			else if (n == 0) { // Left side of matrix
				if (m + 1 == height || m > 0) { // Bottom or middle of left side
					newM -= 1;
				}
				else {
					newN += 1;
				}
			}
			else {
				if (m == 0) {
					newN += 1;
				}
				else {
					newN -= 1;
				}
			}
		}
		else {
			if (width == 1) {
				if (m + 1 == height) {
					newM = 0;
				}
				else {
					newM += 1;
				}
			}
			else if (height == 1) {
				if (n + 1 == width) {
					newN = 0;
				}
				else {
					newN += 1;
				}
			}
		}
		
		return new int[] { newN, newM };
	}
	
	static int[][] deepCopyMatrix(final int[][] matrix) {
		final int width = matrix.length;
		final int height = matrix[0].length;
		final int[][] deepCopy = new int[width][height];
		
		for (int n = 0; n < width; n++) {
			for (int m = 0; m < height; m++) {
				deepCopy[n][m] = matrix[n][m];
			}
		}
		
		return deepCopy;
	}
	
	static int[][] rotateMatrixClockwise(final int[][] matrix) {
		final int[][] rotatedMatrix = deepCopyMatrix(matrix);
		final int numberOfRings = numberOfRings(matrix);
		
		final int width = matrix.length;
		final int height = matrix[0].length;
		
		if (width > 1 || height > 1) {
			for (int ringNumber = 0; ringNumber < numberOfRings; ringNumber++) {
				final int ringWidth = width - (ringNumber * 2);
				final int ringHeight = height - (ringNumber * 2);
				
				for (int n = ringNumber; n < ringWidth; n++) {
					for (int m = ringNumber; m < ringHeight; m++) {
						final int[] nextLocation = adjacentClockwise(width, height, n, m);
						
						rotatedMatrix[n][m] = matrix[nextLocation[0]][nextLocation[1]];
					}
				}
			}
		}
		
		return rotatedMatrix;
	}
	
	static String matrixToString(final int[][] matrix) {
		final StringBuilder sb = new StringBuilder();
		
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				sb.append(matrix[x][y]).append(' ');
			}
			
			sb.append(String.format("%n"));
		}
		
		return sb.toString();
	}
	
	static boolean matricesAreEqual(final int[][] expected, final int[][] rotated) {
		final int width = expected.length;
		final int height = expected[0].length;
		
		for (int n = 0; n < width; n++) {
			for (int m = 0; m < height; m++) {
				if (rotated[n][m] != expected[n][m]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static int executeTestCase(final int[][] matrix, final int numberOfRotations) {
		int[][] rotatedMatrix = deepCopyMatrix(matrix);
		
		for (int x = 0; x < numberOfRotations; x++) {
			rotatedMatrix = deepCopyMatrix(rotateMatrixClockwise(rotatedMatrix));
		}
		
		final int result = (matricesAreEqual(matrix, rotatedMatrix) ? 1 : 0);
		
		return result;
	}
	
	public static void main(String[] args) {
		final Scanner input = new Scanner(System.in);
		final int numberOfTests = input.nextInt();
		final StringBuilder testResults = new StringBuilder();
		
		for (int testNumber = 0; testNumber < numberOfTests; testNumber++) {
			final int width = input.nextInt();
			final int height = input.nextInt();
			final int[][] matrix = new int[width][height];
			
			for (int n = 0; n < width; n++) {
				for (int m = 0; m < height; m++) {
					matrix[n][m] = input.nextInt();
				}
			}
			
			final int numberOfRotations = input.nextInt();
			final int testResult = executeTestCase(matrix, numberOfRotations);
			
			testResults.append(testResult).append(String.format("%n"));
			
//			final String matrixAsString = matrixToString(rotatedMatrix);
		}
		
		input.close();
		
		System.out.format("%s", testResults);
	}

}
