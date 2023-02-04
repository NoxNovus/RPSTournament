package rockpaperscissorstournament;

import static rockpaperscissorstournament.Permutate.iterPermutations;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.lang.reflect.InvocationTargetException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Set<Class<RPSBot>> allBots = ClassLoader.allImplementers(RPSBot.class);
        Iterator<Entry<Class<RPSBot>, Class<RPSBot>>> iter = iterPermutations(allBots.iterator(), allBots.iterator());

        while (iter.hasNext()) {
            Entry<Class<RPSBot>, Class<RPSBot>> x = iter.next();
            RPSBot a = instantiate(x.getKey());
            RPSBot b = instantiate(x.getValue());
            if (a == null || b == null)
                continue;

            System.out.println(a.getName() + " vs " + b.getName());
            System.out.println("Winning Move: " + RPSMove.getWinner(a.getMove(), b.getMove()));
            if (iter.hasNext())
                System.out.println();

        }
    }

    private static RPSBot instantiate(Class<RPSBot> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
                | SecurityException e) {
            return null;
        }
    }
}
