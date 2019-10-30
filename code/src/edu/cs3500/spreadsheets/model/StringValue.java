package edu.cs3500.spreadsheets.model;

public class StringValue implements Value{
  String value;
  public StringValue (String value){
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
}
