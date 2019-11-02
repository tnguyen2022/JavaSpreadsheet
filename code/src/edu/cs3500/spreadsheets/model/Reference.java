package edu.cs3500.spreadsheets.model;

import java.util.ArrayList;
import java.util.Objects;

import edu.cs3500.spreadsheets.model.value.Value;

/**
 * Represents a reference of cells in a worksheet.
 */
public class Reference implements Formula {
  public ArrayList<Cell> region = new ArrayList<>();

  /**
   * Parses a given string to rectangular region of cells that represents a reference.
   *
   * @param s the range of coordinates in string form.
   */
  public Reference(String s) {
    if (s.length() < 2) {
      throw new IllegalArgumentException("Need a valid reference");
    }
    if (s.contains(":")) {
      if (s.split(":")[0].equals(s.split(":")[1])) {
        int col = Coord.colNameToIndex(s.split(":")[0].substring(0,
                Cell.getIndexOfSplit(s)));
        int row = Integer.parseInt(s.substring(Cell.getIndexOfSplit(s)));
        Cell currentCell = BasicWorksheet.getCell(col, row);
        region.add(currentCell);
      } else {
        int col1 = (Coord.colNameToIndex(s.split(":")[0].substring(0,
                Cell.getIndexOfSplit(s.split(":")[0]))));
        int row1 = Integer.parseInt(s.split(":")[0].substring(
                Cell.getIndexOfSplit(s.split(":")[0])));
        int col2 = (Coord.colNameToIndex(s.split(":")[1].substring(0,
                Cell.getIndexOfSplit(s.split(":")[1]))));
        int row2 = Integer.parseInt(s.split(":")[1].substring(
                Cell.getIndexOfSplit(s.split(":")[1])));

        if (col1 > col2) {
          int temp = col1;
          col1 = col2;
          col2 = temp;
        }
        if (row1 > row2) {
          int temp = row1;
          row1 = row2;
          row2 = temp;
        }

        for (int i = col1; i <= col2; i++) {
          for (int j = row1; j <= row2; j++) {
            region.add(BasicWorksheet.getCell(i, j));
          }
        }
      }
    } else {
      int col = Coord.colNameToIndex(s.split(":")[0].substring(0,
              Cell.getIndexOfSplit(s)));
      int row = Integer.parseInt(s.substring(Cell.getIndexOfSplit(s)));
      Cell currentCell = BasicWorksheet.getCell(col, row);
      region.add(currentCell);
    }
  }

  public Reference(ArrayList<Cell> r) {
    this.region = r;
  }

  @Override
  public String toString() {
    if (this.region.size() == 1) {
      return region.get(0).toString();
    } else {
      return region.get(0).toString() + ":" + region.get(region.size() - 1).toString();
    }
  }

  @Override
  public Value evaluate() {
    if (region.size() == 1) {
      return BasicWorksheet.getCell(region.get(0).cellReference.col,
              region.get(0).cellReference.row).content.evaluate();
    } else {
      throw new IllegalArgumentException("Cannot evaluate a reference of more than one cell");

    }
  }

  @Override
  public boolean checkCycle(ArrayList<Coord> acc) {
    for (int i = 0; i < region.size(); i++) {
      if (region.get(i).checkCellForCycle(acc)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public <T> T accept(CellContentVisitor<T> visitor) {
    return visitor.visitReference(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof Reference) {
      Reference that = (Reference) o;
      if (this.region.size() == that.region.size()) {
        for (int i = 0; i < this.region.size(); i++) {
          if (!(this.region.get(i).equals(that.region.get(i)))) {
            return false;
          }
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(region);
  }
}
