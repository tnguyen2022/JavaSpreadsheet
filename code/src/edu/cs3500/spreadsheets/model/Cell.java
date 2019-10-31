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
}
