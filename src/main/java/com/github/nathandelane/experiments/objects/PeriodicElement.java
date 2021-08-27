package com.github.nathandelane.experiments.objects;


public class PeriodicElement implements Comparable<PeriodicElement> {

  private int atomicNumber;

  private String symbol;

  private String name;

  private double atomicMass;

  private PeriodicElement(Builder builder) {
    this.atomicNumber = builder.atomicNumber;
    this.symbol = builder.symbol;
    this.name = builder.name;
    this.atomicMass = builder.atomicMass;
  }

  public int getAtomicNumber() {
    return atomicNumber;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }

  public double getAtomicMass() {
    return atomicMass;
  }

  // Ordering by symbol
  @Override
  public int compareTo(PeriodicElement o) {
    if (symbol.compareTo(o.symbol) == 0) {
      return 0;
    } else if (symbol.compareTo(o.symbol) > 0) {
      return 1;
    } else {
      return -1;
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AtomicNumber: " + atomicNumber);
    builder.append(", Symbol: " + symbol);
    builder.append(", Name: " + name);
    builder.append(", Atomic Mass: " + atomicMass);
    return builder.toString();
  }
  
  @Override
  public boolean equals(Object other) {
    return other != null &&
      other instanceof PeriodicElement &&
      Double.compare(this.atomicMass, ((PeriodicElement) other).atomicMass) == 0 &&
      this.symbol.equals(((PeriodicElement) other).symbol) &&
      this.name.equals(((PeriodicElement) other).name) &&
      Integer.compare(this.atomicNumber, ((PeriodicElement) other).atomicNumber) == 0;
  }
  
  @Override
  public int hashCode() {
    return this.atomicNumber;
  }
  
  // Builder Pattern
  public static class Builder {
    private int atomicNumber;

    private String symbol;

    private String name;

    private double atomicMass;

    public Builder withAtomicNumber(int atomicNumber) {
      this.atomicNumber = atomicNumber;
      return this;
    }

    public Builder withSymbol(String symbol) {
      this.symbol = symbol;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withAtomicMass(double atomicMass) {
      this.atomicMass = atomicMass;
      return this;
    }

    public PeriodicElement build() {
      return new PeriodicElement(this);
    }
  }
}
