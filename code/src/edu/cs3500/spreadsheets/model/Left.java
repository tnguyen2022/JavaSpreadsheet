package edu.cs3500.spreadsheets.model;

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
