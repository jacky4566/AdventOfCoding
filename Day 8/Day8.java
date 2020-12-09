import java.io.*;
import java.util.*;

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

        long accumulator1 = 0;
        int codePosition = 0;
        List<Instruction> modifiedSet1 = new ArrayList<>();
            for (Instruction p : instructionSet) {
                modifiedSet1.add(p.copy());
            }
        while (modifiedSet1.get(codePosition).executionCounter < 1) {// run till we are done
            modifiedSet1.get(codePosition).executionCounter++;
            switch (modifiedSet1.get(codePosition).operation) {
                case 'n': // No Op
                    codePosition++;
                    break;
                case 'j': // jump
                    codePosition = codePosition + modifiedSet1.get(codePosition).argument;
                    break;
                case 'a': // Add to accumulator
                    accumulator1 = accumulator1 + modifiedSet1.get(codePosition).argument;
                    codePosition++;
                    break;
                default:
                    printer = "Error";
                    System.out.println(printer);
                    break;
            }
            if (codePosition > modifiedSet1.size()) {
                codePosition = codePosition - modifiedSet1.size();
            }
        }
        printer = "Part 1 Accumulator " + accumulator1;
        System.out.println(printer);

        // Part 2 Traverse code
        long accumulator2 = 0;
        for (int i = 0; i < instructionSet.size(); i++) { // Go through list changing one value at a time
            List<Instruction> modifiedSet = new ArrayList<>();
            for (Instruction p : instructionSet) {
                modifiedSet.add(p.copy());
            }
            modifiedSet.get(i).swapOP(); // Swap operations
            accumulator2 = execute(modifiedSet);
            if (accumulator2 > 0) {
                break;
            }
        }
        printer = "Part 2 Accumulator " + accumulator2;
        System.out.println(printer);
    }

    public static long execute(List<Instruction> instructions) {
        long accumulator = 0;
        int codePosition = 0;
        while (codePosition < instructions.size()) {// run till we are done
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
                    String printer = "Error";
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

    public void swapOP() {
        if (this.operation == 'j') {
            this.operation = 'n';
        } else if (this.operation == 'n') {
            this.operation = 'j';
        }
    }

    public Instruction copy() { //used to make a deep copy
        Instruction r = new Instruction();
        r.operation = this.operation;
        r.argument = this.argument;
        r.executionCounter = 0;
        return r;
    }
}