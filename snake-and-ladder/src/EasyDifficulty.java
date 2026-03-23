public class EasyDifficulty implements DifficultyStrategy {

    @Override
    public boolean shouldContinueTurn(int consecutiveSixes) {
        return true;
    }
}
