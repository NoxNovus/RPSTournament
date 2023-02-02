package rockpaperscissorstournament;

import java.io.Serializable;

/**
 * Represents a bot that can play Rock Paper Scissors.
 * The bot's strategy is up to you.
 * 
 * The bot must be serializable, so that it can be saved and loaded as
 * necessary. This allows you to
 * create a bot that can learn over a large series of matches.
 * 
 * The bot must be created using the createBot method, which should return an
 * instance of the bot.
 * This bot will be used to fight only one other opponent in a series of
 * matches.
 * 
 * The bot instance will be used for multiple matches, so it is important that
 * the bot is stateful.
 * 
 * A match can be represented as a series of method calls:
 * <ol>
 * <li>createBot is called to create the bot</li>
 * <li>getMove is called to get the bot's first move</li>
 * <li>roundResults is called to tell the bot the result of the round</li>
 * <li>Repeat stpes 2 and 3 until the match is over</li>
 * </ol>
 */
public interface RPSBot extends Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * The name of the bot
     */
    public static final String NAME = "Unnamed Bot";

    /**
     * The description of the bot
     * This is a great place to put your name, bot details, etc
     */
    public static final String DESCRIPTION = "";

    /**
     * Called when the bot encounters an opponent
     * 
     * @param opponentID The ID of the opponent. Will be different over multiple
     *                   matches
     * @return The bot to use for the match
     */
    public static void createBot(int opponentID) {
    }

    /**
     * Called when the bot is about to play a move
     * 
     * @return The move the bot wants to play
     */
    public RPSMove getMove();

    /**
     * Called when both bots have played a move
     * 
     * @param move The move the bot played
     */
    public default void roundResults(RPSMove myMove, RPSMove opponentMove) {
    }
}