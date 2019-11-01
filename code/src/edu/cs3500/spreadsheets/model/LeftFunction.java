package edu.cs3500.spreadsheets.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A left function that returns contents starting from the left from a given number.
 */
public class LeftFunction implements Function{
  private ArrayList<Formula> args;
  String funcName;

  /**
   * The left function and what it is evaluating.
   * @param args The arguments being used for the function
   */
  public LeftFunction(ArrayList<Formula> args) {
    funcName = "LEFT";
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
      secArgInt = Integer.parseInt(secondArg.split(".")[0]);
    }
    // if the second argument was not a number
    catch (UnsupportedOperationException | NumberFormatException e) {
      throw new UnsupportedOperationException("Second argument must be a number/integer");
    }
    return new StringValue(firstArg.substring(0, secArgInt));
  }

  @Override
  public boolean checkCycle(Coord c, ArrayList<Coord> acc) {
return false;
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
    return true;
  }
}
