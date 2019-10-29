package edu.cs3500.spreadsheets.sexp;

/**
 * <p>Represents an s-expression.  An s-expression is one of</p>
 *
 * <ul>
 *   <li>A boolean</li>
 *   <li>A number</li>
 *   <li>A String</li>
 *   <li>A symbol</li>
 *   <li>A list of s-expressions</li>
 * </ul>
 *
 * <p>
 *   S-expressions are a general-purpose datatype, and so have no methods of their own.
 *   All processing on s-expressions is done through the visitor pattern: see {@link SexpVisitor}.
 * </p>
 */
public interface Sexp {
  <R> R accept(SexpVisitor<R> visitor);
}
