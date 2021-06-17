package utilities;

// Import statements
import DBAccess.DBDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Countries;
import models.Division;

public class DataProvider {
    /**
     * Observable List for the Division ID
     */
    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

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

    public static void addCountry(Countries countries) {
        allCountries.add(countries);
    }

    public static ObservableList<Countries>getAllCountries() {
        return allCountries;
    }

    public static ObservableList<Division> getDivisionsByCountryId(int id) {
        ObservableList<Division> division = FXCollections.observableArrayList();
        for (Division D: allDivisions) {
            if (D.getCountryID() == id)
                division.add(D);
        }
        return division;
    }
}
