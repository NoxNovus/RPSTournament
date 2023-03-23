package basicbots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class BasicLearnerBot implements RPSBot {
    private Random rand = new Random();
    private List<Double> winRates = Arrays.asList(0.0, 0.0, 0.0);

    public String getName() {
        return "BasicLearnerBot";
    }

    public RPSMove getMove() {
        //TODO: update probabilities
        //currently only playing rock

        double maxWinRate = Collections.max(winRates);
        return RPSMove.values()[winRates.indexOf(maxWinRate)];
    }
}
