import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private int totalCells;
    private Map<Integer, Integer> snakeMap;
    private Map<Integer, Integer> ladderMap;

    public Board(int n) {
        this.size = n;
        this.totalCells = n * n;
        this.snakeMap = new HashMap<>();
        this.ladderMap = new HashMap<>();
        placeSnakesAndLadders(n);
    }

    private void placeSnakesAndLadders(int count) {
        List<Integer> available = new ArrayList<>();
        for (int i = 2; i < totalCells; i++) {
            available.add(i);
        }
        Collections.shuffle(available);

        int index = 0;

        for (int i = 0; i < count; i++) {
            int head = available.get(index++);
            int tail;
            do {
                tail = (int)(Math.random() * (head - 1)) + 1;
            } while (snakeMap.containsValue(tail) || ladderMap.containsKey(tail) || ladderMap.containsValue(tail));
            snakeMap.put(head, tail);
        }

        for (int i = 0; i < count; i++) {
            int start = available.get(index++);
            int end;
            do {
                end = start + (int)(Math.random() * (totalCells - start)) + 1;
            } while (end > totalCells || ladderMap.containsValue(end) || snakeMap.containsKey(end) || snakeMap.containsValue(end));
            ladderMap.put(start, end);
        }
    }

    public int applySnakeOrLadder(int position) {
        if (snakeMap.containsKey(position)) {
            return snakeMap.get(position);
        }
        if (ladderMap.containsKey(position)) {
            return ladderMap.get(position);
        }
        return position;
    }

    public boolean hasSnakeAt(int position)  { return snakeMap.containsKey(position); }
    public boolean hasLadderAt(int position) { return ladderMap.containsKey(position); }

    public int getTotalCells() { return totalCells; }

    public void printBoard() {
        System.out.println("Snakes (head -> tail):");
        for (Map.Entry<Integer, Integer> e : snakeMap.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
        System.out.println("Ladders (start -> end):");
        for (Map.Entry<Integer, Integer> e : ladderMap.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
    }
}
