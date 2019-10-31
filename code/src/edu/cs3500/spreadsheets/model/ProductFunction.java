package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class ProductFunction implements Function{
  private ArrayList<Formula> args;

  public ProductFunction(ArrayList<Formula> args) {
    String funcName = "PRODUCT";
    this.args = args;
  }

  @Override
  public Value evaluate() {
    if (args.size() == 0) {
      throw new IllegalArgumentException("Needs at least one argument.");
    }
    double totalProduct = 0;
    for (Formula f : args) {
      totalProduct += f.accept(new Product());
    }
    return new DoubleValue(totalProduct);
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }
}
