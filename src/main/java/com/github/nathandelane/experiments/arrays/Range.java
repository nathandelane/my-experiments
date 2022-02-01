package com.github.nathandelane.experiments.arrays;

public interface Range<T> {

  public T getLow();

  public T getHigh();

  public boolean isInRange(final T value);

  public <U extends Range<T>> boolean isInRange(final U range);

}
