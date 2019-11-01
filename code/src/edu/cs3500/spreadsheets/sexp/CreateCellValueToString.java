package edu.cs3500.spreadsheets.sexp;

import java.util.List;

/**
 * Represents the to string method for a cell value.
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
    for(int i = 0; i < l.size(); i++){
      str += l.get(i).accept(new CreateCellValueToString());
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
