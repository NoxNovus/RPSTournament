package rockpaperscissorstournament;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EloLibTest {
    @Test
    void testEloOnWin() {
        int k = (Integer) accessField(EloLib.class, "K");
        int elo = 1000;
        double eloChange = EloLib.calculateElo(elo, elo, RPSOutcome.WIN) - elo;
        assertEquals(k / 2, eloChange);
    }

    @Test
    void testEloOnLose() {
        int k = (Integer) accessField(EloLib.class, "K");
        int elo = 1000;
        double eloChange = EloLib.calculateElo(elo, elo, RPSOutcome.LOSE) - elo;
        assertEquals(-k / 2, eloChange);
    }

    @Test
    void testEloOnEqualDraw() {
        int elo = 1000;
        double eloChange = EloLib.calculateElo(elo, elo, RPSOutcome.DRAW) - elo;
        assertEquals(0, eloChange);
    }

    @Test
    void testEloOnUnequalDraw() {
        int k = (Integer) accessField(EloLib.class, "K");
        int elo = 1000;
        double eloChange = EloLib.calculateElo(elo, 10 * elo, RPSOutcome.DRAW) - elo;
        assertEquals(-k / 2, eloChange);
    }

    // Access private static field K
    private Object accessField(Class<?> clazz, String fieldName) {
        try {
            java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
