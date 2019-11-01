package edu.cs3500.spreadsheets.model.CellContentVisitorFunctions;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Function.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;

/**
 * Represents the sum function.
 */
public class Sum implements CellContentVisitor<Double> {

  @Override
  public Double visitBlank(Blank b) {
    return b.defaultValue;
  }

  @Override
  public Double visitReference(Reference r) {
    double sumReference = 0;
    for (Cell c: r.region) {
      sumReference += Worksheet.getCell(c.cellReference.col,
              c.cellReference.row).content.accept(this);
    }
    return sumReference;
  }

  @Override
  public Double visitFunction(Function func) {
    return func.evaluate().accept(this);
  }

  @Override
  public Double visitDoubleValue (DoubleValue d) {
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
