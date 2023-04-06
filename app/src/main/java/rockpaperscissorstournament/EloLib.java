package rockpaperscissorstournament;

public final class EloLib {

    // this is the K-Factor, a constant that determines variance for elo
    // it is the maximum change in elo per match
    // do not set this too high or too low
    // (most tournaments use values like 24 or 32)
    private static final int K = 32;

    public static double calculateElo(double myElo, double oppElo, RPSOutcome matchResult) {
        double myTransformed = Math.pow(10, (myElo / 400));
        double oppTransformed = Math.pow(10, (oppElo / 400));
        double transformedSkillDiff = myTransformed + oppTransformed;

        double myExpectation = (myTransformed) / (transformedSkillDiff);

        //result of the game as 0 for loss, 0.5 for draw, 1 for win
        double result = ((double) matchResult.ordinal() / 2);
        return myElo + (K * (result - myExpectation));
    }

    private EloLib() {
    }

    public static void main(String[] args) {
        System.out.println(calculateElo(2400, 2000, RPSOutcome.WIN));
    }
}
