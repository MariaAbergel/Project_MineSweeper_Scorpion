package Model;

/**
 * Represents a single cell in the game board.
 * A cell has:
 * - a position on the board (row, col)
 * - content type (empty, mine, question, surprise, number)
 * - current visible state (hidden, revealed, flagged)
 * - number of adjacent mines (for NUMBER cells)
 * - a flag indicating if it was already used (for question/surprise)
 * - an optional questionId for linking to questions data
 */
public class Cell {

    /**
     * Type of content stored in the cell.
     */
    public enum CellContent {
        EMPTY,
        MINE,
        QUESTION,
        SURPRISE,
        NUMBER
    }

    /**
     * Visible state of the cell in the board.
     */
    public enum CellState {
        HIDDEN,
        REVEALED,
        FLAGGED
    }

    // Position of the cell inside the board matrix
    private final int row;
    private final int col;

    // Logical content of the cell
    private CellContent content;

    // How the cell is currently shown to the player
    private CellState state;

    // Number of mines around this cell (used for NUMBER cells)
    private int adjacentMines;

    // Indicates if a question/surprise cell was already activated
    private boolean used;

    // Identifier of the related question (for question cells)
    private Integer questionId;

    /**
     * Creates a new cell at the given board coordinates.
     * By default, the cell is empty and hidden.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = CellContent.EMPTY;
        this.state = CellState.HIDDEN;
        this.adjacentMines = 0;
        this.used = false;
        this.questionId = null;
    }

    // -------- Position getters --------

    /**
     * Returns the row index of the cell.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of the cell.
     */
    public int getCol() {
        return col;
    }

    // -------- Content & state getters --------

    /**
     * Returns the logical content of the cell.
     */
    public CellContent getContent() {
        return content;
    }

    /**
     * Returns the current visible state of the cell.
     */
    public CellState getState() {
        return state;
    }

    /**
     * Returns the number of mines adjacent to this cell.
     */
    public int getAdjacentMines() {
        return adjacentMines;
    }

    /**
     * Returns true if this cell was already used (for question/surprise).
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Returns the question identifier linked to this cell.
     */
    public Integer getQuestionId() {
        return questionId;
    }

    // -------- Content & state setters --------

    /**
     * Sets the content type of the cell.
     */
    public void setContent(CellContent content) {
        this.content = content;
    }

    /**
     * Sets the visible state of the cell.
     */
    public void setState(CellState state) {
        this.state = state;
    }

    /**
     * Sets the number of adjacent mines.
     */
    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    /**
     * Sets the question identifier linked to this cell.
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * Marks the cell as used.
     */
    public void setUsed(boolean used) {
        this.used = used;
    }
}
