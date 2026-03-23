public class HardDifficulty implements DifficultyStrategy {

    @Override
    public boolean shouldContinueTurn(int consecutiveSixes) {
        return consecutiveSixes < 3;
    }
}
