package rockpaperscissorstournament;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RPSOutcomeTest {
    @Test
    void testGetOpposite() {
        assertEquals(RPSOutcome.DRAW, RPSOutcome.DRAW.getOpposite());
        assertEquals(RPSOutcome.WIN, RPSOutcome.LOSE.getOpposite());
        assertEquals(RPSOutcome.LOSE, RPSOutcome.WIN.getOpposite());
    }

    @Test
    void testGetOutcome() {
        assertEquals(RPSOutcome.DRAW, RPSOutcome.getOutcome(RPSMove.ROCK, RPSMove.ROCK));
        assertEquals(RPSOutcome.DRAW, RPSOutcome.getOutcome(RPSMove.PAPER, RPSMove.PAPER));
        assertEquals(RPSOutcome.DRAW, RPSOutcome.getOutcome(RPSMove.SCISSORS, RPSMove.SCISSORS));

        assertEquals(RPSOutcome.WIN, RPSOutcome.getOutcome(RPSMove.ROCK, RPSMove.SCISSORS));
        assertEquals(RPSOutcome.WIN, RPSOutcome.getOutcome(RPSMove.PAPER, RPSMove.ROCK));
        assertEquals(RPSOutcome.WIN, RPSOutcome.getOutcome(RPSMove.SCISSORS, RPSMove.PAPER));

        assertEquals(RPSOutcome.LOSE, RPSOutcome.getOutcome(RPSMove.ROCK, RPSMove.PAPER));
        assertEquals(RPSOutcome.LOSE, RPSOutcome.getOutcome(RPSMove.PAPER, RPSMove.SCISSORS));
        assertEquals(RPSOutcome.LOSE, RPSOutcome.getOutcome(RPSMove.SCISSORS, RPSMove.ROCK));
    }
}