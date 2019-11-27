import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.cs3500.spreadsheets.controller.ButtonListener;
import edu.cs3500.spreadsheets.controller.Controller;
import edu.cs3500.spreadsheets.controller.KeyboardListener;
import edu.cs3500.spreadsheets.controller.MouseListener;
import edu.cs3500.spreadsheets.controller.SpreadsheetController;
import edu.cs3500.spreadsheets.model.BuildWorksheet;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.model.WorksheetReader;

import static org.junit.Assert.assertEquals;

/**
 * Tests the controller class and all of the listeners class methods associated with the controller.
 */
public class TestController {

  @Test
  public void testMouseClick() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    MouseListener ml = new MouseListener(mock);
    ml.mouseReleased(new MouseEvent(mock.initTable, 502, 1, InputEvent.BUTTON1_MASK,
            41, 8, 1, false));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called setJLabel method\n" +
            "Called getRawCellContent method\n" +
            "Called getRawCellContent method\n" +
            "Called setJTextField method\n", log.toString());
  }

  @Test
  public void testDownKey() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    KeyboardListener kl = new KeyboardListener(mock);
    kl.keyPressed(new KeyEvent(mock.initTable, 502, 1, 16, KeyEvent.VK_DOWN));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called setJLabel method\n" +
            "Called getRawCellContent method\n" +
            "Called getRawCellContent method\n" +
            "Called setJTextField method\n", log.toString());
  }

  @Test
  public void testUpKey() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    KeyboardListener kl = new KeyboardListener(mock);
    kl.keyPressed(new KeyEvent(mock.initTable, 502, 1, 16, KeyEvent.VK_UP));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called setJLabel method\n" +
            "Called getRawCellContent method\n" +
            "Called getRawCellContent method\n" +
            "Called setJTextField method\n", log.toString());
  }

  @Test
  public void testRightKey() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    KeyboardListener kl = new KeyboardListener(mock);
    kl.keyPressed(new KeyEvent(mock.initTable, 502, 1, 16, KeyEvent.VK_RIGHT));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called setJLabel method\n" +
            "Called getRawCellContent method\n" +
            "Called getRawCellContent method\n" +
            "Called setJTextField method\n", log.toString());
  }

  @Test
  public void testLeftKey() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    KeyboardListener kl = new KeyboardListener(mock);
    kl.keyPressed(new KeyEvent(mock.initTable, 502, 1, 16, KeyEvent.VK_LEFT));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called setJLabel method\n" +
            "Called getRawCellContent method\n" +
            "Called getRawCellContent method\n" +
            "Called setJTextField method\n", log.toString());
  }

  @Test
  public void testDeleteKey() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    KeyboardListener kl = new KeyboardListener(mock);
    kl.keyPressed(new KeyEvent(mock.initTable, 502, 1, 16, KeyEvent.VK_DELETE));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called setValueAt method\n" +
            "Called setJTextField method\n", log.toString());
  }

  @Test
  public void testConfirmButton() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    ButtonListener bl = new ButtonListener(mock, gw);
    bl.actionPerformed(new ActionEvent(mock.initTable, 502, "Confirm Button"));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called setJTextField method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n" +
            "Called getRawCellContent method\n" +
            "Called setValueAt method\n", log.toString());
  }

  @Test
  public void testCancelButton() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    ButtonListener bl = new ButtonListener(mock, gw);
    bl.actionPerformed(new ActionEvent(mock.initTable, 502, "Cancel Button"));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n" +
            "Called getRawCellContent method\n" +
            "Called getRawCellContent method\n" +
            "Called setJTextField method\n", log.toString());
  }

  @Test
  public void testSaveButton() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    ButtonListener bl = new ButtonListener(mock, gw);
    bl.actionPerformed(new ActionEvent(mock.initTable, 502, "Save Button"));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n", log.toString());
  }

  @Test
  public void testLoadButton() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    ButtonListener bl = new ButtonListener(mock, gw);
    bl.actionPerformed(new ActionEvent(mock.initTable, 502, "Load Button"));
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n" +
            "Called getSelectedCoord method\n" +
            "Called getSelectedCoord method\n", log.toString());
  }

  @Test
  public void testControllerGo() throws IOException {
    GeneralWorksheet gw;
    try {
      FileReader readable = new
              FileReader("C:\\IntelliJ Projects\\speadsheet\\" +
              "code\\resources\\triangular_numbers.txt");
      try {
        gw = WorksheetReader.read(new BuildWorksheet(),
                readable);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Unable to create worksheet: " + e);
      }
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to access .txt file");
    }

    StringBuilder log = new StringBuilder();
    SpreadsheetEditableGUIViewMock mock = new SpreadsheetEditableGUIViewMock(log, gw);

    SpreadsheetController controller = new Controller(gw, mock);
    controller.runController();
    assertEquals("Called render method\n" +
            "Called addMouseListener method\n" +
            "Called addActionListener method\n" +
            "Called addKeyListener method\n", log.toString());
  }
}
