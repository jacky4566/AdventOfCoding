import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 2";
        System.out.println(printer);
        List<passwordSample> allPasswords = new ArrayList<>();

        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                passwordSample newSample = new passwordSample();
                String data = myReader.nextLine();
                String[] dataArray = data.split("\\-|\\ |\\:");
                newSample.minCount = Integer.parseInt(dataArray[0]);
                newSample.maxCount = Integer.parseInt(dataArray[1]);
                newSample.controlCharacter = dataArray[2].charAt(0);
                newSample.password = dataArray[4];
                allPasswords.add(newSample);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // process file and print results
        // Part 1
        int validPasswordsPart1 = 0;
        for (int i = 0; i < allPasswords.size(); i++) {
            passwordSample testSample = allPasswords.get(i);
            if (testSample.check1()) {
                validPasswordsPart1++;
            }
        }
        // Part 2
        int validPasswordsPart2 = 0;
        for (int i = 0; i < allPasswords.size(); i++) {
            passwordSample testSample = allPasswords.get(i);
            if (testSample.check2()) {
                validPasswordsPart2++;
            }
        }
        printer = "Total Passwords: " + allPasswords.size();
        System.out.println(printer);
        printer = "Valid Passwords Part 1: " + validPasswordsPart1;
        System.out.println(printer);
        printer = "Valid Passwords Part 2: " + validPasswordsPart2;
        System.out.println(printer);
        System.out.println("Done");
    }
}

class passwordSample {
    public int minCount = 0;
    public int maxCount = 0;
    public char controlCharacter;
    public String password = "";

    boolean check1() {// checks if password matches rules
        int counter = 0;
        for (int i = 0; i < password.length(); i++) { // iterate through password with control character
            if (password.charAt(i) == controlCharacter) { // if char at i equals control character incrament counter
                counter++;
            }
        }
        if (counter >= minCount && counter <= maxCount) { // check if count is between values
            return true;
        } else {
            return false;
        }
    }

    boolean check2() {// checks if password matches rules
        if ((boolean) (password.charAt(minCount - 1) == controlCharacter) // Check for exclusive OR match.
                                                                          // Check for out of bounds on max value
                ^ (boolean) (maxCount <= password.length() ? password.charAt(maxCount - 1) == controlCharacter
                        : false)) {
            return true;
        }
        return false;
    }
}
