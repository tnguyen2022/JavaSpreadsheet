package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Function class that represents the sum function.
 */
public class SumFunction implements Function{
  private ArrayList<Formula> args;

  /**
   * The sum function and the arguments it is adding together
   * @param args the array list of arguments
   */
  public SumFunction(ArrayList<Formula> args) {
    String funcName = "SUM";
    this.args = args;
  }

  @Override
  public Value canEvaluate(Coord c) {
    return evaluate();
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

  @Override
  public boolean checkCycle(Coord c, ArrayList<Coord> acc) {
    return false;
  }

  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }
}
