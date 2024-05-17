/* Nim Program 1
 * Aaron Bhattachan
 * 
 * Given an input NIM(1, x, y), output a wintable up to 100 sticks
 */

package module1;
import java.util.*;

public class Nim1 {
  public static void main(String[] args) {
    try {
      intro();

      Scanner sc = new Scanner(System.in);
      int[] inpSticks = input(sc);

    } catch (Exception e) {

    }
  }
  
  public static void intro() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.println("======================================================");
    System.out.println("     Nim-Project: Win Table Generator (Program 1)     ");
    System.out.println("======================================================");
    System.out.println("Input: All possible number of sticks that can be removed on a single turn");
    System.out.println("--> Player A can remove 1, x, or y sticks");
    System.out.println("Output: A win table (between 2 players) for all games from 1 to 100 sticks\n");
  }

  public static int[] input(Scanner s) {
    int[] inp = new int[2];

    System.out.print("Enter x: ");
    s.nextInt();

    return inp;
  }
}