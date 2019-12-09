package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a reference of all the values in a column from a given instance of a spreadsheet.
 */
public class ColumnReference extends Reference {
  private int col1;
  private int col2;

  /**
   * Parses a given string to rectangular region of cells that represents a reference.
   * @param r the rectangular region of referenced cells.
   */
  public ColumnReference(int col1, int col2, ArrayList<Cell> r) {
    super(r);
    this.col1 = col1;
    this.col2 = col2;
  }

  @Override
  public String toString() {
    if (col2 < col1) {
      return Coord.colIndexToName(col2) + ":" + Coord.colIndexToName(col1);
    } else {
      return Coord.colIndexToName(col1) + ":" + Coord.colIndexToName(col2);
    }
  }
}
