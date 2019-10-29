package edu.cs3500.spreadsheets.model;

public interface Value extends Formula, CellContent {
  <T> T accept(ValueVisitor<T> visitor);
}
