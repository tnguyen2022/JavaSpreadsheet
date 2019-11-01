package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

import edu.cs3500.spreadsheets.model.Value.Value;

/**
 * Represents the possible contents to be placed into a cell.
 */
public interface CellContent {
  Value canEvaluate(Coord c) throws IllegalArgumentException;

  Value evaluate() throws IllegalArgumentException;

  boolean checkCycle(ArrayList<Coord> acc);

  /**
   * <p>Represents an Cell</p>
   * @param visitor the visiting cell content
   * @param <T> the visiting value type
   * @return the desired result
   * @throws UnsupportedOperationException when the visitor is not a valid cell content
   */
  public <T> T accept(CellContentVisitor<T> visitor) throws UnsupportedOperationException;

  //public String toString();

  //boolean equals();
}


