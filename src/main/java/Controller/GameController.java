package Controller;

import Model.Difficulty;
import Model.Game;

/**
 * Controller class between the UI (View) and the Game model.
 */
public class GameController {

    private Game currentGame;

    /**
     * Creates a new Game instance with the selected difficulty.
     * This is the main entry point for starting a cooperative game.
     */
    public void startNewGame(Difficulty difficulty) {
        currentGame = new Game(difficulty);
    }

    /**
     * Restarts the current game using the same difficulty.
     * If no game exists yet, nothing happens.
     */
    public void restartGame() {
        if (currentGame != null) {
            currentGame.restartGame();
        }
    }

    /**
     * Returns the current Game instance so that other parts
     * of the system (e.g., GUI) can read boards, lives, score, etc.
     */
    public Game getCurrentGame() {
        return currentGame;
    }
}
