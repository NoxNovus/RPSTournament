package bots_1_trivial;

import java.util.Random;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class RandomBot implements RPSBot {
    private Random rand = new Random();

    public String getName() {
        return "RandomBot";
    }

    public RPSMove getMove() {
        return RPSMove.values()[rand.nextInt(3)];
    }
}
