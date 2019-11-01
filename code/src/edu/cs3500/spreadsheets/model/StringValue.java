package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;

/**
 * Represents a string value in a worksheet.
 */
public class StringValue implements Value{
  String value;

  /**
   * The string value in a string value.
   * @param value the strinb
   */
  public StringValue(String value){
    this.value = value;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor){
    return visitor.visitStringValue(this);
  }

  @Override
  public Value canEvaluate(Coord c) {
    return evaluate();
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public boolean checkCycle(Coord c, ArrayList<Coord> acc) {
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
