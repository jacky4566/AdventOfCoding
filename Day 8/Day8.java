import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.toList;

public class Day8 {
    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 8";
        System.out.println(printer);
        // Setup array of passports
        List<Instruction> instructionSet = new ArrayList<>();
        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String newData = myReader.nextLine();
                String[] splitData = newData.split(" ");
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

        long accumulator = 0;
        int codePosition = 0;
        List<Instruction> part1Set = List.copyOf(instructionSet);
        while (part1Set.get(codePosition).executionCounter < 1) {// run till we are done
            part1Set.get(codePosition).executionCounter++;
            switch (part1Set.get(codePosition).operation) {
                case 'n': // No Op
                    codePosition++;
                    break;
                case 'j': // Jump
                    codePosition = codePosition + part1Set.get(codePosition).argument;
                    break;
                case 'a': // Add to accumulator
                    accumulator = accumulator + part1Set.get(codePosition).argument;
                    codePosition++;
                    break;
                default:
                    printer = "Error";
                    System.out.println(printer);
                    break;
            }
            if (codePosition > part1Set.size()) {
                codePosition = codePosition - part1Set.size();
            }
        }
        printer = "Part 1 Accumulator " + accumulator;
        System.out.println(printer);
        System.out.println();

        // Part 2 Traverse code
        accumulator = 0;
        for (int i = 0; i < instructionSet.size(); i++) {
            printer = "Change Position " + i;
            System.out.println(printer);
            List<Instruction> modifiedSet = instructionSet.stream().collect(toList());
            if (modifiedSet.get(i).operation == 'j') {
                modifiedSet.get(i).operation = 'n';
            } else if (modifiedSet.get(i).operation == 'n') {
                modifiedSet.get(i).operation = 'j';
            }
            accumulator = execute(modifiedSet);
            if (accumulator > 0) {
                break;
            }
            for (int j = 0; j < instructionSet.size(); j++) { // dump list
                printer = modifiedSet.get(j).operation + " " + modifiedSet.get(j).argument;
                System.out.println(printer);
            }
            System.out.println();
        }
        printer = "Part 2 Accumulator " + accumulator;
        System.out.println(printer);
    }

    public static long execute(List<Instruction> instructions) {
        long accumulator = 0;
        int codePosition = 0;
        String printer = "Starting new exec";
        System.out.println(printer);
        while (codePosition < instructions.size()) {// run till we are done
            printer = "position " + codePosition + " " + instructions.get(codePosition).operation;
            System.out.println(printer);
            if (instructions.get(codePosition).executionCounter > 0) {// we already visted this node
                return -1;
            }
            instructions.get(codePosition).executionCounter++;
            switch (instructions.get(codePosition).operation) {
                case 'n': // No Op
                    codePosition++;
                    break;
                case 'j': // Jump
                    codePosition = codePosition + instructions.get(codePosition).argument;
                    break;
                case 'a': // Add to accumulator
                    accumulator = accumulator + instructions.get(codePosition).argument;
                    codePosition++;
                    break;
                default:
                    printer = "Error";
                    System.out.println(printer);
                    break;
            }
        }
        return accumulator;
    }
}

class Instruction { // defines a piece of luggage and its count
    char operation;
    int argument;
    int executionCounter = 0;
}