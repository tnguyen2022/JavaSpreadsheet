package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Function implements Formula {
  public enum Func {SUM, PRODUCT, LESSTHAN, LEFT}
  private Func f;
  private ArrayList<Formula> args;

  Function(Func f, ArrayList<Formula> args) {
    this.f = f;
    this.args = args;
  }

  @Override
  public Value evaluate() {
    switch (f){
      case SUM:
        if (args.size() == 0) {
          throw new IllegalArgumentException("Needs at least one argument.");
        }
        double totalSum = 0;
        for (Formula f : args) {
          totalSum += f.accept(new Sum());
        }
        return new DoubleValue(totalSum);
      case PRODUCT:
          if (args.size() == 0) {
            throw new IllegalArgumentException("Needs at least one argument.");
          }
          double totalProduct = 0;
          for (Formula f : args) {
            totalProduct += f.accept(new Product());
          }
          return new DoubleValue(totalProduct);
      case LESSTHAN:
        boolean lessThan = false;
        if (args.size() != 2) {
          throw new IllegalArgumentException("Needs exactly two arguments");
        }
        lessThan = this.args.get(0).accept(new LessThan()) < this.args.get(1).accept(new LessThan());

        return new BooleanValue(lessThan);
      case LEFT:
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
        return new StringValue(firstArg.substring(secArgInt));
      default:
        throw new IllegalArgumentException("Invalid function name");
    }
  }

  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }

}