package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

import edu.cs3500.spreadsheets.sexp.CreateCell;
import edu.cs3500.spreadsheets.sexp.Parser;

public class Worksheet implements GeneralWorksheet {
  static HashMap<Coord, Cell> ws;

  public Worksheet() {
    ws = new HashMap<>();
  }

  @Override
  public void modifyOrAdd(int col, int row, String contents) {
    if ((0 <= col && col <= 16000) && (0 <= row && row <= 1000000)){
      ws.put(new Coord(col, row),
              new Cell (Parser.parse(contents).accept(new CreateCell()), new Coord (col, row)));
    }
    else{
      throw new IllegalArgumentException("Row or columns inputs are not within 1000000 x 16000.");
    }
  }

  @Override
  public Value evaluateCell(Cell c) throws IllegalArgumentException {
    return c.content.evaluate();
  }

  public static Cell getCell(int col, int row) throws IllegalArgumentException {
    if (ws.get(new Coord (col, row)) == null){
      return new Cell(new Coord (col, row));
    }
    else{
      return ws.get(new Coord (col, row));
    }
  }

}
