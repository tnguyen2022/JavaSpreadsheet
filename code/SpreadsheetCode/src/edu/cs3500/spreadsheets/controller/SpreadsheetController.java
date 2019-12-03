package edu.cs3500.spreadsheets.controller;

import java.io.IOException;

/**
 * Controller for Spreadsheet. Manipulates the model and view based on user actions on the
 * spreadsheet view.
 */
public interface SpreadsheetController {

  /**
   * Renders the view and instantiates all of the controller's lisenters, while adding components to
   * their respective listeners.
   */
  void runController() throws IOException;
}
