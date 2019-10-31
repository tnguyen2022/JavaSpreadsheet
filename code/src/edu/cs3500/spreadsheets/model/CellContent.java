package edu.cs3500.spreadsheets.model;

public interface CellContent {
  Value evaluate() throws IllegalArgumentException;

  <T> T accept(CellContentVisitor<T> visitor) throws UnsupportedOperationException;

  //public String toString();

  //boolean equals();
}
