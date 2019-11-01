package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the possible representations of a Cell.
 */
public class Cell {
  static HashMap<Coord, ArrayList<Coord>> memoizeCell = new HashMap<>();
  public Coord cellReference;
  public CellContent content;

  /**
   * Creates a blank cell that is constructed in a given coordinate.
   * @param cellReference the coordinate of the cell
   */
  public Cell(Coord cellReference) {
    this.content = new Blank();
    this.cellReference = cellReference;
  }

  /**
   * Creates a non-blank cell that is constructed with its given content and coordinate.
   * @param content the content of the cell
   * @param cellReference the coordinate of the cell
   */
  public Cell(CellContent content, Coord cellReference) {
    this.content = content;
    this.cellReference = cellReference;
  }

  /**
   * Determines the index of reference.
   * @param split the string to split
   * @return the index of the reference
   */
  public static int getIndexOfSplit(String split) {
    int index = 0;
    boolean isRestInteger = false;
    if (!Character.isLetter(split.charAt(0))) {
      throw new IllegalArgumentException("Column reference has to be a Letter");
    }
    if (!Character.isDigit(split.charAt(split.length() - 1))) {
      throw new IllegalArgumentException("Row reference has to be a Integer number");
    }
    for (int i = 1; i < split.length(); i++) {
      if (!isRestInteger) {
        if (Character.isDigit(split.charAt(i))) {
          index = i;
          isRestInteger = true;
        }
      } else {
        if (Character.isLetter(split.charAt(i))) {
          throw new IllegalArgumentException("Row reference has to be a Integer number");
        }
      }
    }

    return index;
  }

  public boolean checkCellForCycle (ArrayList<Coord> acc){
    if (acc.contains(this.cellReference)) {
      return true;
    } else {
      acc.add(this.cellReference);
      boolean result = this.content.checkCycle(acc);
      acc.remove(this.cellReference);
      return result;
    }
  }

  @Override
  public boolean equals (Object o){
    if (this == o) {
      return true;
    }
    if(o instanceof Cell) {
      Cell that = (Cell) o;
      return this.content.equals(that.content) && this.cellReference.equals(that.cellReference);
    }
    return false;
  }

  public Cell setContent (CellContent c){
    this.content = c;
    return this;
  }
}
