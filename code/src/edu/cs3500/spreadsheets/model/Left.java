package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Left implements ValueVisitor<Value> {

  @Override
  public Value visitDoubleValue(DoubleValue d) {
    throw new IllegalArgumentException("Argument has to be a String");
  }

  @Override
  public Value visitStringValue(StringValue s) {
    return s;
  }

  @Override
  public Value visitBooleanValue(BooleanValue b) {
    throw new IllegalArgumentException("Argument has to be a String");
  }
}
