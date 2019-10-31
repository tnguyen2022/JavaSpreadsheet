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
              getIndexOfSplit(s.split(":")[0]))));
      int row1 =  Integer.parseInt(s.split(":")[0].substring(
              getIndexOfSplit(s.split(":")[0])));
      int col2 = (Coord.colNameToIndex(s.split(":")[1].substring(0,
              getIndexOfSplit(s.split(":")[1]))));
      int row2 =  Integer.parseInt(s.split(":")[1].substring(
              getIndexOfSplit(s.split(":")[1])));

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
      Cell currentCell = Worksheet.getCell(Coord.colNameToIndex(s.substring(0, getIndexOfSplit(s))),
              Integer.parseInt(s.substring(getIndexOfSplit(s))));
      if (checkCycles(currentCell, new ArrayList<Cell>())){

      }
      throw new IllegalArgumentException("Reference contains cycles: Can't reference itself");
      ;
      region.add(currentCell.content);
    }
  }

  private int getIndexOfSplit(String split) {
    int index = 0;
    boolean isRestInteger = false;
    if (!Character.isLetter(split.charAt(0))) {
      throw new IllegalArgumentException("Column reference has to be a Letter");
    }
    if (!Character.isLetter(split.charAt(split.length() - 1))) {
      throw new IllegalArgumentException("Row reference has to be a Integer number");
    }
    String row = Character.toString(split.charAt(0));
    for (int i = 1; i < split.length(); i++) {
      if (!isRestInteger) {
        if (Character.isDigit(split.charAt(i))) {
          index = i;
          isRestInteger = true;
        }
      } else {
        if (Character.isLetter(split.charAt(i))) {
          throw new IllegalArgumentException("Row reference has to be a Integer number");
        }
      }
    }

    return index;
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
  public boolean checkCycles(ArrayList<Cell> acc) {

    return this.checkCycles(acc);
  }
}
