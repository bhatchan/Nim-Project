/* Imperfect Nim Game Class
 * Aaron Bhattachan
 */
package bhatchan;

import java.util.*;

public class nim_game {
  private int num_sticks;
  private int p1;
  private int p2;
  private int[] remove_sticks;
  private String[] win_table;
  private Random random;

  public nim_game(int num_sticks, int p1, int p2, int[] remove_sticks) {
    this.num_sticks = num_sticks;
    this.p1 = p1;
    this.p2 = p2;
    this.remove_sticks = remove_sticks;
    this.win_table = win_table_generator(num_sticks+1, remove_sticks);
    this.random = new Random();
  }

  // Method for creating win table for Player 1
  private String[] win_table_generator(int size, int[] sticks) {
    String[] win_table = new String[size];
    win_table[0] = "L"; // Base Case (0 Sticks is a loss for Player 1)

    // Update win table from 1 stick to size sticks
    for (int i = 1; i < size; i++) {
      boolean changed = false;
      for (int j = 0; j < sticks.length; j++) {
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

  public int play_nim() {
    int game_stick_count = this.num_sticks;
    int turn = 1;

    boolean game_done = false;

    // Repeat until number of sticks is 0 or you can't remove any number of legal sticks to get to 0 sticks
    while (game_stick_count != 0 && !game_done) {
      // System.out.println("Number of Sticks: " + game_stick_count);
      game_done = true;

      // Check that there's still a legal stick remove possibility that leads to number of sticks greater than or equal to 0
      for (int r_stick:remove_sticks) {
        if (game_stick_count - r_stick >= 0) {
          game_done = false;
          break;
        }
      }

      // If there's still a legal "move", play on
      if (!game_done) {
        int randomNum = random.nextInt(101);

        // Player 1 Turn
        if (turn == 1) { 

          if (p1 >= randomNum) {
            // If the Program is in a losing position, just remove 1 stick
            if (win_table[game_stick_count].equals("L")) { 
              game_stick_count--;
              turn = 2;
            } 
            
            // If the Program is in a winning position, find largest removable stick that can be removed to get to a "L"
            else if (win_table[game_stick_count].equals("W")) {
              boolean removed = false;
              for (int i = remove_sticks.length - 1; i >= 0 && removed == false; i--) {
                int new_stick_num = game_stick_count - remove_sticks[i];
                if (new_stick_num >= 0 && win_table[new_stick_num].equals("L")) {
                  removed = true;
                  game_stick_count = new_stick_num;
                  turn = 2;
                }
              }
            }
          } 
          
          else {
            int update = 0;
            do {
              int randomStickNum = random.nextInt(remove_sticks.length);
              int rem_stick = remove_sticks[randomStickNum];
              update = game_stick_count - rem_stick;
            } while (update < 0); 
            game_stick_count = update;
            turn = 2;
          }
        } 

        // Player 2 Turn
        else if (turn == 2) { 

          if (p2 >= randomNum) {
            // If the Program is in a losing position, just remove 1 stick
            if (win_table[game_stick_count].equals("L")) { 
              game_stick_count--;
              turn = 1;
            } 
            
            // If the Program is in a winning position, find largest removable stick that can be removed to get to a "L"
            else if (win_table[game_stick_count].equals("W")) {
              boolean removed = false;
              for (int i = remove_sticks.length - 1; i >= 0 && removed == false; i--) {
                int new_stick_num = game_stick_count - remove_sticks[i];
                if (new_stick_num >= 0 && win_table[new_stick_num].equals("L")) {
                  removed = true;
                  game_stick_count = new_stick_num;
                  turn = 1;
                }
              }
            }
          } 
          
          else {
            int update = 0;
            do {
              int randomStickNum = random.nextInt(remove_sticks.length);
              int rem_stick = remove_sticks[randomStickNum];
              update = game_stick_count - rem_stick;
            } while (update < 0); 
            game_stick_count = update;
            turn = 1;
          }
          
        }
      }
    }

    if (turn == 1) {
      return 2;
    } 
    
    return 1;
  }
}