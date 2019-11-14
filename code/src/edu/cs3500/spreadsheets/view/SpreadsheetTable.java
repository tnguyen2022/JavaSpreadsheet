package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;

public class SpreadsheetTable extends JTable {
  private GeneralWorksheet model;

  public SpreadsheetTable(String[][] data, String[] columnNames, GeneralWorksheet model) {
    super(data, columnNames);
    this.model = model;

    //instance table model
    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
      @Override
      public boolean isCellEditable(int row, int column) {
        //Cannot mutate the first column
        return column != 0;
      }
    };
    this.setModel(tableModel);

    this.getColumn("").setCellRenderer(
            new DefaultTableCellRenderer() {
              public Component getTableCellRendererComponent(JTable table, Object value,
                                                             boolean isSelected,
                                                             boolean hasFocus,
                                                             int row,
                                                             int column) {
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
  public void setValueAt(Object aValue, int row, int column){
  }

}

