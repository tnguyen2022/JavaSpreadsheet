package edu.cs3500.spreadsheets.provider.value;

/**
 * Represents an output value of a cell, either a boolean, double, or a string.
 */

public interface IValue {
  /**
   * Uses the given visitor to process this value.
   *
   * @param visitor has the function method to call on this value
   * @param <R>     the output of the given visitor
   * @return the result of applying the visitor to this value
   */
  <R> R accept(ValueVisitor<R> visitor);
}


