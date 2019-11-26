package edu.cs3500.spreadsheets.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

public class KeyboardListener extends KeyAdapter implements KeyListener {
  private SpreadsheetView view;

  KeyboardListener(SpreadsheetView view){
    this.view = view;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int row = view.getSelectedCellCoord().row;
    int col = view.getSelectedCellCoord().col;

    switch(e.getKeyCode()){
      case KeyEvent.VK_DELETE:
        view.setValueAt(row-1, col-1, null);
        view.setJTextField("");
        break;
      case KeyEvent.VK_UP:
        row = Math.max(1, row-1);
        view.setJLabel(row, col);
        if (view.getRawCellContent(row, col).equals("")){
          view.setJTextField("");
        }
        else {
          view.setJTextField("= " + view.getRawCellContent(row, col));
        }
        break;
      case KeyEvent.VK_DOWN:
        System.out.println(row+1 + ", " + col);
        view.setJLabel(row+1, col);
        if (view.getRawCellContent(row+1, col).equals("")){
          view.setJTextField("");
        }
        else {
          view.setJTextField("= " + view.getRawCellContent(row+1, col));
        }
        break;
      case KeyEvent.VK_LEFT:
        col = Math.max(1, col-1);
        view.setJLabel(row, col);
        if (view.getRawCellContent(row, col).equals("")){
          view.setJTextField("");
        }
        else {
          view.setJTextField("= " + view.getRawCellContent(row, col));
        }
        break;
      case KeyEvent.VK_RIGHT:
        view.setJLabel(row, col+1);
        if (view.getRawCellContent(row, col+1).equals("")){
          view.setJTextField("");
        }
        else {
          view.setJTextField("= " + view.getRawCellContent(row, col+1));
        }
        break;
    }
  }
}
