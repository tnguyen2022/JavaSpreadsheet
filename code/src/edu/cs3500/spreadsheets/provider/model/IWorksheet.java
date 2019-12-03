package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;

/**
 * Represents any type of worksheet using a map of coordinates to cells that can be edited.
 * @param <T> the type of cells in the Worksheet
 */
public interface IWorksheet<T> extends IViewWorksheet<T> {

  /**
   * Adds a cell to the worksheet at the given coordinates with contents.
   * @param col col of new cell
   * @param row row of new cell
   * @param contents input of new cell
   */
  void updateCell(int col, int row, String contents);
}