package edu.cs3500.spreadsheets.view;

import java.awt.Component;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 * Creates a graphical spreadsheet table.
 */
public class SpreadsheetTable extends JTable {
  //private GeneralWorksheet model;

  /**
   * Creates Spreadsheet table.
   *
   * @param data        represents the 2d array of cell data in the Spreadsheet.
   * @param columnNames represents the Column names of the spreadsheet.
   */
  public SpreadsheetTable(String[][] data, String[] columnNames) {
    super(data, columnNames);
    //this.model = model;

    /**
     * Creates a default table model where teh first column is immutable.
     */
    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
      @Override
      public boolean isCellEditable(int row, int column) {
        //Cannot mutate the first column
        return column != 0;
      }
    };
    this.setModel(tableModel);

    this.getColumn("").setCellRenderer(new DefaultTableCellRenderer() {
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                     boolean hasFocus, int row, int column) {
        setText(value.toString());
        //make first column different color
        setBackground(Color.lightGray);
        //make first column text centered
        setHorizontalAlignment(JLabel.CENTER);
        return this;
      }
    });

    //Table column widths cannot be resized
    this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  }

  @Override
  public void setValueAt(Object aValue, int row, int column) {
    //to do for controller
  }

}

