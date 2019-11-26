package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;


/**
 * Represents a Graphical visual GUI of a Spreadsheet using JTables that's non-editable.
 */
public class SpreadsheetGraphicalView extends JFrame implements SpreadsheetView {

  /**
   * Creates a graphical view based on given model.
   *
   * @param model represents the given model of worksheet to be visualized
   */
  public SpreadsheetGraphicalView(GeneralWorksheet model) {
    super();

    int maxTableHeight = Math.max(100, model.getMaxHeight());
    int maxTableWidth = Math.max(52, model.getMaxWidth());

    // Frame Title
    this.setTitle("SpreadSheet Graphical View");

    //instance of out rowHeaders table
    SpreadsheetRowHeader rowHeaders =
            new SpreadsheetRowHeader(getRowHeaders(maxTableHeight),
                    new String[]{""});

    // instance of our customizable table
    SpreadsheetTable initTable =
            new SpreadsheetTable(getData(new String[maxTableHeight][maxTableWidth], model),
                    getColumnNames(new String[maxTableWidth]), model);
            //{
//      //makes this view not editable
//      @Override
//      public boolean isCellEditable(int row, int col){
//        return false;
//      }};


    //combines the two tables next to each other
    ListSelectionModel rowHeaderModel = rowHeaders.getSelectionModel();
    initTable.setSelectionModel(rowHeaderModel);

    // adding it to JScrollPane
    SpreadsheetScroll sp = new SpreadsheetScroll(initTable , rowHeaders);

    //adds JScrollPane Object into the ViewPort
    this.add(sp);
    // Frame Size
    this.setSize(1250, 750);

    //Application automatically closes if user exits out
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  }

  /**
   * Creates and gets the name of the columns of a spreadsheet from the given model.
   *
   * @param columns the columns of a worksheet
   * @return the String of column names
   */
  private String[] getColumnNames(String[] columns) {
    for (int i = 0; i < columns.length; i++) {
      columns[i] = Coord.colIndexToName(i+1);
    }

    return columns;
  }

  /**
   * Creates and gets the data of a spreadsheet from the given the model.
   *
   * @param table the given table/spreadsheet.
   * @param model the model of the worksheet
   * @return the data of the spreadsheet
   */
  private String[][] getData(String[][] table, GeneralWorksheet model) {
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[0].length; j++) {
          if (model.getCell(j+1, i + 1).content.equals(new Blank())) {
            table[i][j] = new Blank().toString();
          } else {
            try {
              table[i][j] = model.evaluateCell(model.getCell(j+1, i + 1)).toString();
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
              table[i][j] = "#VALUE";
            }
          }
      }
    }

    return table;
  }

  /**
   * Creates and gets the rowHeader column of a spreadsheet.
   *
   * @param maxHeight represents the max height of the Spreadsheet.
   * @return the rowHeaders of the spreadsheet
   */
  private String[][] getRowHeaders(int maxHeight) {
    String [][] table = new String [maxHeight][1];
    for (int i = 0; i < maxHeight; i++) {
          int colNumber = i + 1;
          table[i][0] = Integer.toString(colNumber);
    }

    return table;
  }

  /**
   * Renders the graphical view to be visible.
   */
  public void render() {
    // Frame Visible = true
    this.setVisible(true);
  }

  @Override
  public void setJTextField(String s) {
  // Supposed to be empty.
  }

  @Override
  public String getRawCellContent(int row, int col) {
    return null;
  }

  @Override
  public String getInputText() {
    return null;
  }

  @Override
  public Coord getSelectedCellCoord() {
    return null;
  }

  @Override
  public void setValueAt(int row, int col, String value) {
  // supposed to be empty.
  }

  @Override
  public void addActionListener(ActionListener al) {
  // supposed to be empty.
  }

  @Override
  public void setJLabel(int rowIndex, int columnIndex) {
  // supposed to be empty.
  }

}
