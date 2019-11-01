package edu.cs3500.spreadsheets.model.CellContentVisitorFunctions;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Function.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.StringValue;

/**
 * A left function that returns contents starting from the left from a given number.
 */
public class Left implements CellContentVisitor<String> {

  @Override
  public String visitBlank(Blank b) {
    return b.toString();
  }

  @Override
  public String visitReference(Reference r) {
    return r.evaluate().accept(this);
  }

  @Override
  public String visitFunction(Function func) {
    return func.evaluate().accept(this);
  }

  @Override
  public String visitDoubleValue(DoubleValue d) {
    return d.toString();
  }

  @Override
  public String visitStringValue(StringValue s) {
    return s.value;
  }

  @Override
  public String visitBooleanValue(BooleanValue b) throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Argument has to be a String or Double");
  }
}
