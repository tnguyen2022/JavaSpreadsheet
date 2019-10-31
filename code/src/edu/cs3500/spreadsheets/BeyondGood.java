package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import edu.cs3500.spreadsheets.model.BuildWorksheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.model.Worksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;

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


    GeneralWorksheet gw = new Worksheet();
    if (args.length > 0) {
      if (args[0].equals("-in")) {
        try {
          FileReader readable = new FileReader(args[1] + ".txt");
          try {
            gw = WorksheetReader.read(new BuildWorksheet(),
                    readable);
          } catch (IllegalArgumentException e) {
            System.out.println("Unable to create worksheet");
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
                System.out.println("Not Valid Cell Name");
              }
              int col = Coord.colNameToIndex(args[i].substring(0, index));
              int row = Integer.parseInt(args[i].substring(index));
              System.out.println(gw.evaluateCell(Worksheet.getCell(col, row)).toString());
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
              System.out.println("Error in cell " + args[i + 1] + ":" + e);
            }
          }
          else{
            throw new IllegalStateException("Not proper -eval command");
          }
        }
      } else {
        throw new IllegalStateException("Not proper -in command");
      }
    }
  }
}
