package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.cs3500.spreadsheets.model.function.Function;
import edu.cs3500.spreadsheets.model.value.Value;
import edu.cs3500.spreadsheets.sexp.sexpvisitfunc.CreateCellFormula;
import edu.cs3500.spreadsheets.sexp.sexpvisitfunc.CreateCellValue;
import edu.cs3500.spreadsheets.sexp.Parser;

/**
 * Representation of a basic worksheet spreadsheet defined by a Hashmap of existing Coords mapped to
 * a specified Cell.
 */
public class BasicWorksheet implements GeneralWorksheet {
  private HashMap<Coord, Cell> ws;
  private HashMap<Integer, Integer> rowHeight;
  private HashMap<Integer, Integer> columnWidth;

  /**
   * Constructs a a blank worksheet represented by an empty HashMap of Key Coord mapped to a Cell.
   */
  public BasicWorksheet() {
    ws = new HashMap<>();
    rowHeight = new HashMap<>();
    columnWidth = new HashMap<>();
  }

  @Override
  public void modifyOrAdd(int col, int row, String contents) {
    boolean alreadyCreated = ws.containsKey(new Coord(col, row));

    if (contents.length() > 1) {
      if (contents.charAt(0) == '=') {
        if (alreadyCreated) {
          ws.get(new Coord(col, row))
                  .setContent(Parser.parse(contents.substring(1))
                          .accept(new CreateCellFormula(ws)));
        } else {
          ws.put(new Coord(col, row),
                  new Cell(Parser.parse(contents.substring(1)).accept(new CreateCellFormula(ws)),
                          new Coord(col, row)));
        }
      } else if (!ws.containsKey(new Coord(col, row))) {
        ws.put(new Coord(col, row),
                new Cell(Parser.parse(contents).accept(new CreateCellValue()),
                        new Coord(col, row)));
      } else {
        ws.get(new Coord(col, row))
                .setContent(Parser.parse(contents).accept(new CreateCellValue()));
      }
    } else if (!alreadyCreated) {
      ws.put(new Coord(col, row),
              new Cell(Parser.parse(contents).accept(new CreateCellValue()),
                      new Coord(col, row)));
    } else {
      ws.get(new Coord(col, row))
              .setContent(Parser.parse(contents).accept(new CreateCellValue()));
    }
  }

  @Override
  public void removeCell(int col, int row) {
    ws.remove(new Coord(col, row));
  }

  @Override
  public Value evaluateCell(Cell c) throws IllegalArgumentException {
    Function.memoizeFunction.clear();
    Function.memoizeCycle.clear();
    if (c.content.checkCycle(new ArrayList<>(Collections.singletonList(c.cellReference)))) {
      throw new IllegalArgumentException("Cell should not contain cyclical self-references");
    }

    return c.content.evaluate();
  }

  /**
   * gets the cell from the given column and row.
   *
   * @param col the column number
   * @param row the row number
   * @return the cell from given arguments
   * @throws IllegalArgumentException if the given coordinate is out of set bounds
   */
  public Cell getCell(int col, int row) throws IllegalArgumentException {
    if (this.ws.get(new Coord(col, row)) == null) {
      //Cell newCell = new Cell(new Coord(col, row));
      //this.ws.put(new Coord(col, row), newCell);
      //return this.ws.get(new Coord(col, row));
      return new Cell(new Coord(col, row));
    } else {
      return this.ws.get(new Coord(col, row));
    }
  }

  /**
   * Gets the maximum width of the spreadsheet.
   *
   * @return the maximum width
   */
  public int getMaxWidth() {
    int maxWidth = 0;
    for (Coord c : this.ws.keySet()) {
      if (c.col > maxWidth) {
        maxWidth = c.col;
      }
    }
    for (int i : columnWidth.keySet()) {
      if (i > maxWidth) {
        maxWidth = i;
      }
    }
    return maxWidth;
  }

  /**
   * Gets the maximum height of the spreadsheet.
   *
   * @return the maximum height
   */
  public int getMaxHeight() {
    int maxHeight = 0;
    for (Coord c : ws.keySet()) {
      if (c.row > maxHeight) {
        maxHeight = c.row;
      }
    }
    for (int i : rowHeight.keySet()) {
      if (i > maxHeight) {
        maxHeight = i;
      }
    }
    return maxHeight;
  }

  @Override
  public void addOrSetRowHeight(int row, int rowHeight) {
    this.rowHeight.put(row, rowHeight);
  }

  @Override
  public void addOrSetColWidth(int col, int colWidth) {
    this.columnWidth.put(col, colWidth);
  }

  @Override
  public int getRowHeight(int row) {
    return this.rowHeight.getOrDefault(row, 16);
  }

  @Override
  public int getColWidth(int col) {
    return this.columnWidth.getOrDefault(col, 75);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof BasicWorksheet) {
      BasicWorksheet that = (BasicWorksheet) o;
      HashMap<Coord, Cell> filteredThis = new HashMap<>();
      HashMap<Coord, Cell> filteredThat = new HashMap<>();
      for (Map.Entry<Coord, Cell> entry : this.ws.entrySet()) {
        if (!entry.getValue().content.equals(new Blank())) {
          filteredThis.put(entry.getKey(), entry.getValue());
        }
      }

      for (Map.Entry<Coord, Cell> entry : that.ws.entrySet()) {
        if (!entry.getValue().content.equals(new Blank())) {
          filteredThat.put(entry.getKey(), entry.getValue());
        }
      }
      return filteredThis.equals(filteredThat);
    } else {
      return false;
    }
  }
}

