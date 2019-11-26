package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.function.Function;


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
    this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
      @Override
      public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected,
                                                     boolean hasFocus, int row, int column) {
        Component cell =
                super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);

        if (isSelected){
          cell.setBackground(new Color (0xe9f5f9));
          if (getSelectedColumn() == column && getSelectedRow() == row){
            cell.setBackground(new Color(0x8decec));
          }
        }
        else{
          cell.setBackground(new Color(0xFFFFFF));
        }

        return cell;
      }
    });

    //this.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
  }

//  @Override
//  public void setDefaultEditor(Class<?> columnClass, TableCellEditor editor){
//    super.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()){
//      @Override
//      public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
//                                                   int row, int column) {
//        value = getRawCellContent(getSelectedColumn(), getSelectedRow());
//        if (!value.equals("")){
//          value = "= " + value;
//        }
//        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
//      }
//    });
//  }
//
//  private String getRawCellContent(int col, int row) {
//    col = Math.max(0, col);
//    row = Math.max(0, row);
//    return model.getCell(col+1, row+1).content.toString();
//  }

//  private void addKeyFeatures(){
//    InputMap inputMap = this.getInputMap(WHEN_FOCUSED);
//    ActionMap actionMap = this.getActionMap();
//
//    //configures delete key to deleting the cell
//    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
//    actionMap.put("delete", new AbstractAction() {
//      public void actionPerformed(ActionEvent evt) {
//        int row = getSelectedRow();
//        int col = getSelectedColumn();
//        if (row >= 0 && col >= 0) {
//          row = convertRowIndexToModel(row);
//          col = convertColumnIndexToModel(col);
//          getModel().setValueAt(null, row, col);
//        }
//      }
//    });
//  }

  /**
   * Adds a column to the spreadsheet model.
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
  public Object getValueAt(int row, int col){
    try {
      String result = model.getCell(col + 1, row + 1).content.toString();
      if (result.equals("")){
        return "";
      }
      return  model.evaluateCell(model.getCell(col + 1, row + 1));
    }
    catch (IllegalArgumentException | UnsupportedOperationException e) {
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
  public boolean isCellEditable(int row, int col){
    return false;
  }
}



