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
  public HashMap<Coord, Cell> WS;

  /**
   * Constructs a a blank worksheet represented by an empty HashMap of Key Coord mapped to a Cell.
   */
  public BasicWorksheet() {
    WS = new HashMap<>();
  }

  @Override
  public void modifyOrAdd(int col, int row, String contents) {
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
    if (this.WS.get(new Coord(col, row)) == null) {
      Cell newCell = new Cell(new Coord(col, row));
      WS.put(new Coord(col, row), newCell);
      return WS.get(new Coord(col, row));
    } else {
      return WS.get(new Coord(col, row));
    }
  }

  public int getMaxWidth(){
    int maxWidth = 0;
    for (Coord c : WS.keySet()){
      if (c.col > maxWidth){
        maxWidth = c.col;
      }
    }
    return maxWidth;
  }

  public int getMaxHeight(){
    int maxHeight = 0;
    for (Coord c : WS.keySet()){
      if (c.row > maxHeight){
        maxHeight = c.row;
      }
    }
    return maxHeight;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o instanceof BasicWorksheet) {
//      BasicWorksheet that = (BasicWorksheet) o;
//      HashMap<Coord,Cell> filteredThis = new HashMap<>();
//      HashMap<Coord,Cell> filteredThat = new HashMap<>();
//      if (that.WS.size()  this.WS.size()){
//
//      }
//      if (this.args.size() == that.args.size()) {
//        for (int i = 0; i < this.args.size(); i++) {
//          if (!this.args.get(i).equals(that.args.get(i))) {
//            return false;
//          }
//        }
//      } else {
//        return false;
//      }
//    }
//    return false;
//  }
}

