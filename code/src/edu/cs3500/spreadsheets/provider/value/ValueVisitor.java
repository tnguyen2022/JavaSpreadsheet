package edu.cs3500.spreadsheets.provider.value;

import java.util.List;

/**
 * An abstracted function object for processing any {@link IValue}s.
 * @param <R> The return type of this function
 */
public interface ValueVisitor<R> {

  /**
   * Process a double value.
   * @param d the value
   * @return the desired result
   */
  R visitDouble(double d);

  /**
   * Process a boolean value.
   * @param b the value
   * @return the desired result
   */
  R visitBoolean(boolean b);

  /**
   * Process a string value.
   * @param s the value
   * @return the desired result
   */
  R visitString(String s);

  /**
   * Process a list of values.
   * @param list the list of values
   * @return the desired result
   */
  R visitList(List<IValue> list);
}
