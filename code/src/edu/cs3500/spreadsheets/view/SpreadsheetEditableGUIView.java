package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;


/**
 * Represents a Graphical visual GUI of a Spreadsheet using JTables thats non-editable.
 */
public class SpreadsheetEditableGUIView extends JFrame implements SpreadsheetView {
  private GeneralWorksheet model;
  private JButton confirmButton;
  private JButton cancelButton;
  private JButton saveButton;
  private SpreadsheetTable initTable;
  private JTextField input;
  private JLabel cellSelection;
  private JButton loadButton;

  /**
   * Creates a graphical view based on given model.
   *
   * @param model represents the given model of worksheet to be visualized
   */
  public SpreadsheetEditableGUIView(GeneralWorksheet model) {
    super();

    this.model = model;

    int maxTableHeight = Math.max(100, model.getMaxHeight());
    int maxTableWidth = Math.max(52, model.getMaxWidth());

    // Frame Title
    this.setTitle("SpreadSheet Graphical View");

    //instance of out rowHeaders table
    SpreadsheetRowHeader rowHeaders =
            new SpreadsheetRowHeader(getRowHeaders(maxTableHeight),
                    new String[]{""});

    // instance of our customizable table
    initTable = new SpreadsheetTable(getData(new String[maxTableHeight][maxTableWidth], model),
                    getColumnNames(new String[maxTableWidth]), model);

    //combines the two tables next to each other
    ListSelectionModel rowHeaderModel = rowHeaders.getSelectionModel();
    initTable.setSelectionModel(rowHeaderModel);

    // adding it to JScrollPane
    SpreadsheetScroll sp = new SpreadsheetScroll(initTable, rowHeaders);

    //adds JScrollPane Object into the ViewPort
    this.add(sp);
    // Frame Size
    this.setSize(1250, 750);

    //button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.NORTH);

    //Confirm and Cancel buttons
    confirmButton = new JButton("Confirm");
    buttonPanel.add(confirmButton);
    confirmButton.setActionCommand("Confirm Button");
    cancelButton = new JButton("Cancel");
    buttonPanel.add(cancelButton);
    cancelButton.setActionCommand("Cancel Button");

    //Label denoting current cell selection
    cellSelection = new JLabel();
    cellSelection.setText("A1");
    buttonPanel.add(cellSelection);

    //input textfield
    input = new JTextField(85);
    input.setText("= " + initTable.getValueAt(0,0).toString());
    buttonPanel.add(input);

    //save button
    saveButton = new JButton("Save");
    buttonPanel.add(saveButton);
    saveButton.setActionCommand("Save Button");
//
    //Load button
    loadButton = new JButton("Load");
    buttonPanel.add(loadButton);
    loadButton.setActionCommand("Load Button");

    //Application automatically closes if user exits out
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);



    //System.out.println(initTable.getSelectionModel());
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
  public void addActionListener(ActionListener actionEvent) {
    cancelButton.addActionListener(actionEvent);
    confirmButton.addActionListener(actionEvent);
    saveButton.addActionListener(actionEvent);
    loadButton.addActionListener(actionEvent);
  }

  @Override
  public void addMouseListener(MouseListener actionMouse) {
    initTable.addMouseListener(actionMouse);
  }

  @Override
  public void addKeyListener(KeyListener actionKey){
    initTable.addKeyListener(actionKey);
  }

  public void setJTextField(String s){
      input.setText(s);
  }

  //to be added to interface
  public String getRawCellContent(int row, int col){
    return model.getCell(col, row).content.toString();
  }

  @Override
  public String getInputText() {
      return input.getText();
  }

  @Override
  public Coord getSelectedCellCoord() {
    final int row =  Math.max(0, initTable.getSelectedRow());
    final int col =  Math.max(0, initTable.getSelectedColumn());
    return new Coord(col+1, row+1);
  }

  @Override
  public void setValueAt(int row, int col, String value) {
    initTable.setValueAt(value, row, col);
  }

  @Override
  public void setJLabel(int rowIndex, int columnIndex){
    String s = Coord.colIndexToName(columnIndex) + rowIndex;
    cellSelection.setText(s);
  }

}
