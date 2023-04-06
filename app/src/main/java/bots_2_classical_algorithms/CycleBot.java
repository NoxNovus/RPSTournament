package bots_2_classical_algorithms;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class CycleBot implements RPSBot {
    private int current = 0;

    public String getName() {
        return "CycleBot";
    }

    public RPSMove getMove() {
        current = (current + 1) % 3;
        return RPSMove.values()[current];
    }
}
