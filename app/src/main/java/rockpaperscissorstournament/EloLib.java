package rockpaperscissorstournament;

public final class EloLib {
    public static int calculateElo(int myElo, int oppElo, RPSOutcome matchResult) {
        // super WIP (this is obviously not how elo works)
        return 10 * (matchResult.ordinal() - 1) + myElo;
    }

    private EloLib() {
    }
}
