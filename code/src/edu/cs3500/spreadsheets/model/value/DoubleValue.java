package edu.cs3500.spreadsheets.model.value;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents the boolean value type.
 */
public class DoubleValue implements Value {
  public double value;

  /**
   * Creates a double value with the given value.
   *
   * @param value the double value.
   */
  public DoubleValue(double value) {
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitDoubleValue(this);
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    return false;
  }

  @Override
  public String toString() {
    return String.format("%f", value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof DoubleValue) {
      DoubleValue that = (DoubleValue) o;
      return Math.abs(this.value - that.value) < .00001;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
