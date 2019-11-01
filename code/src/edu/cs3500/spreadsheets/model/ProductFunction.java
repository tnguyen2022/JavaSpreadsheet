package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Representation of a product function in a cell.
 */
public class ProductFunction implements Function{
  private ArrayList<Formula> args;

  /**
   * The product function and the arguments it is being multiplied.
   * @param args the arguments being evaluated
   */
  public ProductFunction(ArrayList<Formula> args) {
    String funcName = "PRODUCT";
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
    double totalProduct = 1;
    for (Formula f : args) {
      totalProduct *= f.accept(new Product());
    }
    return new DoubleValue(totalProduct);
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
