import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    public static final int AIRPLANEROWS = 128;
    public static final int AIRPLANECOLS = 8;

    public static void main(String[] args) throws IOException {
        String printer = "Solving Advent of Coding Day 7";
        System.out.println(printer);
        // Setup array of passports
        ArrayList<LuggageRule> processingRules = new ArrayList<>();
        Pattern parentPattern = Pattern.compile("^\\w+ \\w+");
        Pattern childPattern = Pattern.compile("\\d+ \\w+ \\w+");
        // Read file and put into list
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            int lineCounter = 0;
            while (myReader.hasNextLine()) {
                if (myReader.hasNextLine()) {
                    processingRules.add(new LuggageRule());
                }
                String newData = myReader.nextLine();
                // Parent
                Matcher parentMatch = parentPattern.matcher(newData);
                parentMatch.find();
                processingRules.get(lineCounter).parentDescription.descripOne = parentMatch.group().split(" ")[0];
                processingRules.get(lineCounter).parentDescription.descripTwo = parentMatch.group().split(" ")[1];
                // Children
                Matcher childMatch = childPattern.matcher(newData);
                int childNumber = 0;
                while (childMatch.find()) {
                    processingRules.get(lineCounter).childDescriptions.add(new BagDescription());
                    processingRules.get(lineCounter).childDescriptions.get(childNumber).maxBags = Integer
                            .parseInt(childMatch.group().split(" ")[0]);
                    processingRules.get(lineCounter).childDescriptions.get(childNumber).descripOne = childMatch.group()
                            .split(" ")[1];
                    processingRules.get(lineCounter).childDescriptions.get(childNumber).descripTwo = childMatch.group()
                            .split(" ")[2];
                    childNumber++;
                }
                lineCounter++;
            }
            printer = "Items read " + processingRules.size();
            System.out.println(printer);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int canContainOneGoldBag = 0; // Count the number of bags and parent bags that can hold a shiny gold bag
        Queue<BagDescription> queue = new LinkedList<>(); // a target list of bags than can hold gold or parents of gold
        BagDescription goldBag = new BagDescription();
        goldBag.descripOne = "shiny";
        goldBag.descripTwo = "gold";
        queue.add(goldBag); // Add golden ticket to search queue
        while (!queue.isEmpty()) {
            BagDescription currentTargetBag = queue.poll(); // get new search target
            for (int i = 0; i < processingRules.size(); i++) {
                for (int j = 0; j < processingRules.get(i).childDescriptions.size(); j++) { // search list for target
                    if (processingRules.get(i).childDescriptions.get(j).descripOne.contains(currentTargetBag.descripOne)
                            && processingRules.get(i).childDescriptions.get(j).descripTwo
                                    .contains(currentTargetBag.descripTwo)) {
                        // add parent to queue
                        BagDescription newTargetBag = new BagDescription();
                        newTargetBag.descripOne = processingRules.get(i).parentDescription.descripOne;
                        newTargetBag.descripTwo = processingRules.get(i).parentDescription.descripTwo;
                        queue.add(newTargetBag);
                        canContainOneGoldBag++;
                        break;
                    }
                }
            }
        }

        printer = "Can Contain One Gold Bag: " + canContainOneGoldBag;
        System.out.println(printer);
    }
}

class LuggageRule { // Defines a luggage rule
    BagDescription parentDescription = new BagDescription(); // desciption of the bag
    List<BagDescription> childDescriptions = new ArrayList<>(); // desciptions of the bags that fit inside
}

class BagDescription { // defines a piece of luggage and its count
    int maxBags = 0;
    String descripOne = "";
    String descripTwo = "";
}
