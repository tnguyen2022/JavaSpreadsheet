package edu.cs3500.spreadsheets.provider.model;

import edu.cs3500.spreadsheets.model.Coord;
import java.util.Objects;

/**
 * <p>
 * A generic cell in a spreadsheet. The formula stored as a {@code String} represents the raw input
 * from a user. An empty {@code String} or a null String represents an empty cell. Evaluation of
 * the formula is managed by the worksheet model.
 * </p>
 * <p>
 *   The coordinates of each cell can be accessed individually, and are given when the cell is
 *   first created. Two cells are considered equal if they are in the same location, but unequal
 *   if they are in different locations and contain the same raw input {@code String}.
 * </p>
 */
public class Cell {
  private final String form;
  private final Coord coord;


  /**
   * Constructs a spreadsheet cell with a location. It's either empty or has a formula.
   * @param form the input formula
   * @param coord the location on the spreadsheet
   */
  public Cell(String form, Coord coord) {
    this.form = form;
    this.coord = coord;
  }

  /**
   * Gets the formula of the cell.
   * @return the formula contained in the cell.
   */
  public String getForm() {
    return form;
  }

  /**
   * Gets the coordinate of the cell.
   * @return the coordinate contained in the cell.
   */
  public Coord getCoord() {
    return coord;
  }

  /**
   * Determines if a cell is empty.
   * @return true if the cell is empty
   */
  public boolean isEmpty() {
    return form == null || form.equals("");
  }


  @Override
  public boolean equals(Object object) {
    if (object instanceof Cell) {
      return this.coord.col == ((Cell) object).coord.col
              && this.coord.row == ((Cell) object).coord.row;
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(coord.row, coord.col);
  }
}
