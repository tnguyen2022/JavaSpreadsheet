package edu.cs3500.spreadsheets.model;

/**
 * Represents the possible representations of a Cell.
 */
public class Cell {
  public Coord cellReference;
  public CellContent content;

  /**
   * Creates a blank cell that is placed in a given coordinate.
   * @param cellReference the coordinate of the cell
   */
  Cell(Coord cellReference) {
    this.content = new Blank();
    this.cellReference = cellReference;
  }

  /**
   * Creates a non-blank cell that is placed in a given content and coordinate.
   * @param content the content of the cell
   * @param cellReference the coordinate of the cell
   */
  Cell(CellContent content, Coord cellReference) {
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

}
