package edu.cs3500.spreadsheets.model.Function;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.CellContentVisitorFunctions.Left;
import edu.cs3500.spreadsheets.model.Value.StringValue;
import edu.cs3500.spreadsheets.model.Value.Value;

/**
 * A left function that returns contents starting from the left from a given number.
 */
public class LeftFunction implements Function {
  private ArrayList<Formula> args;

  /**
   * The left function and what it is evaluating.
   * @param args The arguments being used for the function
   */
  public LeftFunction(ArrayList<Formula> args) {
    this.args = args;
  }

  @Override
  public Value canEvaluate(Coord c) {
    return evaluate();
  }

  @Override
  public Value evaluate() {
    if (args.size() != 2) {
      throw new IllegalArgumentException("Needs exactly two arguments");
    }
    for (Function f : memoizeFunction.keySet()){
      if(this.equals(f)){
        return memoizeFunction.get(f);
      }
    }
    String firstArg;
    String secondArg;
    int secArgInt;
    // tries to get first argument as a String (could be a string or number)
    try {
      firstArg = this.args.get(0).accept(new Left());
    } catch (UnsupportedOperationException e) {
      throw new UnsupportedOperationException("First argument must be a String or Number.");
    }
    // tries to get the second argument which must be a number
    // cuts off the digits after the decimal if a double
    // converts String into index as an integer
    try {
      secondArg = this.args.get(1).accept(new Left());
      secArgInt= (int) Double.parseDouble(secondArg);
    }
    // if the second argument was not a number
    catch (UnsupportedOperationException | NumberFormatException e) {
      throw new UnsupportedOperationException("Second argument must be a number/integer");
    }
    memoizeFunction.put(this, new StringValue(firstArg.substring(0, secArgInt)));
    return new StringValue(firstArg.substring(0, secArgInt));
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

  /**
   * checks if there is a circular reference when using a function.
   * @param acc the coordinated being referenced
   * @return whether or not there is a circular reference
   */
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
    if(o instanceof LeftFunction) {
      LeftFunction that = (LeftFunction) o;
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
