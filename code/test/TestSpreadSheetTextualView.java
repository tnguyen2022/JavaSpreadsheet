import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.BuildWorksheet;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

import static org.junit.Assert.assertEquals;

//delete tests that uses text strings

/**
 * Tests for correct rendering of the textual view of the model.
 */
public class TestSpreadSheetTextualView {
  @Test
  public void testEmptyTextualView() {
    try {
      PrintWriter saveFile = new
              PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\resources\\save.txt");
      try {
        GeneralWorksheet gw = new BasicWorksheet();
        SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
        saveView.render();
        saveFile.close();

        FileReader testFile = new
                FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\resources\\save.txt");
        GeneralWorksheet gwTest = WorksheetReader.read(new BuildWorksheet(), testFile);
        assertEquals(gw, gwTest);
      } catch (IOException e) {
        throw new IllegalStateException("Unable to save worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }
  }

  @Test
  public void testValidCellsSave() {
    try {
      PrintWriter saveFile =
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                      "\\save2.txt");
      try {
        GeneralWorksheet gw = new BasicWorksheet();
        gw.modifyOrAdd(1, 1, "= (SUM A2 A3)");
        gw.modifyOrAdd(1, 2, "= 69");
        gw.modifyOrAdd(1, 3, "= 420");
        gw.modifyOrAdd(2, 1, "= (PRODUCT A2 A3)");
        gw.modifyOrAdd(3, 1, "= (< A2 A3)");
        gw.modifyOrAdd(4, 1, "= (LEFT A2 1)");
        gw.modifyOrAdd(1, 2, "= 31");
        SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
        saveView.render();
        saveFile.close();

        FileReader testFile =
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                        "\\save2.txt");
        GeneralWorksheet gwTest = WorksheetReader.read(new BuildWorksheet(), testFile);
        assertEquals(gw, gwTest);
      } catch (IOException e) {
        throw new IllegalStateException("Unable to save worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }
  }

  @Test
  public void testInvalidCellsSave() {
    try {
      PrintWriter saveFile =
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                      "\\save3.txt");
      try {
        GeneralWorksheet gw = new BasicWorksheet();
        gw.modifyOrAdd(1, 1, "= (PRODUCT true A3)");
        gw.modifyOrAdd(1, 2, "= 69");
        gw.modifyOrAdd(1, 3, "= 420");
        gw.modifyOrAdd(2, 1, "= (PRODUCT false A3)");
        gw.modifyOrAdd(3, 1, "= (< true A3)");
        gw.modifyOrAdd(4, 1, "= (LEFT true 1)");
        gw.modifyOrAdd(1, 2, "= 31");
        SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
        saveView.render();
        saveFile.close();

        FileReader testFile =
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                        "\\save3.txt");
        GeneralWorksheet gwTest = WorksheetReader.read(new BuildWorksheet(), testFile);
        assertEquals(gw, gwTest);
      } catch (IOException e) {
        throw new IllegalStateException("Unable to save worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }
  }

  @Test
  public void testValidRowHeightSave() {
    try {
      PrintWriter saveFile =
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                      "\\save4.txt");
      try {
        GeneralWorksheet gw = new BasicWorksheet();
        gw.addOrSetRowHeight(3, 69);
        gw.addOrSetRowHeight(4, 420);

        gw.modifyOrAdd(1, 2, "= 69");

        SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
        saveView.render();
        saveFile.close();

        FileReader testFile =
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                        "\\save4.txt");
        GeneralWorksheet gwTest = WorksheetReader.read(new BuildWorksheet(), testFile);
        assertEquals(gw, gwTest);
      } catch (IOException e) {
        throw new IllegalStateException("Unable to save worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }
  }

  @Test
  public void testValidColWidthSave() {
    try {
      PrintWriter saveFile =
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                      "\\save5.txt");
      try {
        GeneralWorksheet gw = new BasicWorksheet();
        gw.addOrSetColWidth(3, 69);
        gw.addOrSetColWidth(4, 420);

        gw.modifyOrAdd(1, 2, "= 69");

        SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
        saveView.render();
        saveFile.close();

        FileReader testFile =
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\resources" +
                        "\\save5.txt");
        GeneralWorksheet gwTest = WorksheetReader.read(new BuildWorksheet(), testFile);
        assertEquals(gw, gwTest);
      } catch (IOException e) {
        throw new IllegalStateException("Unable to save worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }
  }
}
