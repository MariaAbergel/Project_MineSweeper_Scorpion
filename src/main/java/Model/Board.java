package Model;

/**
 * Represents a single board for one player.
 * The board knows:
 * - its size (rows, cols)
 * - how many mines/question/surprise cells it should contain
 * - a 2D array of Cell objects
 */
public class Board {

    private final int rows;
    private final int cols;
    private final int totalMines;
    private final int totalQuestionCells;
    private final int totalSurpriseCells;

    private final Cell[][] cells;

    public Board(Difficulty difficulty) {
        // Configure board size from difficulty
        this.rows = difficulty.getRows();
        this.cols = difficulty.getCols();

        // Configure how many special cells are required
        this.totalMines = difficulty.getMines();
        this.totalQuestionCells = difficulty.getQuestionCells();
        this.totalSurpriseCells = difficulty.getSurpriseCells();

        // Allocate the cells matrix
        this.cells = new Cell[rows][cols];
        //Initialize each cell as an empty, hidden cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public int getTotalQuestionCells() {
        return totalQuestionCells;
    }

    public int getTotalSurpriseCells() {
        return totalSurpriseCells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

}
