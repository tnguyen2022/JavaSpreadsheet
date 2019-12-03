package edu.cs3500.spreadsheets.provider.controller;


import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.provider.model.IWorksheet;
import edu.cs3500.spreadsheets.provider.view.IView;

public class FeaturesProviderController implements Features {
  IWorksheet model;
  IView view;

  public FeaturesProviderController(IWorksheet model, IView view){
    this.model = model;
    this.view = view;
  }

  @Override
  public String selectForm(Coord c) {
    return model.getCellAt(c.col, c.row).toString();
  }

  @Override
  public void updateCell(Coord c, String input) {
    model.updateCell(c.col, c.row, input);
    view.render();
  }

  @Override
  public void setView(IView view) {
    this.view = view;
    view.addFeatures(this);
  }
}
