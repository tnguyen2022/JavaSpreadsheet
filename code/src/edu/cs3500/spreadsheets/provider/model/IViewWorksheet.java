package edu.cs3500.spreadsheets.provider.model;

import java.util.HashMap;

import edu.cs3500.spreadsheets.provider.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.value.IValue;


/**
 * A type of worksheet that doesn't have any setter methods. This is part of the adapter pattern
 * for a view communicating with a worksheet model.
 * @param <T> type of cell in this worksheet model
 */
public interface IViewWorksheet<T> {
  /**
   * Gets the coordinate of the cell at the bottom right corner.
   * @return the expression of the cell
   */
  Coord getLargestCoord();

  /**
   * Gets all the non-empty cells in the worksheet.
   * @return a map of non-empty cells by their locations
   */
  HashMap<Coord, Cell> getAllCells();

  /**
   * Evaluates the formula of a cell into an expression.
   * @return the expression of the cell
   * @throws IllegalArgumentException Index is out of bounds.
   */
  IValue evaluateCell(int col, int row) throws IllegalArgumentException;

  /**
   * Finds a cell at a certain coordinate with one-indexing.
   * @return The cell found at this coordinate
   * @throws IllegalArgumentException Index is out of bounds.
   */
  T getCellAt(int col, int row) throws IllegalArgumentException;

}
