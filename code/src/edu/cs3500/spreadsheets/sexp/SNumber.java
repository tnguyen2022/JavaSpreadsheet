package edu.cs3500.spreadsheets.sexp;

import java.util.Objects;

/**
 * A numeric constant {@link Sexp}.
 */
public class SNumber implements Sexp {
  double num;

  public SNumber(double num) {
    this.num = num;
  }

  @Override
  public <R> R accept(SexpVisitor<R> visitor) {
    return visitor.visitNumber(this.num);
  }

  @Override
  public String toString() {
    return Double.toString(this.num);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SNumber sNumber = (SNumber) o;
    return Double.compare(sNumber.num, num) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(num);
  }
}
