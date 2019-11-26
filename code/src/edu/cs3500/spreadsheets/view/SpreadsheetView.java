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

  /**
   * Sets the JSwing text field to a desired string.
   * @param s the text field.
   */
  void setJTextField(String s);

  /**
   * Get the cell content from a given row and column.
   * @param row the row in the spreadsheet.
   * @param col the column in the spreadsheet.
   * @return the string representation of the content in the selected cell.
   */
  String getRawCellContent(int row, int col);

  /**
   * gets the input text from the spreadsheet.
   * @return the input in a string representation.
   */
  String getInputText();

  /**
   * Gets the coordinates of a given cell.
   * @return the coordinates of the cell as a Coord.
   */
  Coord getSelectedCellCoord();

  /**
   * Sets the value of a cell in the view to a desired value.
   * @param row the row in the spreadsheet.
   * @param col the column in the spreadsheet.
   * @param value the string representation of the value.
   */
  void setValueAt(int row, int col, String value);

  /**
   * Creates a given MouseListener in the Spreadsheet view.
   * @param ml the mouse action from the user.
   */
  void addMouseListener(MouseListener ml);

  /**
   * Creates the desired amount of ActionListeners in the Spreadsheet view.
   * @param al the action from the user.
   */
  void addActionListener(ActionListener al);

  /**
   * Creates the desired amount of KeyListeners in the Spreadsheet view.
   * @param kl the key stroke from the user.
   */
  void addKeyListener(KeyListener kl);

  /**
   * Creates the Jlabel for Spreadsheet view.
   * @param rowIndex the spreadsheet row.
   * @param columnIndex the spreadsheet column.
   */
  void setJLabel(int rowIndex, int columnIndex);
}
