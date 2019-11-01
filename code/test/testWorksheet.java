import org.junit.Test;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;

import static org.junit.Assert.*;

public class testWorksheet {
  @Test
  public void testEmptyWorksheet() {
    Worksheet worksheet = new Worksheet();
    assertTrue(worksheet.ws.isEmpty());
  }

  @Test
  public void testWorksheetCellsExist() {
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "69");
    worksheet.modifyOrAdd(1, 2, "boi");
    assertEquals(worksheet.ws.get(new Coord(1, 1)),
            new Cell(new DoubleValue(69), new Coord(1,1)));
    assertEquals(worksheet.ws.get(new Coord(1, 2)),
            new Cell(new StringValue("boi"), new Coord(1,2)));
    worksheet.modifyOrAdd(1, 1, "true");
    assertEquals(worksheet.ws.get(new Coord(1, 1)),
            new Cell(new BooleanValue(true), new Coord(1,1)));
  }
}