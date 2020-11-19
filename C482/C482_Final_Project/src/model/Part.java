package model;

/**
 * Do Not Modify File - I did add a doc block to all method and some comments. I can remove these if required
 * @author Greg Westmoreland
 * C482 Class Project
 */

/**
 * This class is abstract class. I did add a doc block to all methods and some comments.
 */
public abstract class Part {

    // Initialize variables
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Part Constructor
     * @param id - Part Id
     * @param name - Part Name
     * @param price - Part Price
     * @param stock - Price Stock
     * @param min - Part Min
     * @param max - Part Max
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    // Getter and Setter Start Methods For All Part Parameters
    /**
     * getId starter method
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * setId starter method
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getName starter method
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setName starter method
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPrice starter method
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * setPrice starter method
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * getStock starter method
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * setStock starter method
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * getMin starter method
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * setMin starter method
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * getMax starter method
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * setMax starter method
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
