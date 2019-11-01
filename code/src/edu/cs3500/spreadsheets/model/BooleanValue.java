package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a boolean value that can be put into a cell.
 */
public class BooleanValue implements Value{
  private boolean value;

  /**
   * The boolean value that can be contained in a cell.
   * @param value the boolean value
   */
  public BooleanValue(boolean value){
    this.value = value;
  }

  /**
   * Allows for the value to be evaluated by the cell container.
   * @param visitor the visiting cell content
   * @param <T> the type of value
   * @return the desired result
   */
  public <T> T accept(CellContentVisitor<T> visitor){
    return visitor.visitBooleanValue(this);
  }


  @Override
  public Value canEvaluate(Coord c) {
    return evaluate();
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycle(Coord c, ArrayList<Coord> acc) {
    return false;
  }

  @Override
  public String toString(){
    return ("" + this.value).toLowerCase();
  }
}

