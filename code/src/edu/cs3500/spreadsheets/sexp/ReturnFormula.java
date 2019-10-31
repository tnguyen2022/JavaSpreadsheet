package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.LeftFunction;
import edu.cs3500.spreadsheets.model.LessThanFunction;
import edu.cs3500.spreadsheets.model.ProductFunction;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.SumFunction;

public class ReturnFormula implements SexpVisitor<Formula> {
  @Override
  public Formula visitBoolean(boolean b) {
    return new BooleanValue(b);
  }

  @Override
  public Formula visitNumber(double d) {
    return new DoubleValue(d);
  }

  @Override
  public Formula visitSList(List<Sexp> l) {
    String funcName = "";

    try{
      funcName = l.get(0).accept(new FunctionName());
    }
    catch (IllegalArgumentException | NullPointerException e){
      throw new IllegalArgumentException("Not a valid Function Name");
    }

    ArrayList<Formula> args = new ArrayList<>();
    try{
      for (int i = 1; i < l.size(); i++) {
        args.add(l.get(i).accept(new ReturnFormula()));
      }
    }
    catch (NullPointerException e){
      throw new IllegalArgumentException("Function needs at least 1 argument");
    }

    switch (funcName){
      case "SUM":
        return new SumFunction(args);
      case "PRODUCT":
        return new ProductFunction(args);
      case "<":
        return new LessThanFunction(args);
      case "LEFT":
        return new LeftFunction(args);
      default:
        throw new IllegalArgumentException("Function needs to be a valid name");
    }
  }

  @Override
  public Formula visitSymbol(String s) {
    return new Reference(s);
  }

  @Override
  public Formula visitString(String s) {
    return new StringValue(s);
  }
}
