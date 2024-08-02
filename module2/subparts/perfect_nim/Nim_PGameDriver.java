/* Perfect Nim Game Driver Program
 * Aaron Bhattachan
 */

package module2.subparts.perfect_nim;

import java.util.Scanner;

public class Nim_PGameDriver {
  public static void main(String[] args) {
    // Create scanner object for user input
    Scanner sc = new Scanner(System.in);

    // Get user input
    intro();
    int[] user_input = input(sc);

    int[] remove_sticks = new int[user_input.length-2];
    for (int i = 0; i < remove_sticks.length; i++) {
      remove_sticks[i] = user_input[i];
    }
    int num_sticks = user_input[user_input.length-2];
    int num_simulations = user_input[user_input.length-1];

    simulate_games(remove_sticks, num_sticks, num_simulations);
  }

  // Method for Intro Print Statements
  public static void intro() {
    System.out.println("\n=======================================================");
    System.out.println("     Nim-Project: Perfect NIM Simulator (Module 2)     ");
    System.out.println("=======================================================");
  }

  // Method for User Input
  public static int[] input(Scanner s) {
    // User input for number of simulations
    System.out.print("> Enter Number of Simulations: ");
    int num_simulations = s.nextInt();

    // User input for number of sticks
    System.out.print("> Enter Number of Sticks: ");
    int num_sticks = s.nextInt();

    // User input for number of removable stick possibilities
    System.out.print("\n> Enter Number of Removable Stick Possibilities: ");
    int size = s.nextInt();
    int[] user_input = new int[size+2]; // First "size" places are for remove_sticks, size is number of sticks, size+1 is for number of simulations

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

    return user_input;
  }

  public static void simulate_games(int[] remove_sticks, int num_sticks, int num_simulations) {
    Nim_PGameClass simulator = new Nim_PGameClass(num_sticks, remove_sticks);
    simulator.game_stats();

    int p1_wins = 0;
    int p2_wins = 0;

    while (num_simulations > 0) {
      int winner = simulator.play_nim();

      if (winner == 1) {
        p1_wins++;
      } else {
        p2_wins++;
      }

      num_simulations--;
    }

    // double p1_ratio = p1_wins / num_simulations;
    // double p2_ratio = 100 - p1_ratio;

    // System.out.println("---------------------");
    // System.out.println("Player 1 Win Percentage: " + p1_ratio + "%");
    // System.out.println("Player 2 Win Percentage: " + p2_ratio + "%");

    System.out.println("Player 1 Wins: " + p1_wins);
    System.out.println("Player 2 Wins: " + p2_wins);
    System.out.println("=======================================================");
  }
}
