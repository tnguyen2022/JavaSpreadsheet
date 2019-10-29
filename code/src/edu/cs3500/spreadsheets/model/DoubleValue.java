package edu.cs3500.spreadsheets.model;

public class DoubleValue implements Value{
  private double dValue;

  public DoubleValue (double dValue){
    this.dValue = dValue;
  }

  @Override
  public Formula Sum() {
    return new DoubleValue(this.dValue);
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


  public <T> T accept(ValueVisitor<T> visitor){
    return visitor.visitDoubleValue(this);
  }

  @Override
  public Value evaluate() {
    return null;
  }
}
