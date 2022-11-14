import java.io.*;
import java.io.IOException;
import java.util.*;

public class Day6 {
    public static final int AIRPLANEROWS = 128;
    public static final int AIRPLANECOLS = 8;

    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 6";
        System.out.println(printer);
        // Setup array of passports
        ArrayList<BoardingGroup> CustomsList = new ArrayList<BoardingGroup>();
        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            int lineCounter = 0;
            int groupCounter = 0;
            CustomsList.add(new BoardingGroup());
            while (myReader.hasNextLine()) {
                String newData = myReader.nextLine();
                if (newData.length() == 0) {
                    groupCounter++;
                    if (myReader.hasNextLine()) { // If there is another line add new group
                        CustomsList.add(new BoardingGroup());
                    }
                } else {
                    CustomsList.get(groupCounter).groupFlags.add(newData);
                }
                lineCounter++;
            }
            printer = "Items read " + CustomsList.size();
            System.out.println(printer);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // sum of unique flags
        int sumOfUniqueFlags = 0;
        for (int i = 0; i < CustomsList.size(); i++) {
            sumOfUniqueFlags = sumOfUniqueFlags + CustomsList.get(i).uniqueFlags();
        }
        printer = "Sum Of Unique Flags: " + sumOfUniqueFlags;
        System.out.println(printer);

        // sum of common flags
        int groupsWithCommonFlags = 0;
        for (int i = 0; i < CustomsList.size(); i++) {
            groupsWithCommonFlags = groupsWithCommonFlags + CustomsList.get(i).groupsWithCommonFlags();
        }
        printer = "Groups With Common Flags: " + groupsWithCommonFlags;
        System.out.println(printer);
    }
}

class BoardingGroup {
    List<String> groupFlags = new ArrayList<>();

    public int uniqueFlags() {
        int uniqueFlags = 0;
        boolean[] flagIDs = new boolean[26];
        for (int i = 0; i < groupFlags.size(); i++) {
            for (int j = 0; j < groupFlags.get(i).length(); j++) {
                int flag = groupFlags.get(i).charAt(j) - 'a';
                if (!flagIDs[flag]) { // if not set
                    flagIDs[flag] = true;
                    uniqueFlags++;
                }
            }
        }
        return uniqueFlags;
    }

    public int groupsWithCommonFlags() {
        int commonFlags = 0;
        for (int flag = 0; flag < 26; flag++) {//for each flag, check if exists in every string
            int flagTrueCount = 0;
            for (int i = 0; i < groupFlags.size(); i++) { // for each string
                if (groupFlags.get(i).indexOf((char) (flag + 'a')) != -1) {
                    flagTrueCount++;
                }
            }
            if (flagTrueCount == groupFlags.size()) {
                commonFlags++;
            }
        }
        return commonFlags;
    }

}
