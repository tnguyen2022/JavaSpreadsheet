package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representation of a Function.
 */
public interface Function extends Formula {
  static HashMap<Function, Value> memoizeFunction = new HashMap<>();
}