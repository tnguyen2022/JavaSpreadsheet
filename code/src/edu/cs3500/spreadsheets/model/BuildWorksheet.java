package edu.cs3500.spreadsheets.model;

/**
 * Builds a representation of a Worksheet.
 */
public class BuildWorksheet implements WorksheetReader.WorksheetBuilder<BasicWorksheet> {
  private final BasicWorksheet w;

  /**
   * Constructs an instance of a BasicWorksheet.
   */
  public BuildWorksheet() {
    w = new BasicWorksheet();
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicWorksheet>
      createCell(int col, int row, String contents) {
    w.modifyOrAdd(col, row, contents);
    return this;
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicWorksheet>
      adjustRowHeight(int row, int rowHeight) {
    w.addOrSetRowHeight(row, rowHeight);
    return this;
  }

  @Override
  public WorksheetReader.WorksheetBuilder<BasicWorksheet> adjustColWidth(int col, int colWidth) {
    w.addOrSetColWidth(col, colWidth);
    return this;
  }

  @Override
  public BasicWorksheet createWorksheet() {
    return w;
  }
}
