import java.io.*;
import java.io.IOException;
import java.util.*;

public class Day4 {
    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 4";
        System.out.println(printer);
        // Setup array of passports
        List<PassportSample> allPassports = new ArrayList<>();
        allPassports.add(new PassportSample()); // add 0th row
        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            myReader.useDelimiter("\s|\n");
            int lineCounter = 0;
            String[] nextInformationPiece;
            while (myReader.hasNext()) {
                nextInformationPiece = myReader.next().split(":");
                switch (nextInformationPiece[0]) {
                    case "byr":
                        allPassports.get(lineCounter).byr = nextInformationPiece[1];
                        break;
                    case "iyr":
                        allPassports.get(lineCounter).iyr = nextInformationPiece[1];
                        break;
                    case "eyr":
                        allPassports.get(lineCounter).eyr = nextInformationPiece[1];
                        break;
                    case "hgt":
                        allPassports.get(lineCounter).hgt = nextInformationPiece[1];
                        break;
                    case "hcl":
                        allPassports.get(lineCounter).hcl = nextInformationPiece[1];
                        break;
                    case "ecl":
                        allPassports.get(lineCounter).ecl = nextInformationPiece[1];
                        break;
                    case "pid":
                        allPassports.get(lineCounter).pid = nextInformationPiece[1];
                        break;
                    case "cid":
                        allPassports.get(lineCounter).cid = nextInformationPiece[1];
                        break;
                    case "\r":
                        lineCounter++;
                        allPassports.add(new PassportSample()); // add new row
                        break;
                    case "\n":
                        break;
                    default:
                        printer = "unknown information " + nextInformationPiece[0];
                        System.out.println(printer);
                        break;
                }
            }
            printer = "Passports read " + lineCounter;
            System.out.println(printer);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int validPassports = 0;
        for (int i = 0; i < allPassports.size(); i++) {
            if (allPassports.get(i).verify()) {
                validPassports++;
            }
        }
        printer = "Valid Passports " + validPassports;
        System.out.println(printer);
    }
}

class PassportSample {
    String byr; // Birth Year
    String iyr; // Issue Year
    String eyr; // Expiration Year
    String hgt; // Height
    String hcl; // Hair Color
    String ecl; // Eye Color
    String pid; // Passport ID
    String cid; // Country ID

    public boolean verify() { // checks if this passport is valid
        if (!((byr != null) && (iyr != null) && (eyr != null) && (hgt != null) && (hcl != null) && (ecl != null)
                && (pid != null))) {
            return false;
        }
        return true;
    }
}
