package edu.cs3500.spreadsheets.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.cs3500.spreadsheets.view.SpreadsheetView;

public class MouseListener extends MouseAdapter implements java.awt.event.MouseListener{
  private SpreadsheetView view;
  MouseListener(SpreadsheetView view){
    this.view = view;
  }
  @Override
  public void mouseReleased(final MouseEvent e) {
      final int row =  view.getSelectedCellCoord().row;
      final int col =  view.getSelectedCellCoord().col;
      view.setJLabel(row, col);
      if (view.getRawCellContent(row, col).equals("")){
        view.setJTextField("");
        //view.setValueAt(row-1, col-1, view.getInputText());
      }
      else{
        view.setJTextField("= " + view.getRawCellContent(row, col));
        //view.setValueAt(row-1, col-1, view.getInputText());
      }
  }
}
