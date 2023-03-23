package rockpaperscissorstournament;

import static rockpaperscissorstournament.Permutate.iterPermutations;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Set<Arbitrator> allBots = Arbitrator.loadAllArbitrators();
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
            if (outcome == RPSOutcome.WIN)
                System.out.println(a.getName() + " wins!");
            else if (outcome == RPSOutcome.LOSE)
                System.out.println(b.getName() + " wins!");
            else
                System.out.println("Draw!");

            if (iter.hasNext())
                System.out.println();

        }
    }
}
