package edu.cs3500.spreadsheets.model.function;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.CellContentVisitor;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.cellcontentvisitfunc.Sum;
import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Function class that represents the sum function that computes the total sum of its given
 * arguments.
 */
public class SumFunction implements Function {
  private ArrayList<Formula> args;

  /**
   * The sum function and the arguments it is adding together.
   *
   * @param args the array list of arguments
   */
  public SumFunction(ArrayList<Formula> args) {
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
    double totalSum = 0;
    for (Formula f : args) {
      totalSum += f.accept(new Sum());
    }

    memoizeFunction.put(this, new DoubleValue(totalSum));
    return new DoubleValue(totalSum);
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    if (memoizeCycle.containsKey(this)) {
      return memoizeCycle.get(this);
    }
    for (int i = 0; i < args.size(); i++) {
      if (args.get(i).checkCycle(acc)) {
        return true;
      }
    }
    memoizeCycle.put(this, false);
    return false;
  }

  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof SumFunction) {
      SumFunction that = (SumFunction) o;
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
    String output = "(SUM";
    for (int i = 0; i < this.args.size(); i++) {
      output += " " + args.get(i).toString();
    }
    output += ")";
    return output;
  }
}
