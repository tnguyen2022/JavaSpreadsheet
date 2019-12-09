package edu.cs3500.spreadsheets.view;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;

//class found online that allows user to resize a specific row of a table - for kev
public class TableRowResizer extends MouseInputAdapter
{
  public static Cursor resizeCursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);

  private int mouseYOffset, resizingRow;
  private Cursor otherCursor = resizeCursor;
  private JTable rowHeaderTable;
  private JTable table;
  private GeneralWorksheet model;

  public TableRowResizer(JTable rowHeaderTable, JTable table, GeneralWorksheet model){
    this.rowHeaderTable = rowHeaderTable;
    this.table = table;
    this.model = model;
    rowHeaderTable.addMouseListener(this);
    rowHeaderTable.addMouseMotionListener(this);
  }

  private int getResizingRow(Point p){
    return getResizingRow(p, rowHeaderTable.rowAtPoint(p));
  }

  private int getResizingRow(Point p, int row){
    if(row == -1){
      return -1;
    }
    int col = rowHeaderTable.columnAtPoint(p);
    if(col==-1)
      return -1;
    Rectangle r = rowHeaderTable.getCellRect(row, col, true);
    r.grow(0, -3);
    if(r.contains(p))
      return -1;

    int midPoint = r.y + r.height / 2;
    int rowIndex = (p.y < midPoint) ? row - 1 : row;

    return rowIndex;
  }

  @Override
  public void mousePressed(MouseEvent e){
    Point p = e.getPoint();

    resizingRow = getResizingRow(p);
    mouseYOffset = p.y - rowHeaderTable.getRowHeight(resizingRow);
  }

  private void swapCursor(){
    Cursor tmp = rowHeaderTable.getCursor();
    rowHeaderTable.setCursor(otherCursor);
    otherCursor = tmp;
  }

  @Override
  public void mouseMoved(MouseEvent e){
    if((getResizingRow(e.getPoint())>=0)
            != (rowHeaderTable.getCursor() == resizeCursor)){
      swapCursor();
    }
  }

  @Override
  public void mouseDragged(MouseEvent e){
    int mouseY = e.getY();

    if(resizingRow >= 0){
      int newHeight = mouseY - mouseYOffset;
      if(newHeight > 1) {
        rowHeaderTable.setRowHeight(resizingRow, newHeight);
        table.setRowHeight(resizingRow, newHeight);
        model.addOrSetRowHeight(resizingRow+1, newHeight);
      }
    }
  }
}