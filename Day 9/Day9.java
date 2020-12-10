import java.io.*;
import java.util.*;

public class Day9 {
    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 9";
        System.out.println(printer);
        // Setup array of passports
        int preambleLength = 25;
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

        // Part 1 Solution
        long fracture = -1;
        for (int i = preambleLength; i < inputData.size(); i++) { // start at the 5th number
            long testNumber = inputData.get(i).longValue();
            boolean failTest = true;
            for (int j = i - preambleLength; j < i && failTest; j++) { // check the past preambles
                for (int h = i - preambleLength; h < i && failTest; h++) { // check the past preambles
                    if (inputData.get(j).longValue() + inputData.get(h).longValue() == testNumber) {
                        failTest = false;
                    } else {
                        fracture = testNumber;
                    }
                }
            }
            if (failTest) {
                printer = "Item failed test " + fracture;
                System.out.println(printer);
                break;
            }
        }
        // Part Two
        for (int i = 2; i < inputData.size(); i++) { // start at the 2nd number
            long accumulator = 0;
            long smallest = 99999999;
            long largest = 0;
            for (int j = i; j < inputData.size(); j++) {
                long nextValue = inputData.get(j).longValue();
                accumulator = accumulator + nextValue;
                if (nextValue > largest){
                    largest = nextValue;
                }
                if (nextValue < smallest){
                    smallest = nextValue;
                }
                if (accumulator == fracture) {
                    printer = "Found: " + i + " " + j + " " + (smallest + largest);
                    System.out.println(printer);
                }
            }

        }
    }
}
