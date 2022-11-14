import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Solving Advent of Coding Day 1");
        List<Integer> allNumbers = new ArrayList<>();

        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int StringAsInt = Integer.parseInt(data);
                allNumbers.add(StringAsInt);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // process file and print results
        System.out.println("Suitable combinations are:");
        System.out.println("Part 1:");
        for (int i = 0; i < allNumbers.size(); i++) {
            for (int j = i; j < allNumbers.size(); j++) {
                if ((allNumbers.get(i) + allNumbers.get(j)) == (int) 2020) {
                    String answer = allNumbers.get(i) + " + " + allNumbers.get(j) + " = "
                            + (allNumbers.get(i) + allNumbers.get(j));
                    String answer2 = allNumbers.get(i) + " * " + allNumbers.get(j) + " = "
                            + (allNumbers.get(i) * allNumbers.get(j));
                    System.out.println(answer);
                    System.out.println(answer2);
                }
            }
        }
        // Part 2
        System.out.println("Part 2:");
        for (int i = 0; i < allNumbers.size(); i++) {
            for (int j = i; j < allNumbers.size(); j++) {
                for (int k = j; k < allNumbers.size(); k++) {
                    if ((allNumbers.get(i) + allNumbers.get(j) + allNumbers.get(k)) == (int) 2020) {
                        String answer = allNumbers.get(i) + " + " + allNumbers.get(j) + " + " + allNumbers.get(k)
                                + " = " + (allNumbers.get(i) + allNumbers.get(j) + allNumbers.get(k));
                        String answer2 = allNumbers.get(i) + " * " + allNumbers.get(j) + " * " + allNumbers.get(k)
                                + " = " + (allNumbers.get(i) * allNumbers.get(j) * allNumbers.get(k));
                        System.out.println(answer);
                        System.out.println(answer2);
                    }
                }
            }
        }
        System.out.println("Done");
    }
}
