package edu.cs3500.spreadsheets.provider.value;

import java.util.List;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.function.Function;
import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.value.StringValue;

public class ValueVisitorAdapter<T> implements CellContentVisitor<T> {
  private ValueVisitor v;

  ValueVisitorAdapter(ValueVisitor v){
    this.v = v;
  }

  @Override
  public T visitBlank(Blank b) {
    return null;
  }

  @Override
  public T visitReference(Reference r) {
    return null;
  }

  @Override
  public T visitFunction(Function func) {
    return null;
  }

  @Override
  public T visitDoubleValue(DoubleValue d) throws UnsupportedOperationException {
    return (T) v.visitDouble(d.value);
  }

  @Override
  public T visitStringValue(StringValue s) throws UnsupportedOperationException {
    return (T) v.visitString(s.value);
  }

  @Override
  public T visitBooleanValue(BooleanValue b) throws UnsupportedOperationException {
    return (T) v.visitBoolean(b.value);
  }
}
