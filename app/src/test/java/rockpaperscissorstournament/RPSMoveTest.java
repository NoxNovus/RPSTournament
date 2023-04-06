package rockpaperscissorstournament;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RPSMoveTest {
    @Test
    void testGetWinner() {
        assertEquals(RPSMove.ROCK, RPSMove.getWinner(RPSMove.ROCK, RPSMove.SCISSORS));
        assertEquals(RPSMove.PAPER, RPSMove.getWinner(RPSMove.PAPER, RPSMove.ROCK));
        assertEquals(RPSMove.SCISSORS, RPSMove.getWinner(RPSMove.SCISSORS, RPSMove.PAPER));
        assertNull(RPSMove.getWinner(RPSMove.ROCK, RPSMove.ROCK));
        assertNull(RPSMove.getWinner(RPSMove.PAPER, RPSMove.PAPER));
        assertNull(RPSMove.getWinner(RPSMove.SCISSORS, RPSMove.SCISSORS));
    }

    @Test
    void testWinsAgainst() {
        assertTrue(RPSMove.ROCK.winsAgainst(RPSMove.SCISSORS));
        assertTrue(RPSMove.PAPER.winsAgainst(RPSMove.ROCK));
        assertTrue(RPSMove.SCISSORS.winsAgainst(RPSMove.PAPER));

        assertFalse(RPSMove.ROCK.winsAgainst(RPSMove.ROCK));
        assertFalse(RPSMove.PAPER.winsAgainst(RPSMove.PAPER));
        assertFalse(RPSMove.SCISSORS.winsAgainst(RPSMove.SCISSORS));

        assertFalse(RPSMove.ROCK.winsAgainst(RPSMove.PAPER));
        assertFalse(RPSMove.PAPER.winsAgainst(RPSMove.SCISSORS));
        assertFalse(RPSMove.SCISSORS.winsAgainst(RPSMove.ROCK));
    }

    @Test
    void testWeakness() {
        assertEquals(RPSMove.PAPER, RPSMove.ROCK.weakness());
        assertEquals(RPSMove.SCISSORS, RPSMove.PAPER.weakness());
        assertEquals(RPSMove.ROCK, RPSMove.SCISSORS.weakness());
    }

    @Test
    void testStrength() {
        assertEquals(RPSMove.SCISSORS, RPSMove.ROCK.strength());
        assertEquals(RPSMove.ROCK, RPSMove.PAPER.strength());
        assertEquals(RPSMove.PAPER, RPSMove.SCISSORS.strength());
    }
}
