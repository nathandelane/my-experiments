package com.github.nathandelane.experiments.rotatematrix;

import java.util.function.Function;

public interface FunctionMatrix<T extends Function<?, ?>> {

  public T get(int rowIndex, int columnIndex);
  
}
