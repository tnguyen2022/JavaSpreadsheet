package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class Function implements Formula {
  ArrayList<Formula> args;
  StringValue func;

  public Function (String func, ArrayList<Formula> args){
    this.func = new StringValue(func);
    this.args = args;
  }

  @Override
  public Formula Sum() {
    return new Function("SUM", args);
  }

  @Override
  public Formula Product() {
    return new Function("PRODUCT", args);
  }

  @Override
  public Formula LessThan() {
    return new Function("<", args);
  }

  @Override
  public Formula Left() {
    return new Function("left", args);
  }

  @Override
  public Value evaluate() {
    return null;
  }
}