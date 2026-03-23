public class Player {
    private String name;
    private int position;
    private int consecutiveSixes;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.consecutiveSixes = 0;
    }

    public String getName()             { return name; }
    public int getPosition()            { return position; }
    public void setPosition(int pos)    { this.position = pos; }
    public int getConsecutiveSixes()    { return consecutiveSixes; }
    public void incrementSixes()        { consecutiveSixes++; }
    public void resetSixes()            { consecutiveSixes = 0; }
}
