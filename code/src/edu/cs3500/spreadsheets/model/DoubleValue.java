package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents the boolean value type.
 */
public class DoubleValue implements Value{
  double value;

  /**
   * Creates a double value with the given value.
   * @param value the double value.
   */
  public DoubleValue(double value){
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor){
    return visitor.visitDoubleValue(this);
  }

  @Override
  public Value canEvaluate(Coord c) {
    return evaluate();
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycle(Coord c, ArrayList<Coord> acc) {
    return false;
  }

  @Override
  public String toString() {
    return String.format("%f", value);
  }

}
