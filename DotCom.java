import java.util.ArrayList;

/**
 * DotCom
 */
public class DotCom {

    public ArrayList<String> locationCells;
    private String name;

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);

        if (index >= 0) {
            locationCells.remove(index);

            if (locationCells.isEmpty()) {
                result = "kill";
            } else {
                result = "hit";
            }
        }

        return result;
    }

    /**
     * @param locationCells the locationCells to set
     */
    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}