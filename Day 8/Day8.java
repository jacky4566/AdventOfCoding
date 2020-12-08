import java.io.*;
import java.util.*;

public class Day8 {
    public static final int AIRPLANEROWS = 128;
    public static final int AIRPLANECOLS = 8;

    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 8";
        System.out.println(printer);
        // Setup array of passports
        ArrayList<Instruction> instructionSet = new ArrayList<>();
        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String newData = myReader.nextLine();
                String splitData[] = newData.split(" ");
                Instruction newInstruction = new Instruction();
                newInstruction.operation = splitData[0].charAt(0);
                newInstruction.argument = Integer.parseInt(splitData[1]);
                instructionSet.add(newInstruction);
            }
            printer = "Items read " + instructionSet.size();
            System.out.println(printer);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Traverse code
        long accumulator = 0;
        int codePosition = 0;
        while (instructionSet.get(codePosition).executionCounter < 1) {// run till we are done
            instructionSet.get(codePosition).executionCounter++;
            printer = "Instruction " + codePosition + " : " + instructionSet.get(codePosition).operation + " : " + instructionSet.get(codePosition).argument;
            System.out.println(printer);
            switch (instructionSet.get(codePosition).operation) {
                case 'n': // No Op
                    codePosition++;
                    break;
                case 'j': // Jump
                    codePosition = codePosition + instructionSet.get(codePosition).argument;
                    break;
                case 'a': // Add to accumulator
                    accumulator = accumulator + instructionSet.get(codePosition).argument;
                    codePosition++;
                    break;
                default:
                    printer = "Error";
                    System.out.println(printer);
                    break;
            }
            if (codePosition > instructionSet.size()) {
                codePosition = codePosition - instructionSet.size();
            }
        }
        printer = "accumulator " + accumulator;
        System.out.println(printer);
    }
}

class Instruction { // defines a piece of luggage and its count
    char operation;
    int argument;
    int executionCounter = 0;
}
