package edu.cs3500.spreadsheets.model;

import java.util.HashMap;

import edu.cs3500.spreadsheets.sexp.CreateCell;
import edu.cs3500.spreadsheets.sexp.Parser;

public class Worksheet implements WorksheetReader.WorksheetBuilder<Worksheet> {
  HashMap<Coord, Cell> worksheet = new HashMap<>();

  Worksheet(HashMap<Coord, Cell> worksheet){
    this.worksheet = worksheet;
  }

  @Override
  public WorksheetReader.WorksheetBuilder<Worksheet> createCell(int col, int row, String contents) {
    this.worksheet.put(new Coord(col, row), Parser.parse(contents).accept(new CreateCell()));

    return this;
  }

  @Override
  public Worksheet createWorksheet() {
    int maxRow = 0;
    int maxCol = 0;

    for (Coord c : worksheet.keySet()) {
      if (c.col > maxCol) {
        maxCol = c.col;
      }
      if (c.row > maxRow) {
        maxRow = c.row;
      }
    }

    for (int i = 0; i < maxCol; i++) {
      for (int j = 0; j < maxRow; j++) {
        if (!this.worksheet.containsKey(new Coord(i, j))) {
          this.worksheet.put(new Coord(i,j), new Cell());
        }
      }
    }
    return this;
  }
}
