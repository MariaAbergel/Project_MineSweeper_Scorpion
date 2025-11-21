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

    // --- NEW HELPER METHODS FOR LOGIC ---

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
        if (state == CellState.REVEALED) return false; // Cannot flag revealed cells

        if (state == CellState.FLAGGED) {
            state = CellState.HIDDEN;
            return false; // Flag removed
        } else {
            state = CellState.FLAGGED;
            return true; // Flag added
        }
    }

    /**
     * Marks the cell as REVEALED.
     */
    public void reveal() {
        this.state = CellState.REVEALED;
    }

    // ... [Keep your existing Getters and Setters] ...
}