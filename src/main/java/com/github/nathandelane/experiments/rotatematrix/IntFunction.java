package com.github.nathandelane.experiments.rotatematrix;

import java.util.function.Function;

/**
 * Represents a {@code Function<Integer, Integer>} that can be used in a {@link IntFunctionMatrix}.
 * @author nathandelane
 *
 */
public abstract class IntFunction implements Function<Integer, Integer> {
  
  /**
   * Special identity function that returns the value passed in, as in multiplication be one.
   */
  public static final IntFunction IDENTITY_FUNCTION = new IntFunction() {

    @Override
    public Integer apply(final Integer t) {
      return t;
    }
    
  };
  
  /**
   * Special identity function that returns zero, as in zero multiplication.
   */
  public static final IntFunction IDENTITY_ZERO_FUNCTION = new IntFunction() {

    @Override
    public Integer apply(final Integer t) {
      return 0;
    }
    
  };
  
  /**
   * Special identity function that returns one, as in division by one's self.
   */
  public static final IntFunction IDENTITY_ONE_FUNCTION = new IntFunction() {

    @Override
    public Integer apply(final Integer t) {
      return 1;
    }
    
  };
  
}
