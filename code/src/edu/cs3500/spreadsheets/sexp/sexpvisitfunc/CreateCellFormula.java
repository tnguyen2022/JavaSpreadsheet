package edu.cs3500.spreadsheets.sexp.sexpvisitfunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.ColumnReference;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.function.LeftFunction;
import edu.cs3500.spreadsheets.model.function.LessThanFunction;
import edu.cs3500.spreadsheets.model.function.ProductFunction;
import edu.cs3500.spreadsheets.model.value.StringValue;
import edu.cs3500.spreadsheets.model.function.SumFunction;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor function object that creates the Formula for a Cell from a Sexp.
 */
public class CreateCellFormula implements SexpVisitor<Formula> {
  private HashMap<Coord, Cell> ws;

  public CreateCellFormula(HashMap<Coord, Cell> ws) {
    this.ws = ws;
  }

  @Override
  public Formula visitBoolean(boolean b) {
    return new BooleanValue(b);
  }

  @Override
  public Formula visitNumber(double d) {
    return new DoubleValue(d);
  }

  @Override
  public Formula visitSList(List<Sexp> l) {
    String funcName;

    try {
      funcName = l.get(0).accept(new FunctionName());
    } catch (IllegalArgumentException | NullPointerException e) {
      throw new IllegalArgumentException("Not a valid Function Name");
    }

    ArrayList<Formula> args = new ArrayList<>();
    if (l.size() > 1) {
      for (int i = 1; i < l.size(); i++) {
        args.add(l.get(i).accept(new CreateCellFormula(ws)));
      }
    }

    switch (funcName) {
      case "SUM":
        return new SumFunction(args);
      case "PRODUCT":
        return new ProductFunction(args);
      case "<":
        return new LessThanFunction(args);
      case "LEFT":
        return new LeftFunction(args);
      default:
        throw new IllegalArgumentException("Function needs to be a valid name");
    }
  }

  @Override
  public Formula visitSymbol(String s) {
    ArrayList<Cell> region = new ArrayList<>();
    if (s.length() < 2) {
      throw new IllegalArgumentException("Need a valid reference");
    }
    if (s.contains(":")) {
      if (s.split(":")[0].equals(s.split(":")[1])) {
        if (!s.matches(".*\\d.*")){
          int col = Coord.colNameToIndex(s.split(":")[0]);
          for (Coord c : ws.keySet()){
            if (c.col == col){
              Cell currentCell = retrieveCell(c.col, c.row);
              region.add(currentCell);
            }
          }
          return new ColumnReference(col, col, region);
        }
        int col = Coord.colNameToIndex(s.split(":")[0].substring(0,
                Cell.getIndexOfSplit(s)));
        int row = Integer.parseInt(s.substring(Cell.getIndexOfSplit(s)));
        Cell currentCell = retrieveCell(col, row);
        region.add(currentCell);
      } else if (!s.matches(".*\\d.*")){
        int col1 = Coord.colNameToIndex(s.split(":")[0]);
        int col2 = Coord.colNameToIndex(s.split(":")[1]);
        for (Coord c : ws.keySet()){
          if (c.col == col1 || c.col == col2){
            Cell currentCell = retrieveCell(c.col, c.row);
            region.add(currentCell);
          }
        }
        return new ColumnReference(col1, col2, region);
      }
      else {
        int col1 = (Coord.colNameToIndex(s.split(":")[0].substring(0,
                Cell.getIndexOfSplit(s.split(":")[0]))));
        int row1 = Integer.parseInt(s.split(":")[0].substring(
                Cell.getIndexOfSplit(s.split(":")[0])));
        int col2 = (Coord.colNameToIndex(s.split(":")[1].substring(0,
                Cell.getIndexOfSplit(s.split(":")[1]))));
        int row2 = Integer.parseInt(s.split(":")[1].substring(
                Cell.getIndexOfSplit(s.split(":")[1])));

        if (col1 > col2) {
          int temp = col1;
          col1 = col2;
          col2 = temp;
        }
        if (row1 > row2) {
          int temp = row1;
          row1 = row2;
          row2 = temp;
        }

        for (int i = col1; i <= col2; i++) {
          for (int j = row1; j <= row2; j++) {
            region.add(retrieveCell(i, j));
          }
        }
      }
    } else {
      int col = Coord.colNameToIndex(s.split(":")[0].substring(0,
              Cell.getIndexOfSplit(s)));
      int row = Integer.parseInt(s.substring(Cell.getIndexOfSplit(s)));
      Cell currentCell = retrieveCell(col, row);
      region.add(currentCell);
    }
    return new Reference(region);
  }

  @Override
  public Formula visitString(String s) {
    return new StringValue(s);
  }

  private Cell retrieveCell(int col, int row) throws IllegalArgumentException {
    if (this.ws.get(new Coord(col, row)) == null) {
      Cell newCell = new Cell(new Coord(col, row));
      this.ws.put(new Coord(col, row), newCell);
      return this.ws.get(new Coord(col, row));
    } else {
      return this.ws.get(new Coord(col, row));
    }
  }
}
