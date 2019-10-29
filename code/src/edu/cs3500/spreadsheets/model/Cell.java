package edu.cs3500.spreadsheets.model;

/**
 * Represents the possible representations of a Cell.
 */

public class Cell {
  CellContent content;

  public Cell () {
    this.content = new Blank();
  }

  public Cell (CellContent content) {
    this.content = content;
  }
}
