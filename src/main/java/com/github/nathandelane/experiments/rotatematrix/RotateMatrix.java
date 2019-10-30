package com.github.nathandelane.experiments.rotatematrix;

import java.util.Scanner;

public class RotateMatrix {
	
	static int numberOfRings(final int[][] matrix) {
		final int minDim = Math.min(matrix.length, matrix[0].length);
		
		return ((minDim / 2) + (minDim % 2));
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
	
	static int[][] matrixOfSameSize(final int[][] matrix) {
		final int width = matrix.length;
		final int height = matrix[0].length;
		final int[][] matrixOfSameSize = new int[width][height];
		
		return matrixOfSameSize;
	}
	
	static int[] nextSpaceInMatrixClockwise(final int ringWidth, final int ringHeight, final int column, final int row) {
		int newN = column;
		int newM = row;
		
		if (column + 1 == ringWidth) { // Right side checks
			if (row + 1 == ringHeight) { // Bottom
				newN -= 1;
			}
			else { // Top
				newM += 1;
			}
		}
		else if (column == 0) { // Left side
			if (row + 1 == ringHeight) { // Bottom
				newM -= 1;
			}
			else { // Top
				newN += 1;
			}
		}
		else if (column + 1 < ringWidth && column > 0) {
			if (row == 0) {
				newN += 1;
			}
			else {
				newN -= 1;
			}
		}
		else if (row + 1 < ringHeight && row > 0) {
			if (column == 0) {
				newM -= 1;
			}
			else {
				newM += 1;
			}
		}
		
		return new int[] { newN, newM };
	}
	
	static int[][] rotateMatrixClockwise(final int[][] matrix) {
		final int[][] rotatedMatrix = matrixOfSameSize(matrix);
		
		final int width = matrix.length;
		final int height = matrix[0].length;
		final int numberOfRings = numberOfRings(matrix);
		
		if (width > 1 || height > 1) {
			for (int ringNumber = 0; ringNumber < numberOfRings; ringNumber++) {
				final int ringWidth = (width - (ringNumber * 2));
				final int ringHeight = (height - (ringNumber * 2));
				
				// Top
				int m = 0;
				
				for (int n = 0; n < ringWidth; n++) {
					final int actualN = ringNumber + n;
					final int actualM = ringNumber + m;
					
					final int currentValue = matrix[actualN][actualM];
					final int[] newLocation = nextSpaceInMatrixClockwise(ringWidth, ringHeight, n, m);
					
					final int newN = newLocation[0];
					final int newM = newLocation[1];
					
					rotatedMatrix[newM][newN] = currentValue;
				}
				
				// Bottom
				m = ringHeight - 1;

				for (int n = 0; n < ringWidth; n++) {
					final int actualN = ringWidth - ringNumber - n - 1;
					final int actualM = ringNumber + m;
					
					final int currentValue = matrix[actualN][actualM];
					final int[] newLocation = nextSpaceInMatrixClockwise(ringWidth, ringHeight, n, m);
					
					final int newN = newLocation[0];
					final int newM = newLocation[1];
					
					rotatedMatrix[newM][newN] = currentValue;
				}

				// Left
				int n = 0;
				
				for (m = 0; m < ringWidth; m++) {
					final int actualN = ringNumber + n;
					final int actualM = ringNumber + m;
					
					final int currentValue = matrix[actualN][actualM];
					final int[] newLocation = nextSpaceInMatrixClockwise(ringWidth, ringHeight, n, m);
					
					final int newN = newLocation[0];
					final int newM = newLocation[1];
					
					rotatedMatrix[newM][newN] = currentValue;
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
		
		for (int m = 0; m < height; m++) {
			for (int n = 0; n < width; n++) {
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
			
			for (int m = 0; m < height; m++) {
				for (int n = 0; n < width; n++) {
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
