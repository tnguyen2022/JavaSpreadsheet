package edu.cs3500.spreadsheets.view;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;

public class SpreadsheetGraphicalView extends JFrame implements SpreadsheetView{
  private int maxTableHeight;
  private int maxTableWidth;

  private SpreadsheetTable initTable;

  // Constructor
  public SpreadsheetGraphicalView(GeneralWorksheet model) {
    super();

    maxTableHeight = Math.max(100, model.getMaxHeight());
    maxTableWidth = Math.max(52, model.getMaxWidth());

    // Frame Title
    this.setTitle("SpreadSheet Graphical View");

    // instance of our customizable table
    initTable = new SpreadsheetTable(getData(new String[maxTableHeight][maxTableWidth+1], model),
            getColumnNames(new String [maxTableWidth + 1]), model);

    // adding it to JScrollPane
    JScrollPane sp = new JScrollPane(initTable);
    this.add(sp);
    // Frame Size
    this.setSize(1250, 750);

    //Application automatically closes if user exits out
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  }

  private String[] getColumnNames (String[] columns){
    columns[0] = "";
    for (int i = 1; i < columns.length; i++){
      columns[i] = Coord.colIndexToName(i);
    }

    return columns;
  }

  private String[][] getData (String[][] table, GeneralWorksheet model){
    for (int i = 0; i < table.length; i++){
      for (int j = 0; j < table[0].length; j++){
        if (j == 0){
          int colNumber = i+1;
          table[i][j] = Integer.toString(colNumber);
        }
        else {
          if (model.getCell(j, i+1).content.equals(new Blank())){
            table[i][j] = new Blank().toString();
          }
          else {
            try {
              table[i][j] = model.evaluateCell(model.getCell(j, i + 1)).toString();
            }
            catch (IllegalArgumentException | UnsupportedOperationException e){
              table[i][j] = "#VALUE";
            }
          }
        }
      }
    }

    return table;
  }

  public void render (){
    // Frame Visible = true
    this.setVisible(true);
  }
}
