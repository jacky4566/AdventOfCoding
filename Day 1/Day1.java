import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Solving Advent of Coding Day 1");
        List<Integer> AllNumbers = new ArrayList<>();

        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int StringAsInt = Integer.parseInt(data);
                AllNumbers.add(StringAsInt);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // process file and print results
        System.out.println("Suitable combinations are:");

        for (int i = 0; i < AllNumbers.size(); i++) {
            for (int j = i; j < AllNumbers.size(); j++) {
                if ((AllNumbers.get(i) + AllNumbers.get(j)) == (int) 2020) {
                    String answer = AllNumbers.get(i) + " + " + AllNumbers.get(j) + " = " + "2020";
                    System.out.println(answer);
                }
            }
        }
        System.out.println("Done");
    }
}
