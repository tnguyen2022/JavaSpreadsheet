package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import edu.cs3500.spreadsheets.model.Function.Function;
import edu.cs3500.spreadsheets.model.Value.Value;
import edu.cs3500.spreadsheets.sexp.SexpVisitorFunctions.CreateCellFormula;
import edu.cs3500.spreadsheets.sexp.SexpVisitorFunctions.CreateCellValue;
import edu.cs3500.spreadsheets.sexp.Parser;

/**
 * Representation of a worksheet spreadsheet.
 */
public class Worksheet implements GeneralWorksheet {
  public static HashMap<Coord, Cell> ws;

  /**
   * A worksheet is a blank Hashmap until cells are added into the worksheet.
   */
  public Worksheet() {
    ws = new HashMap<>();
  }

  @Override
  public void modifyOrAdd(int col, int row, String contents) {
    if ((0 <= col && col <= 16000) && (0 <= row && row <= 1000000)) {
      if (contents.length() > 2) {
        if (contents.charAt(0) == '=') {
          if (ws.containsKey(new Coord(col, row))) {
            ws.get(new Coord(col, row))
                    .setContent(Parser.parse(contents.substring(1))
                            .accept(new CreateCellFormula()));
          } else {
            ws.put(new Coord(col, row),
                    new Cell(Parser.parse(contents.substring(1)).accept(new CreateCellFormula()),
                            new Coord(col, row)));
          }
        } else {
          ws.put(new Coord(col, row),
                  new Cell(Parser.parse(contents).accept(new CreateCellValue()),
                          new Coord(col, row)));
        }
      } else {
        ws.put(new Coord(col, row),
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
    if (ws.get(new Coord(col, row)) == null) {
      Cell newCell = new Cell(new Coord(col, row));
      ws.put(new Coord(col, row), newCell);
      return ws.get(new Coord(col, row));
    } else {
      return ws.get(new Coord(col, row));
    }
  }
}

