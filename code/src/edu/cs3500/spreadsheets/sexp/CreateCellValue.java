package edu.cs3500.spreadsheets.sexp;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Value;

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
    for (int i = 0; i < l.size(); i++){
      str +=  l.get(i).accept(new CreateCellValueToString());
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
