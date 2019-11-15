package edu.cs3500.spreadsheets.model.function;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.cellcontentvisitfunc.LessThan;
import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Function class for less than function that compares two numbers and outputs if with first number
 * is less than the second number.
 */
public class LessThanFunction implements Function {
  private ArrayList<Formula> args;

  /**
   * The less than function and the arguments that are being compared.
   *
   * @param args the arguments that are being evaluated
   */
  public LessThanFunction(ArrayList<Formula> args) {
    this.args = args;
  }


  @Override
  public Value evaluate() {
    boolean lessThan;
    //can only evaluate if there are exactly two arguments
    if (args.size() != 2) {
      throw new IllegalArgumentException("Needs exactly two arguments");
    }
    for (Function f : memoizeFunction.keySet()) {
      if (this.equals(f)) {
        return memoizeFunction.get(f);
      }
    }
    lessThan = this.args.get(0).accept(new LessThan()) < this.args.get(1).accept(new LessThan());

    memoizeFunction.put(this, new BooleanValue(lessThan));
    return new BooleanValue(lessThan);
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
    if (o instanceof LessThanFunction) {
      LessThanFunction that = (LessThanFunction) o;
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
    String output = "(<";
    for (int i = 0; i < this.args.size(); i++) {
      output += " " + args.get(i).toString();
    }
    output += ")";
    return output;
  }
}
