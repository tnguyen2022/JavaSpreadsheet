package edu.cs3500.spreadsheets.controller;

import java.io.IOException;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

/**
 * Allows user to update model and view by allowing for cells to be edited and txt files to be
 * loaded and saved.
 */
public class Controller implements SpreadsheetController {
  private GeneralWorksheet model;
  private SpreadsheetView view;

  public Controller(GeneralWorksheet model, SpreadsheetView view){
    this.model = model;
    this.view = view;
  }

  public void go() throws IOException {
    this.view.render();
    ButtonListener bl = new ButtonListener(view, model, this);
    MouseListener ml = new MouseListener(view);
    KeyboardListener kl = new KeyboardListener(view);
    view.addMouseListener(ml);
    view.addActionListener(bl);
    view.addKeyListener(kl);
  }

}
