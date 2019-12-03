package edu.cs3500.spreadsheets.provider.value;

import edu.cs3500.spreadsheets.model.value.Value;

public class ValueAdapter implements IValue {

  private Value v;

  public ValueAdapter(Value v){
    this.v = v;
  }

  @Override
  public String toString(){
    return v.toString();
  }

  @Override
  public <R> R accept(ValueVisitor<R> visitor) {
    System.out.println("boi");
    //return v.accept(new ValueVisitorAdapter<>(visitor));
    return null;
  }
}
