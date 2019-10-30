package edu.cs3500.spreadsheets.model;

public interface CellContent {
  Value evaluate ();

  <T> T accept(CellContentVisitor<T> visitor);

  //public String toString();

  //boolean equals();
}
