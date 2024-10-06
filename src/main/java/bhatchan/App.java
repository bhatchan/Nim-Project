package bhatchan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.*;

public class App {
    public static void main(String[] args) {
        int[] input = input();
        create_results(input);
    }

    private static int[] input() {
        Scanner s = new Scanner(System.in);
    
        System.out.print("Number of Simulations: ");
        int num_sim = s.nextInt();
    
        System.out.print("Enter n (lower bound): ");
        int n1 = s.nextInt();
    
        System.out.print("Enter n (upper bound): ");
        int n2 = s.nextInt();
    
        System.out.print("Enter n (step): ");
        int n_step = s.nextInt();

        s.close();
    
        int[] inputs = { num_sim, n1, n2, n_step };
        return inputs;
    }

    public static void create_results(int[] input) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("data");
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 1000);
        sheet.setColumnWidth(2, 1000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 4000);

        XSSFRow header = sheet.createRow(0);

        XSSFCell headerCell = header.createCell(0);
        headerCell.setCellValue("n");

        headerCell = header.createCell(1);
        headerCell.setCellValue("p1");

        headerCell = header.createCell(2);
        headerCell.setCellValue("p2");

        headerCell = header.createCell(3);
        headerCell.setCellValue("p1 win rate");

        headerCell = header.createCell(4);
        headerCell.setCellValue("p2 win rate");

        headerCell = header.createCell(5);
        headerCell.setCellValue("win rate difference");

        int rowNum = 1;
        for (int n = input[1]; n <= input[2]; n += input[3]) {
            for (double p = 0.9; p >= 0.0; p -= 0.1) {
                List<Double> results = simulate_nim(input, n, (int) (p*100));

                XSSFRow row = sheet.createRow(rowNum++);
                XSSFCell cell = row.createCell(0);
                cell.setCellValue(n);

                cell = row.createCell(1);
                cell.setCellValue(p);

                cell = row.createCell(2);
                cell.setCellValue(p);

                cell = row.createCell(3);
                cell.setCellValue(results.get(0));

                cell = row.createCell(4);
                cell.setCellValue(results.get(1));

                cell = row.createCell(5);
                cell.setCellValue(results.get(2));
            }
        }

        String fileName = "nim_results(" + input[1] + ", " + input[2] + ", " + input[3] + ")";
        try {
            File file = new File("results/" + fileName + ".xlsx");
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream("results/" + fileName + ".xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
        }    
    }

    public static List<Double> simulate_nim(int[] input, int n, int p) {
        int[] moveset = {1, 2, 3};
        nim_game simulator = new nim_game(n, p, p, moveset);

        int p1_wins = 0;
        int p2_wins = 0;

        for (int i = 0; i < input[0]; i++) {
            int winner = simulator.play_nim();

            if (winner == 1) {
                p1_wins++;
            } else {
                p2_wins++;
            }
        }

        double p1_ratio = (double) p1_wins / (double) input[0];
        double p2_ratio = (double) p2_wins / (double) input[0];
        double difference = Math.abs(p1_ratio - p2_ratio);

        List<Double> list = Arrays.asList(p1_ratio, p2_ratio, difference);

        return list;
    }
}
