package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import edu.cs3500.spreadsheets.controller.Controller;
import edu.cs3500.spreadsheets.controller.SpreadsheetController;
import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.BuildWorksheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.model.value.Value;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.provider.controller.Features;
import edu.cs3500.spreadsheets.provider.controller.FeaturesProviderController;
import edu.cs3500.spreadsheets.provider.model.IWorksheet;
import edu.cs3500.spreadsheets.provider.model.WorksheetAdapter;
import edu.cs3500.spreadsheets.provider.view.IView;
import edu.cs3500.spreadsheets.provider.view.WorksheetViewEditable;
import edu.cs3500.spreadsheets.view.SpreadsheetEditableGUIView;
import edu.cs3500.spreadsheets.view.SpreadsheetGraphicalView;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */

    if (args.length > 0) {
      GeneralWorksheet gw = new BasicWorksheet();
      if (args[0].equals("-in")) {
        try {
          FileReader readable = new FileReader(args[1]);
          try {
            gw = WorksheetReader.read(new BuildWorksheet(),
                    readable);
          } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unable to create worksheet: " + e);
          }
        } catch (FileNotFoundException e) {
          throw new IllegalStateException("Unable to access .txt file");
        }

        for (int i = 2; i < args.length; i += 2) {
          if (args[i].equals("-eval")) {
            int index = 0;
            try {
              try {
                index = Cell.getIndexOfSplit(args[i + 1]);
              } catch (IllegalArgumentException e) {
                System.out.print("Not Valid Cell Name");
              }
              int col = Coord.colNameToIndex(args[i + 1].substring(0, index));
              int row = Integer.parseInt(args[i + 1].substring(index));
              Value v = gw.evaluateCell(gw.getCell(col, row));
              System.out.print(v.toString());
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
              System.out.println("Error in cell " + args[i + 1] + ":" + e);
            }
          } else if (args[i].equals("-save")) {
            try {
              PrintWriter saveFile = new PrintWriter(args[i + 1]);
              try {
                SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
                saveView.render();
                saveFile.close();
              } catch (IOException e) {
                throw new IllegalStateException("Unable to save worksheet: " + e);
              }
            } catch (FileNotFoundException e) {
              throw new IllegalStateException("Unable to access .txt file");
            }
          } else if (args[i].equals("-gui")) {
            SpreadsheetGraphicalView view = new SpreadsheetGraphicalView(gw);
            view.render();
          } else if (args[i].equals("-edit")) {
            SpreadsheetEditableGUIView view = new SpreadsheetEditableGUIView(gw);
            SpreadsheetController controller = new Controller(gw, view);
            try {
              controller.runController();
            } catch (IOException e) {
              throw new IllegalStateException("Cannot create Spreadsheet view");
            }
          } else if (args[i].equals("-provider")) {
            IWorksheet providerModel = new WorksheetAdapter(gw);
            IView view = new WorksheetViewEditable(providerModel);
            Features controller = new FeaturesProviderController(providerModel, view);
            controller.setView(view);
            view.render();
          } else {
            throw new IllegalStateException("Not proper command-line style (needs to specify" +
                    "-eval, -save, -gui, or -edit)");
          }
        }
      } else if (args[0].equals("-gui")) {
        SpreadsheetGraphicalView view = new SpreadsheetGraphicalView(new BasicWorksheet());
        view.render();
      } else if (args[0].equals("-edit")) {
        SpreadsheetGraphicalView view = new SpreadsheetGraphicalView(new BasicWorksheet());
        SpreadsheetController controller = new Controller(gw, view);
        try {
          controller.runController();
        } catch (IOException e) {
          throw new IllegalStateException("Cannot create Spreadsheet view");
        }
      } else if (args[0].equals("-provider")) {
        IWorksheet providerModel = new WorksheetAdapter(new BasicWorksheet());
        IView view = new WorksheetViewEditable(providerModel);
        Features controller = new FeaturesProviderController(providerModel, view);
        controller.setView(view);
        view.render();
      } else {
        throw new IllegalStateException("Not proper -in command");
      }
    }
  }
}
