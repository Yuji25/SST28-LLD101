import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private List<Player> winners;
    private DifficultyStrategy difficultyStrategy;
    private Scanner sc;

    public Game(Board board, List<Player> playerList, DifficultyStrategy difficultyStrategy, Scanner sc) {
        this.board = board;
        this.dice = new Dice();
        this.players = new LinkedList<>(playerList);
        this.winners = new ArrayList<>();
        this.difficultyStrategy = difficultyStrategy;
        this.sc = sc;
    }

    public void start() {
        board.printBoard();
        System.out.println();
        printPositions();

        while (players.size() > 1) {
            Player current = players.poll();
            playTurn(current);

            if (current.getPosition() == board.getTotalCells()) {
                winners.add(current);
                System.out.println(">> " + current.getName() + " finished! (rank " + winners.size() + ")");
            } else {
                players.add(current);
            }
            System.out.println();
            printPositions();
        }

        Player lastPlayer = players.poll();
        winners.add(lastPlayer);
        System.out.println("\n--- Final Rankings ---");
        for (int i = 0; i < winners.size(); i++) {
            System.out.println((i + 1) + ". " + winners.get(i).getName());
        }
    }

    private void playTurn(Player player) {
        player.resetSixes();

        while (true) {
            System.out.print(player.getName() + "'s turn (pos " + player.getPosition() + ") — press Enter to roll... ");
            sc.nextLine();

            int rolled = dice.roll();
            System.out.println("  Rolled: " + rolled);

            if (rolled == 6) {
                player.incrementSixes();
            } else {
                player.resetSixes();
            }

            int newPos = player.getPosition() + rolled;

            if (newPos > board.getTotalCells()) {
                System.out.println("  Cannot move, stays at " + player.getPosition());
            } else {
                player.setPosition(newPos);

                if (board.hasSnakeAt(newPos)) {
                    int tail = board.applySnakeOrLadder(newPos);
                    System.out.println("  Oh no, snake! " + newPos + " -> " + tail);
                    player.setPosition(tail);
                } else if (board.hasLadderAt(newPos)) {
                    int end = board.applySnakeOrLadder(newPos);
                    System.out.println("  Ladder! " + newPos + " -> " + end);
                    player.setPosition(end);
                } else {
                    System.out.println("  Moved to " + player.getPosition());
                }
            }

            if (player.getPosition() == board.getTotalCells()) {
                break;
            }

            if (rolled != 6) {
                break;
            }

            if (!difficultyStrategy.shouldContinueTurn(player.getConsecutiveSixes())) {
                System.out.println("  Three 6s in a row — turn ends.");
                break;
            }

            System.out.println("  Rolled a 6 — roll again!");
        }
    }

    private void printPositions() {
        System.out.println("Positions:");
        for (Player p : players) {
            System.out.println("  " + p.getName() + ": " + p.getPosition());
        }
    }
}