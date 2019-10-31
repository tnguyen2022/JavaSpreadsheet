package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class StringValue implements Value{
  String value;
  public StringValue(String value){
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor){
    return visitor.visitStringValue(this);
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycles(Cell c, ArrayList<Cell> acc) {
    return false;
  }
}
