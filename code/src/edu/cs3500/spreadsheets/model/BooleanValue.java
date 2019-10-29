package edu.cs3500.spreadsheets.model;

public class BooleanValue implements Value{
  private boolean boolValue;
  public BooleanValue(boolean boolValue){
    this.boolValue = boolValue;
  }

  @Override
  public Formula Sum() {
    return new BooleanValue(this.boolValue);
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
    return visitor.visitBooleanValue(this);
  }


  @Override
  public Value evaluate() {
    return null;
  }
}

