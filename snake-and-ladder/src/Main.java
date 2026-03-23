import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Board size (n): ");
        int n = sc.nextInt();

        System.out.print("Number of players: ");
        int x = sc.nextInt();

        System.out.print("Difficulty (easy/hard): ");
        String diffInput = sc.next().trim().toLowerCase();

        Difficulty difficulty = diffInput.equals("hard") ? Difficulty.HARD : Difficulty.EASY;
        DifficultyStrategy strategy = difficulty == Difficulty.HARD ? new HardDifficulty() : new EasyDifficulty();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            System.out.print("Player " + i + " name: ");
            String name = sc.next();
            players.add(new Player(name));
        }

        sc.nextLine();

        Board board = new Board(n);
        Game game = new Game(board, players, strategy, sc);

        System.out.println("\n=== Snake and Ladder (" + difficulty + ") | Board: " + n + "x" + n + " ===\n");
        game.start();

        sc.close();
    }
}