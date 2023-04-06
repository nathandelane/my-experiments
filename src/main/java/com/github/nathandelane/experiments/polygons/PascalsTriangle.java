package com.github.nathandelane.experiments.polygons;

/**
 * Pascal's Triangle is a growing visualizable mathematical structure wherein interleaving elements of subsequent
 * levels in the triangle are the sum of the two elements surrounding the element in the previous level.
 *
 * <pre>
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 * ...
 * </pre>
 */
public class PascalsTriangle {

  private static int getCoefficient(final int rowNumber, final int elementNumber, final int coefficient) {
    if (elementNumber == 0 || rowNumber == 0) return 1;

    return (coefficient * (rowNumber - elementNumber + 1) / elementNumber);
  }

  private static void printElements(final int rowNumber) {
    int coefficient = 0;
    int elementNumber = 0;

    while (elementNumber <= rowNumber) {
      coefficient = getCoefficient(rowNumber, elementNumber, coefficient);

      System.out.format("%4d", coefficient);

      elementNumber++;
    }
  }

  private static void printSpacing(final int numberOfRows, final int rowNumber) {
    int spacing = 1;

    while (spacing <= (numberOfRows - rowNumber)) {
      System.out.print(" ");

      spacing++;
    }

    System.out.print("\n\n");
  }

  private static void printRows(final int numberOfRows) {
    int rowNumber = 0;

    while (rowNumber < numberOfRows) {
      printSpacing(numberOfRows, rowNumber);
      printElements(rowNumber);

      rowNumber++;
    }
  }

  public static void main(final String[] args) {
    final int numberOfRows = 5;

    printRows(numberOfRows);
  }

}
