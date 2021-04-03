import java.util.ArrayList;
import java.util.List;

/**
 * Use nomes que revelem a intenção.
 */
public class Minesweeper {
  List<int[]> theList = new ArrayList<int[]>();

  List<int[]> gameBoard = new ArrayList<int[]>();
  final int STATUS_VALUE = 0;
  final int FLAGGED = 4;
  
  List<Cell> gameBoard2 = new ArrayList<Cell>();

  /**
   * Ruim
   */
  public List<int[]> getThem() {
    List<int[]> list1 = new ArrayList<int[]>();

    for (int[] x : theList)
      if (x[0] == 4)
        list1.add(x);
    return list1;
  }
  
  /**
   * Primeira refatoração de {@link getThem}
   */
  public List<int[]> getFlaggedCells() {
    List<int[]> flaggedCells = new ArrayList<int[]>();

    for (int[] cell : gameBoard)
      if (cell[STATUS_VALUE] == FLAGGED)
        flaggedCells.add(cell);
    return flaggedCells;
  }


  /**
   * Essa classe não está pronta pra uso. Serve
   * apenas para demonstrar o uso do método refatorado.
   */
  public class Cell {
    int[] cell;
    final int FLAGGED = 4;

    public boolean isFlagged() {
      return cell[0] == FLAGGED;
    }
  }

  /**
   * Segunda refatoração de {@link getThem}
   */
  public List<Cell> getFlaggedCells2() {
    List<Cell> flaggedCells = new ArrayList<Cell>();

    for (Cell cell : gameBoard2)
      if (cell.isFlagged())
        flaggedCells.add(cell);
    return flaggedCells;
  }

}
