package models;

public class Countries {
    /**
     * Declare int id and String name variables to be used in the DBCountries.java class
     */
    private int id;
    private String name;

    /**
     * The Countries method with the country id and name from the countries tables
     * @param id
     * @param name
     */
    public Countries(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * The getId method that gets the Country_ID from the countries table
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * The getName method that gets the Country (name) from the countries table
     * @return
     */
    public String getName() {
        return name;
    }
}
