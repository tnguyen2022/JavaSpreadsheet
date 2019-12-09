package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;


/**
 * Creates a JTable with the worksheet data to represent graphical spreadsheet table.
 */
public class SpreadsheetTable extends JTable {
  private DefaultTableModel tableModel;
  private GeneralWorksheet model;
  private boolean isColumnWidthChanged;

  /**
   * Creates Spreadsheet table.
   *
   * @param data        represents the 2d array of cell data in the Spreadsheet.
   * @param columnNames represents the Column names of the spreadsheet.
   */
  public SpreadsheetTable(String[][] data, String[] columnNames, GeneralWorksheet model) {
    super(data, columnNames);
    this.model = model;

    tableModel = new DefaultTableModel(data, columnNames);

    //Table column widths cannot be resized
    this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    //makes selection of cell noticeable
    this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected,
                                                     boolean hasFocus, int row, int column) {
        Component cell =
                super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);

        if (isSelected) {
          cell.setBackground(new Color(0xe9f5f9));
          if (getSelectedColumn() == column && getSelectedRow() == row) {
            cell.setBackground(new Color(0x8decec));
          }
        } else {
          cell.setBackground(new Color(0xFFFFFF));
        }

        return cell;
      }
    });

    //disable user column dragging
    this.getTableHeader().setReorderingAllowed(false);

    this.setRowAndColDimensions();

    getTableHeader().addMouseListener( new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e)
      {
        /* On mouse release, check if column width has changed */
        if(getColumnWidthChanged())
        {
          for (int i = 0; i < getColumnCount(); i++){
            if (!(columnModel.getColumn(i).getWidth() == 75)){
              model.addOrSetColWidth(i+1, columnModel.getColumn(i).getWidth());
            }
          }

          // Reset the flag on the table.
          setColumnWidthChanged(false);
        }
      }
    }
    );

    getColumnModel().addColumnModelListener(new TableColumnModelListener()
    {
      @Override
      public void columnMarginChanged(ChangeEvent e)
      {
        /* columnMarginChanged is called continuously as the column width is changed
           by dragging. Therefore, execute code below ONLY if we are not already
           aware of the column width having changed */
        if(!getColumnWidthChanged())
        {
            /* the condition  below will NOT be true if
               the column width is being changed by code. */
          if(getTableHeader().getResizingColumn() != null)
          {
            // User must have dragged column and changed width
            setColumnWidthChanged(true);
          }
        }
      }

      @Override
      public void columnMoved(TableColumnModelEvent e) { }

      @Override
      public void columnAdded(TableColumnModelEvent e) { }

      @Override
      public void columnRemoved(TableColumnModelEvent e) { }

      @Override
      public void columnSelectionChanged(ListSelectionEvent e) { }
    });

  }

  private boolean getColumnWidthChanged() {
    return isColumnWidthChanged;
  }

  private void setColumnWidthChanged(boolean widthChanged) {
    isColumnWidthChanged = widthChanged;
  }

  private void setRowAndColDimensions(){
    for (int i = 0; i < model.getMaxWidth(); i++){
      System.out.println(model.getColWidth(i+1));
      this.columnModel.getColumn(i).setPreferredWidth(model.getColWidth(i+1));
    }
    for (int i = 0; i < model.getMaxHeight(); i++){
      this.setRowHeight(i, model.getRowHeight(i+1));
    }
  }

  /**
   * Adds a column to the spreadsheet model.
   *
   * @param column the desired column.
   */
  public void addColumn(String column) {
    tableModel.addColumn(column);
    this.setModel(tableModel);
    this.setRowAndColDimensions();
  }

  /**
   * Adds a row to the spreadsheet model.
   */
  public void addRow() {
    tableModel.insertRow(this.getRowCount(), new String[]{});
    this.setModel(tableModel);
    this.setRowAndColDimensions();
  }

  @Override
  public Object getValueAt(int row, int col) {
    try {
      String result = model.getCell(col + 1, row + 1).content.toString();
      if (result.equals("")) {
        return "";
      }
      return model.evaluateCell(model.getCell(col + 1, row + 1));
    } catch (IllegalArgumentException | UnsupportedOperationException e) {
      return "#VALUE";
    }
  }

  @Override
  public void setValueAt(Object o, int row, int col) {
    if (o == null) {
      model.removeCell(col + 1, row + 1);
      getModel().setValueAt(null, convertRowIndexToModel(row),
              convertColumnIndexToModel(col));
    } else if (!o.equals("")) {
      try {
        model.modifyOrAdd(col + 1, row + 1, (String) o);

        String evaluatedCell =
                model.evaluateCell(model.getCell(col + 1, row + 1)).toString();

        getModel().setValueAt(evaluatedCell, convertRowIndexToModel(row),
                convertColumnIndexToModel(col));

      } catch (IllegalArgumentException | UnsupportedOperationException e) {
        getModel().setValueAt("#VALUE", convertRowIndexToModel(row),
                convertColumnIndexToModel(col));
      }
    }
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return false;
  }
}




