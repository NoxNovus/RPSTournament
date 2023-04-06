package rockpaperscissorstournament;

/**
 * Represents a move in Rock Paper Scissors.
 * The move is immutable.
 */
public enum RPSMove {
    ROCK("Rock"),
    PAPER("Paper"),
    SCISSORS("Scissors");

    private String name;

    RPSMove(String name) {
        this.name = name;
    }

    /**
     * @return the name of the move
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the move that corresponds to the given name.
     * 
     * @param name the name of the move
     * @return the move that corresponds to the given name, or null if no such move
     *         exists
     */
    public static RPSMove fromString(String name) {
        for (RPSMove move : RPSMove.values()) {
            if (move.getName().equals(name)) {
                return move;
            }
        }
        return null;
    }

    /**
     * Returns the winner of the given moves.
     * 
     * @param move1 the first move
     * @param move2 the second move
     * @return the winner of the given moves, or null if there is no winner
     */
    public static RPSMove getWinner(RPSMove move1, RPSMove move2) {
        if (move1 == move2) {
            return null;
        }
        switch (move1) {
            case ROCK:
                return (move2 == SCISSORS) ? move1 : move2;
            case PAPER:
                return (move2 == ROCK) ? move1 : move2;
            case SCISSORS:
                return (move2 == PAPER) ? move1 : move2;
        }
        return null;
    }

    /**
     * Returns the counter of this move, that is to say,
     * the move that beats this one.
     * 
     * @param move the move to counter
     * @return the move that counters the specified one
     */
    public static RPSMove counter(RPSMove move) {
        switch (move) {
            case ROCK:
                return PAPER;
            case PAPER:
                return SCISSORS;
            case SCISSORS:
                return ROCK;
        }
        return null;
    }

    /**
     * Returns true if this move won against the given move.
     * 
     * @param move the move to check
     * @return true if this move won against the given move, false if it was a tie
     *         or lost
     */
    public boolean winsAgainst(RPSMove move) {
        return getWinner(this, move) == this;
    }

    /**
     * @return Returns the move that beats this one.
     */
    public RPSMove weakness() {
        return counter(this);
    }

    /**
     * @return Returns the move that loses against this one.
     */
    public RPSMove strength() {
        return counter(counter(this));
    }
}
