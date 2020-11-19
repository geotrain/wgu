package model;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

/**
 * This class contains all the methods for the outsourced parts. It extends the part class.
 */
public class Outsourced extends Part {

    // Private Int variable called companyName
    private String companyName;

    /**
     * Outsourced Constructor
     * @param id - Part Id
     * @param name - Part Name
     * @param price - Part Price
     * @param stock - Part Inventory
     * @param min - Part Min
     * @param max - Part Max
     * @param companyName - Part CompanyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * getCompanyName starter method
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * setCompanyName starter method
     * @param name = This name is used to set the company name for outsourced parts.
     */
    public void setCompanyName(String name) {
        this.companyName = name;
    }
}