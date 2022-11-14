import java.io.*;
import java.util.*;

public class Day10 {
    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 10";
        System.out.println(printer);
        List<Long> inputData = new ArrayList<>();
        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String newData = myReader.nextLine();
                inputData.add(Long.parseLong(newData));
            }
            printer = "Items read " + inputData.size();
            System.out.println(printer);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Part 1
        Collections.sort(inputData);
        System.out.println(inputData);
        long diffOneJolt = 0;
        long diffThreeJolt = 0;
        long currentJolts = 0;
        for (int i = 0; i < inputData.size(); i++) {
            if (inputData.get(i).longValue() - currentJolts == 1) {
                diffOneJolt++;
            } else if (inputData.get(i).longValue() - currentJolts == 3) {
                diffThreeJolt++;
            }
            currentJolts = inputData.get(i).longValue();
        }
        diffThreeJolt++; // plus final adapter
        printer = "Differences of 1 Jolt " + diffOneJolt;
        System.out.println(printer);
        printer = "Differences of 3 Jolt " + diffThreeJolt;
        System.out.println(printer);
        System.out.println();
        System.out.println("Part 2");
        // Part 2
        joltAdapter root = new joltAdapter(0, null);
        joltAdapter index = root;
        boolean traversing = true;
        while (traversing) {
            // traverse tree and add where appropriate
            for (int i = 0; i < inputData.size(); i++) { // for all our adapters can any be attached to index
                long nextAdapter = inputData.get(i).longValue();
                if ((nextAdapter - index.output) == 1) {
                    index.oneJolt = new joltAdapter(nextAdapter, index);
                    printer = "oneJolt";
                    System.out.println(printer);
                } else if ((nextAdapter - index.output) == 2) {
                    index.twoJolt = new joltAdapter(nextAdapter, index);
                    printer = "twoJolt";
                    System.out.println(printer);
                } else if ((nextAdapter - index.output) == 3) {
                    index.threeJolt = new joltAdapter(nextAdapter, index);
                    printer = "index: " + index.output + " next:" + nextAdapter;
                    System.out.println(printer);
                }
            }
            if (index.oneJolt != null) {
                index = index.oneJolt;
            } else if (index.twoJolt != null) {
                index = index.twoJolt;
            } else if (index.threeJolt != null) {
                index = index.threeJolt;
            } else if (index.parent != null) {
                index = index.parent;
            } else if (index.parent == null) {
                traversing = false;
            }
            // go to next left, middle, or right index
            // If all null go to parent
            // if at parent we are done.
        }
    }
}

class joltAdapter {
    long output;
    joltAdapter parent;
    joltAdapter oneJolt;
    joltAdapter twoJolt;
    joltAdapter threeJolt;

    joltAdapter(long value, joltAdapter oldman) {
        this.output = value;
        this.parent = oldman;
    }
}
