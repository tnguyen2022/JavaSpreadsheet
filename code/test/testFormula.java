import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Value.BooleanValue;
import edu.cs3500.spreadsheets.model.Value.DoubleValue;
import edu.cs3500.spreadsheets.model.Value.StringValue;
import edu.cs3500.spreadsheets.model.Worksheet;
import org.junit.Before;
import org.junit.Test;

public class testFormula {

  private Worksheet worksheet = new Worksheet();

  @Before
  public void setup() {
    Worksheet worksheet = new Worksheet();
    assertTrue(worksheet.ws.isEmpty());

    worksheet.modifyOrAdd(1, 1, "= 1");
    worksheet.modifyOrAdd(1, 2, "2");
  }

  @Test
  public void testSum() {
    this.setup();

  }
}