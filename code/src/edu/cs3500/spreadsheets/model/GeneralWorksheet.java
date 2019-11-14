package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Represents a basic worksheet.
 */
public interface GeneralWorksheet {
  /**
   * Modifies or adds a cell into the worksheet.
   *
   * @param col      column number on the worksheet
   * @param row      row number on the worksheet
   * @param contents the desired input in the cell
   * @throws IllegalArgumentException when the column and/or rows are outside the supported range.
   */
  public void modifyOrAdd(int col, int row, String contents) throws IllegalArgumentException;

  /**
   * Evaluates the contents of a cell.
   *
   * @param c the cell
   * @return the evaluation of the contents in the cell
   * @throws IllegalArgumentException when the cell has contents that cannot be evaluated
   */
  public Value evaluateCell(Cell c) throws IllegalArgumentException;

  /**
   * Gets the cell in the spreadsheet from a given column and row.
   * @param col spreadsheet column
   * @param row spreadsheet row
   * @return the cell
   * @throws IllegalArgumentException
   */
  public Cell getCell(int col, int row) throws IllegalArgumentException;

  public int getMaxWidth();

  public int getMaxHeight();
}
