package bots_1_trivial;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class ScissorsBot implements RPSBot {

    public String getName() {
        return "ScissorsBot";
    }

    public RPSMove getMove() {
        return RPSMove.SCISSORS;
    }

}
