package rockpaperscissorstournament;

import java.util.Scanner;

import bots_1_trivial.RandomBot;

public class HumanVSRobot {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        RPSBot human = new HumanBot();
        RPSBot robot = new RandomBot();

        while (true) {
            RPSMove humanMove = human.getMove();
            RPSMove robotMove = robot.getMove();

            RPSOutcome outcome = RPSOutcome.getOutcome(humanMove, robotMove);
            System.out.println("You played " + humanMove + " and the robot played " + robotMove + ".");
            System.out.println("You " + outcome + "!");
            System.out.println();

            human.roundResults(humanMove, robotMove);
            robot.roundResults(robotMove, humanMove);
        }
    }

    private static class HumanBot implements RPSBot {
        public String getName() {
            return "Human";
        }

        public RPSMove getMove() {
            System.out.println("Enter your move (R, P, or S): ");
            String input = scanner.nextLine();
            if (input.equals("R")) {
                return RPSMove.ROCK;
            } else if (input.equals("P")) {
                return RPSMove.PAPER;
            } else if (input.equals("S")) {
                return RPSMove.SCISSORS;
            } else {
                System.out.println("Invalid input. Defaulting to ROCK.");
                return RPSMove.ROCK;
            }
        }
    }
}
