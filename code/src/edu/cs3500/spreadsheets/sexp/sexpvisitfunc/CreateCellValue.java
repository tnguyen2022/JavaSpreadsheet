package edu.cs3500.spreadsheets.sexp.sexpvisitfunc;

import java.util.List;

import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.value.StringValue;
import edu.cs3500.spreadsheets.model.value.Value;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor function object that will creates the Value for a cell if not a Formula.
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
