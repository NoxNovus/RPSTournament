package bots_1_trivial;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class RockBot implements RPSBot {

    public String getName() {
        return "RockBot";
    }

    public RPSMove getMove() {
        return RPSMove.ROCK;
    }

}
