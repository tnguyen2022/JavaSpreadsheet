package edu.cs3500.spreadsheets.model;

public class Blank implements CellContent {
  double defaultValue;

  public Blank (){
    defaultValue = 0;
  }

  @Override
  public String toString(){
    return "";
  }

  @Override
  public Value evaluate() {
    return null;
  }
}
