package models;

public class Division {

    // Declare Integer and String Variables For first_level_divisions table
    private int divisionID, countryID;
    private String division;

    /**
     * Constructor for Division Class
     * @param divisionID This is a parameter
     * @param countryID This is a parameter
     * @param division This is a parameter
     */
    public Division(int divisionID, int countryID, String division) {
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.division = division;
    }

    /**
     * getDivisionID method returns divisionID
     * @return This is a return statement
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * setDivisionID method returns divisionID
     * @param divisionID This is a parameter
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * getCountryID method returns countryID
     * @return This is a return statement
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * setCountryID method returns countryID
     * @param countryID This is a parameter
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * getDivision method returns division
     * @return This is a return statement
     */
    public String getDivision() {
        return division;
    }

    /**
     * setDivision method returns division
     * @param division This is a parameter
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * This changes division to a string
     * @return This is a return statement
     */
    @Override public String toString() {
        return division;
    }
}
