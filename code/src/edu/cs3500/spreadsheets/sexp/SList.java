package edu.cs3500.spreadsheets.sexp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a list s-expression (i.e. <code>({@link Sexp} ...)</code>).
 */
public class SList implements Sexp {
  private final List<Sexp> contents;

  public SList(Sexp... contents) {
    this(Arrays.asList(contents));
  }

  public SList(List<Sexp> contents) {
    this.contents = new ArrayList<>(Objects.requireNonNull(contents));
  }

  @Override
  public <R> R accept(SexpVisitor<R> visitor) {
    return visitor.visitSList(Collections.unmodifiableList(this.contents));
  }

  @Override
  public String toString() {
    return "(" + this.contents.stream().map(Sexp::toString).collect(Collectors.joining(" ")) + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SList sList = (SList) o;
    return contents.equals(sList.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contents);
  }
}
