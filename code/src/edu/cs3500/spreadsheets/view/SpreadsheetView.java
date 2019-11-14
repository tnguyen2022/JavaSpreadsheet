package edu.cs3500.spreadsheets.view;

import java.io.IOException;

/**
 * Represents a spreadsheet.
 */
public interface SpreadsheetView {

  /**
   * Renders the view for the Spreadsheet.
   * @throws IOException throws an exception of the view is invalid.
   */
  public void render() throws IOException;
}
