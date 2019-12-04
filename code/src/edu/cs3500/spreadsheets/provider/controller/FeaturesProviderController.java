package edu.cs3500.spreadsheets.provider.controller;


import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.IWorksheet;
import edu.cs3500.spreadsheets.provider.view.IView;
import edu.cs3500.spreadsheets.provider.model.Cell;

/**
 * Allows user to update model and view by allowing for cells to be selected,edited, and gain
 * access to a provided view.
 */
public class FeaturesProviderController implements Features {
  private IWorksheet<Cell> model;
  private IView view;

  public FeaturesProviderController(IWorksheet<Cell> model, IView view){
    this.model = model;
    this.view = view;
  }

  @Override
  public String selectForm(Coord c) {
    return "= " + model.getCellAt(c.col, c.row).getForm();
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
