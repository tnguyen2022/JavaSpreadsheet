package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Function class for less than function.
 */
public class LessThanFunction implements Function{
  private ArrayList<Formula> args;

  /**
   * The less than function and the arguments that are being compared.
   * @param args the arguments that are being evaluated
   */
  public LessThanFunction(ArrayList<Formula> args) {
    String funcName = "<";
    this.args = args;
  }

  @Override
  public Value canEvaluate(Coord c) {
    return evaluate();
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
  public boolean checkCycle(Coord c, ArrayList<Coord> acc) {
    return false;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }
}
