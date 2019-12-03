package edu.cs3500.spreadsheets.provider.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;

/**
 * A panel that draws all the cells in a worksheet, the numbered row labels, and the lettered
 * column labels.
 */
public class DrawSpreadsheet extends JPanel {
  private IViewWorksheet worksheet;
  private Coord gc; // how far to draw the spreadsheet
  private Coord fc; // current highlighted cell
  static final int CELL_WIDTH = 40;
  static final int CELL_HEIGHT = 20;
  static final int DEFAULT_STROKE = 1;
  static final int BOLD_STROKE = 2;
  static final int PADDING = 2;
  //initial cell highlight
  static final Coord INITIAL_CELL = new Coord(1,1);

  /**
   * Constructs the interface of labels and cells for the GUI.
   * @param ivw the worksheet model to be rendered visually
   */
  public DrawSpreadsheet(IViewWorksheet ivw) {
    super();
    this.worksheet = ivw;
    this.gc = ivw.getLargestCoord();
    this.fc = INITIAL_CELL;
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    // drawing cells
    for (int c = 0; c <= gc.col; c++) {
      for (int r = 0; r <= gc.row; r++) {
        // first column (numbered labels)
        if (c == 0) {
          // draw gray label box
          g2d.setPaint(Color.gray);
          g2d.fill(new Rectangle2D.Double(0, r * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT));
          if (r != 0) {
            int textWidth = g.getFontMetrics().stringWidth(Integer.toString(r));
            int textHeight = g.getFontMetrics().getHeight();
            int textAscent = g.getFontMetrics().getAscent();
            // draw box outline
            g2d.setPaint(Color.black);
            g2d.drawRect(0, r * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            // draw label
            g2d.drawString(String.valueOf(r), CELL_WIDTH / 2 - textWidth / 2,
                    r * CELL_HEIGHT + (CELL_HEIGHT - textHeight) / 2 + textAscent);
          }
        }
        // first row (letter labels)
        else if (r == 0) {
          // draw gray label box
          g2d.setPaint(Color.gray);
          g2d.fill(new Rectangle2D.Double(c * CELL_WIDTH, 0, CELL_WIDTH, CELL_HEIGHT));
          // draw box outline
          g2d.setPaint(Color.black);
          g2d.drawRect(c * CELL_WIDTH, 0, CELL_WIDTH, CELL_HEIGHT);
          // draw label
          int textWidth = g.getFontMetrics().stringWidth(Coord.colIndexToName(c));
          int textHeight = g.getFontMetrics().getHeight();
          int textAscent = g.getFontMetrics().getAscent();
          g2d.drawString(Coord.colIndexToName(c), c * CELL_WIDTH + CELL_WIDTH / 2 - textWidth / 2,
                  (CELL_HEIGHT - textHeight) / 2 + textAscent);
        }
        // cells with possible contents
        else {
          Shape oldClip = g2d.getClip();
          // if cell is the currently selected cell, draw a red outline
          if (fc.col == c && fc.row == r) {
            g2d.setStroke(new BasicStroke(BOLD_STROKE));
            g2d.setPaint(Color.red);
          }
          else {
            g2d.setPaint(Color.black);
          }
          g2d.drawRect(c * CELL_WIDTH, r * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
          g2d.setStroke(new BasicStroke(DEFAULT_STROKE));
          g2d.clip(new Rectangle(c * CELL_WIDTH - PADDING, r * CELL_HEIGHT, CELL_WIDTH,
                  CELL_HEIGHT));
          try {
            g2d.drawString(worksheet.evaluateCell(c, r).toString(),
                    c * CELL_WIDTH + PADDING, (r + 1) * CELL_HEIGHT - PADDING);
          } catch (IllegalArgumentException e) {
            g2d.drawString("Error!", c * CELL_WIDTH + PADDING, (r + 1) * CELL_HEIGHT - PADDING);
          }
          g2d.setClip(oldClip);
        }
      }
    }
  }

  // increments largest row to be drawn
  void addRow() {
    this.gc = new Coord(gc.col, gc.row + 1);
  }

  // increments largest column to be drawn
  void addCol() {
    this.gc = new Coord(gc.col + 1, gc.row);
  }

  // sets the currently highlighted cell's location
  void setFocusCell(Coord c) {
    this.fc = c;
  }

  // gets the location of the currently highlighted cell
  Coord getFocusCell() {
    return this.fc;
  }
}