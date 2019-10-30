package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.StringValue;

public class CreateCell implements SexpVisitor<Cell> {
//ask vishal if i should create 6 diff constructors for cell to make constructors package protected
  @Override
  public Cell visitBoolean(boolean b) {
    return new Cell(new BooleanValue(b));
  }

  @Override
  public Cell visitNumber(double d) {
    return new Cell(new DoubleValue(d));
  }

  @Override
  public Cell visitSList(List<Sexp> l) {

    ArrayList<Formula> args;
    try{
      for (int i = 1; i < l.size(); i++) {
        l.get(i).accept(this);
      }
    }
    catch (NullPointerException e){
        throw new IllegalArgumentException("Function needs at least 1 argument");
      }
    return new Cell(new
    ());
  }

  @Override
  public Cell visitSymbol(String s) {
    switch (s){
      case "SUM":
      case "PRODUCT":
      case "<":
      case "LEFT":
      default:
       return new Cell(new Reference(s));
    }
    return null;
  }

  @Override
  public Cell visitString(String s) {
    return new Cell(new StringValue(s));
  }
}
