package bots_1_trivial;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class PaperBot implements RPSBot {

    public String getName() {
        return "PaperBot";
    }

    public RPSMove getMove() {
        return RPSMove.PAPER;
    }

}
