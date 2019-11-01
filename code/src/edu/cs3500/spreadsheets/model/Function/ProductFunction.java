package edu.cs3500.spreadsheets.model.Function;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.CellContentVisitorFunctions.Product;
import edu.cs3500.spreadsheets.model.Value.Value;

/**
 * Representation of a product function in a cell.
 */
public class ProductFunction implements Function {
  private ArrayList<Formula> args;

  /**
   * The product function and the arguments it is being multiplied.
   * @param args the arguments being evaluated
   */
  public ProductFunction(ArrayList<Formula> args) {
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
    for (Function f : memoizeFunction.keySet()){
      if(this.equals(f)){
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
    for (Formula f : args) {
      if (f.checkCycle(acc)){
        return true;
      }
    }
    return false;
  }

  public boolean checkCycleHelper(ArrayList<Coord> acc){
    return checkCycle(acc);
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }

  @Override
  public boolean equals (Object o){
    if (this == o) {
      return true;
    }
    if(o instanceof ProductFunction) {
      ProductFunction that = (ProductFunction) o;
      if (this.args.size() == that.args.size()){
        for (int i = 0; i < this.args.size(); i++){
          if(!this.args.get(i).equals(that.args.get(i))){
            return false;
          }
        }
      }
      else{
        return false;
      }
    }
      return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(args);
  }
}
