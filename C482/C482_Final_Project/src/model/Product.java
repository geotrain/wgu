package model;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

// Import statements
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * This class is the product list methods for adding, updating, and deleting products.
 */
public class Product {

    // Observable list for In-House data that is saved in the main.java class
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * This method creates a Product() method used in inventory management of products.
     */
    public Product() {
    }

    // Initialize variables productId, name, price, inStock, min, and max
    private int productId;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;

    /**
     * Product Constructor
     *
     * @param productId - Product Id
     * @param name - Product Name
     * @param price - Product Price
     * @param inStock - Product InStock
     * @param min - Product Min
     * @param max - Product Max
     */
    public Product(int productId, String name, double price, int inStock, int min, int max) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    /**
     * getID starter method
     * @return this.productId
     */
    public int getId() {
        return this.productId;
    }

    /**
     * setId starter method
     * @param productId - This productId is used to set the Id of a product.
     */
    public void setId(Integer productId) {
        this.productId = productId;
    }

    /**
     * getName starter method
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * setName starter method
     * @param name - This name is used to set the name of a product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPrice starter method
     * @return this.price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * setPrice Starter method
     * @param price - This price is used when updating the price of a product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * getInStock starter method
     * @return this.inStock
     */
    public int getInStock() {
        return this.inStock;
    }

    /**
     * setInStock starter method
     * @param quantity - This quantity is used to set the inventory level of a product.
     */
    public void setInStock(int quantity) {
        this.inStock = quantity;
    }

    /**
     * getMin starter method
     * @return this.min
     */
    public int getMin() {
        return this.min;
    }

    /**
     * setMin starter method
     * @param min - This min is used to set the min level of a product.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * getMax starter method
     * @return this.max
     */
    public int getMax() {
        return this.max;
    }

    /**
     * setMax starter method
     * @param max - This max is used to set the max level of a product.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This method will add a new part to inventory array
     * @param part - This part is used to add a new associated part to a product.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method deletes a part from the associated part list
     * @param selectedAssociatedPart - This selectedAssociatedPart in assigning a part to the associatedParts table.
     * @return associatedParts.remove(selectedAssociatedPart)
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * This method gathers all available parts in inventory search
     * @return associatedParts
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * getProductId starter method
     * @return thisProductId
     */
    public int getProductId() {
        return this.productId;
    }

    /**
     * setProductID starter method
     * @param id - This id is used when setting the id for a product.
     */
    public void setProductId(int id) {
        this.productId = id;
    }

    /**
     * getPartId starter method
     * @return associatedParts.size()
     */
    public int getPartId() {
        return associatedParts.size();
    }

    /**
     * setPartId starter method
     * @param id - This id is used when setting an associated partId to a product.
     */
    public void setPartId(int id) {
        this.productId = id;
    }
}