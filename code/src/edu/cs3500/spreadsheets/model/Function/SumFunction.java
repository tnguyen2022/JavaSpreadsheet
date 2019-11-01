package edu.cs3500.spreadsheets.model.Function;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.CellContentVisitorFunctions.Sum;
import edu.cs3500.spreadsheets.model.Value.Value;

/**
 * Function class that represents the sum function that computes the total sum of its given
 * arguments.
 */
public class SumFunction implements Function {
  private ArrayList<Formula> args;

  /**
   * The sum function and the arguments it is adding together
   * @param args the array list of arguments
   */
  public SumFunction(ArrayList<Formula> args) {
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
    for (Function f : memoizeFunction.keySet()) {
      if (this.equals(f)) {
        return memoizeFunction.get(f);
      }
    }
    double totalSum = 0;
    for (Formula f : args) {
      totalSum += f.accept(new Sum());
    }

    memoizeFunction.put(this, new DoubleValue(totalSum));
    return new DoubleValue(totalSum);
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    for (int i  = 0; i < args.size(); i++){
      if (args.get(i).checkCycle(acc)){
        return true;
      }
    }
    return false;
  }

  /**
   * checks if there is a circular reference when using a function.
   * @param acc the coordinated being referenced
   * @return whether or not there is a circular reference
   */
  public boolean checkCycleHelper(ArrayList<Coord> acc){
    return checkCycle(acc);
  }

  /**
   * Accepts the CellContentVisitor to allow for function objects to exist.
   * @param visitor the visiting cell content
   * @param <T> the value type
   * @return The desired result
   */
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }

  @Override
  public boolean equals (Object o){
    if (this == o) {
      return true;
    }
    if( o instanceof SumFunction) {
      SumFunction that = (SumFunction) o;
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
