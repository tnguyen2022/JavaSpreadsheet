package edu.cs3500.spreadsheets.sexp.sexpvisitfunc;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.function.LeftFunction;
import edu.cs3500.spreadsheets.model.function.LessThanFunction;
import edu.cs3500.spreadsheets.model.function.ProductFunction;
import edu.cs3500.spreadsheets.model.value.StringValue;
import edu.cs3500.spreadsheets.model.function.SumFunction;
import edu.cs3500.spreadsheets.sexp.Sexp;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * Visitor function object that creates the Formula for a Cell from a Sexp.
 */
public class CreateCellFormula implements SexpVisitor<Formula> {
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
    String funcName;

    try {
      funcName = l.get(0).accept(new FunctionName());
    } catch (IllegalArgumentException | NullPointerException e) {
      throw new IllegalArgumentException("Not a valid Function Name");
    }

    ArrayList<Formula> args = new ArrayList<>();
    if (l.size() > 1) {
      for (int i = 1; i < l.size(); i++) {
        args.add(l.get(i).accept(new CreateCellFormula()));
      }
    }

    switch (funcName) {
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
