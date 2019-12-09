package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Represents a reference of cells in a worksheet.
 */
public class Reference implements Formula {
  public ArrayList<Cell> region;

  /**
   * Parses a given string to rectangular region of cells that represents a reference.
   *
   * @param r the rectangular region of referenced cells.
   */
  public Reference(ArrayList<Cell> r) {
    this.region = r;
  }

  @Override
  public String toString() {
    if (this.region.size() == 1) {
      return Coord.colIndexToName(region.get(0).cellReference.col)
          + region.get(0).cellReference.row;
    } else {
      return Coord.colIndexToName(region.get(0).cellReference.col)
          + region.get(0).cellReference.row + ":"
          + Coord.colIndexToName(region.get(region.size() - 1).cellReference.col)
          + region.get(region.size() - 1).cellReference.row;
    }
  }

  @Override
  public Value evaluate() {
    if (region.size() == 1) {
      return region.get(0).content.evaluate();
    } else {
      throw new IllegalArgumentException("Cannot evaluate a reference of more than one cell");

    }
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    for (int i = 0; i < region.size(); i++) {
      if (region.get(i).checkCellForCycle(acc)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitReference(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof Reference) {
      Reference that = (Reference) o;
      if (this.region.size() == that.region.size()) {
        for (int i = 0; i < this.region.size(); i++) {
          if (!(this.region.get(i).equals(that.region.get(i)))) {
            return false;
          }
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(region);
  }
}
