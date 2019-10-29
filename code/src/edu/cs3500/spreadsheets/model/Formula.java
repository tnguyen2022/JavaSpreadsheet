package edu.cs3500.spreadsheets.model;

public interface Formula extends CellContent {
  public Formula Sum();

  public Formula Product();

  public Formula LessThan();

  public Formula Left();

}
