package bots_2_classical_algorithms;

import java.util.Random;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;
import rockpaperscissorstournament.RPSOutcome;

/*
 * Inspired by this article: 
 * https://arstechnica.com/science/2014/05/win-at-rock-paper-scissors-by-knowing-thy-opponent/
 */
public class AntiWSLSBot implements RPSBot {
    private Random rand = new Random();
    private RPSMove nextMove = RPSMove.values()[rand.nextInt(3)];

    public String getName() {
        return "AntiWSLSBot";
    }

    public RPSMove getMove() {
        return nextMove;
    }

    @Override
    public void roundResults(RPSMove myMoveRaw, RPSMove opponentMoveRaw) {
        // get my move and outcome
        RPSOutcome outcome = RPSOutcome.getOutcome(myMoveRaw, opponentMoveRaw);

        // If you win, switch to the thing that would beat the thing that you just
        // played.
        if (outcome.equals(RPSOutcome.WIN)) {
            this.nextMove = RPSMove.counter(myMoveRaw);
        }

        // If you lose, switch to the thing that beats the thing your opponent just
        // played.
        else { // draw or loss
            this.nextMove = RPSMove.counter(opponentMoveRaw);
        }
    }
}
