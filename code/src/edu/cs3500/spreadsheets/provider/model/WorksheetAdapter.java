package edu.cs3500.spreadsheets.provider.model;

import java.util.HashMap;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.provider.value.IValue;
import edu.cs3500.spreadsheets.provider.value.ValueAdapter;

/**
 * Translates a GeneralWorksheet to an IWorksheet allowing for the ability to retrieve/evaluate data
 * about the current state of the spreadsheet. Uses composition to adapt our worksheet interface
 * with the providers worksheet interface.
 */
public class WorksheetAdapter implements IWorksheet<Cell> {
  private GeneralWorksheet gw;

  public WorksheetAdapter(GeneralWorksheet gw) {
    this.gw = gw;
  }

  @Override
  public void updateCell(int col, int row, String contents) {
    if (contents.equals("")) {
      gw.removeCell(col, row);
    } else {
      gw.modifyOrAdd(col, row, contents);
    }
  }

  @Override
  public Coord getLargestCoord() {
    int width = Math.max(52, gw.getMaxWidth());
    int height = Math.max(100, gw.getMaxHeight());
    return new Coord(width, height);
  }

  @Override
  public HashMap<Coord, Cell> getAllCells() {
    HashMap<Coord, Cell> allCells = new HashMap<>();
    for (int i = 1; i <= gw.getMaxHeight(); i++) {
      for (int j = 1; j <= gw.getMaxWidth(); j++) {
        if (!gw.getCell(j, i).content.equals(new Blank())) {
          allCells.put(new Coord(j, i), getCellAt(j, i));
        }
      }
    }
    return allCells;
  }

  @Override
  public IValue evaluateCell(int col, int row) throws IllegalArgumentException {
    try {
      return new ValueAdapter(gw.evaluateCell(gw.getCell(col, row)));
    } catch (UnsupportedOperationException | IllegalArgumentException e) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public Cell getCellAt(int col, int row) {
    edu.cs3500.spreadsheets.model.Cell c = gw.getCell(col, row);
    return new Cell(c.content.toString(), c.cellReference);
  }
}
