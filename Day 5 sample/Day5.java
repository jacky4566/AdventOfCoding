import java.io.*;
import java.io.IOException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            ArrayList<Integer> seats = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
                String row = line.substring(0, 7).replaceAll("F", "0").replaceAll("B", "1");
                String col = line.substring(7).replaceAll("L", "0").replaceAll("R", "1");
    
                int r = Integer.parseInt(row, 2);
                int c = Integer.parseInt(col, 2);
                int i = r * 8 + c;
                //System.out.println(r+"\t"+c+"\t"+i);
    
                seats.add(i);
            }
            
            Collections.sort(seats);
            System.out.println(seats.get(seats.size()-1));
            int seat = seats.get(0);
            for(int i = 1; i < seats.size(); i++) {
                seat++;
                if(seats.get(i)!=seat) {
                    System.out.println(seat);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}