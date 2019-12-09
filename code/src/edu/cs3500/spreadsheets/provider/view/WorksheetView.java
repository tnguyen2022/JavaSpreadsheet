package edu.cs3500.spreadsheets.provider.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;

/**
 * Represents a frame window for a view-only worksheet GUI built with Swing.
 */
public class WorksheetView extends JFrame implements IView {

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
    DrawSpreadsheet spread = new DrawSpreadsheet(worksheet);
    ScrollView scroll = new ScrollView(worksheet, spread);
    this.add(scroll);
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