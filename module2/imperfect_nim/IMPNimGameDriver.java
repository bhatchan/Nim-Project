/* Imperfect Nim Game Driver Program
 * Aaron Bhattachan
 */

package module2.imperfect_nim;

import java.util.Scanner;
import java.text.DecimalFormat;

public class IMPNimGameDriver {
  public static void main(String[] args) {
    // Create scanner object for user input
    Scanner sc = new Scanner(System.in);

    // Get user input
    intro();
    int[] user_input = input(sc);

    int[] remove_sticks = new int[user_input.length-4];
    for (int i = 0; i < remove_sticks.length; i++) {
      remove_sticks[i] = user_input[i];
    }
    int num_sticks = user_input[user_input.length-4];
    int num_simulations = user_input[user_input.length-3];
    int p1 = user_input[user_input.length-2];
    int p2 =user_input[user_input.length-1];

    simulate_games(sc, remove_sticks, p1, p2, num_sticks, num_simulations);
  }

  // Method for Intro Print Statements
  public static void intro() {
    System.out.println("\n=========================================================");
    System.out.println("     Nim-Project: Imperfect NIM Simulator (Module 2)     ");
    System.out.println("=========================================================");
  }

  // Method for User Input
  public static int[] input(Scanner s) {
    // User input for number of simulations
    System.out.print("> Enter Number of Simulations: ");
    int num_simulations = s.nextInt();

    // User input for number of sticks
    System.out.print("> Enter Number of Sticks: ");
    int num_sticks = s.nextInt();

    // User input for player 1 probability
    System.out.print("> Enter Player 1 Probability (0-100): ");
    int p1 = s.nextInt();

    // User input for player 2 probability
    System.out.print("> Enter Player 2 Probability (0-100): ");
    int p2 = s.nextInt();

    // User input for number of removable stick possibilities
    System.out.print("\n> Enter Number of Removable Stick Possibilities: ");
    int size = s.nextInt();
    int[] user_input = new int[size+4];

    // Print statement for number of removable stick possibilities
    System.out.print("------------------\nNIM(");
    for (int letter = 0; letter < size; letter++) {
      System.out.print(((char) (97 + letter)));
      if (letter != size-1) {
        System.out.print(", ");
      }
    }
    System.out.println(")");

    // User input for removable stick possibilities
    for (int i = 0; i < size; i++) {
      System.out.print("> Enter " + ((char) (97 + i)) + ": ");
      user_input[i] = s.nextInt();
    }

    user_input[size] = num_sticks;
    user_input[size+1] = num_simulations;
    user_input[size+2] = p1;
    user_input[size+3] = p2;

    return user_input;
  }

  public static void simulate_games(Scanner s, int[] remove_sticks, int p1, int p2, int num_sticks, int num_simulations) {
    IMPNimGameClass simulator = new IMPNimGameClass(num_sticks, p1, p2, remove_sticks);
    boolean repeat = false;
    do {
      simulator.game_stats();
  
      int p1_wins = 0;
      int p2_wins = 0;
  
      for (int i = 0; i < num_simulations; i++) {
        int winner = simulator.play_nim();
  
        if (winner == 1) {
          p1_wins++;
        } else {
          p2_wins++;
        }
      }

      DecimalFormat df = new DecimalFormat("0.00");

      double p1_ratio = (double) p1_wins / (double) num_simulations * 100;
      double p2_ratio = 100.0 - p1_ratio;
  
      System.out.println("Player 1 Win Percentage: " + p1_wins + "/" + num_simulations + " = " + df.format(p1_ratio) + "%");
      System.out.println("Player 2 Win Percentage: " + p2_wins + "/" + num_simulations + " = " + df.format(p2_ratio) + "%\n");

      simulator.perfect_winner();

      System.out.print("\n> Repeat " + num_simulations + " Simulations (0=No/1=Yes)? ");

      int user_repeat = s.nextInt();
      if (user_repeat == 1) {
        repeat = true;
      } else {
        repeat = false;
      }
    } while (repeat);

    System.out.println("=======================================================");

  }
}
