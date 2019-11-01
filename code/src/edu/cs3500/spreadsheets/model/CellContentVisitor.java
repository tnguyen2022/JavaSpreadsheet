package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.Function.Function;
import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.StringValue;

/**
 * An abstracted function object for processing any {@link CellContent}.
 * @param <T> The return type of this function
 */
public interface CellContentVisitor<T>{
  /**
   * Process a blank value.
   * @param b the value
   * @return the desired result
   */
  T visitBlank (Blank b);

  /**
   * Process a reference.
   * @param r the reference
   * @return the desired result
   */
  T visitReference (Reference r);

  /**
   * Process a function.
   * @param func the function
   * @return the desired result
   */
  T visitFunction (Function func);

  /**
   * Process a function.
   * @param d the value
   * @return the desired result
   * @throws UnsupportedOperationException when the the value is not a double value
   */
  T visitDoubleValue(DoubleValue d) throws UnsupportedOperationException;

  /**
   * Process a string value.
   * @param s the value
   * @return the desired result
   * @throws UnsupportedOperationException when the value is not a string
   */
  T visitStringValue(StringValue s) throws UnsupportedOperationException;

  /**
   * Process a boolean value.
   * @param b the value
   * @return the desired result
   * @throws UnsupportedOperationException when the value is not a boolean
   */
  T visitBooleanValue(BooleanValue b) throws UnsupportedOperationException;
}
