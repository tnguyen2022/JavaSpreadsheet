package edu.cs3500.spreadsheets.provider.value;

import java.util.List;
import java.util.Objects;

/**
 * Represents a list of IValues. Intended for giving functions multiple inputs, not for
 * displaying as a final cell output.
 */
public class ListVal implements IValue {
  private List<IValue> values;

  public ListVal(List<IValue> values) {
    this.values = values;
  }

  @Override
  public <R> R accept(ValueVisitor<R> visitor) {
    return visitor.visitList(this.values);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return values.equals(((ListVal) o).values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values);
  }

}
