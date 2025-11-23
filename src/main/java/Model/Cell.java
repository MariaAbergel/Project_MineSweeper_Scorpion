package Model;

public class Cell {

    public enum CellContent { EMPTY, MINE, QUESTION, SURPRISE, NUMBER }
    public enum CellState { HIDDEN, REVEALED, FLAGGED }

    private final int row;
    private final int col;
    private CellContent content;
    private CellState state;
    private int adjacentMines;
    private boolean used;
    private Integer questionId;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = CellContent.EMPTY;
        this.state = CellState.HIDDEN;
        this.adjacentMines = 0;
        this.used = false;
        this.questionId = null;
    }

    // --- CRITICAL FIXES: Getters and Setters needed by Board.java ---

    // REQUIRED by Board.placeContent, Board.calculateNumbers, GameStartTest.findCellWithContent
    public CellContent getContent() {
        return content;
    }

    // REQUIRED by Board.placeContent, Board.calculateNumbers
    public void setContent(CellContent content) {
        this.content = content;
    }

    // REQUIRED by Board.calculateNumbers
    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    // ... (other getters/setters for state, used, questionId)

    // --- HELPER METHODS FOR LOGIC (From your provided snippet) ---

    public boolean isMine() {
        return content == CellContent.MINE;
    }

    public boolean isRevealed() {
        return state == CellState.REVEALED;
    }

    public boolean isFlagged() {
        return state == CellState.FLAGGED;
    }

    public boolean isQuestionOrSurprise() {
        return content == CellContent.QUESTION || content == CellContent.SURPRISE;
    }

    /**
     * Toggles flag state. Returns true if the cell is now FLAGGED, false if HIDDEN.
     * Only works if cell is not REVEALED.
     */
    public boolean toggleFlag() {
        if (state == CellState.REVEALED) return false;

        if (state == CellState.FLAGGED) {
            state = CellState.HIDDEN;
            return false;
        } else {
            state = CellState.FLAGGED;
            return true;
        }
    }

    /**
     * Marks the cell as REVEALED.
     */
    public void reveal() {
        this.state = CellState.REVEALED;
    }
}