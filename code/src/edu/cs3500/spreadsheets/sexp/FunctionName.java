package edu.cs3500.spreadsheets.sexp;

import java.util.List;

/**
 * Represents the function name for a function.
 */
public class FunctionName implements SexpVisitor<String> {
  @Override
  public String visitBoolean(boolean b) {
    throw new IllegalArgumentException("Has to be a valid function");
  }

  @Override
  public String visitNumber(double d) {
    throw new IllegalArgumentException("Has to be a valid function");
  }

  @Override
  public String visitSymbol(String s) {
    if (s.equalsIgnoreCase("SUM") || s.equalsIgnoreCase("PRODUCT") ||
            s.equals("<") || s.equalsIgnoreCase("LEFT")){
      return s.toUpperCase();
    }
    else {
      throw new IllegalArgumentException("Has to be a valid function");
    }
  }

  @Override
  public String visitString(String s) {
    throw new IllegalArgumentException("Has to be a valid function");
  }

  @Override
  public String visitSList(List l) {
    throw new IllegalArgumentException("Has to be a valid function");
  }
}
