package basicbots;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class LongCycleBot implements RPSBot {
    public static final int CYCLE_LENGTH = 100;

    private int current = 0;
    private int cycle = 0;

    public String getName() {
        return "LongCycleBot";
    }

    public RPSMove getMove() {
        cycle++;
        if (cycle > CYCLE_LENGTH) {
            cycle = 0;
            current = (current + 1) % 3;
        }
        return RPSMove.values()[current];
    }
}
