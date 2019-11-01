package edu.cs3500.spreadsheets.model.Function;

import java.util.HashMap;

import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Value.Value;

/**
 * Representation of a Function.
 */
public interface Function extends Formula {
  static HashMap<Function, Value> memoizeFunction = new HashMap<>();
}