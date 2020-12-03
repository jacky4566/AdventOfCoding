import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 3";
        System.out.println(printer);
        Terrain myTerrain = new Terrain();
        // Read file and put into list
        File myObj = new File("input.txt");
        try (InputStream in = new FileInputStream(myObj); Reader reader = new InputStreamReader(in);) {
            int lineCounter = 0;
            boolean keepReading = true;
            int c = reader.read();
            myTerrain.arborealTopography.add(new ArrayList<Boolean>()); // add 0th row
            while (keepReading) {
                switch (c) {
                    case 46: // . Empty Space
                        myTerrain.arborealTopography.get(lineCounter).add(false);
                        break;
                    case 35: // # Tree
                        myTerrain.arborealTopography.get(lineCounter).add(true);
                        break;
                    case 13: // CR
                        // Do nothing
                        break;
                    case -1: // EOF
                        keepReading = false;
                        break;
                    case 10: // NL
                        lineCounter++;
                        myTerrain.arborealTopography.add(new ArrayList<Boolean>()); // add new row
                        break;
                    default:
                        printer = "unknown char " + c;
                        System.out.println(printer);
                        break;
                }
                c = reader.read();
            }
        } catch (Exception e) {
            printer = "Exception occurred " + e;
            System.out.println(printer);
        }
        printer = "Done. Trees Smashed with slope 1, 1: " + myTerrain.processTerrain(1, 1);
        System.out.println(printer);
        printer = "Done. Trees Smashed with slope 3, 1: " + myTerrain.processTerrain(3, 1);
        System.out.println(printer);
        printer = "Done. Trees Smashed with slope 5, 1: " + myTerrain.processTerrain(5, 1);
        System.out.println(printer);
        printer = "Done. Trees Smashed with slope 7, 1: " + myTerrain.processTerrain(7, 1);
        System.out.println(printer);
        printer = "Done. Trees Smashed with slope 1, 2: " + myTerrain.processTerrain(1, 2);
        System.out.println(printer);
        long allTheTrees = myTerrain.processTerrain(1, 1) * myTerrain.processTerrain(3, 1) * myTerrain.processTerrain(5, 1) * myTerrain.processTerrain(7, 1) * myTerrain.processTerrain(1, 2);
        printer = "Done. Total Trees Smashed: " + allTheTrees;
        System.out.println(printer);
    }
}

class Terrain {
    List<List<Boolean>> arborealTopography = new ArrayList<>(); // true for tree, false for open space

    public long processTerrain(int slopeX, int slopeY) {
        int numberOfTrees = 0; // Keep track of squrrels
        int tobogganPositionX = 0;
        int tobogganPositionY = 0;
        while (tobogganPositionY + slopeY < arborealTopography.size()) {
            tobogganPositionX = tobogganPositionX + slopeX; // move Toboggan
            tobogganPositionY = tobogganPositionY + slopeY; // move Toboggan
            // Check for out of bounds
            if (tobogganPositionX >= arborealTopography.get(0).size()) {
                tobogganPositionX = tobogganPositionX - arborealTopography.get(0).size();
            } // Check for tree
            if ((boolean) arborealTopography.get(tobogganPositionY).get(tobogganPositionX)) {
                numberOfTrees++;
            }
        } // while we are within the terrain
        return numberOfTrees;
    }
}
