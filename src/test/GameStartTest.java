
import Controller.GameController;
import Model.Board;
import Model.Difficulty;
import Model.Game;

public class GameStartTest {

    public static void main(String[] args) {
        GameController controller = new GameController();

        // ----- 1) Start new game on EASY -----
        controller.startNewGame(Difficulty.EASY);
        Game game = controller.getCurrentGame();

        System.out.println("=== TEST: Start new EASY game ===");
        check("Game should not be null", game != null);
        check("Difficulty should be EASY", game.getDifficulty() == Difficulty.EASY);

        Board b1 = game.getBoard1();
        Board b2 = game.getBoard2();

        check("Board1 should not be null", b1 != null);
        check("Board2 should not be null", b2 != null);

        // Board size mapping
        check("Board1 rows == EASY rows", b1.getRows() == Difficulty.EASY.getRows());
        check("Board1 cols == EASY cols", b1.getCols() == Difficulty.EASY.getCols());
        check("Board2 rows == EASY rows", b2.getRows() == Difficulty.EASY.getRows());
        check("Board2 cols == EASY cols", b2.getCols() == Difficulty.EASY.getCols());

        // Mines / question / surprise mapping
        check("Board1 mines == EASY mines", b1.getTotalMines() == Difficulty.EASY.getMines());
        check("Board1 question cells == EASY question cells",
                b1.getTotalQuestionCells() == Difficulty.EASY.getQuestionCells());
        check("Board1 surprise cells == EASY surprise cells",
                b1.getTotalSurpriseCells() == Difficulty.EASY.getSurpriseCells());

        // Shared lives / score
        check("Shared lives == EASY startingLives",
                game.getSharedLives() == Difficulty.EASY.getStartingLives());
        check("Shared score starts at 0", game.getSharedScore() == 0);

        // Cells initialized
        check("Board1 first cell should not be null",
                b1.getCells() != null && b1.getCells()[0][0] != null);


        // ----- 2) Modify lives & score, then restart -----
        game.setSharedLives(game.getSharedLives() - 3);
        game.setSharedScore(25);

        Board oldBoard1 = game.getBoard1();
        Board oldBoard2 = game.getBoard2();

        controller.restartGame();
        Game restarted = controller.getCurrentGame();

        System.out.println("\n=== TEST: Restart game ===");
        check("Difficulty after restart still EASY",
                restarted.getDifficulty() == Difficulty.EASY);

        check("Shared lives reset to EASY startingLives",
                restarted.getSharedLives() == Difficulty.EASY.getStartingLives());
        check("Shared score reset to 0",
                restarted.getSharedScore() == 0);

        // Check that new boards were created
        check("Board1 should be a new instance after restart",
                restarted.getBoard1() != oldBoard1);
        check("Board2 should be a new instance after restart",
                restarted.getBoard2() != oldBoard2);
    }

    private static void check(String description, boolean condition) {
        if (condition) {
            System.out.println("[PASS] " + description);
        } else {
            System.out.println("[FAIL] " + description);
        }
    }
}
