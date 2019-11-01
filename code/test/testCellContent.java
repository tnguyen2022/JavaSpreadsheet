import org.junit.Test;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;

import static org.junit.Assert.*;

public class testCellContent {
  @Test
  public void testBlankValue(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= A2");
    assertEquals(worksheet.ws.get(new Coord(1,2)).content, new Blank());
  }

  @Test
  public void testBooleanValue(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= true");
    worksheet.modifyOrAdd(1, 2, "false");
    assertEquals(worksheet.ws.get(new Coord(1,1)).content, new BooleanValue(true));
    assertEquals(worksheet.ws.get(new Coord(1,2)).content, new BooleanValue(false));
  }

  @Test
  public void testDoubleValue(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= 420");
    worksheet.modifyOrAdd(1, 2, "69");
    assertEquals(worksheet.ws.get(new Coord(1,1)).content, new DoubleValue(420));
    assertEquals(worksheet.ws.get(new Coord(1,2)).content, new DoubleValue(69));
  }
}
