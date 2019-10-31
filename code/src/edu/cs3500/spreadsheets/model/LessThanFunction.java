package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class LessThanFunction implements Function{
  private ArrayList<Formula> args;

  public LessThanFunction(ArrayList<Formula> args) {
    String funcName = "<";
    this.args = args;
  }

  @Override
  public Value evaluate() {
    boolean lessThan = false;
    if (args.size() != 2) {
      throw new IllegalArgumentException("Needs exactly two arguments");
    }
    lessThan = this.args.get(0).accept(new LessThan()) < this.args.get(1).accept(new LessThan());

    return new BooleanValue(lessThan);
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }
}
