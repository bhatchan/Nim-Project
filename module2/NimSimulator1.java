/* 1-Pile Nim Simulator GUI
 * Aaron Bhattachan
 */

package module2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import module2.imperfect_nim.IMPNimGameClass;

import java.text.DecimalFormat;

public class NimSimulator1 implements ActionListener, KeyListener{
  private JFrame frame;
  private JTextField text1, text2, text3, text4, text5, text6;
  private static JTextField p1_rate, p2_rate;
  private static JLabel output_p1, output_p2, error1;

  public NimSimulator1() {
    initialize();
  }

  private void initialize() {
    // Create JFrame
    frame = new JFrame("1-Pile Nim Simulator");
    frame.setLayout(new BorderLayout(10, 5));
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(500, 575);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setBackground(Color.lightGray);

    // Create Title JPanel
    JPanel menu = new JPanel();
    menu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
    menu.setBackground(Color.BLACK);

    JLabel title = new JLabel("1-Pile Nim Simulator");
    title.setFont(new Font("Arial", Font.BOLD, 28));
    title.setForeground(Color.WHITE);
    menu.add(title);

    // Create Input JPanel
    JPanel panel1 = new JPanel();
    panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

    JLabel label1 = createJLabel("Number of Simulations:");
    panel1.add(label1);

    text1 = new JTextField("10000");
    text1.setFont(new Font("Arial", Font.PLAIN, 13));
    text1.setMargin(new Insets(5, 10, 5, 10));
    text1.setColumns(4);
    panel1.add(text1);

    JLabel line = createJLabel("   =======================================   ");
    panel1.add(line);

    JLabel filler1 = createJLabel("                     ");
    panel1.add(filler1);

    JLabel label2 = createJLabel("Number of Sticks (n):");
    panel1.add(label2);

    text2 = new JTextField();
    text2.setFont(new Font("Arial", Font.PLAIN, 13));
    text2.setMargin(new Insets(5, 10, 5, 10));
    text2.setColumns(4);
    panel1.add(text2);

    JLabel filler2 = createJLabel("                     ");
    panel1.add(filler2);

    JLabel filler3 = createJLabel("                     ");
    panel1.add(filler3);

    JLabel label3 = new JLabel("Player 1 Probability (p1):");
    label3.setFont(new Font("Arial", Font.PLAIN, 16));
    panel1.add(label3);

    text3 = new JTextField();
    text3.setFont(new Font("Arial", Font.PLAIN, 13));
    text3.setMargin(new Insets(5, 10, 5, 10));
    text3.setColumns(3);
    panel1.add(text3);

    JLabel filler4 = createJLabel("                     ");
    panel1.add(filler4);

    JLabel filler5 = createJLabel("                     ");
    panel1.add(filler5);

    JLabel label4 = createJLabel("Player 2 Probability (p2):");
    panel1.add(label4);

    text4 = new JTextField();
    text4.setFont(new Font("Arial", Font.PLAIN, 13));
    text4.setMargin(new Insets(5, 10, 5, 10));
    text4.setColumns(3);
    panel1.add(text4);

    JLabel filler6 = createJLabel("                     ");
    panel1.add(filler6);

    JLabel label5 = createJLabel("             NIM(1, x, y) = NIM(1,");
    panel1.add(label5);

    text5 = new JTextField("2");
    text5.setFont(new Font("Arial", Font.PLAIN, 13));
    text5.setMargin(new Insets(5, 10, 5, 10));
    text5.setColumns(1);
    panel1.add(text5);

    JLabel label6 = createJLabel(",");
    panel1.add(label6);

    text6 = new JTextField("3");
    text6.setFont(new Font("Arial", Font.PLAIN, 13));
    text6.setMargin(new Insets(5, 10, 5, 10));
    text6.setColumns(1);
    panel1.add(text6);

    JLabel label7 = createJLabel(")             ");
    panel1.add(label7);

    JButton button = new JButton("Run");
    button.setFocusable(false);
    button.setMargin(new Insets(7, 20,7, 20));
    button.setFont(new Font("Arial", Font.PLAIN, 18));
    button.addActionListener(this);
    button.addKeyListener(this);
    panel1.add(button);

    error1 = new JLabel();
    error1.setFont(new Font("Arial", Font.PLAIN, 12));
    error1.setVisible(false);
    panel1.add(error1);

    // Create Output JPanel
    JPanel panel2 = new JPanel();
    panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
    panel2.setBackground(Color.darkGray);

    output_p1 = new JLabel("Player 1 Win Rate:");
    output_p1.setForeground(Color.white);
    output_p1.setFont(new Font("Arial", Font.PLAIN, 16));
    panel2.add(output_p1);

    p1_rate = new JTextField ("x%");
    p1_rate.setFont(new Font("Arial", Font.PLAIN, 12));
    p1_rate.setMargin(new Insets(5, 5, 5, 5));
    p1_rate.setColumns(5);
    p1_rate.setEditable(false);
    p1_rate.setFocusable(false);
    panel2.add(p1_rate);

    JLabel filler7 = new JLabel("  |  ");
    filler7.setForeground(Color.white);
    filler7.setFont(new Font("Arial", Font.PLAIN,24));
    panel2.add(filler7);

    output_p2 = new JLabel("Player 2 Win Rate:");
    output_p2.setForeground(Color.white);
    output_p2.setFont(new Font("Arial", Font.PLAIN, 16));
    panel2.add(output_p2);

    p2_rate = new JTextField ("x%");
    p2_rate.setFont(new Font("Arial", Font.PLAIN, 12));
    p2_rate.setMargin(new Insets(5, 5, 5, 5));
    p2_rate.setColumns(5);
    p2_rate.setEditable(false);
    p2_rate.setFocusable(false);
    panel2.add(p2_rate);

    // Add JPanels to the JFrame
    frame.add(menu, BorderLayout.NORTH);
    frame.add(panel1, BorderLayout.CENTER);
    frame.add(panel2, BorderLayout.SOUTH);

    frame.getRootPane().setDefaultButton(button);

    frame.setVisible(true);
  }

  private JLabel createJLabel(String text) {
    JLabel label = new JLabel(text);
    label.setFont(new Font("Arial", Font.PLAIN, 16));
    return label;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      int num_sim = Integer.parseInt(text1.getText());
      int num_sticks = Integer.parseInt(text2.getText());
      int p1 = Integer.parseInt(text3.getText());
      int p2 = Integer.parseInt(text4.getText());
      int x = Integer.parseInt(text5.getText());
      int y = Integer.parseInt(text6.getText());
      int[] remove_sticks = {1, x, y};

      if (num_sim < 0 || num_sticks < 0 || p1 < 0 || p1 > 100 || p2 < 0 || p2 > 100 || x < 0 || y < 0) {
        throw new NumberFormatException();
      }

      error1.setText("                                             Green = Positional Advantage                                              ");
      error1.setForeground(Color.BLACK);
      error1.setVisible(true);

      simulate_games(num_sim, num_sticks, p1, p2, remove_sticks);
    } catch (NumberFormatException error) {
      error1.setText("                                             Error. Please try again.                                              ");
      error1.setForeground(Color.RED);
      error1.setVisible(true);
    }
  }

  
  // Handle the key-pressed event from the text field
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER){
      System.out.println("Hello");
    }  
  }

  public void keyTyped(KeyEvent e) {

  }

  public void keyReleased(KeyEvent e) {

  }

  private static void simulate_games(int num_simulations, int num_sticks, int p1, int p2, int[] remove_sticks) {
    IMPNimGameClass simulator = new IMPNimGameClass(num_sticks, p1, p2, remove_sticks);

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
    double p2_ratio = (double) p2_wins / (double) num_simulations * 100;

    String p1_result = df.format(p1_ratio) + "%";
    String p2_result = df.format(p2_ratio) + "%";

    p1_rate.setText(p1_result);
    p2_rate.setText(p2_result);

    int positional_advantage = simulator.perfect_winner_gui();
    if (positional_advantage == 1) {
      output_p1.setFont(new Font("Arial", Font.BOLD, 16));
      output_p1.setForeground(Color.GREEN);
      output_p2.setFont(new Font("Arial", Font.PLAIN, 16));
      output_p2.setForeground(Color.WHITE);
      p1_rate.setFont(new Font("Arial", Font.BOLD, 12));
      p2_rate.setFont(new Font("Arial", Font.PLAIN, 12));
    } else {
      output_p1.setFont(new Font("Arial", Font.PLAIN, 16));
      output_p1.setForeground(Color.WHITE);
      output_p2.setFont(new Font("Arial", Font.BOLD, 16));
      output_p2.setForeground(Color.GREEN);
      p1_rate.setFont(new Font("Arial", Font.PLAIN, 12));
      p2_rate.setFont(new Font("Arial", Font.BOLD, 12));
    }
  }

  private void tip() {
    System.out.println("----------------------------------------------------------------------------------");
    System.out.println("Tip: If a player and their win rate is bolded, they have the positional advantage.");
    System.out.println("----------------------------------------------------------------------------------");
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        NimSimulator1 nim = new NimSimulator1();
        nim.tip();
      }
    });
  }
}
