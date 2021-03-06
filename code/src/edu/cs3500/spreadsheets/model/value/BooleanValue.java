package edu.cs3500.spreadsheets.model.value;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a boolean value that can be put into a cell.
 */
public class BooleanValue implements Value {
  public boolean value;

  /**
   * The boolean value that can be contained in a cell.
   *
   * @param value the boolean value
   */
  public BooleanValue(boolean value) {
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitBooleanValue(this);
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
    return ("" + this.value).toLowerCase();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof BooleanValue) {
      BooleanValue that = (BooleanValue) o;
      return this.value == that.value;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}

