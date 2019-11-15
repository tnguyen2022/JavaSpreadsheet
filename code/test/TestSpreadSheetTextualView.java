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

/**
 * Tests for correct rendering of the textual view of the model.
 */
public class TestSpreadSheetTextualView {
  @Test
  public void testEmptyTextualView() {
    try {
      PrintWriter saveFile =
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\save.txt");
      try {
        GeneralWorksheet gw = new BasicWorksheet();
        SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
        saveView.render();
        saveFile.close();

        FileReader testFile =
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\save.txt");
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
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\save2.txt");
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
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\save2.txt");
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
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\save3.txt");
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
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\save3.txt");
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
  public void testCycleCellsSave() {
    try {
      PrintWriter saveFile =
              new PrintWriter("C:\\IntelliJ Projects\\speadsheet\\code\\save4.txt");
      try {
        GeneralWorksheet gw = new BasicWorksheet();
        gw.modifyOrAdd(1, 1, "= (SUM A2 A3)");
        gw.modifyOrAdd(1, 2, "= 66");
        gw.modifyOrAdd(1, 3, "= A1");
        gw.modifyOrAdd(2, 1, "= (PRODUCT A1 34)");
        SpreadsheetView saveView = new SpreadsheetTextualView(gw, saveFile);
        saveView.render();
        saveFile.close();

        FileReader testFile =
                new FileReader("C:\\IntelliJ Projects\\speadsheet\\code\\save4.txt");
        GeneralWorksheet gwTest = WorksheetReader.read(new BuildWorksheet(), testFile);
        assertEquals(gw.getCell(1, 1).content.toString(),
                gwTest.getCell(1, 1).content.toString());
        assertEquals(gw.getCell(1, 2).content.toString(),
                gwTest.getCell(1, 2).content.toString());
        assertEquals(gw.getCell(1, 3).content.toString(),
                gwTest.getCell(1, 3).content.toString());
        assertEquals(gw.getCell(2, 1).content.toString(),
                gwTest.getCell(2, 1).content.toString());
      } catch (IOException e) {
        throw new IllegalStateException("Unable to save worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }
  }
}
