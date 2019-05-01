import java.io.*;
import java.util.*;

public class GameHelper {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public void gridDisplay(ArrayList<String> correctUserGuesses, ArrayList<String> wrongUserGuesses) {
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("a");
        aList.add("b");
        aList.add("c");
        aList.add("d");
        aList.add("e");
        aList.add("f");
        aList.add("g");


        System.out.println("  + - + - + - + - + - + - + - +");
        for (String s : aList) {
            System.out.print(s + " |");
            for (int i = 0; i < gridSize / aList.size(); i++) {
                if (correctUserGuesses.contains(s + i)) {
                    System.out.print(" 1 ");
                } else if (wrongUserGuesses.contains(s + i)) {
                    System.out.print(" X ");
                }
                else {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.println("\n  + - + - + - + - + - + - + - +");
        }
        System.out.println("    0   1   2   3   4   5   6");

    }

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0)
                return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        String[] alphacoords = new String[comSize];
        String temp = null;
        int[] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;
        comCount++;
        int incr = 1;
        if ((comCount % 2) == 1) { // if odd dot com (place vertically)
            incr = gridLength; // set vertical increment
        }

        while (!success & attempts++ < 200) {
            location = (int) (Math.random() * gridSize); // get random starting point
            // System.out.print(" try " + location);
            int x = 0;
            success = true;
            while (success && x < comSize) {
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {
                        success = false;
                    }
                } else { // found already used location
                    // System.out.print(" used " + location);
                    success = false;
                }
            }
        }

        int x = 0; // turn location into alpha coords
        int row = 0;
        int column = 0;
        // System.out.println(“\n");
        while (x < comSize) {
            grid[coords[x]] = 1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(alphabet.charAt(column)); // convert to alpha
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        // System.out.println("\n");
        return alphaCells;
    }
}