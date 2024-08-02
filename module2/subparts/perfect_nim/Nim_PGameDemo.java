/* Perfect Nim Game
 * Aaron Bhattachan
 */
package module2.subparts.perfect_nim;

import java.util.Scanner;


public class Nim_PGameDemo {
  public static void main(String[] args) {
    // Create scanner object for user input and empty array for win table 
    int win_table_size = 1001;
    String[] win_table = new String[win_table_size];
    Scanner sc = new Scanner(System.in);

    // Get user input
    intro();
    int[] inp_sticks = input(sc);
    int[] remove_sticks = new int[inp_sticks.length-2];
    for (int i = 0; i < remove_sticks.length; i++) {
      remove_sticks[i] = inp_sticks[i];
    }

    // Create win table
    win_table = win_table_generator(win_table_size, inp_sticks);

    // Play NIM
    play_nim(win_table, remove_sticks, inp_sticks[inp_sticks.length-2], inp_sticks[inp_sticks.length-1]);
  }
  
  // Method for Intro Print Statements
  public static void intro() {
    System.out.println("\n==================================================");
    System.out.println("     Nim-Project: Perfect NIM Game (Module 2)     ");
    System.out.println("==================================================");
  }

  // Method for User Input
  public static int[] input(Scanner s) {
    // User input for number of sticks
    System.out.print("> Enter Number of Sticks: ");
    int num_sticks = s.nextInt();

    // User input for number of removable stick possibilities
    System.out.print("\n> Enter Number of Removable Stick Possibilities: ");
    int size = s.nextInt();
    int[] user_input = new int[size+2]; // First "size" places are for remove_sticks, size is number of sticks, size+1 is player turn

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
    user_input[size+1] = 1;

    return user_input;
  }

  // Method for creating win table for Player 1
  public static String[] win_table_generator(int size, int[] sticks) {
    String[] win_table = new String[size];
    win_table[0] = "L"; // Base Case (0 Sticks is a loss for Player 1)

    int remove_sticks_size = sticks.length - 2;

    // Update win table from 1 stick to size sticks
    for (int i = 1; i < size; i++) {
      boolean changed = false;
      for (int j = 0; j < remove_sticks_size; j++) {
        int val = sticks[j];
        if (i >= val && win_table[i-val] == "L") {
          win_table[i] = "W";
          changed = true;
        } 
      }
      if (!changed) {
        win_table[i] = "L";
      }
    }

    return win_table;
  }

  public static void play_nim(String[] win_table, int[] remove_sticks, int num_sticks, int turn) {
    // Print statements
    System.out.println("-------------------------");
    System.out.print("NIM(");
    for (int i = 0; i < remove_sticks.length; i++) {
      System.out.print(remove_sticks[i]);
      if (i != remove_sticks.length-1) {
        System.out.print(", ");
      }
    }
    System.out.println(")\n");

    boolean game_done = false;

    // Repeat until number of sticks is 0 or you can't remove any number of legal sticks to get to 0 sticks
    while (num_sticks != 0 && !game_done) {
      // System.out.println("Number of Sticks: " + num_sticks);
      game_done = true;

      // Check that there's still a legal stick remove possibility that leads to number of sticks greater than or equal to 0
      for (int r_stick:remove_sticks) {
        if (num_sticks - r_stick >= 0) {
          game_done = false;
        }
      }

      // If there's still a legal "move", play on
      if (!game_done) {

        // Player 1 Turn
        if (turn == 1) { 
          // System.out.println("Player Turn: Player 1");

          // If the Program is in a losing position, just remove 1 stick
          if (win_table[num_sticks].equals("L")) { 
            num_sticks--;
            turn = 2;
            // System.out.println("Player 1 removes 1 stick.");
          } 
          
          // If the Program is in a winning position, find largest removable stick that can be removed to get to a "L"
          else if (win_table[num_sticks].equals("W")) {
            boolean removed = false;
            for (int i = remove_sticks.length - 1; i >= 0 && removed == false; i--) {
              int new_stick_num = num_sticks - remove_sticks[i];
              if (new_stick_num >= 0 && win_table[new_stick_num].equals("L")) {
                removed = true;
                num_sticks = new_stick_num;
                turn = 2;
                // System.out.println("Player 1 removes " + remove_sticks[i] + " sticks.");
              }
            }
          }
        } 

        // Player 2 Turn
        else if (turn == 2) { 
          // System.out.println("Player Turn: Player 2");

          // If the Program is in a losing position, just remove 1 stick
          if (win_table[num_sticks].equals("L")) { 
            num_sticks--;
            turn = 1;
            // System.out.println("Player 2 removes 1 stick.");
          } 
          
          // If the Program is in a winning position, find largest removable stick that can be removed to get to a "L"
          else if (win_table[num_sticks].equals("W")) {
            boolean removed = false;
            for (int i = remove_sticks.length - 1; i >= 0 && removed == false; i--) {
              int new_stick_num = num_sticks - remove_sticks[i];
              if (new_stick_num >= 0 && win_table[new_stick_num].equals("L")) {
                removed = true;
                num_sticks = new_stick_num;
                turn = 1;
                // System.out.println("Player 2 removes " + remove_sticks[i] + " sticks.");
              }
            }
          }
        }
        // System.out.println("------------------");
      }
    }

    if (turn == 1) {
      System.out.println("Player 2 wins the game.");
    } else {
      System.out.println("Player 1 wins the game.");
    }
  }
}
