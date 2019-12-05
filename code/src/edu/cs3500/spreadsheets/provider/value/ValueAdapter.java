package edu.cs3500.spreadsheets.provider.value;

import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Translates a Value to an IValue to represent an output value of a cell, either a boolean, double,
 * or a string.
 */
public class ValueAdapter implements IValue {

  private Value v;

  public ValueAdapter(Value v) {
    this.v = v;
  }

  @Override
  public String toString() {
    return v.toString();
  }

  @Override
  public <R> R accept(ValueVisitor<R> visitor) {
    return v.accept(new ValueVisitorAdapter<>(visitor));
  }
}
