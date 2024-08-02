/* Perfect Nim Game Class
 * Aaron Bhattachan
 */
package module2.subparts.perfect_nim;

public class Nim_PGameClass {
  private int num_sticks;
  private int[] remove_sticks;
  private String[] win_table;

  public Nim_PGameClass(int num_sticks, int[] remove_sticks) {
    this.num_sticks = num_sticks;
    this.remove_sticks = remove_sticks;
    this.win_table = win_table_generator(num_sticks+1, remove_sticks);
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
        }
      }

      // If there's still a legal "move", play on
      if (!game_done) {

        // Player 1 Turn
        if (turn == 1) { 
          // System.out.println("Player Turn: Player 1");

          // If the Program is in a losing position, just remove 1 stick
          if (win_table[game_stick_count].equals("L")) { 
            game_stick_count--;
            turn = 2;
            // System.out.println("Player 1 removes 1 stick.");
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
                // System.out.println("Player 1 removes " + remove_sticks[i] + " sticks.");
              }
            }
          }
        } 

        // Player 2 Turn
        else if (turn == 2) { 
          // System.out.println("Player Turn: Player 2");

          // If the Program is in a losing position, just remove 1 stick
          if (win_table[game_stick_count].equals("L")) { 
            game_stick_count--;
            turn = 1;
            // System.out.println("Player 2 removes 1 stick.");
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
                // System.out.println("Player 2 removes " + remove_sticks[i] + " sticks.");
              }
            }
          }
        }
        // System.out.println("------------------");
      }
    }

    if (turn == 1) {
      return 2;
    } 
    
    return 1;
  }

  public void game_stats() {
    System.out.println("---------------------");
    System.out.print("Num. of Sticks: " + num_sticks + " | ");
    System.out.print("NIM(");
    for (int i = 0; i < remove_sticks.length; i++) {
      if (i != 0) {
        System.out.print(", ");
      }
      System.out.print(remove_sticks[i]);
    }
    System.out.println(")\n");
  }
}
