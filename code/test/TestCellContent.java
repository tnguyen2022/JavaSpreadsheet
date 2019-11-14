import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.function.LeftFunction;
import edu.cs3500.spreadsheets.model.function.LessThanFunction;
import edu.cs3500.spreadsheets.model.function.ProductFunction;
import edu.cs3500.spreadsheets.model.function.SumFunction;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.value.BooleanValue;
import edu.cs3500.spreadsheets.model.value.DoubleValue;
import edu.cs3500.spreadsheets.model.value.StringValue;

import static org.junit.Assert.assertEquals;

/**
 * Tests for validity of CellContents.
 */
public class TestCellContent {
  @Test
  public void testBlankValue() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= A2");
    assertEquals(basicWorksheet.getCell(1, 2).content, new Blank());
  }

  @Test
  public void testBooleanValue() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= true");
    basicWorksheet.modifyOrAdd(1, 2, "false");
    assertEquals(basicWorksheet.getCell(1, 1).content, new BooleanValue(true));
    assertEquals(basicWorksheet.getCell(1, 2).content, new BooleanValue(false));
  }

  @Test
  public void testDoubleValue() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= 420");
    basicWorksheet.modifyOrAdd(1, 2, "69");
    assertEquals(basicWorksheet.getCell(1, 1).content, new DoubleValue(420));
    assertEquals(basicWorksheet.getCell(1, 2).content, new DoubleValue(69));
  }

  @Test
  public void testStringValue() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "=\"I hate OOD\"");
    basicWorksheet.modifyOrAdd(1, 2, "\"OOD is not fun\"");
    assertEquals(basicWorksheet.getCell(1, 1).content,
            new StringValue("I hate OOD"));
    assertEquals(basicWorksheet.getCell(1, 2).content,
            new StringValue("OOD is not fun"));
  }

  @Test
  public void testReference() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= A2");

    String s2 = (basicWorksheet.getCell(1, 1).content).toString();
    String s1 = new Reference(new ArrayList<>(Collections.singletonList
            (basicWorksheet.getCell(1, 1)))).toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(0),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test
  public void testSumFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (SUM 69 420)");

    String s2 = (basicWorksheet.getCell(1, 1).content).toString();
    String s1 = (new SumFunction(new ArrayList<>(Arrays.asList(new DoubleValue(69),
            new DoubleValue(420))))).toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(489),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInvalidSumFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (SUM true 420)");

    assertEquals(new DoubleValue(489),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test
  public void testProductFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (PRODUCT 4 5)");
    String s1 = basicWorksheet.getCell(1, 1).content.toString();
    String s2 = new ProductFunction(new ArrayList<>(Arrays.asList(new DoubleValue(4),
            new DoubleValue(5)))).toString();
    assertEquals(s1, s2);
    assertEquals(new DoubleValue(20),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInvalidProductFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (PRODUCT true 5)");
    assertEquals(new DoubleValue(20),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test
  public void testLessThanFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (< 4 5)");
    String s1 = basicWorksheet.getCell(1, 1).content.toString();
    String s2 = new LessThanFunction(new ArrayList<>(Arrays.asList(new DoubleValue(4),
            new DoubleValue(5)))).toString();
    assertEquals(s1, s2);
    assertEquals(new BooleanValue(true),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInvalidLessThanFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (< true 5)");
    assertEquals(new BooleanValue(true),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test
  public void testLeftFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (LEFT \"Ben Lerner\" 5)");
    String s1 = basicWorksheet.getCell(1, 1).content.toString();
    String s2 = new LeftFunction(new ArrayList<>(Arrays.asList(new StringValue("Ben Lerner"),
            new DoubleValue(5)))).toString();
    assertEquals(s1, s2);
    assertEquals(new StringValue("Ben L"),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInvalidLeftFunction() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (LEFT \"Ben Lerner\" true)");
    assertEquals(new StringValue("Ben L"),
            basicWorksheet.getCell(1, 1).content.evaluate());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNoArgFormula() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= (SUM)");
    basicWorksheet.modifyOrAdd(1, 2, "= (PRODUCT)");
    basicWorksheet.modifyOrAdd(1, 3, "= (<)");
    basicWorksheet.modifyOrAdd(1, 4, "= (LEFT)");

    assertEquals(new DoubleValue(10),
            basicWorksheet.getCell(1, 1).content.evaluate());
    assertEquals(new DoubleValue(10),
            basicWorksheet.getCell(1, 2).content.evaluate());
    assertEquals(new DoubleValue(10),
            basicWorksheet.getCell(1, 3).content.evaluate());
    assertEquals(new DoubleValue(10),
            basicWorksheet.getCell(1, 4).content.evaluate());
  }

  @Test
  public void testMultipleSameCellFormula() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= 5");
    basicWorksheet.modifyOrAdd(1, 2, "= (SUM A1 A1)");

    String s2 = (basicWorksheet.getCell(1, 2).content).toString();
    String s1 = (new SumFunction(new ArrayList<>(Arrays.asList(
            new Reference(new ArrayList<>(Collections.singletonList
                    (basicWorksheet.getCell(1, 1)))),
            new Reference(new ArrayList<>(Collections.singletonList
                    (basicWorksheet.getCell(1, 1)))))))).toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(10),
            basicWorksheet.getCell(1, 2).content.evaluate());
  }

  @Test
  public void testReferenceFormula() {
    BasicWorksheet basicWorksheet = new BasicWorksheet();
    basicWorksheet.modifyOrAdd(1, 1, "= 5");
    basicWorksheet.modifyOrAdd(1, 2, "= 3");
    basicWorksheet.modifyOrAdd(1, 3, "= 2");
    basicWorksheet.modifyOrAdd(1, 4, "= (SUM A1:A3)");

    String s2 = (basicWorksheet.getCell(1, 4).content).toString();
    String s1 = (new SumFunction(new ArrayList<>(Arrays.asList(new Reference(new ArrayList<>(Arrays.asList(
            (basicWorksheet.getCell(1, 1)), (basicWorksheet.getCell(1, 2)),
            (basicWorksheet.getCell(1, 3)))))))))
            .toString();
    assertEquals(s1, s2);

    assertEquals(new DoubleValue(10),
            basicWorksheet.getCell(1, 4).content.evaluate());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInvalidArgumentTypes() {
    BasicWorksheet worksheet = new BasicWorksheet();
    worksheet.modifyOrAdd(1, 1, "= (SUM true 5)");
    assertEquals(worksheet.getCell(1, 1).content.evaluate(),
            "HAH IT DOESNT WORK");
  }

  @Test
  public void testPrintValuesAsString() {
    BasicWorksheet worksheet = new BasicWorksheet();
    worksheet.modifyOrAdd(1, 1, "= 5");
    worksheet.modifyOrAdd(1, 2, "= true");
    worksheet.modifyOrAdd(1, 3, "=\"hi b\\\"ye \\\\ n\\\\o\"");
    assertEquals(worksheet.getCell(1, 1).content.toString(),
            "5.000000");
    assertEquals(worksheet.getCell(1, 2).content.toString(),
            "true");
    assertEquals(worksheet.getCell(1, 3).content.toString(),
            "\"hi b\\\"ye \\\\ n\\\\o\"");
  }
}
