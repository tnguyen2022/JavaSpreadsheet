package edu.cs3500.spreadsheets.view;

import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;

/**
 * Allows user to resize a specific row of a table.
 */
public class TableRowResizer extends MouseInputAdapter {
  public static Cursor RESIZECURSOR = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);

  private int mouseYOffset;
  private int resizingRow;
  private Cursor otherCursor = RESIZECURSOR;
  private JTable rowHeaderTable;
  private JTable table;
  private GeneralWorksheet model;

  /**
   * Clicking on a specific row in a spreadsheet, users are allowed to change the size of it.
   *
   * @param rowHeaderTable The name of the row that is being resized
   * @param table          The specific table in the spreadsheet
   * @param model          The model implementation of the spreadsheet
   */
  public TableRowResizer(JTable rowHeaderTable, JTable table, GeneralWorksheet model) {
    this.rowHeaderTable = rowHeaderTable;
    this.table = table;
    this.model = model;
    rowHeaderTable.addMouseListener(this);
    rowHeaderTable.addMouseMotionListener(this);
  }

  private int getResizingRow(Point p) {
    return getResizingRow(p, rowHeaderTable.rowAtPoint(p));
  }

  private int getResizingRow(Point p, int row) {
    if (row == -1) {
      return -1;
    }
    int col = rowHeaderTable.columnAtPoint(p);
    if (col == -1) {
      return -1;
    }
    Rectangle r = rowHeaderTable.getCellRect(row, col, true);
    r.grow(0, -3);
    if (r.contains(p)) {
      return -1;
    }

    int midPoint = r.y + r.height / 2;
    int rowIndex = (p.y < midPoint) ? row - 1 : row;

    return rowIndex;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    Point p = e.getPoint();

    resizingRow = getResizingRow(p);
    mouseYOffset = p.y - rowHeaderTable.getRowHeight(resizingRow);
  }

  private void swapCursor() {
    Cursor tmp = rowHeaderTable.getCursor();
    rowHeaderTable.setCursor(otherCursor);
    otherCursor = tmp;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if ((getResizingRow(e.getPoint()) >= 0)
            != (rowHeaderTable.getCursor() == RESIZECURSOR)) {
      swapCursor();
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    int mouseY = e.getY();

    if (resizingRow >= 0) {
      int newHeight = mouseY - mouseYOffset;
      if (newHeight > 1) {
        rowHeaderTable.setRowHeight(resizingRow, newHeight);
        table.setRowHeight(resizingRow, newHeight);
        model.addOrSetRowHeight(resizingRow + 1, newHeight);
      }
    }
  }
}