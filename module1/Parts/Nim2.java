/* Nim Program 2
 * Aaron Bhattachan
 * 
 * Given a win-table, output the win condition for Player 2
 */

import java.util.*;
import java.io.*;

public class Nim2 {
  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);
      String[] win_table = new String[101];
  
      intro();
  
      String fileName = input(sc);
      File inp_file = new File(fileName);
      Scanner reader = new Scanner(inp_file);

      win_table = parse_file(inp_file, reader);

      // print_win_table(win_table);

      int[] result = find_mod_condition(win_table);
      int period = result[0];
      int[] losingPositions = Arrays.copyOfRange(result, 1, result.length);
      
      print_condition(period, losingPositions);

      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("File doesn't exist.");
    }
  }
  
  public static void intro() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.println("\n==========================================================");
    System.out.println("     Nim-Project: Win Condition Generator (Program 2)     ");
    System.out.println("==========================================================");
    System.out.println("Input: A win table (between 2 players) for all games from 1 to 100 sticks");
    System.out.println("--> File input: fileName.txt");
    System.out.println("Output: All possible number of sticks that can be removed on a single turn\n");
  }

  public static String input(Scanner s) {
    System.out.print("> Enter the filename for the wintable: ");
    String inp = s.nextLine();
    return inp;
  }

  public static String[] parse_file(File inp_file, Scanner s) {
    String[] win_table = new String[101];
    int pos = 0;

    while (s.hasNextLine()) {
      String data = s.nextLine();
      String ch = data.substring(data.length()-1);
      win_table[pos] = ch;
      pos++;
    }

    return win_table;
  }

  public static void print_win_table(String[] table) {
    System.out.println("---------------------------------");
    for (int i = 0; i < table.length; i++) {
      System.out.println(table[i]);
    }
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

  public static void print_condition(int period, int[] losingPositions) {
    System.out.println("---------------------------------");
    if (period != -1) {
      System.out.print("If n ≡ ");
      for (int i = 0; i < losingPositions.length; i++) {
        System.out.print(losingPositions[i]);
        if (i < losingPositions.length - 1) {
          System.out.print(", ");
        }
      }
      System.out.println(" (mod " + period + ") then player II wins");
    } else {
      System.out.println("No periodic pattern found in the WIN table.");
    }
  }
}