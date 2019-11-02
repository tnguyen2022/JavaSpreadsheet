package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Represents an empty cell in a spreadsheet that does ot contain any content.
 */
public class Blank implements CellContent {

  @Override
  public String toString() {
    return "";
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    return o instanceof Blank;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
