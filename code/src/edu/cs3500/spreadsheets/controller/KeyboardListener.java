package edu.cs3500.spreadsheets.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

/**
 * Waits for key press to occur and performs desired action in the view.
 * Handles the Up, down, left, right, enter, and delete key presses.
 */
public class KeyboardListener extends KeyAdapter implements KeyListener {
  private SpreadsheetView view;
  private GeneralWorksheet model;

  /**
   * Changes the view of the spreadsheet based on certain key inputs.
   * @param view a instance of a view of the spreadsheet
   * @param model the model the spreadsheet is implemented on
   */
  public KeyboardListener(SpreadsheetView view, GeneralWorksheet model) {

    this.view = view;
    this.model = model;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int row = view.getSelectedCellCoord().row;
    int col = view.getSelectedCellCoord().col;

    switch (e.getKeyCode()) {
      case KeyEvent.VK_DELETE:
        view.setValueAt(row - 1, col - 1, null);
        view.setJTextField("");
        for (int i = 0; i < model.getMaxHeight(); i++) {
          for (int j = 0; j < model.getMaxWidth(); j++) {
            if (!model.getCell(j + 1, i + 1).content.toString().equals("")) {
              view.setValueAt(i, j, "= " + view.getRawCellContent(i + 1, j + 1));
            }
          }
        }
        break;
      case KeyEvent.VK_UP:
        row = Math.max(1, row - 1);
        view.setJLabel(row, col);
        if (view.getRawCellContent(row, col).equals("")) {
          view.setJTextField("");
        } else {
          view.setJTextField("= " + view.getRawCellContent(row, col));
        }
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_ENTER:
        System.out.println(row + 1 + ", " + col);
        view.setJLabel(row + 1, col);
        if (view.getRawCellContent(row + 1, col).equals("")) {
          view.setJTextField("");
        } else {
          view.setJTextField("= " + view.getRawCellContent(row + 1, col));
        }
        break;
      case KeyEvent.VK_LEFT:
        col = Math.max(1, col - 1);
        view.setJLabel(row, col);
        if (view.getRawCellContent(row, col).equals("")) {
          view.setJTextField("");
        } else {
          view.setJTextField("= " + view.getRawCellContent(row, col));
        }
        break;
      case KeyEvent.VK_RIGHT:
        view.setJLabel(row, col + 1);
        if (view.getRawCellContent(row, col + 1).equals("")) {
          view.setJTextField("");
        } else {
          view.setJTextField("= " + view.getRawCellContent(row, col + 1));
        }
        break;
      default:
        //void method so if not the set given key is pressed, nothing happens
        break;
    }
  }
}
