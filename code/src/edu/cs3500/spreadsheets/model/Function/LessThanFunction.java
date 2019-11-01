package edu.cs3500.spreadsheets.model.Function;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.CellContentVisitorFunctions.LessThan;
import edu.cs3500.spreadsheets.model.Value.Value;

/**
 * Function class for less than function.
 */
public class LessThanFunction implements Function {
  private ArrayList<Formula> args;

  /**
   * The less than function and the arguments that are being compared.
   * @param args the arguments that are being evaluated
   */
  public LessThanFunction(ArrayList<Formula> args) {
    this.args = args;
  }

  @Override
  public Value canEvaluate(Coord c) {
    return evaluate();
  }

  @Override
  public Value evaluate() {
    boolean lessThan;
    if (args.size() != 2) {
      throw new IllegalArgumentException("Needs exactly two arguments");
    }
    for (Function f : memoizeFunction.keySet()){
      if(this.equals(f)){
        return memoizeFunction.get(f);
      }
    }
    lessThan = this.args.get(0).accept(new LessThan()) < this.args.get(1).accept(new LessThan());

    memoizeFunction.put(this, new BooleanValue(lessThan));
    return new BooleanValue(lessThan);
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    for (Formula f : args) {
      if (f.checkCycleHelper(acc)) {
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
    if(o instanceof LessThanFunction) {
      LessThanFunction that = (LessThanFunction) o;
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
