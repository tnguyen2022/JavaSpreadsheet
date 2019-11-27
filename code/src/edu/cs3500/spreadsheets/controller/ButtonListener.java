package edu.cs3500.spreadsheets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import edu.cs3500.spreadsheets.model.BuildWorksheet;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableGUIView;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

/**
 * Waits for a button to be pressed and performs desired actions in the view.
 * Handles the Confirm edit, Cancel edit, Loading Files, and Saving Files buttons.
 */
public class ButtonListener implements ActionListener {
  private GeneralWorksheet model;
  private SpreadsheetView view;

  public ButtonListener(SpreadsheetView view, GeneralWorksheet model) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    final int row = view.getSelectedCellCoord().row;
    final int col = view.getSelectedCellCoord().col;
    if (e.getActionCommand().equals("Confirm Button")) {
      String text = view.getInputText();
      view.setValueAt(row - 1, col - 1, text);
      for (int i = 0; i < model.getMaxHeight(); i++) {
        for (int j = 0; j < model.getMaxWidth(); j++) {
          if (!model.getCell(j + 1, i + 1).content.toString().equals("")) {
            view.setValueAt(i, j, "= " + view.getRawCellContent(i + 1, j + 1));
          }
        }
      }
    } else if (e.getActionCommand().equals("Cancel Button")) {
      //view.getRawCellContent(row, col);
      if (view.getRawCellContent(row, col).equals("")) {
        view.setJTextField("");
      } else {
        view.setJTextField("= " + view.getRawCellContent(row, col));
      }
    } else if (e.getActionCommand().equals("Save Button")) {
      JFileChooser saveChooser = new JFileChooser();
      saveChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
      int retrieval = saveChooser.showSaveDialog(null);
      if (retrieval == JFileChooser.APPROVE_OPTION) {
        try {
          String s = saveChooser.getSelectedFile().getPath();
          if (!s.substring(s.length() - 4).equals(".txt")) {
            s = s + ".txt";
          }
          File f = new File(s);
          PrintWriter saveFile = new PrintWriter(f);
          try {
            SpreadsheetView saveView = new SpreadsheetTextualView(model, saveFile);
            saveView.render();
            saveFile.close();
          } catch (IOException ex) {
            throw new IllegalStateException("Unable to save worksheet: " + e);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    } else if (e.getActionCommand().equals("Load Button")) {
      JFileChooser loadChooser = new JFileChooser();
      loadChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
      int retrieval = loadChooser.showOpenDialog(null);
      if (retrieval == JFileChooser.APPROVE_OPTION) {
        try {
          String s = loadChooser.getSelectedFile().getPath();
          if (!s.substring(s.length() - 4).equals(".txt")) {
            s = s + ".txt";
          }
          File f = new File(s);
          try {
            FileReader readable = new FileReader(f);
            try {
              GeneralWorksheet newModel = WorksheetReader.read(new BuildWorksheet(), readable);
              SpreadsheetEditableGUIView view = new SpreadsheetEditableGUIView(newModel);
              SpreadsheetController controller = new Controller(newModel, view);
              controller.runController();
            } catch (IllegalArgumentException ex) {
              throw new IllegalStateException("Unable to create worksheet: " + e);
            }
          } catch (FileNotFoundException ex) {
            throw new IllegalStateException("Unable to access .txt file");
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
