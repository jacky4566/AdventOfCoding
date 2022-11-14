import java.io.*;
import java.io.IOException;
import java.util.*;

public class Day5 {
    public static final int AIRPLANEROWS = 128;
    public static final int AIRPLANECOLS = 8;

    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 5";
        System.out.println(printer);
        // Setup array of passports
        ArrayList<Integer> seats = new ArrayList<Integer>();
        BoardingPass[][] seatGrid = new BoardingPass[AIRPLANEROWS][AIRPLANECOLS];
        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            myReader.useDelimiter("\n|\r");
            int lineCounter = 0;
            while (myReader.hasNextLine()) {
                BoardingPass newPass = new BoardingPass();
                newPass.inputString = myReader.nextLine();
                newPass.decodeString();
                newPass.computeSeatID();
                seatGrid[newPass.row][newPass.col] = newPass;
                lineCounter++;
                seats.add(newPass.seatID);
            }
            printer = "Items read " + lineCounter;
            System.out.println(printer);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int highestID = 0;
        for (int i = 0; i < AIRPLANEROWS; i++) {
            for (int j = 0; j < AIRPLANECOLS; j++) {
                if (seatGrid[i][j] != null) {
                    if (seatGrid[i][j].seatID > highestID) {
                        highestID = seatGrid[i][j].seatID;
                    }
                }
            }
        }
        printer = "Highest ID: " + highestID;
        System.out.println(printer);

        // Find blank by grid, no works
        for (int i = 0; i < AIRPLANEROWS; i++) {
            for (int j = 0; j < AIRPLANECOLS; j++) {
                if (seatGrid[i][j] == null) { // look for null spot in grid
                    BoardingPass newPass = new BoardingPass();
                    newPass.col = j;
                    newPass.row = i;
                    int thisID = newPass.computeSeatID();
                    printer = "Blank ID seat: " + i + " " + j + " " + thisID;
                    System.out.println(printer);
                    System.out.println("");
                }
            }
        }
    }
}

class BoardingPass {
    String inputString = "";
    int row = 0;
    int col = 0;
    int seatID = 0;

    public void decodeString() {
        row = Integer.parseInt(inputString.substring(0, 7).replaceAll("F", "0").replaceAll("B", "1"), 2);
        col = Integer.parseInt(inputString.substring(7).replaceAll("L", "0").replaceAll("R", "1"), 2);
    }

    public int computeSeatID() { // checks if this passport is valid
        seatID = (row * 8) + col;
        return seatID;
    }
}
