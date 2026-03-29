import java.util.*;

public class Board {
    private int size;
    private int totalCells;

    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(int n) {
        this.size = n;
        this.totalCells = n * n;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();

        placeSnakesAndLadders(n);
    }

    private void placeSnakesAndLadders(int count) {
        Random rand = new Random();

        int firstRowEnd = size; // 1 → size
        int lastRowStart = totalCells - size + 1; // last row start

        Set<Integer> used = new HashSet<>();

        for (int i = 0; i < count; i++) {
            int head, tail;

            do {
                head = rand.nextInt(totalCells - firstRowEnd) + firstRowEnd + 1;
            } while (used.contains(head));

            int headRow = (head - 1) / size;

            do {
                tail = rand.nextInt(head - 1) + 1;
            } while (used.contains(tail) ||
                    ((tail - 1) / size == headRow) // same row restriction
            );

            snakes.add(new Snake(head, tail));
            used.add(head);
            used.add(tail);
        }

        for (int i = 0; i < count; i++) {
            int start, end;

            do {
                start = rand.nextInt(lastRowStart - 1) + 1;
            } while (used.contains(start));

            int startRow = (start - 1) / size;

            do {
                end = rand.nextInt(totalCells - start) + start + 1;
            } while (used.contains(end) ||
                    ((end - 1) / size == startRow) // same row restriction
            );

            ladders.add(new Ladder(start, end));
            used.add(start);
            used.add(end);
        }

    }

    public int applySnakeOrLadder(int position) {
        for (Snake s : snakes) {
            if (s.getHead() == position) {
                return s.getTail();
            }
        }
        for (Ladder l : ladders) {
            if (l.getStart() == position) {
                return l.getEnd();
            }
        }
        return position;
    }

    public boolean hasSnakeAt(int position) {
        for (Snake s : snakes) {
            if (s.getHead() == position)
                return true;
        }
        return false;
    }

    public boolean hasLadderAt(int position) {
        for (Ladder l : ladders) {
            if (l.getStart() == position)
                return true;
        }
        return false;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public void printBoard() {
        System.out.println("Snakes (head -> tail):");
        for (Snake s : snakes) {
            System.out.println("  " + s.getHead() + " -> " + s.getTail());
        }

        System.out.println("Ladders (start -> end):");
        for (Ladder l : ladders) {
            System.out.println("  " + l.getStart() + " -> " + l.getEnd());
        }
    }
}