package com.github.nathandelane.experiments.search;

import java.util.Arrays;

public class EnumSearch {

  public static enum TransferMethod {
    AS2,
    SFTP,
    FTPS,
    OTHER
  }

  public static void main(final String[] args) {
    if (Arrays.binarySearch(new TransferMethod[] { TransferMethod.AS2, TransferMethod.SFTP, TransferMethod.FTPS }, TransferMethod.OTHER) < 0) {
      System.out.println("Could not find value");
    }
    else {
      System.out.println("Something Else");
    }
  }

}
