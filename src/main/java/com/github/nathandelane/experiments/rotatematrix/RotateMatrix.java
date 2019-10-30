package com.github.nathandelane.experiments.rotatematrix;

import java.util.Scanner;

/**
 * https://cdnpractice.geeksforgeeks.org/problems/lets-play/0
 * @author nathanlane
 *
 */
public class RotateMatrix {
	
	static int numberOfLoops(final int[][] matrix) {
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
	
	static int loopWidth(final int[][] matrix, final int loopNumber) {
		return matrix[0].length - (2 * loopNumber);
	}
	
	static int loopHeight(final int[][] matrix, final int loopNumber) {
		return matrix.length - (2 * loopNumber);
	}
	
	static int loopLength(final int[][] matrix, final int loopNumber) {
		return (loopWidth(matrix, loopNumber) * 2) + (loopHeight(matrix, loopNumber) * 2);
	}
	
	static int[] push(final int[] loop, final int value) {
		final int[] newLoop = new int[loop.length + 1];
		
		for (int i = 0; i < loop.length; i++) {
			newLoop[i] = loop[i];
		}
		
		newLoop[loop.length] = value;
		
		return newLoop;
	}
	
	static int[] unwrapLoop(final int[][] matrix, final int loopNumber) {
		int[] loop = new int[0];
		
		if (loopWidth(matrix, loopNumber) == 1) {
      for (int i = loopNumber; i < (loopNumber + loopHeight(matrix, loopNumber) - 1); i++) {
        loop = push(loop, matrix[i][loopNumber]);
      }
    }
    else if (loopHeight(matrix, loopNumber) == 1) {
      for (int j = loopNumber; j < (loopNumber + loopWidth(matrix, loopNumber)); j++) {
        loop = push(loop, matrix[loopNumber][j]);
      }
    }
    else {
      // Top
      for (int j = loopNumber; j < (loopNumber + loopWidth(matrix, loopNumber)); j++) {
        loop = push(loop, matrix[loopNumber][j]);
      }

      // Right
      for (int i = loopNumber + 1; i < (loopNumber + loopHeight(matrix, loopNumber) - 1); i++) {
        loop = push(loop, matrix[i][loopWidth(matrix, loopNumber) - 1]);
      }

      // Bottom
      for (int j = (loopNumber + loopWidth(matrix, loopNumber) - 1); j >= loopNumber ; j--) {
        loop = push(loop, matrix[(loopHeight(matrix, loopNumber) - 1)][j]);
      }

      // Left
      for (int i = (loopNumber + loopHeight(matrix, loopNumber) - 2); i > loopNumber; i--) {
        loop = push(loop, matrix[i][loopNumber]);
      }
    }
		
		return loop;
	}
	
	static int[][] wrapLoop(final int[][] matrix, final int[] loop, final int loopNumber) {
		final int[][] copyOfMatrix = deepCopyMatrix(matrix);
		
		if (loopWidth(matrix, loopNumber) == 1) {
      for (int i = loopNumber, j = 0; i < (loopNumber + loopHeight(matrix, loopNumber) - 1); i++, j++) {
      	copyOfMatrix[i][loopNumber] = loop[j];
      }
    }
    else if (loopHeight(matrix, loopNumber) == 1) {
      for (int j = loopNumber, i = 0; j < (loopNumber + loopWidth(matrix, loopNumber)); j++, i++) {
      	copyOfMatrix[loopNumber][j] = loop[i];
      }
    }
    else {
      int s = 0;
      // Top
      for (int j = loopNumber; j < (loopNumber + loopWidth(matrix, loopNumber)); j++, s++) {
      	copyOfMatrix[loopNumber][j] = loop[s];
      }

      // Right
      for (int i = loopNumber + 1; i < (loopNumber + loopHeight(matrix, loopNumber) - 1); i++, s++) {
        matrix[i][loopWidth(matrix, loopNumber) - 1] = loop[s];
      }

      // Bottom
      for (int j = (loopNumber + loopWidth(matrix, loopNumber) - 1); j >= loopNumber ; j--, s++) {
      	copyOfMatrix[(loopHeight(matrix, loopNumber) - 1)][j] = loop[s];
      }

      // Left
      for (int i = (loopNumber + loopHeight(matrix, loopNumber) - 2); i > loopNumber; i--, s++) {
      	copyOfMatrix[i][loopNumber] = loop[s];
      }
    }
		
		return copyOfMatrix;
	}
	
	static int[] shiftLoop(final int[] loop, final int num) {
		final int[] newLoop = new int[loop.length];

    for (int i = 0; i < loop.length; i++) {
      int nextI = i + num;

      if (nextI >= loop.length) {
        int diff = (nextI - loop.length);

        nextI = diff;
      }
      else if (nextI < 0) {
        int diff = (loop.length + nextI);

        nextI = diff;
      }

      newLoop[nextI] = loop[i];
    }

    return newLoop;
	}
	
	static int[][] rotateMatrixClockwise(final int[][] matrix) {
		int[][] copyOfMatrix = deepCopyMatrix(matrix);
		
		final int numberOfLoops = numberOfLoops(matrix);
		
		for (int loopNumber = 0; loopNumber < numberOfLoops; loopNumber++) {
			final int[] loop = unwrapLoop(matrix, loopNumber);
			final int[] shiftedLoop = shiftLoop(loop, 1);
			
			copyOfMatrix = wrapLoop(copyOfMatrix, shiftedLoop, loopNumber);
		}
		
		return copyOfMatrix;
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
			final int height = input.nextInt();
			final int width = input.nextInt();
			final int[][] matrix = new int[height][width];
			
			for (int m = 0; m < height; m++) {
				for (int n = 0; n < width; n++) {
					matrix[m][n] = input.nextInt();
				}
			}
			
			final int numberOfRotations = input.nextInt();
			final int testResult = executeTestCase(matrix, numberOfRotations);
			
			testResults.append(testResult).append(String.format("%n"));
		}
		
		input.close();
		
		System.out.format("%s", testResults);
	}

}
