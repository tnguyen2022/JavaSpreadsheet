package edu.cs3500.spreadsheets.model;

public class DoubleValue implements Value{
  double value;

  public DoubleValue (double value){
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor){
    return visitor.visitDoubleValue(this);
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public String toString() {
    return "" + value;
  }
}
