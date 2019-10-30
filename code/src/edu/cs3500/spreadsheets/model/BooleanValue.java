package edu.cs3500.spreadsheets.model;

public class BooleanValue implements Value{
  boolean value;
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
}

