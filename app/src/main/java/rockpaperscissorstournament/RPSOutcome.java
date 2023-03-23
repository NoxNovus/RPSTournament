package rockpaperscissorstournament;

public enum RPSOutcome {
    LOSE, DRAW, WIN;

    /**
     * Computes the opposite of this outcome. Draws are by
     * definition their own opposite, while wins and losses
     * are opposites.
     * 
     * @return an RPSOutcome corresponding to the opposite outcome
     * of this one.
     */
    public RPSOutcome getOpposite() {
        switch (this) {
            case WIN:
                return LOSE;
            case LOSE:
                return WIN;
            case DRAW:
                return DRAW;
        }
        return null;
    }

    /**
     * Computes the outcome of a matchup of two RPS moves played by
     * bot 1 and bot 2 respectively from bot 1's perspective (first argument). 
     * Flip arguments if the corresponding outcome from bot 2 is desired.
     * 
     * @param move1 The move bot 1 played.
     * @param move2 The move bot 2 played.
     * @return an RPSOutcome corresponding to the outcome of the play
     * from bot 1's perspective.
     */
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
