package edu.cs3500.spreadsheets.model;

public class StringValue implements Value{
  private String sValue;
  public StringValue (String sValue){
    this.sValue = sValue;
  }

  @Override
  public Formula Sum() {
    return null;
  }

  @Override
  public Formula Product() {
    return null;
  }

  @Override
  public Formula LessThan() {
    return null;
  }

  @Override
  public Formula Left() {
    return null;
  }

  @Override
  public <T> T accept(ValueVisitor<T> visitor){
    return visitor.visitStringValue(this);
  }

  @Override
  public Value evaluate() {
    return null;
  }
}
