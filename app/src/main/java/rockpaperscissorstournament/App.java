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
        HashMap<Arbiter, Double> eloRankings = new HashMap<>();
        Set<Arbiter> allBots = Arbiter.loadAllArbitrators();

        // Initialize elo rankings
        for (Arbiter a : allBots)
            eloRankings.put(a, 1000.0);

        // Play all possible matches
        Iterator<Entry<Arbiter, Arbiter>> iter = iterPermutations(allBots.iterator(), allBots.iterator());
        long startTime = System.currentTimeMillis();

        while (iter.hasNext()) {
            Entry<Arbiter, Arbiter> x = iter.next();
            Arbiter a = x.getKey();
            Arbiter b = x.getValue();

            if (a == null || b == null || a.equals(b))
                continue;

            System.out.println(a.getName() + " vs " + b.getName());

            System.out.print("Playing standard match: ");
            RPSOutcome outcome = a.playMatch(b);
            if (outcome == RPSOutcome.DRAW)
                System.out.println("Draw");
            else
                System.out.println((outcome == RPSOutcome.WIN ? a.getName() : b.getName()) + " wins");

            double newAElo = EloLib.calculateElo(eloRankings.get(a), eloRankings.get(b), outcome);
            double newBElo = EloLib.calculateElo(eloRankings.get(b), eloRankings.get(a), outcome.getOpposite());
            System.out.printf(
                    "%s elo: %.2f -> %.2f (%+.2f)%n",
                    a.getName(), eloRankings.get(a), newAElo, (newAElo - eloRankings.get(a)));
            System.out.printf(
                    "%s elo: %.2f -> %.2f (%+.2f)%n",
                    b.getName(), eloRankings.get(b), newBElo, (newBElo - eloRankings.get(b)));

            eloRankings.put(a, newAElo);
            eloRankings.put(b, newBElo);

            System.out.println();
        }

        // Print elo rankings
        System.out.println("FINAL TOURNAMENT ELO RANKINGS:");
        allBots.stream()
                .sorted((a, b) -> Double.compare(eloRankings.get(b), eloRankings.get(a)))
                .forEach(a -> System.out.printf("%s: %.2f%n", a.getName(), eloRankings.get(a)));

        System.out.printf("\nTotal time: %.2f seconds%n", (System.currentTimeMillis() - startTime) / 1000.0);
    }
}
