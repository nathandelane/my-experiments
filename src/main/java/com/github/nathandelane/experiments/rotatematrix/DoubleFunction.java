package com.github.nathandelane.experiments.rotatematrix;

import java.util.function.Function;

public abstract class DoubleFunction implements Function<Double, Double> {
  
  public static final DoubleFunction IDENTITY_FUNCTION = new DoubleFunction() {

    @Override
    public Double apply(final Double t) {
      return t;
    }
    
  };
  
  public static final DoubleFunction ZERO_FUNCTION = new DoubleFunction() {

    @Override
    public Double apply(final Double t) {
      return 0.0;
    }
    
  };
  
  public static final DoubleFunction ONE_FUNCTION = new DoubleFunction() {

    @Override
    public Double apply(final Double t) {
      return 1.0;
    }
    
  };

  // Empty on purpose
  
}
