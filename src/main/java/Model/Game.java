package Model;

public class Game {

    private Board board1;
    private Board board2;
    private Difficulty difficulty;
    private int sharedLives;
    private int sharedScore;

    // NEW: Track the game state (NOT_STARTED, RUNNING, WON, LOST)
    private GameState gameState;

    public Game(Difficulty difficulty) {
        // Initialize game immediately
        startNewGame(difficulty);
    }

    /**
     * Starts a new cooperative game.
     * Resets score, lives, and sets state to RUNNING.
     */
    public void startNewGame(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.sharedLives = difficulty.getStartingLives();
        this.sharedScore = 0;
        this.gameState = GameState.RUNNING; // Set initial state

        // Create boards and pass 'this' Game instance to them
        this.board1 = new Board(difficulty, this);
        this.board2 = new Board(difficulty, this);
    }

    public void restartGame() {
        if (this.difficulty != null) {
            startNewGame(this.difficulty);
        }
    }

    /**
     * Checks if the game is Won or Lost based on SRS rules.
     * Called by Board whenever a cell is revealed or lives change.
     */
    public void checkGameStatus() {
        // If game is already over, do nothing
        if (gameState != GameState.RUNNING) return;

        // 1. Check Lose Condition: Shared lives reach 0 [cite: 97, 103]
        if (sharedLives <= 0) {
            gameState = GameState.LOST;
            printGameStatus();
            return;
        }

        // 2. Check Win Condition: All safe cells revealed on BOTH boards [cite: 97, 103]
        if (board1.getSafeCellsRemaining() == 0 && board2.getSafeCellsRemaining() == 0) {
            gameState = GameState.WON;
            printGameStatus();
        }
    }

    /**
     * Prints the game status to the console (Iteration 1 Requirement).
     */
    public void printGameStatus() {
        System.out.println("=== GAME STATUS UPDATE ===");
        System.out.println("State: " + gameState);
        System.out.println("Lives: " + sharedLives);
        System.out.println("Score: " + sharedScore);
        System.out.println("Board 1 Safe Cells Left: " + board1.getSafeCellsRemaining());
        System.out.println("Board 2 Safe Cells Left: " + board2.getSafeCellsRemaining());

        if (gameState == GameState.WON) {
            System.out.println("RESULT: VICTORY! The team cleared all mines.");
        } else if (gameState == GameState.LOST) {
            System.out.println("RESULT: GAME OVER. The team ran out of lives.");
        }
        System.out.println("==========================");
    }

    // --- Getters and Setters ---

    public void setSharedLives(int sharedLives) {
        this.sharedLives = sharedLives;
        // Check status immediately when lives change (e.g., hitting a mine)
        checkGameStatus();
    }

    public void setSharedScore(int sharedScore) {
        this.sharedScore = sharedScore;
    }

    /**
     * Activates a special cell (question or surprise).
     * This method handles the special effects of question and surprise cells.
     * It deducts the activation cost from the shared score.
     * 
     * @param cellContent the type of special cell (QUESTION or SURPRISE)
     * @param questionId optional question identifier (for QUESTION cells)
     */
    public void activateSpecialCell(Cell.CellContent cellContent, Integer questionId) {
        if (cellContent != Cell.CellContent.QUESTION && cellContent != Cell.CellContent.SURPRISE) {
            return;
        }

        int cost = difficulty.getActivationCost();
        
        // Check if player has enough score to activate
        if (sharedScore >= cost) {
            sharedScore -= cost;
            
            // Here you can add additional logic for:
            // - Question cells: display question, check answer, reward/penalty
            // - Surprise cells: random positive/negative effects
            // For now, we just deduct the cost
        }
        // If not enough score, the activation fails (cell is still marked as used)
        // This prevents players from trying again without penalty
    }
}
