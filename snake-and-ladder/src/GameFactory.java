import java.util.List;
import java.util.Scanner;

public class GameFactory {

    public static Game createGame(int n, List<Player> players, String difficulty, Scanner sc) {

        Board board = new Board(n);

        DifficultyStrategy strategy;

        if (difficulty.equalsIgnoreCase("hard")) {
            strategy = new HardDifficulty();
        } else {
            strategy = new EasyDifficulty();
        }

        return new Game(board, players, strategy, sc);
    }
}