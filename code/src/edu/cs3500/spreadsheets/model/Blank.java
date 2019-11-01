package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents an empty cell in a spreadsheet. Will either be a 0 or an empty string.
 */
public class Blank implements CellContent {
  double defaultValue;

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
  public boolean checkCycle(Coord c, ArrayList<Coord> acc) {
    return false;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitBlank(this);
  }
}
