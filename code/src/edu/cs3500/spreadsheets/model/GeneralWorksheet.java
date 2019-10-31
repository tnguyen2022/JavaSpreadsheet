package edu.cs3500.spreadsheets.model;

public interface GeneralWorksheet {
  public void modifyOrAdd(int col, int row, String contents) throws IllegalArgumentException;

  public Value evaluateCell (Cell c) throws IllegalArgumentException;
}
