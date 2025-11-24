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

    /**
     * Reveals a cell at the given position and recursively reveals adjacent empty cells
     * if the revealed cell is empty with no adjacent mines.
     *
     * @param row The row index of the cell to reveal
     * @param col The column index of the cell to reveal
     * @return true if the cell was successfully revealed, false otherwise
     */
    public boolean revealCell(int row, int col) {
        // Check if coordinates are valid
        if (!isValidPosition(row, col)) {
            return false;
        }

        Cell cell = cells[row][col];

        // Don't reveal if already revealed or flagged
        if (cell.getState() == Cell.CellState.REVEALED ||
                cell.getState() == Cell.CellState.FLAGGED) {
            return false;
        }

        // Reveal the cell
        cell.setState(Cell.CellState.REVEALED);

        // If cell is empty and has no adjacent mines, recursively reveal adjacent cells
        if (cell.getContent() == Cell.CellContent.EMPTY &&
                countAdjacentMines(row, col) == 0) {
            revealAdjacentCells(row, col);
        }

        return true;
    }

    /**
     * Recursively reveals all adjacent cells for an empty cell with no mines.
     * This method expands the empty chain until it hits cells with numbers or mines.
     *
     * @param row The row index of the center cell
     * @param col The column index of the center cell
     */
    private void revealAdjacentCells(int row, int col) {
        // Check all 8 adjacent cells (including diagonals)
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                // Skip the center cell itself
                if (dr == 0 && dc == 0) {
                    continue;
                }

                int newRow = row + dr;
                int newCol = col + dc;

                // Check if position is valid
                if (!isValidPosition(newRow, newCol)) {
                    continue;
                }

                Cell adjacentCell = cells[newRow][newCol];

                // Skip if already revealed or flagged
                if (adjacentCell.getState() == Cell.CellState.REVEALED ||
                        adjacentCell.getState() == Cell.CellState.FLAGGED) {
                    continue;
                }

                // Reveal the adjacent cell
                adjacentCell.setState(Cell.CellState.REVEALED);

                // If the adjacent cell is also empty with no mines, recursively reveal its neighbors
                if (adjacentCell.getContent() == Cell.CellContent.EMPTY &&
                        countAdjacentMines(newRow, newCol) == 0) {
                    revealAdjacentCells(newRow, newCol);
                }
            }
        }
    }

    /**
     * Counts the number of mines adjacent to the cell at the given position.
     *
     * @param row The row index of the cell
     * @param col The column index of the cell
     * @return The number of adjacent mines
     */
    public int countAdjacentMines(int row, int col) {
        int count = 0;

        // Check all 8 adjacent cells (including diagonals)
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                // Skip the center cell itself
                if (dr == 0 && dc == 0) {
                    continue;
                }

                int newRow = row + dr;
                int newCol = col + dc;

                // Check if position is valid and contains a mine
                if (isValidPosition(newRow, newCol) &&
                        cells[newRow][newCol].getContent() == Cell.CellContent.MINE) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Checks if the given row and column indices are valid for this board.
     *
     * @param row The row index to check
     * @param col The column index to check
     * @return true if the position is valid, false otherwise
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

}
