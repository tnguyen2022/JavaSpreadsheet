package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class SumFunction implements Function{
  private ArrayList<Formula> args;

  public SumFunction(ArrayList<Formula> args) {
    String funcName = "SUM";
    this.args = args;
  }

  @Override
  public Value evaluate() {
        if (args.size() == 0) {
          throw new IllegalArgumentException("Needs at least one argument.");
        }
        double totalSum = 0;
        for (Formula f : args) {
          totalSum += f.accept(new Sum());
        }
        return new DoubleValue(totalSum);
  }

  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }
}
