package edu.cs3500.spreadsheets.model.cellcontentvisitfunc;


import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.function.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.value.StringValue;

/**
 * A SUM visitor function that returns the sum of a given reference or value.
 */
public class Sum implements CellContentVisitor<Double> {

  @Override
  public Double visitBlank(Blank b) {
    return 0.0;
  }

  @Override
  public Double visitReference(Reference r) {
    double sumReference = 0;
    for (Cell c : r.region) {
      sumReference += c.content.accept(this);
    }
    return sumReference;
  }

  @Override
  public Double visitFunction(Function func) {
    return func.evaluate().accept(this);
  }

  @Override
  public Double visitDoubleValue(DoubleValue d) {
    return d.value;
  }

  @Override
  public Double visitStringValue(StringValue s) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Argument has to be a Number");
  }

  @Override
  public Double visitBooleanValue(BooleanValue b) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Argument has to be a Number");
  }

}
