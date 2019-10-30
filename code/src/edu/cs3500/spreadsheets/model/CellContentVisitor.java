package edu.cs3500.spreadsheets.model;

public interface CellContentVisitor<T>{

  T visitBlank (Blank b);

  T visitReference (Reference r);

  T visitFunction (Function func);

  T visitDoubleValue(DoubleValue d) throws UnsupportedOperationException;

  T visitStringValue(StringValue s) throws UnsupportedOperationException;

  T visitBooleanValue(BooleanValue b) throws UnsupportedOperationException;
}
