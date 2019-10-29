package edu.cs3500.spreadsheets.model;

public interface ValueVisitor<T>{
  /**
   * Process a boolean value.
   * @param d the value
   * @return the desired result
   */

  T visitDoubleValue(DoubleValue d);

  T visitStringValue(StringValue s);

  T visitBooleanValue(BooleanValue b);
}
