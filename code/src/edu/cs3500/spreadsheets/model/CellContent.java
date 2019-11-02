package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Represents the possible contents to be placed into a cell.
 */
public interface CellContent {

  /**
   * evaluates the contents in the cell.
   *
   * @return an evaluated value
   * @throws IllegalArgumentException invalid arguments for the values in a cell content
   */
  Value evaluate() throws IllegalArgumentException;

  /**
   * checks whether or not the cell content contains an direct or indirect circular reference.
   *
   * @param acc accumulator of referenced coordinates in the cell contents
   * @return true if the cell contents contains a circular reference
   */
  boolean checkCycle(ArrayList<Coord> acc);

  /**
   * <p>Represents the Cell Contents for a specified cell.</p>
   *
   * @param visitor the visiting cell content
   * @param <T>     the visiting value type
   * @return the desired result
   * @throws UnsupportedOperationException when the visitor is not a valid cell content
   */
  public <T> T accept(CellContentVisitor<T> visitor) throws UnsupportedOperationException;
}


