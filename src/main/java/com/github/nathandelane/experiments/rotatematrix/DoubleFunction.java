package com.github.nathandelane.experiments.rotatematrix;

import java.util.function.Function;

/**
 * Represents a {@code Function<Double, Double>} that can be used in a {@link DoubleFunctionMatrix}.
 * @author nathandelane
 *
 */
public abstract class DoubleFunction implements Function<Double, Double> {
  
  /**
   * Special identity function that returns the value passed in, as in multiplication be one.
   */
  public static final DoubleFunction IDENTITY_FUNCTION = new DoubleFunction() {

    @Override
    public Double apply(final Double t) {
      return t;
    }
    
  };
  
  /**
   * Special identity function that returns zero, as in zero multiplication.
   */
  public static final DoubleFunction IDENTITY_ZERO_FUNCTION = new DoubleFunction() {

    @Override
    public Double apply(final Double t) {
      return 0.0;
    }
    
  };
  
  /**
   * Special identity function that returns one, as in division by one's self.
   */
  public static final DoubleFunction IDENTITY_ONE_FUNCTION = new DoubleFunction() {

    @Override
    public Double apply(final Double t) {
      return 1.0;
    }
    
  };
  
}
