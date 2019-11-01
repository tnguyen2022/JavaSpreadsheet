package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.Value;

/**
 * Represents an empty cell in a spreadsheet. Will either be a 0 or an empty string.
 */
public class Blank implements CellContent {
  public double defaultValue;

  /**
   * A blank cell will have a default value of int 0.
   */
  Blank(){
    defaultValue = 0;
  }

  @Override
  public String toString(){
    return "";
  }

  @Override
  public Value canEvaluate(Coord c) throws IllegalArgumentException {
    return evaluate();
  }

  @Override
  public Value evaluate() {
    return new DoubleValue(0);
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
      return false;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitBlank(this);
  }

  @Override
  public boolean equals (Object o){
    if (this == o) {
      return true;
    }
    return o instanceof Blank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultValue);
  }
}
