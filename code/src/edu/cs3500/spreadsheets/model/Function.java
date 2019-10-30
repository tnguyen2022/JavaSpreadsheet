package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Function implements Formula {
  ArrayList<Formula> args;
  String func; //make into enum

  public Function(String func, ArrayList<Formula> args) {
    this.func = func;
    this.args = args;
  }

  @Override
  public Value evaluate() {
    if (func.equals("SUM")) {
      if (args.size() == 0) {
        throw new IllegalArgumentException("Needs at least one argument.");
      }
      double totalSum = 0;
      for (Formula f : args) {
        totalSum += f.accept(new Sum());
      }
      return new DoubleValue(totalSum);
    } else if (func.equals("PRODUCT")) {
      if (args.size() == 0) {
        throw new IllegalArgumentException("Needs at least one argument.");
      }
      double totalProduct = 0;
      for (Formula f : args) {
        totalProduct += f.accept(new Product());
      }
      return new DoubleValue(totalProduct);
    } else if (func.equals("<")) {
      boolean lessThan = false;
      if (args.size() != 2) {
        throw new IllegalArgumentException("Needs exactly two arguments");
      }
        lessThan = this.args.get(0).accept(new LessThan()) < this.args.get(1).accept(new LessThan());

      return new BooleanValue(lessThan);
    } else if (func.equals("LEFT")) {
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
    }

    throw new IllegalArgumentException("Invalid function name");
  }

  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitFunction(this);
  }

}