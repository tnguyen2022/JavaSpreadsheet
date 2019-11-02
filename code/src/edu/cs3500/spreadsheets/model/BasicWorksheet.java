package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
  public static HashMap<Coord, Cell> WS;

  /**
   * Constructs a a blank worksheet represented by an empty HashMap of Key Coord mapped to a Cell.
   */
  public BasicWorksheet() {
    WS = new HashMap<>();
  }

  @Override
  public void modifyOrAdd(int col, int row, String contents) {
    if ((0 <= col && col <= 16000) && (0 <= row && row <= 1000000)) {
      if (contents.length() > 2) {
        if (contents.charAt(0) == '=') {
          if (WS.containsKey(new Coord(col, row))) {
            WS.get(new Coord(col, row))
                    .setContent(Parser.parse(contents.substring(1))
                            .accept(new CreateCellFormula()));
          } else {
            WS.put(new Coord(col, row),
                    new Cell(Parser.parse(contents.substring(1)).accept(new CreateCellFormula()),
                            new Coord(col, row)));
          }
        } else {
          WS.put(new Coord(col, row),
                  new Cell(Parser.parse(contents).accept(new CreateCellValue()),
                          new Coord(col, row)));
        }
      } else {
        WS.put(new Coord(col, row),
                new Cell(Parser.parse(contents).accept(new CreateCellValue()),
                        new Coord(col, row)));
      }
    } else {
      throw new IllegalArgumentException("Row or columns inputs are not within 1000000 x 16000.");
    }
  }

  @Override
  public Value evaluateCell(Cell c) throws IllegalArgumentException {
    if (c.content.checkCycle(new ArrayList<>(Collections.singletonList(c.cellReference)))) {
      throw new IllegalArgumentException("Cell should not contain cyclical self-references");
    }
    Function.memoizeCycle.clear();
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
  public static Cell getCell(int col, int row) throws IllegalArgumentException {
    if (WS.get(new Coord(col, row)) == null) {
      Cell newCell = new Cell(new Coord(col, row));
      WS.put(new Coord(col, row), newCell);
      return WS.get(new Coord(col, row));
    } else {
      return WS.get(new Coord(col, row));
    }
  }
}

