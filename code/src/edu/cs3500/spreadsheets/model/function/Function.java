package edu.cs3500.spreadsheets.model.function;

import java.util.HashMap;

import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Representation of a Function.
 */
public interface Function extends Formula {
  /**
   * Memoize Functions to increase efficiency when evaluating cells.
   */
  HashMap<Function, Value> memoizeFunction = new HashMap<>();

  /**
   * Memoize Functions to increase efficiency when checking if the cell contains a cycle.
   */
  HashMap<Function, Boolean> memoizeCycle = new HashMap<>();
}