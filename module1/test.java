/* Nim Main4 Program
 * Aaron Bhattachan
 * 
 * Given an input NIM(1, x, y, z), output the win condition for Player 2
 */

import java.util.Arrays;
import java.util.Scanner;

public class test {
  public static void main(String[] args) {
    // Create empty array for win table and scanner
    String[] win_table = new String[101];
    Scanner sc = new Scanner(System.in);

    // Get input from user
    intro();
    int[] inp_sticks = input(sc);

    // Create win table
    win_table = NIM(inp_sticks);

    // Find modulus condition and print
    int[] result = find_mod_condition(win_table);
    int period = result[0];
    int[] losingPositions = Arrays.copyOfRange(result, 1, result.length);

    print_condition(period, losingPositions, inp_sticks);
  }
  
  public static void intro() {
    System.out.println("\n==========================================================");
    System.out.println("     Nim-Project: Mod Condition Generator (Program 3)     ");
    System.out.println("==========================================================");
  }

  
  public static int[] input(Scanner s) {
    System.out.print("Possible Removables: ");
    int count = s.nextInt();
    int[] inp = new int[count];

    for (int i = 1; i <= count; i++) {
      System.out.print("> Enter " + i + ": ");
      inp[i-1] = s.nextInt();
    }

    return inp;
  }

  public static String[] NIM(int[] sticks) {
    String[] win_table = new String[201];

    win_table[0] = "L"; // Base Case

    for (int i = 1; i < win_table.length; i++) {
      boolean changed = false;
      for (int j = 0; j < sticks.length; j++) {
        int x = sticks[j];
        if (i >= x && win_table[i-x] == "L") {
          win_table[i] = "W";
          changed = true;
        }
      }
      if (changed) {
        win_table[i] = "L";
      }
    }

    return win_table;
  }

  public static int[] find_mod_condition(String[] table) {
    for (int period = 1; period < table.length; period++) {
      String[] pattern = Arrays.copyOfRange(table, 0, period);
      boolean is_periodic = true;
      for (int i = 0; i < table.length; i++) {
        if (!(table[i].equals(pattern[i % period]))) {
          is_periodic = false;
          break;
        }
      }
      if (is_periodic) {
        int[] losingPositions = new int[period];
        int index = 0;
        for (int i = 0; i < period; i++) {
          if (table[i].equals("L")) {
            losingPositions[index++] = i;
          }
        }

        losingPositions = Arrays.copyOfRange(losingPositions, 0, index);
        
        int[] result = new int[losingPositions.length + 1];
        result[0] = period;
        System.arraycopy(losingPositions, 0, result, 1, losingPositions.length);
        return result;
      }
    }
    return new int[] { -1 };
  }

  public static void print_condition(int period, int[] losingPositions, int[] sticks) {
    System.out.println("---------------------------------");
    System.out.print("NIM(");
    for (int i = 0; i < sticks.length; i++) {
      if (i != 0) {
        System.out.print(", ");
      }
      System.out.print(sticks[i]);
    }
    System.out.print(")\n");

    if (period != -1) {
      System.out.print("Player 2 wins IFF: ");
      System.out.print("n ≡ ");
      for (int i = 0; i < losingPositions.length; i++) {
        System.out.print(losingPositions[i]);
        if (i < losingPositions.length - 1) {
          System.out.print(", ");
        }
      }
      System.out.println(" (mod " + period + ")\n");
    } else {
      System.out.println("No periodic pattern found in the WIN table.\n");
    }
  }
  
}
