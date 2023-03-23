package basicbots;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class LongCycleBot implements RPSBot {
    private int current = 0;
    private int cycle = 0;

    public String getName() {
        return "LongCycleBot";
    }

    public RPSMove getMove() {
        cycle++;
        if(cycle > 100) {
            cycle = 0;
            current = (current + 1) % 3;
        }
        return RPSMove.values()[current];
    }
}
