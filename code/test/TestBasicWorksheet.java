import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.value.StringValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for the construction of a BasicWorkSheet.
 */
public class TestBasicWorksheet {
  @Test
  public void testEmptyWorksheet() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    assertTrue(basicWorksheet.WS.isEmpty());
  }

  @Test
  public void testWorksheetCellsExist() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "69");
    basicWorksheet.modifyOrAdd(1, 2, "boi");
    assertEquals(basicWorksheet.WS.get(new Coord(1, 1)),
            new Cell(new DoubleValue(69), new Coord(1, 1)));
    assertEquals(basicWorksheet.WS.get(new Coord(1, 2)),
            new Cell(new StringValue("boi"), new Coord(1, 2)));
    basicWorksheet.modifyOrAdd(1, 1, "true");
    assertEquals(basicWorksheet.WS.get(new Coord(1, 1)),
            new Cell(new BooleanValue(true), new Coord(1, 1)));
  }
}