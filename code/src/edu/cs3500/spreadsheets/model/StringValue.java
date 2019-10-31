package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

public class StringValue implements Value{
  String value;
  public StringValue(String value){
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor){
    return visitor.visitStringValue(this);
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycles(Cell c, ArrayList<Cell> acc) {
    return false;
  }

  @Override
  public String toString(){
    String print = "";
    for (int i = 0; i < value.length(); i++){
      if (this.value.charAt(i) == '"'){
        print += "\\\"";
      }
      else if (this.value.charAt(i) == '\\'){
        print += "\\\\";
      }
      else{
        print += Character.toString(this.value.charAt(i));
      }
    }
    return "\"" + print + "\"";
  }
}
