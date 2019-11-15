package edu.cs3500.spreadsheets.model.function;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.cellcontentvisitfunc.Product;
import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Representation of a product function that computes the total product of its given arguments.
 */
public class ProductFunction implements Function {
  private ArrayList<Formula> args;

  /**
   * The product function and the arguments it is being multiplied.
   *
   * @param args the arguments being evaluated
   */
  public ProductFunction(ArrayList<Formula> args) {
    this.args = args;
  }

  @Override
  public Value evaluate() {
    if (args.size() == 0) {
      throw new IllegalArgumentException("Needs at least one argument.");
    }
    for (Function f : memoizeFunction.keySet()) {
      if (this.equals(f)) {
        return memoizeFunction.get(f);
      }
    }
    double totalProduct = 1;
    for (Formula f : args) {
      totalProduct *= f.accept(new Product());
    }

    memoizeFunction.put(this, new DoubleValue(totalProduct));
    return new DoubleValue(totalProduct);
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    if (memoizeCycle.containsKey(this)) {
      return memoizeCycle.get(this);
    }
    for (Formula f : args) {
      if (f.checkCycle(acc)) {
        return true;
      }
    }
    memoizeCycle.put(this, false);
    return false;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof ProductFunction) {
      ProductFunction that = (ProductFunction) o;
      if (this.args.size() == that.args.size()) {
        for (int i = 0; i < this.args.size(); i++) {
          if (!this.args.get(i).equals(that.args.get(i))) {
            return false;
          }
        }
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(args);
  }

  @Override
  public String toString() {
    String output = "(PRODUCT";
    for (int i = 0; i < this.args.size(); i++) {
      output += " " + args.get(i).toString();
    }
    output += ")";
    return output;
  }
}
