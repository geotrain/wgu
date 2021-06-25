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
     * @param division This is a parameter
     */
    public static void addDivision(Division division) {
        allDivisions.add(division);
    }

    /**
     * Observable List to call getAllDivisions method
     * @return This is a return statement
     */
    public static ObservableList<Division>getAllDivisions() {
        return allDivisions;
    }

    /**
     * This adds a new country
     * @param countries This is a parameter
     */
    public static void addCountry(Countries countries) {
        allCountries.add(countries);
    }

    /**
     * This observable list gets all countries
     * @return This is a return statement
     */
    public static ObservableList<Countries>getAllCountries() {
        return allCountries;
    }

    /**
     * This overservable list gets all countries by division id
     * @param id This is a parameter
     * @return This is a return statement
     */
    public static ObservableList<Division> getDivisionsByCountryId(int id) {
        ObservableList<Division> division = FXCollections.observableArrayList();
        for (Division D: allDivisions) {
            if (D.getCountryID() == id)
                division.add(D);
        }
        return division;
    }
}
