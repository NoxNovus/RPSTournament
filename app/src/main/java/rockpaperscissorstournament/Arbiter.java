package rockpaperscissorstournament;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Arbiter class is responsible for a single bot type.
 * 
 * It wraps RPSBot instances, protecting information such as
 * win/loss records, elo, and history.
 * 
 * It also "arbitrates"/plays a series of matches between bots of that type
 * and another Arbiter
 */
public class Arbiter {
    private static final int BEST_OF = 500_000;

    public static Set<Arbiter> loadAllArbitrators() {
        Set<Class<RPSBot>> allBots = ClassLoader.allImplementers(RPSBot.class);
        // Do not load subclass bots
        // Bandaid fix for bigger problem
        // Could probably be solved with an annotation
        return allBots.stream()
                .filter(c -> c.getEnclosingClass() == null)
                .map(Arbiter::new).collect(Collectors.toSet());
    }

    private Class<? extends RPSBot> botClass;
    private String name;
    private String description;

    public Arbiter(Class<? extends RPSBot> botClass) {
        this.botClass = botClass;

        // Create a bot that plays no games,
        // immediately extract all it's info
        RPSBot tortureVictim = getNewBot();
        this.name = tortureVictim.getName();
        this.description = tortureVictim.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RPSBot getNewBot() {
        try {
            java.lang.reflect.Constructor<?> ctor = botClass.getConstructor();
            return (RPSBot) ctor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
                | SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    public RPSOutcome playMatch(Arbiter opponent) {
        RPSBot bot1 = getNewBot();
        RPSBot bot2 = opponent.getNewBot();

        int bot1Wins = 0;
        int bot2Wins = 0;
        int draws = 0;

        for (int i = 0; i < BEST_OF; i++) {
            RPSMove bot1Move = bot1.getMove();
            RPSMove bot2Move = bot2.getMove();

            RPSOutcome outcome = RPSOutcome.getOutcome(bot1Move, bot2Move);

            bot1.roundResults(bot1Move, bot2Move);
            bot2.roundResults(bot2Move, bot1Move);

            switch (outcome) {
                case WIN:
                    bot1Wins++;
                    break;
                case LOSE:
                    bot2Wins++;
                    break;
                case DRAW:
                    draws++;
                    break;
            }
        }

        if (draws >= BEST_OF / 2)
            return RPSOutcome.DRAW;
        else if (bot1Wins > bot2Wins)
            return RPSOutcome.WIN;
        else if (bot2Wins > bot1Wins)
            return RPSOutcome.LOSE;
        else
            return RPSOutcome.DRAW;
    }

    // Make .equals and .hashCode all depend ONLY on the subject bot class
    // This is so that we can use Arbitrator as a key in a Map
    // it's kinda not ideal if we ever store anything else here (elo)

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Arbiter))
            return false;
        Arbiter other = (Arbiter) o;
        return botClass.equals(other.botClass);
    }

    public int hashCode() {
        return botClass.hashCode();
    }
}