package edu.cs3500.spreadsheets.model;

public class Sum implements ValueVisitor<Value> {

  @Override
  public Value visitDoubleValue (DoubleValue d) {
    return d;
  }

  @Override
  public Value visitStringValue(StringValue s) {
    throw new IllegalArgumentException("Argument has to be a Number");
  }

  @Override
  public Value visitBooleanValue(BooleanValue b) {
    throw new IllegalArgumentException("Argument has to be a Number");
  }

}
