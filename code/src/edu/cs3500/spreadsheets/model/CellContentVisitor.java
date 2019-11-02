package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.function.Function;
import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.value.StringValue;

/**
 * An abstracted function object for processing any {@link CellContent}.
 *
 * @param <T> The return type of this function
 */
public interface CellContentVisitor<T> {
  /**
   * Visits and process a blank value according to the visitor function specifications.
   *
   * @param b the value
   * @return the desired result
   */
  T visitBlank(Blank b);

  /**
   * Visits and process a reference according to the visitor function specifications.
   *
   * @param r the reference
   * @return the desired result
   */
  T visitReference(Reference r);

  /**
   * Visits and process a function according to the visitor function specifications.
   *
   * @param func the function
   * @return the desired result
   */
  T visitFunction(Function func);

  /**
   * Visits and process a DoubleValue according to the visitor function specifications.
   *
   * @param d the value
   * @return the desired result
   * @throws UnsupportedOperationException when the the value is not a double value
   */
  T visitDoubleValue(DoubleValue d) throws UnsupportedOperationException;

  /**
   * Visits and process a StringValue according to the visitor function specifications.
   *
   * @param s the value
   * @return the desired result
   * @throws UnsupportedOperationException when the value is not a string
   */
  T visitStringValue(StringValue s) throws UnsupportedOperationException;

  /**
   * Visits and process a BooleanValue according to the visitor function specifications.
   *
   * @param b the value
   * @return the desired result
   * @throws UnsupportedOperationException when the value is not a boolean
   */
  T visitBooleanValue(BooleanValue b) throws UnsupportedOperationException;
}
