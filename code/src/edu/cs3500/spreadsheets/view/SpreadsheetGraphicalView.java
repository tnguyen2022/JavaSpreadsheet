package edu.cs3500.spreadsheets.view;

import javax.swing.JScrollPane;
import javax.swing.JFrame;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;

/**
 * Graphical view of a Spreadsheet.
 */
public class SpreadsheetGraphicalView extends JFrame implements SpreadsheetView{
  private int maxTableHeight;
  private int maxTableWidth;

  private SpreadsheetTable initTable;


  /**
   * Creates a graphical view based on given model.
   * @param model the type of worksheet
   */
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

  /**
   * Gets the name of the columns of a spreadsheet
   * @param columns the columns of a worksheet
   * @return the String of column names
   */
  private String[] getColumnNames (String[] columns){
    columns[0] = "";
    for (int i = 1; i < columns.length; i++){
      columns[i] = Coord.colIndexToName(i);
    }

    return columns;
  }

  /**
   * Gets the data of a spreadsheet.
   * @param table the given table/spreadsheet.
   * @param model the model of the worksheet
   * @return
   */
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

  /**
   * Renders the graphical view.
   */
  public void render (){
    // Frame Visible = true
    this.setVisible(true);
  }
}
