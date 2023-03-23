package basicbots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;
import rockpaperscissorstournament.RPSOutcome;

public class BasicLearnerBot implements RPSBot {
    private Random rand = new Random();
    
    // all these lists' values are stored in order rock, paper, scissors
    private List<Double> winRates = Arrays.asList(0.0, 0.0, 0.0); // this is a fixed-size list
    private double[] winCounts = {0.0, 0.0, 0.0};
    private double[] totalCounts = {0.0, 0.0, 0.0};

    public String getName() {
        return "BasicLearnerBot";
    }

    public RPSMove getMove() {
        double maxWinRate = Collections.max(winRates);
        System.out.println("Current win rates: " + winRates);
        return RPSMove.values()[winRates.indexOf(maxWinRate)];
    }

    @Override
    public void roundResults(RPSMove myMoveRaw, RPSMove opponentMoveRaw) {
        // get my move and outcome
        int myMove = myMoveRaw.ordinal();
        RPSOutcome outcome = RPSOutcome.getOutcome(myMoveRaw, opponentMoveRaw);

        // count played increments
        totalCounts[myMove]++;

        // if won, count won increments
        if(outcome == RPSOutcome.WIN) {
            winCounts[myMove]++;
        }

        // recompute values
        for(int i = 0; i < winRates.size(); i++) {
            double newWinRate = winCounts[i] / totalCounts[i];
            winRates.set(i, newWinRate);
        }
    } 
}
