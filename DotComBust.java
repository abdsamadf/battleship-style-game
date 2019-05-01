import java.util.ArrayList;

/**
 * DotComBust
 */
public class DotComBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    private int numOfGuesses;

    public void setUpGame() {
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");

        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);
        System.out.println("Your goal is to sink three dot coms.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in the fewest number of guesses");
        for (DotCom dotCom : dotComList) {
            ArrayList<String> newLoacation = helper.placeDotCom(3);
            dotCom.setLocationCells(newLoacation);
        }
    }

    public void startPlaying() {
        ArrayList<String> locationCells = new ArrayList<String>();
        for (DotCom dotCom : dotComList) {
            locationCells.addAll(dotCom.locationCells);
        }

        ArrayList<String> wrongUserGuesses = new ArrayList<String>();
        ArrayList<String> correctUserGuesses = new ArrayList<String>();

        while (!dotComList.isEmpty()) {
            helper.gridDisplay(correctUserGuesses, wrongUserGuesses);
            String userGuess = helper.getUserInput("enter a guess");
            if (locationCells.contains(userGuess)) {
                correctUserGuesses.add(userGuess);
            }
            else {
                wrongUserGuesses.add(userGuess);
            }
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    public void checkUserGuess(String userGuess) {
        numOfGuesses += 1;
        String result = "miss";
        for (DotCom dotCom : dotComList) {
            result = dotCom.checkYourself(userGuess);
            if (result.equals("hit")) {
                break;
            }
            if (result == "kill") {
                result += " " + dotCom.getName();
                dotComList.remove(dotCom);
                break;
            }

        }

        System.out.println("Result: " + result);
    }

    public void finishGame() {
        System.out.println("All Dot Coms are dead! Your stock is now worthless.");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}