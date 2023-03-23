package rockpaperscissorstournament;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Arbitrator class is responsible for a single bot type.
 * 
 * It wraps RPSBot instances, protecting information such as
 * win/loss records, elo, and history.
 * 
 * It also "arbitrates"/plays a series of matches between bots of that type
 * and another Arbitrator
 */
public class Arbitrator {
    public static final int BEST_OF = 100;

    public static Set<Arbitrator> loadAllArbitrators() {
        Set<Class<RPSBot>> allBots = ClassLoader.allImplementers(RPSBot.class);
        return allBots.stream().map(Arbitrator::new).collect(Collectors.toSet());
    }

    private Class<RPSBot> botClass;
    private String name;
    private String description;

    public Arbitrator(Class<RPSBot> botClass) {
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
            return botClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
                | SecurityException e) {
            return null;
        }
    }

    public RPSOutcome playMatch(Arbitrator opponent) {
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

        if (bot1Wins > bot2Wins)
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
        if (!(o instanceof Arbitrator))
            return false;
        Arbitrator other = (Arbitrator) o;
        return botClass.equals(other.botClass);
    }

    public int hashCode() {
        return botClass.hashCode();
    }
}