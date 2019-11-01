package edu.cs3500.spreadsheets.model;

/**
 * Builds a representation of the worksheet.
 */
public class BuildWorksheet implements WorksheetReader.WorksheetBuilder<Worksheet> {
  private final Worksheet w;

  /**
   * Creates the Worksheet.
   */
  public BuildWorksheet() {
    w = new Worksheet();
  }

  @Override
  public WorksheetReader.WorksheetBuilder<Worksheet> createCell(int col, int row, String contents) {
    w.modifyOrAdd(col, row, contents);
    return this;
  }

  @Override
  public Worksheet createWorksheet() {
    return w;
  }
}
