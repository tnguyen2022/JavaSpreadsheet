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
    return new DoubleValue(0);
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitBlank(this);
  }
}
