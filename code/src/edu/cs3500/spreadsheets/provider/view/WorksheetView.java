package edu.cs3500.spreadsheets.provider.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;

/**
 * Represents a frame window for a view-only worksheet GUI built with Swing.
 */
public class WorksheetView extends JFrame implements IView {
  private DrawSpreadsheet spread;
  private ScrollView scroll;

  /**
   * Constructs a view-only worksheet GUI with all the Swing components inside the frame.
   *
   * @param ivw the worksheet to be viewed
   */
  public WorksheetView(IViewWorksheet ivw) {

    super();
    IViewWorksheet worksheet = ivw;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(850, 450));
    this.spread = new DrawSpreadsheet(worksheet);
    this.scroll = new ScrollView(worksheet, this.spread);
    this.add(this.scroll);
  }


  @Override
  public void render() {
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    return;
  }
}