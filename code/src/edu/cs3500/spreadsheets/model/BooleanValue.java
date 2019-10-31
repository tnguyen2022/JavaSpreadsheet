package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class BooleanValue implements Value{
  private boolean value;
  public BooleanValue(boolean value){
    this.value = value;
  }

  public <T> T accept(CellContentVisitor<T> visitor){
    return visitor.visitBooleanValue(this);
  }


  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycles(Cell c, ArrayList<Cell> acc) {
    return false;
  }

  @Override
  public String toString(){
    return ("" + this).toUpperCase();
  }
}

