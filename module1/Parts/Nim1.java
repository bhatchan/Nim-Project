/* Nim Program 1
 * Aaron Bhattachan
 * 
 * Given an input NIM(1, x, y), output a win table up to 100 sticks both in terminal and as an output file
 */

import java.util.*;
import java.io.*;

public class Nim1 {
  public static void main(String[] args) {
    // Create empty array for win table and scanner
    char[] win_table = new char[101];
    Scanner sc = new Scanner(System.in);

    // Get input from user
    intro();
    int[] inp_sticks = input(sc);

    // Create win table and output file
    win_table = NIM(1, inp_sticks[0], inp_sticks[1]);
    print_win_table(win_table, inp_sticks[0], inp_sticks[1]);
  }
  
  public static void intro() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.println("\n======================================================");
    System.out.println("     Nim-Project: Win Table Generator (Program 1)     ");
    System.out.println("======================================================");
    System.out.println("Nim(1, x, y) --> Win-Table\n");
  }

  public static int[] input(Scanner s) {
    int[] inp = new int[3];

    System.out.print("> Enter x: ");
    inp[0] = s.nextInt();

    System.out.print("> Enter y: ");
    inp[1] = s.nextInt();

    return inp;
  }

  public static char[] NIM(int z, int x, int y) {
    char[] win_table = new char[101];

    win_table[0] = 'L'; // Base Case

    for (int i = 1; i < 101; i++) {
      if (i >= 1 && win_table[i-1] == 'L') {
        win_table[i] = 'W';
      } else if (i >= x && win_table[i-x] == 'L') {
        win_table[i] = 'W';
      } else if (i >= y && win_table[i-y] == 'L') {
        win_table[i] = 'W';
      } else {
        win_table[i] = 'L';
      }
    }

    return win_table;
  }

  public static void print_win_table(char[] table, int x, int y) {
    try {
      File output = new File("Nim1_Output.txt");
      output.createNewFile();
      FileWriter file = new FileWriter("Nim1_Output.txt");
      
      System.out.println("---------------------------------");
      System.out.println("Win-Table for Player II given NIM(1," + x + "," + y + "):");
      for (int i = 0; i < table.length; i++) {
        System.out.println(i + " sticks: " + table[i]);
        file.write(i + " sticks: " + table[i] + "\n");
      }
      file.close();
      System.out.println("---------------------------------");
      System.out.println("Output file successfully created.");

    } catch (Exception e) {
      System.out.println("File doesn't exist.");
    }
  }
}