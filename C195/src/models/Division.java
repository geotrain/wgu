package models;

public class Division {

    // Declare Integer and String Variables For first_level_divisions table
    private int divisionID, countryID;
    private String division;

    /**
     * Constructor for Division Class
     * @param divisionID
     * @param countryID
     * @param division
     */
    public Division(int divisionID, int countryID, String division) {
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.division = division;
    }

    /**
     * getDivisionID method returns divisionID
     * @return
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * setDivisionID method returns divisionID
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * getCountryID method returns countryID
     * @return
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * setCountryID method returns countryID
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * getDivision method returns division
     * @return
     */
    public String getDivision() {
        return division;
    }

    /**
     * setDivision method returns division
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    // Override method to convert division to a String
    @Override public String toString() {
        return division;
    }
}
