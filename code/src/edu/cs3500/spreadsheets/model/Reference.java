package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Reference implements Formula {
  ArrayList<CellContent> region = new ArrayList<CellContent>();

  public Reference(String s) {
    if (s.length() < 2) {
      throw new IllegalArgumentException("Need a valid reference");
    }

    if (s.contains(":")) {
      int col1 = (Coord.colNameToIndex(s.split(":")[0].substring(0,
              Cell.getIndexOfSplit(s.split(":")[0]))));
      int row1 =  Integer.parseInt(s.split(":")[0].substring(
              Cell.getIndexOfSplit(s.split(":")[0])));
      int col2 = (Coord.colNameToIndex(s.split(":")[1].substring(0,
              Cell.getIndexOfSplit(s.split(":")[1]))));
      int row2 =  Integer.parseInt(s.split(":")[1].substring(
              Cell.getIndexOfSplit(s.split(":")[1])));

      if (col1 > col2){
        int temp = col1;
        col1 = col2;
        col2 = temp;
      }
      if (row1 > row2){
        int temp = row1;
        row1 = row2;
        row2 = temp;
      }

      for (int i = col1; i <= col2; i++){
        for (int j = row1; j <= row2; j++){
          region.add(Worksheet.getCell(i, j).content);
        }
      }
    } else {
      Cell currentCell = Worksheet.getCell(Coord.colNameToIndex(s.substring(0,
              Cell.getIndexOfSplit(s))),
              Integer.parseInt(s.substring(Cell.getIndexOfSplit(s))));
      if (checkCycles(currentCell, new ArrayList<Cell>())){

      }
      else{
        throw new IllegalArgumentException("Reference contains cycles: Can't reference itself");
      }

      region.add(currentCell.content);
    }
  }

  public Reference(ArrayList<CellContent> region) {
    this.region = region;
  }

  @Override
  public String toString() {
    if (this.region.size() == 1) {
      return region.get(0).toString();
    } else {
      return region.get(0).toString() + ":" + region.get(region.size() - 1).toString();
    }
  }

  @Override
  public Value evaluate() {
    if (region.size() == 1)
      return region.get(0).evaluate();
    else {
      throw new IllegalArgumentException("Cannot evaluate a reference of more than one cell");

    }
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitReference(this);
  }


  @Override
  public boolean checkCycles(Cell c, ArrayList<Cell> acc) {
    return false;
  }
}
