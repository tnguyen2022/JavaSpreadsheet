package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;


import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a Visual representational view of the worksheet.
 */
public interface SpreadsheetView {

  /**
   * Renders the view for the Spreadsheet.
   *
   * @throws IOException throws an exception of the view is invalid.
   */
  void render() throws IOException;

  void setJTextField(String s);

  String getRawCellContent(int row, int col);

  String getInputText();

  Coord getSelectedCellCoord();

  void setValueAt(int row, int col, String value);

  void addMouseListener(MouseListener ml);

  void addActionListener(ActionListener al);

  void addKeyListener(KeyListener kl);

  void setJLabel(int rowIndex, int columnIndex);
}
