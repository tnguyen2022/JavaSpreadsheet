package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Reference implements Formula {
  private ArrayList<CellContent> region;

  public Reference(ArrayList<CellContent> region){
    this.region = region;
  }

  @Override
  public String toString() {
    if (this.region.size() == 1){
      return region.get(0).toString();
    }
    else{
      return region.get(0).toString() + ":" + region.get(region.size() - 1).toString();
    }
  }

  @Override
  public Formula Sum() {
    return this;
  }

  @Override
  public Formula Product() {
    return this;
  }

  @Override
  public Formula LessThan() {
    return this;
  }

  @Override
  public Formula Left() {
    return this;
  }

  @Override
  public Value evaluate() {
    return null;
  }
}
