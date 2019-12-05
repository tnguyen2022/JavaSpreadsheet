package edu.cs3500.spreadsheets.provider.view;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;

/**
 * The panel that adds all the scrolling features to the visual representation of cells. This
 * contains the infinite scroll component.
 */
public class ScrollView extends JScrollPane {
  static final int BORDER = 10;
  private DrawSpreadsheet ds;
  private ScrollView scroll;

  /**
   * Creates the panel that contains the cells and labels and surrounds them with scroll bars.
   *
   * @param w  the model that provides cell information
   * @param ds the panel that contains the same model given in {@code w} and draws the cells and
   *           labels
   */
  ScrollView(IViewWorksheet w, DrawSpreadsheet ds) {
    super(ds);
    this.ds = ds;
    this.scroll = this;
    this.getVerticalScrollBar().addAdjustmentListener(ae -> {
      if (this.scroll.getVerticalScrollBar().getValueIsAdjusting()) {
        return;
      }
      if (this.scroll.getPreferredSize().height - BORDER
              == this.scroll.getVerticalScrollBar().getVisibleAmount() + ae.getValue()) {
        this.ds.addRow();
        this.ds.setPreferredSize(new Dimension(this.ds.getPreferredSize().width,
                this.ds.getPreferredSize().height + DrawSpreadsheet.CELL_HEIGHT));
        return;
      }
    });

    this.getHorizontalScrollBar().addAdjustmentListener(ae -> {
      if (this.scroll.getHorizontalScrollBar().getValueIsAdjusting()) {
        return;
      }
      if (this.scroll.getPreferredSize().width - BORDER
              == this.scroll.getHorizontalScrollBar().getVisibleAmount() + ae.getValue()) {
        this.ds.addCol();
        this.ds.setPreferredSize(new Dimension(this.ds.getPreferredSize().width
                + DrawSpreadsheet.CELL_WIDTH, this.ds.getPreferredSize().height));
        return;
      }
    });
    this.ds.setPreferredSize(new Dimension((w.getLargestCoord().col + 1)
            * DrawSpreadsheet.CELL_WIDTH, (w.getLargestCoord().row + 1)
            * DrawSpreadsheet.CELL_HEIGHT));
  }
}