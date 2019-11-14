package edu.cs3500.spreadsheets.view;

import java.io.IOException;

import edu.cs3500.spreadsheets.model.BasicWorksheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.GeneralWorksheet;
import edu.cs3500.spreadsheets.model.value.DoubleValue;

public class SpreadsheetTextualView implements SpreadsheetView {
  /**
   * Creates an example of a worksheet model.
   */
  private final GeneralWorksheet model;

  /**
   * Creates an appendable that can append to the current textual input.
   */
  private Appendable in;

  /**
   * Constructs a SpreadsheetTextualView with an appendable.
   *
   * @param model represents a worksheet model of the view.
   * @param in    represents an appendable to the textual view.
   */
  public SpreadsheetTextualView(GeneralWorksheet model, Appendable in) {
    this.in = in;
    this.model = model;
  }

  /**
   * Outputs a visual representation of the model.
   */
  @Override
  public void save() throws IOException {
    int viewHeight = model.getMaxHeight();
    int viewWidth = model.getMaxWidth();

    for (int i = 1; i <= viewHeight; i++) {
      for (int j = 1; j <= viewWidth; j++) {
        if (!model.getCell(j, i).content.equals(new Blank())) {

          this.in.append(Coord.colIndexToName(j)).append(String.valueOf(i)).append(" = ").append
                  (model.getCell(j, i).content.toString()).append("\n");
        }

//       if (i == 0){
//          if (j == 0){
//            output += (" ");
//          }
//          else{
//            output += " " + Coord.colIndexToName(j);
//          }
//       }
//        else if (j == 0){
//           output += "" + j;
//        }
//       else{
//         output += " " + model.evaluateCell(BasicWorksheet.getCell(i, j)).toString();
//       }
//       }
//     output += "\n";
      }
    }
  }
}
