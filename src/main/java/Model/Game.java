package Model;

/**
 * Represents a cooperative game session.
 * A Game holds:
 * - two boards (one for each player)
 * - the selected difficulty
 * - shared lives and shared score for the team
 */
public class Game {

    private Board board1;
    private Board board2;
    private Difficulty difficulty;
    private int sharedLives;
    private int sharedScore;

    public Game(Difficulty difficulty) {
        // When a Game is created we immediately start a new session
        startNewGame(difficulty);
    }

    /**
     * Starts a new cooperative game with the given difficulty.
     * - stores the difficulty
     * - initializes shared lives from Difficulty
     * - resets shared score to 0
     * - creates two fresh boards according to the difficulty
     */
    public void startNewGame(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.sharedLives = difficulty.getStartingLives();
        this.sharedScore = 0;

        this.board1 = new Board(difficulty);
        this.board2 = new Board(difficulty);
        // Mine / question / surprise placement will be done later by Dev 2
    }

    /**
     * Restarts the game using the same difficulty as the current game.
     * If no difficulty was set yet, this method does nothing.
     */
    public void restartGame() {
        if (this.difficulty != null) {
            startNewGame(this.difficulty);
        }
    }

    public Board getBoard1() {
        return board1;
    }

    public Board getBoard2() {
        return board2;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getSharedLives() {
        return sharedLives;
    }

    public int getSharedScore() {
        return sharedScore;
    }

    public void setSharedLives(int sharedLives) {
        this.sharedLives = sharedLives;
    }

    public void setSharedScore(int sharedScore) {
        this.sharedScore = sharedScore;
    }
}
