import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.CellContentVisitorFunctions.Sum;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Function.LeftFunction;
import edu.cs3500.spreadsheets.model.Function.LessThanFunction;
import edu.cs3500.spreadsheets.model.Function.ProductFunction;
import edu.cs3500.spreadsheets.model.Function.SumFunction;
import edu.cs3500.spreadsheets.model.Reference;
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

  @Test
  public void testStringValue(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "=\"I hate OOD\"");
    worksheet.modifyOrAdd(1, 2, "\"OOD is not fun\"");
    assertEquals(worksheet.ws.get(new Coord(1,1)).content,
            new StringValue("I hate OOD"));
    assertEquals(worksheet.ws.get(new Coord(1,2)).content,
            new StringValue("OOD is not fun"));
  }

  @Test
  public void testReference(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= A2");

    String s2 = (worksheet.ws.get(new Coord(1,1)).content).toString();
    String s1 = new Reference("A2").toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(0),
            worksheet.ws.get(new Coord(1,1)).content.evaluate());
  }

  @Test
  public void testSumFunction(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= (SUM 69 420)");

    String s2 = (worksheet.ws.get(new Coord(1,1)).content).toString();
    String s1 =     (new SumFunction(new ArrayList<>(Arrays.asList(new DoubleValue(69),
            new DoubleValue(420))))).toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(489),
            worksheet.ws.get(new Coord(1,1)).content.evaluate());
  }

  @Test
  public void testProductFunction(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= (PRODUCT 4 5)");
    String s1 = worksheet.ws.get(new Coord(1,1)).content.toString();
    String s2 =new ProductFunction(new ArrayList<>(Arrays.asList(new DoubleValue(4),
                    new DoubleValue(5)))).toString();
    assertEquals(s1, s2);
    assertEquals(new DoubleValue(20),
            worksheet.ws.get(new Coord(1,1)).content.evaluate());
  }

  @Test
  public void testLessThanFunction(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= (< 4 5)");
    String s1 = worksheet.ws.get(new Coord(1,1)).content.toString();
    String s2 =new LessThanFunction(new ArrayList<>(Arrays.asList(new DoubleValue(4),
            new DoubleValue(5)))).toString();
    assertEquals(s1, s2);
    assertEquals(new BooleanValue(true),
            worksheet.ws.get(new Coord(1,1)).content.evaluate());
  }

  @Test
  public void testLeftFunction(){
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= (LEFT \"Ben Lerner\" 5)");
    String s1 = worksheet.ws.get(new Coord(1,1)).content.toString();
    String s2 =new LeftFunction(new ArrayList<>(Arrays.asList(new StringValue("Ben Lerner"),
            new DoubleValue(5)))).toString();
    assertEquals(s1, s2);
    assertEquals(new StringValue("Ben L"),
            worksheet.ws.get(new Coord(1,1)).content.evaluate());
  }

  @Test
  public void testMultipleSameCellFormula() {
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= 5");
    worksheet.modifyOrAdd(1, 2, "= (SUM A1 A1)");

    String s2 = (worksheet.ws.get(new Coord(1,2)).content).toString();
    String s1 =     (new SumFunction(new ArrayList<>(Arrays.asList(new Reference("A1"),
            new Reference("A1"))))).toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(10),
            worksheet.ws.get(new Coord(1,2)).content.evaluate());
  }

  @Test
  public void testReferenceFormula() {
    Worksheet worksheet = new Worksheet();
    worksheet.modifyOrAdd(1, 1, "= 5");
    worksheet.modifyOrAdd(1, 2, "= 3");
    worksheet.modifyOrAdd(1, 3, "= 2");
    worksheet.modifyOrAdd(1, 4, "= (SUM A1:A3)");

    String s2 = (worksheet.ws.get(new Coord(1,4)).content).toString();
    String s1 =     (new SumFunction(new ArrayList<>(Arrays.asList(new Reference("A1:A3")))))
            .toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(10),
            worksheet.ws.get(new Coord(1,4)).content.evaluate());
  }
}
