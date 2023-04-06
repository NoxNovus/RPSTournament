package bots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import rockpaperscissorstournament.RPSBot;
import rockpaperscissorstournament.RPSMove;

public class PredictorBot implements RPSBot {
    // Predicts the next move of the opponent based on the last X moves
    private final int PREDICTION_LENGTH = 5;

    // Stores the last 2X+1 moves, where X is the prediction length
    // Format: [myMove, opponentMove, myMove, opponentMove, ... myMove]
    // Maps the last 2X moves to the int[] representing moves made by the opponent
    private HashMap<List<RPSMove>, int[]> observations = new HashMap<>();

    // Stores the last X moves made
    private Queue<RPSMove> lastMoves = new LinkedList<>();

    public String getName() {
        return "PredictorBot";
    }

    @Override
    public RPSMove getMove() {
        double maxWinChance = -1f;
        RPSMove maxWinChanceMove = RPSMove.PAPER;

        double maxTieChance = -1f;
        RPSMove maxTieChanceMove = RPSMove.PAPER;

        for (RPSMove possibleMove : RPSMove.values()) {
            // Get last 2X moves and the move we're considering
            List<RPSMove> lastMovesRaw = getLastMoves(possibleMove);

            // Get the observations from previous rounds
            int[] opponentMoves = observations.getOrDefault(lastMovesRaw, new int[] { 1, 1, 1 });
            double total = Arrays.stream(opponentMoves).sum();

            // Compute the win chance
            double winChance = opponentMoves[possibleMove.strength().ordinal()] / total;

            if (winChance > maxWinChance) {
                maxWinChance = winChance;
                maxWinChanceMove = possibleMove;
            }

            // Compute the tie chance
            double tieChance = opponentMoves[possibleMove.ordinal()] / total;

            if (tieChance > maxTieChance) {
                maxTieChance = tieChance;
                maxTieChanceMove = possibleMove;
            }
        }

        if (maxWinChance < 0.33)
            return maxTieChanceMove;

        return maxWinChanceMove;
    }

    @Override
    public void roundResults(RPSMove myMoveRaw, RPSMove opponentMoveRaw) {
        // If our prediction is of valid length:
        if (lastMoves.size() == 2 * PREDICTION_LENGTH) {
            // Get last 2X moves, adding the current move to the end
            List<RPSMove> lastMovesRaw = getLastMoves(myMoveRaw);

            // Get the corresponding array of observations
            int[] opponentMoves = observations.getOrDefault(lastMovesRaw, new int[3]);

            // Increment the count of the move the opponent made this round
            opponentMoves[opponentMoveRaw.ordinal()]++;

            // Update the map

            observations.put(lastMovesRaw, opponentMoves);
        }

        // Append to lastMoves
        lastMoves.add(myMoveRaw);
        lastMoves.add(opponentMoveRaw);
        while (lastMoves.size() > 2 * PREDICTION_LENGTH)
            lastMoves.remove();

    }

    private List<RPSMove> getLastMoves(RPSMove myMove) {
        // Get last 2X moves, adding the current move to the end
        ArrayList<RPSMove> lastMovesRaw = new ArrayList<>(lastMoves);
        lastMovesRaw.add(myMove);
        return lastMovesRaw;
    }

}
