package com.github.nathandelane.experiments.rotatematrix;

import java.util.function.Function;

public abstract class IntFunction implements Function<Integer, Integer> {
  
  public static final IntFunction IDENTITY_FUNCTION = new IntFunction() {

    @Override
    public Integer apply(final Integer t) {
      return t;
    }
    
  };
  
  public static final IntFunction ZERO_FUNCTION = new IntFunction() {

    @Override
    public Integer apply(final Integer t) {
      return 0;
    }
    
  };
  
  public static final IntFunction ONE_FUNCTION = new IntFunction() {

    @Override
    public Integer apply(final Integer t) {
      return 1;
    }
    
  };

  // Empty on purpose
  
}
