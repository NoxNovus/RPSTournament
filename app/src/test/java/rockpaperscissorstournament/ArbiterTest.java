package rockpaperscissorstournament;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArbiterTest {
    private static final String botName = "CountingRockBot";
    private static final String botDescr = "Rocks and Counts";

    private static int movesPlayed = 0;
    private static int feedbackReceived = 0;

    @Test
    void getNameCorrect() {
        Arbiter arbiter = new Arbiter(CountingRockBot.class);
        assertEquals(botName, arbiter.getName());
    }

    @Test
    void getDescrCorrect() {
        Arbiter arbiter = new Arbiter(CountingRockBot.class);
        assertEquals(botDescr, arbiter.getDescription());
    }

    @Test
    void getInstantiationCorrect() {
        Arbiter arbiter = new Arbiter(CountingRockBot.class);
        CountingRockBot bot = (CountingRockBot) arbiter.getNewBot();
        assertTrue(bot instanceof CountingRockBot);
    }

    @Test
    void actuallyPlaysGames() {
        Arbiter challenger = new Arbiter(CountingRockBot.class);
        Arbiter king = new Arbiter(CountingRockBot.class);

        int games = (Integer) accessField(Arbiter.class, "BEST_OF");
        ArbiterTest.movesPlayed = 0;
        ArbiterTest.feedbackReceived = 0;

        assertEquals(RPSOutcome.DRAW, challenger.playMatch(king));
        assertEquals(2 * games, movesPlayed);
        assertEquals(2 * games, feedbackReceived);
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

    public static class CountingRockBot implements RPSBot {
        public CountingRockBot() {
        }

        public String getName() {
            return botName;
        }

        public String getDescription() {
            return botDescr;
        }

        public RPSMove getMove() {
            ArbiterTest.movesPlayed++;
            return RPSMove.ROCK;
        }

        public void roundResults(RPSMove myMoveRaw, RPSMove opponentMoveRaw) {
            ArbiterTest.feedbackReceived++;
        }
    }
}
