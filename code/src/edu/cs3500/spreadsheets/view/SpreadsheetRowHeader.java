package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


/**
 * Creates the rowHeaders of the Spreadsheet.
 */
public class SpreadsheetRowHeader extends JTable {
  private DefaultTableModel tableModel;
  //private GeneralWorksheet model;

  /**
   * Creates rowHeaders table.
   *
   * @param data        represents rowHeaders data of the spreadsheet
   * @param columnNames represents column header of the rowHeaders
   */
  public SpreadsheetRowHeader(String[][] data, String[] columnNames) {
    super(data, columnNames);
    //this.model = model;

    JTableHeader header = this.getTableHeader();
    header.setBackground(Color.gray);

    /**
     * Creates a default table model where the first column is immutable.
     */
    tableModel = new DefaultTableModel(data, columnNames) {
      @Override
      public boolean isCellEditable(int row, int column) {
        //Cannot mutate the first column
        return column != 0;
      }
    };
    this.setModel(tableModel);

    styleFirstColumnToRowValues();

    //Table column widths cannot be resized
    this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  }

  @Override
  public void setValueAt(Object aValue, int row, int column) {
    //to do for controller
  }

  public void addRow(String row){
    tableModel.insertRow(this.getRowCount(), new Object[] { row });
    this.setModel(tableModel);
    styleFirstColumnToRowValues();
  }

  /**
   * Creates the first column in the spreadsheet to be a non-editable column with row values.
   */
  private void styleFirstColumnToRowValues() {
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
  }

}

