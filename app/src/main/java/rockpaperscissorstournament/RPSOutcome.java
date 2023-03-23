package rockpaperscissorstournament;

public enum RPSOutcome {
    LOSE, DRAW, WIN;

    public static RPSOutcome getOutcome(RPSMove move1, RPSMove move2) {
        if (move1 == move2)
            return DRAW;

        switch (move1) {
            case ROCK:
                return (move2 == RPSMove.SCISSORS) ? WIN : LOSE;
            case PAPER:
                return (move2 == RPSMove.ROCK) ? WIN : LOSE;
            case SCISSORS:
                return (move2 == RPSMove.PAPER) ? WIN : LOSE;
        }
        return null;
    }
}