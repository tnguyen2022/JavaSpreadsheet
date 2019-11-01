package edu.cs3500.spreadsheets.sexp.SexpVisitorFunctions;

import java.util.List;

import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.StringValue;
import edu.cs3500.spreadsheets.model.Value.Value;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Creates the value for the cell.
 */
public class CreateCellValue implements SexpVisitor<Value> {

  @Override
  public Value visitBoolean(boolean b) {
    return new BooleanValue(b);
  }

  @Override
  public Value visitNumber(double d) {
    return new DoubleValue(d);
  }

  @Override
  public Value visitSList(List<Sexp> l) {
    String str = "";
    for (Sexp sexp : l) {
      str += sexp.accept(new CreateCellValueToString());
    }
    return new StringValue(str);
  }

  @Override
  public Value visitSymbol(String s) {
    return new StringValue(s);
  }

  @Override
  public Value visitString(String s) {
    return new StringValue(s);
  }
}
