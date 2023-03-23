package basicbots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;
import rockpaperscissorstournament.RPSOutcome;

/*
 * Inspired by this article: 
 * https://arstechnica.com/science/2014/05/win-at-rock-paper-scissors-by-knowing-thy-opponent/
 */
public class WSLSBot implements RPSBot {
    private Random rand = new Random();
    private RPSMove nextMove = RPSMove.values()[rand.nextInt(3)];

    public String getName() {
        return "WSLSBot";
    }

    public RPSMove getMove() {
        return nextMove;
    }

    @Override
    public void roundResults(RPSMove myMoveRaw, RPSMove opponentMoveRaw) {
        // get outcome
        RPSOutcome outcome = RPSOutcome.getOutcome(myMoveRaw, opponentMoveRaw);

        // win stay
        if(outcome.equals(RPSOutcome.WIN)) {
            this.nextMove = myMoveRaw;
        } else { // draw or loss, shift to beat opp's last throw
            this.nextMove = RPSMove.counter(opponentMoveRaw);
        }
    } 
}
