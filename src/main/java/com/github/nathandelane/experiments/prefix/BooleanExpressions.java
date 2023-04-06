package com.github.nathandelane.experiments.prefix;

import java.util.*;

public class BooleanExpressions {

  private static final String[] FUNCTIONS = new String[] {
    "and", "or", "equal", "notequal",
    "greaterthan", "lessthan",
    "greaterthanorequal", "lessthanorequal",
    "between", "istrue", "isfalse",
    "add", "mul", "div", "sub"
  };

  private static final String[] PROPERTIES = new String[] {
    "shiptoaddress", "shiptocountry", "shipifromaddress", "shipfromcountry",
    "length", "width", "height", "shipweight", "volumetricweight", "isltl",
    "releasedate", "pricecents", "pricedollars", "addresslocationtype",
    "ismarketplacesku"
  };

  private static final Map<String, Integer> EXPECTED_ARGUMENTS = new HashMap<>();
  static {
    EXPECTED_ARGUMENTS.put("and", 2);
    EXPECTED_ARGUMENTS.put("or", 2);
    EXPECTED_ARGUMENTS.put("equal", 2);
    EXPECTED_ARGUMENTS.put("notequal", 2);
    EXPECTED_ARGUMENTS.put("greaterthan", 2);
    EXPECTED_ARGUMENTS.put("lessthan", 2);
    EXPECTED_ARGUMENTS.put("greaterthanorequal", 2);
    EXPECTED_ARGUMENTS.put("lessthanorequal", 2);
    EXPECTED_ARGUMENTS.put("between", 3);
    EXPECTED_ARGUMENTS.put("istrue", 1);
    EXPECTED_ARGUMENTS.put("isfalse", 1);
    EXPECTED_ARGUMENTS.put("add", 2);
    EXPECTED_ARGUMENTS.put("mul", 2);
    EXPECTED_ARGUMENTS.put("div", 2);
    EXPECTED_ARGUMENTS.put("sub", 2);
  };

  private static final char START_FUNCTION = '(';
  private static final char END_FUNCTION = ')';
  private static final char[] WHITESPACE = new char[] {
    ' ', '\t', '\n', '\r'
  };

  private static final char[] TOKEN_CHARS = "\"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789.".toCharArray();

  static {
    Arrays.sort(FUNCTIONS);
    Arrays.sort(PROPERTIES);
    Arrays.sort(TOKEN_CHARS);
    Arrays.sort(WHITESPACE);
  }

  public List<String> parseExpression(final String expression) {
    final List<String> listOfTokens = new LinkedList<>();

    if (expression != null && !expression.trim().equals("")) {
      final char[] expressionChars = expression.toCharArray();

      int index = 0;
      String token = "";
      boolean expectFunction = false;
      int expectedParameters = 0;

      final Stack<String> functionStack = new Stack<>();

      while (index < expressionChars.length) {
        final char nextChar = expressionChars[index];

        if (Arrays.binarySearch(WHITESPACE, nextChar) > -1) {
          token = token.toLowerCase();

          listOfTokens.add(token);

          if (Arrays.binarySearch(FUNCTIONS, token) > -1) {
            functionStack.push(token);

            final Integer numberOfParameters = EXPECTED_ARGUMENTS.get(token);

            if (numberOfParameters == null) {
              log("Unconfigured function " + token);
              break;
            }

            expectedParameters = numberOfParameters;
          }

          token = "";
        }
        else if (Arrays.binarySearch(PROPERTIES, token) > -1) {
          if (expectedParameters > 0) {
            listOfTokens.add(token);

            expectedParameters--;
            token = "";
          }
        }
        else if (nextChar == START_FUNCTION) {
          expectFunction = true;
        }
        else if (nextChar == END_FUNCTION) {
          if (!token.equals("")) {
            listOfTokens.add(token);

            token = "";
          }

          expectFunction = false;

          final String function = functionStack.pop();
        }
        else if (Arrays.binarySearch(TOKEN_CHARS, nextChar) > -1) {
          token = token + nextChar;
        }

        index++;
      }
    }

    return listOfTokens;
  }

  public boolean evaluate(final List<String> tokens) {
    boolean result = false;
    final int size = tokens.size();

    int tokenIndex = 0;

    while (tokenIndex < size) {
      final String nextToken = tokens.get(tokenIndex);

      if (Arrays.binarySearch(FUNCTIONS, nextToken) > -1) {
        final int expectedParameters = EXPECTED_ARGUMENTS.get(nextToken);
        final String[] parameters = new String[expectedParameters];

        for (int parameterIndex = 0; parameterIndex < expectedParameters; parameterIndex++) {
          final String parameter = tokens.get(tokenIndex + parameterIndex + 1);

          parameters[parameterIndex] = parameter;
        }
      }
    }

    return result;
  }

  private static final void log(final String message) {
    System.err.println(message);
  }

}
