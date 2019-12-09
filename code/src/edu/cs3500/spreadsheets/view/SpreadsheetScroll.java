package edu.cs3500.spreadsheets.view;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * Represents a scroll bar for the spreadsheet view through the use of JSwing.
 */
public class SpreadsheetScroll extends JScrollPane {

  /**
   * Creates the SpreadSheet scroll with infinite scrolling horizontally and vertically.
   *
   * @param model      the chosen representation of a spreadsheet model.
   * @param rowHeaders the chosen representation of a row header for the model.
   */
  public SpreadsheetScroll(SpreadsheetTable model, SpreadsheetRowHeader rowHeaders) {
    super(model);

    //set rowHeaders to always be in the ViewPort
    JViewport viewport = new JViewport();
    viewport.setView(rowHeaders);
    Dimension originalFixedSize = rowHeaders.getPreferredSize();
    viewport.setPreferredSize(originalFixedSize);
    this.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowHeaders.getTableHeader());
    this.setRowHeaderView(viewport);

    //infinite scrolling vertically
    this.getVerticalScrollBar().addAdjustmentListener(e -> {
      int maxValue = this.getVerticalScrollBar().getMaximum()
          - this.getVerticalScrollBar().getVisibleAmount();
      int currentValue = this.getVerticalScrollBar().getValue();
      float fraction = (float) currentValue / (float) maxValue;

      //checks if the scroll is near the bottom of the spreadsheet
      if (fraction > .99) {
        String newRowNumber = Integer.toString((rowHeaders.getRowCount()) + 1);
        rowHeaders.addRow(newRowNumber);
        model.addRow();
        Dimension newFixedSize = rowHeaders.getPreferredSize();
        viewport.setPreferredSize(newFixedSize);
      }
    });

    //infinite scrolling horizontally
    this.getHorizontalScrollBar().addAdjustmentListener(e -> {
      int maxValue = this.getHorizontalScrollBar().getMaximum()
          - this.getHorizontalScrollBar().getVisibleAmount();
      int currentValue = this.getHorizontalScrollBar().getValue();
      float fraction = (float) currentValue / (float) maxValue;

      //checks if the scroll is near the right-most edge of the spreadsheet
      if (fraction > .99) {
        String newColumnName = Coord.colIndexToName(model.getColumnCount());
        model.addColumn(newColumnName);
      }
    });
  }
}
