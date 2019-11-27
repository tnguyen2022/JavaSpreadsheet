package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;


/**
 * Creates a JTable with the worksheet data to represent graphical spreadsheet table.
 */
public class SpreadsheetTable extends JTable {
  private DefaultTableModel tableModel;
  private GeneralWorksheet model;

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

    //addKeyFeatures();

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

    //this.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
  }

  /**
   * Adds a column to the spreadsheet model.
   *
   * @param column the desired column.
   */
  public void addColumn(String column) {
    tableModel.addColumn(column);
    this.setModel(tableModel);

  }

  /**
   * Adds a row to the spreadsheet model.
   */
  public void addRow() {
    tableModel.insertRow(this.getRowCount(), new String[]{});
    this.setModel(tableModel);

  }

  //
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



