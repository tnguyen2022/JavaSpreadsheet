package edu.cs3500.spreadsheets.sexp;

import java.util.Objects;

/**
 * A string constant {@link Sexp}.
 */
public class SString implements Sexp {
  String val;

  public SString(String name) {
    this.val = Objects.requireNonNull(name);
  }

  @Override
  public <R> R accept(SexpVisitor<R> visitor) {
    return visitor.visitString(this.val);
  }

  @Override
  public String toString() {
    return "\"" + this.val.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SString sString = (SString) o;
    return val.equals(sString.val);
  }

  @Override
  public int hashCode() {
    return Objects.hash(val);
  }
}
