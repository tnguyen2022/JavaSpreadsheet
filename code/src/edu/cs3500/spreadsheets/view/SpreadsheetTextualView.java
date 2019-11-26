package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.event.ChangeEvent;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;

/**
 * Textual view of a Spreadsheet used to save a model into a file.
 */
public class SpreadsheetTextualView implements SpreadsheetView {
  /**
   * Creates an example of a worksheet model.
   */
  private final GeneralWorksheet model;

  /**
   * Creates an appendable that can append to the current textual input.
   */
  private Appendable in;

  /**
   * Constructs a SpreadsheetTextualView with an appendable.
   *
   * @param model represents a worksheet model of the view.
   * @param in    represents an appendable to the textual view.
   */
  public SpreadsheetTextualView(GeneralWorksheet model, Appendable in) {
    this.in = in;
    this.model = model;
  }

  /**
   * Saves a textual representation of the model.
   */
  @Override
  public void render() throws IllegalArgumentException {
    int viewHeight = model.getMaxHeight();
    int viewWidth = model.getMaxWidth();

    try {
      for (int i = 1; i <= viewHeight; i++) {
        for (int j = 1; j <= viewWidth; j++) {
          if (!model.getCell(j, i).content.equals(new Blank())) {
            this.in.append(Coord.colIndexToName(j)).append(String.valueOf(i)).append(" = ")
                    .append(model.getCell(j, i).content.toString()).append("\n");
          }
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to save spreadsheet model");
    }
  }

  @Override
  public void setJTextField(String s) {

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

  }

  @Override
  public void addMouseListener(MouseListener ml) {

  }

  @Override
  public void addActionListener(ActionListener al) {

  }

  @Override
  public void addKeyListener(KeyListener ak) {

  }

  @Override
  public void setJLabel(int rowIndex, int columnIndex) {

  }
}
