package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public interface Formula extends CellContent {
  public boolean checkCycles (Cell c, ArrayList<Cell> acc);
}
