package rockpaperscissorstournament;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        for (Class<?> c : ClassLoader.allImplementers(RPSBot.class)) {
            System.out.println("Loaded bot: " + c);
        }
    }
}
