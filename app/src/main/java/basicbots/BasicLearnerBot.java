package basicbots;

import java.util.Random;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class BasicLearnerBot implements RPSBot {
    private Random rand = new Random();
    
    int current = 0;

    public String getName() {
        return "BasicLearnerBot";
    }

    public RPSMove getMove() {
        current = (current + 1) % 3;
        return RPSMove.values()[current];
    }
}
