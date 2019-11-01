import org.junit.Test;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Worksheet;

import static org.junit.Assert.*;

public class testCell {
  @Test (expected = IllegalArgumentException.class)
  public void testCellDirectCycle() {
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= (SUM A1 1)");
    assertEquals(worksheet.evaluateCell(worksheet.ws.get(new Coord(1,1))),
            new Cell(new BooleanValue(true), new Coord(1,1)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCellIndirectCycle() {
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= (SUM A2 A3)");
    worksheet.modifyOrAdd(1, 2, "= A3");
    worksheet.modifyOrAdd(1, 3, "= A2");
    assertEquals(worksheet.evaluateCell(worksheet.ws.get(new Coord(1,1))),
            new Cell(new BooleanValue(true), new Coord(1,1)));
  }
}
