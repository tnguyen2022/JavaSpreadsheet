package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Reference implements Formula {
  ArrayList<CellContent> region;

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
  public Value evaluate() {
    if (region.size() == 1)
      return region.get(0).evaluate();
    else{
      throw new IllegalArgumentException("Cannot evaluate a reference of more than one cell");

    }
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitReference(this);
  }
}
