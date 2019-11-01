package edu.cs3500.spreadsheets.sexp.SexpVisitorFunctions;

import java.util.List;

import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor function object that returns the string representation of a Sexp.
 */
public class CreateCellValueToString implements SexpVisitor<String> {
  @Override
  public String visitBoolean(boolean b) {
    return "" + b;
  }

  @Override
  public String visitNumber(double d) {
    return "" + d;
  }

  @Override
  public String visitSList(List<Sexp> l) {
    String str = "";
    for (Sexp sexp : l) {
      str += sexp.accept(new CreateCellValueToString());
    }
    return str;
  }

  @Override
  public String visitSymbol(String s) {
    return s;
  }

  @Override
  public String visitString(String s) {
    return s;
  }
}
