package edu.cs3500.spreadsheets.model;

/**
 * Represents the possible representations of a Cell.
 */

public class Cell {
  Coord cellReference;
  CellContent content;

  public Cell (Coord cellReference) {
    this.content = new Blank();
    this.cellReference = cellReference;
  }

  public Cell (CellContent content, Coord cellReference) {
    this.content = content;
    this.cellReference = cellReference;
  }

  public static int getIndexOfSplit(String split) {
    int index = 0;
    boolean isRestInteger = false;
    if (!Character.isLetter(split.charAt(0))) {
      throw new IllegalArgumentException("Column reference has to be a Letter");
    }
    if (!Character.isLetter(split.charAt(split.length() - 1))) {
      throw new IllegalArgumentException("Row reference has to be a Integer number");
    }
    String row = Character.toString(split.charAt(0));
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
}
