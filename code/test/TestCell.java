import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.value.BooleanValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for cycles exist when attempting to evaluate the cell.
 */
public class TestCell {
  @Test(expected = IllegalArgumentException.class)
  public void testCellDirectCycle() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (SUM A1 1)");
    assertEquals(basicWorksheet.evaluateCell(basicWorksheet.getCell(1, 1)),
            new Cell(new BooleanValue(true), new Coord(1, 1)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCellIndirectCycle() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (SUM A2 A3)");
    basicWorksheet.modifyOrAdd(1, 2, "= A3");
    basicWorksheet.modifyOrAdd(1, 3, "= A2");
    assertEquals(basicWorksheet.evaluateCell(basicWorksheet.getCell(1, 1)),
            new Cell(new BooleanValue(true), new Coord(1, 1)));
  }

  @Test
  public void testCellNoCycle() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (SUM A2 A3)");
    basicWorksheet.modifyOrAdd(1, 2, "= A3");
    basicWorksheet.modifyOrAdd(1, 3, "= 5");
    assertTrue(basicWorksheet.getCell(1, 1)
                    .checkCellForCycle(new ArrayList<Coord>(Collections
                            .singletonList(new Coord(1, 1)))));
  }
}
