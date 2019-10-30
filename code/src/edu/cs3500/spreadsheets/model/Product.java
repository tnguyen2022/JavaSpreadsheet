package edu.cs3500.spreadsheets.model;

public class Product implements CellContentVisitor<Double> {
  @Override
  public Double visitBlank(Blank b) {
    return b.defaultValue;
  }

  @Override
  public Double visitReference(Reference r) {
    double sumReference = 0;
    for (CellContent c: r.region) {
      sumReference *= c.accept(this);
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
