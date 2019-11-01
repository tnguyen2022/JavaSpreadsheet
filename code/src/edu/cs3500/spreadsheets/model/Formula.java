package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Representation of a formula.
 */
public interface Formula extends CellContent {
  public boolean checkCycleHelper(ArrayList<Coord> acc);
}
