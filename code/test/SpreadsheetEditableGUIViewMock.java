import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.JFrame;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.view.SpreadsheetRowHeader;
import edu.cs3500.spreadsheets.view.SpreadsheetScroll;
import edu.cs3500.spreadsheets.view.SpreadsheetTable;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

/**
 * Represents a Mock class of the SpreadsheetEditableGUIView for testing, returns logs when methods
 * are called.
 */
public class SpreadsheetEditableGUIViewMock extends JFrame implements
        SpreadsheetView {

  /**
   * Appendable that has outputs of all the inputs that comes into model.
   */
  private final StringBuilder log;

  private GeneralWorksheet model;
  private JButton confirmButton;
  private JButton cancelButton;
  private JButton saveButton;
  public SpreadsheetTable initTable;
  private JTextField input;
  private JLabel cellSelection;
  private JButton loadButton;

  /**
   * Constructs a BasicPyramidSolitaireMock.
   *
   * @param log represents a log of all the inputs that comes into model
   */
  public SpreadsheetEditableGUIViewMock(StringBuilder log, GeneralWorksheet model) {
    super();
    this.log = Objects.requireNonNull(log);


    this.model = model;

    int maxTableHeight = Math.max(100, model.getMaxHeight());
    int maxTableWidth = Math.max(52, model.getMaxWidth());

    // Frame Title
    this.setTitle("SpreadSheet Graphical View");

    //instance of out rowHeaders table
    SpreadsheetRowHeader rowHeaders =
            new SpreadsheetRowHeader(getRowHeaders(maxTableHeight),
                    new String[]{""}, model);

    // instance of our customizable table
    this.initTable = new SpreadsheetTable(getData(new String[maxTableHeight][maxTableWidth], model),
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
    //input = new JTextField(80);
    input = new JTextField(60);
    if (!initTable.getValueAt(0, 0).toString().equals("")) {
      input.setText("= " + initTable.getValueAt(0, 0).toString());
    }
    buttonPanel.add(input);

    //save button
    saveButton = new JButton("Save File");
    buttonPanel.add(saveButton);
    saveButton.setActionCommand("Save Button");

    //Load button
    loadButton = new JButton("Load File");
    buttonPanel.add(loadButton);
    loadButton.setActionCommand("Load Button");

    //Application automatically closes if user exits out
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

  }

  @Override
  public String[] getColumnNames(String[] columns) {
    for (int i = 0; i < columns.length; i++) {
      columns[i] = Coord.colIndexToName(i + 1);
    }

    return columns;
  }

  @Override
  public String[][] getData(String[][] table, GeneralWorksheet model) {
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[0].length; j++) {
        if (model.getCell(j + 1, i + 1).content.equals(new Blank())) {
          table[i][j] = new Blank().toString();
        } else {
          try {
            table[i][j] = model.evaluateCell(model.getCell(j + 1, i + 1)).toString();
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
  @Override
  public String[][] getRowHeaders(int maxHeight) {
    String[][] table = new String[maxHeight][1];
    for (int i = 0; i < maxHeight; i++) {
      int colNumber = i + 1;
      table[i][0] = Integer.toString(colNumber);
    }

    return table;
  }

  @Override
  public void render() {
    log.append("Called render method\n");
  }

  @Override
  public void setJTextField(String s) {
    log.append("Called setJTextField method\n");
  }

  @Override
  public String getRawCellContent(int row, int col) {
    log.append("Called getRawCellContent method\n");
    return "null";
  }

  @Override
  public String getInputText() {
    log.append("Called setJTextField method\n");
    return "null";
  }

  @Override
  public Coord getSelectedCellCoord() {
    log.append("Called getSelectedCoord method\n");
    return new Coord(1, 1);
  }

  @Override
  public void setValueAt(int row, int col, String value) {
    log.append("Called setValueAt method\n");
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    log.append("Called addMouseListener method\n");
  }

  @Override
  public void addActionListener(ActionListener al) {
    log.append("Called addActionListener method\n");
  }

  @Override
  public void addKeyListener(KeyListener kl) {
    log.append("Called addKeyListener method\n");
  }

  @Override
  public void setJLabel(int rowIndex, int columnIndex) {
    log.append("Called setJLabel method\n");
  }
}
