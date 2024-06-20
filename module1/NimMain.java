/* Nim Main Program
 * Aaron Bhattachan
 * 
 * Given an input NIM(a, b, c, ... y, z), output the win condition for Player 2
 */

import java.util.Arrays;
import java.util.Scanner;

public class NimMain {
  public static void main(String[] args) {
    // Create empty array for win table and scanner
    int win_table_size = 1001;
    String[] win_table = new String[win_table_size];
    Scanner sc = new Scanner(System.in);

    // Get input from user
    intro();
    int[] inp_sticks = input(sc);

    // Create win table
    win_table = NIM(win_table_size, inp_sticks);

    // Find modulus condition and print
    int[] result = find_mod_condition(win_table);
    int period = result[0];
    int[] losingPositions = Arrays.copyOfRange(result, 1, result.length);

    print_condition(period, losingPositions, inp_sticks);
  }
  
  public static void intro() {
    System.out.println("\n=========================================================");
    System.out.println("     Nim-Project: Mod Condition Generator (Module 1)     ");
    System.out.println("=========================================================");
    System.out.println("NIM(a, b, c, ... y, z) --> Modulo Condition for Player 2\n");
  }

  public static int[] input(Scanner s) {
    System.out.print("> Enter Number of Removable Stick Possibilities: ");
    int size = s.nextInt();

    int[] inp = new int[size];

    System.out.print("------------------\nNIM(");
    for (int letter = 0; letter < size; letter++) {
      System.out.print(((char) (97 + letter)));
      if (letter != size-1) {
        System.out.print(", ");
      }
    }
    System.out.println(")");

    for (int i = 0; i < size; i++) {
      System.out.print("> Enter " + ((char) (97 + i)) + ": ");
      inp[i] = s.nextInt();
    }

    return inp;
  }

  public static String[] NIM(int size, int[] sticks) {
    String[] win_table = new String[size];
    win_table[0] = "L"; // Base Case

    int sticks_size = sticks.length;

    for (int i = 1; i < size; i++) {
      boolean changed = false;
      for (int j = 0; j < sticks_size; j++) {
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

  public static int[] find_mod_condition(String[] table) {
    for (int period = 1; period < table.length/2; period++) {
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
      System.out.println("No modulo condition found.\n");
    }
  }
}
