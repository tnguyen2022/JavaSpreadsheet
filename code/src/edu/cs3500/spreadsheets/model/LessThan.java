package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class LessThan implements ValueVisitor<Value> {


  @Override
  public Value visitDoubleValue(DoubleValue d) {
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
