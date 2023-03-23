package rockpaperscissorstournament;

public final class EloLib {

    // this is the K-Factor, a constant that determines variance for elo
    // it is the maximum change in elo per match
    // do not set this too high or too low (most tournaments use values like 24 or 32)
    private static final int K = 32;

    public static double calculateElo(double myElo, double oppElo, RPSOutcome matchResult) {
        double mySkillDiff = Math.pow(10, ((myElo - oppElo) / 400));
        double oppSkillDiff = Math.pow(10, ((oppElo - myElo) / 400));

        double myWinChance = (1.0 / (1.0 + mySkillDiff));
        double oppWinChance = (1.0 / (1.0 + oppSkillDiff));

        // result of the game as 0 for loss, 0.5 for draw, 1 for win
        double result = ((double) matchResult.ordinal() / 2);

        double delta = K * (result - myWinChance);

        return myElo + delta;
    }

    private EloLib() {
    }
}
