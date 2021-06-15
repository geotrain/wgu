package utilities;

// Import statements
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Division;

public class DataProvider {
    /**
     * Observable List for the Division ID
     */
    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    /**
     * addDivision method
     * @param division
     */
    public static void addDivision(Division division) {
        allDivisions.add(division);
    }

    /**
     * Observable List to call getAllDivisions method
     * @return
     */
    public static ObservableList<Division>getAllDivisions() {
        return allDivisions;
    }
}
