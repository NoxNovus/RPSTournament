package rockpaperscissorstournament;

import static rockpaperscissorstournament.Permutate.iterPermutations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        HashMap<Arbitrator, Integer> eloRankings = new HashMap<>();
        Set<Arbitrator> allBots = Arbitrator.loadAllArbitrators();

        // Initialize elo rankings
        for (Arbitrator a : allBots)
            eloRankings.put(a, 1000);

        // Play all possible matches
        Iterator<Entry<Arbitrator, Arbitrator>> iter = iterPermutations(allBots.iterator(), allBots.iterator());

        while (iter.hasNext()) {
            Entry<Arbitrator, Arbitrator> x = iter.next();
            Arbitrator a = x.getKey();
            Arbitrator b = x.getValue();

            if (a == null || b == null)
                continue;

            System.out.println(a.getName() + " vs " + b.getName());

            System.out.print("Playing standard match: ");
            RPSOutcome outcome = a.playMatch(b);
            if (outcome == RPSOutcome.DRAW)
                System.out.println("Draw");
            else
                System.out.println((outcome == RPSOutcome.WIN ? a.getName() : b.getName()) + " wins");

            int newAElo = EloLib.calculateElo(eloRankings.get(a), eloRankings.get(b), outcome);
            int newBElo = EloLib.calculateElo(eloRankings.get(b), eloRankings.get(a), outcome.getOpposite());
            System.out.println(
                    a.getName() + " elo: " + eloRankings.get(a) + " -> " + newAElo + " ("
                            + (newAElo - eloRankings.get(a)) + ")");
            System.out.println(
                    b.getName() + " elo: " + eloRankings.get(b) + " -> " + newBElo + " ("
                            + (newBElo - eloRankings.get(b)) + ")");
            eloRankings.put(a, newAElo);
            eloRankings.put(b, newBElo);

            System.out.println();
        }

        // Print elo rankings
        System.out.println("Bot Elo rankings:");
        for (Arbitrator a : allBots)
            System.out.println(a.getName() + ": " + eloRankings.get(a));
    }
}
