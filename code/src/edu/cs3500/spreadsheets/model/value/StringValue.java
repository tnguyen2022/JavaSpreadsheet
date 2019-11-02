package edu.cs3500.spreadsheets.model.value;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a string value in a worksheet.
 */
public class StringValue implements Value {
  public String value;

  /**
   * The string value in a string value.
   *
   * @param value the strinb
   */
  public StringValue(String value) {
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitStringValue(this);
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    return false;
  }


  @Override
  public String toString() {
    String print = "";
    for (int i = 0; i < value.length(); i++) {
      if (this.value.charAt(i) == '"') {
        print += "\\\"";
      } else if (this.value.charAt(i) == '\\') {
        print += "\\\\";
      } else {
        print += Character.toString(this.value.charAt(i));
      }
    }
    return "\"" + print + "\"";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof StringValue) {
      StringValue that = (StringValue) o;
      return this.value.equals(that.value);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
