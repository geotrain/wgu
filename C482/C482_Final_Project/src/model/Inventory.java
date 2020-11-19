package model;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

// Import statements
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the inventory class and contains all the methods for adding, updating, and deleting parts and products in the inventory lists.
 */
public class Inventory {

    /**
     * Initialize three observable array lists. These arrays will hold all of the all parts, all products, and
     * a list for filtered parts. The filtered parts is not actually used in then program.
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static final ObservableList<Product>  allProducts = FXCollections.observableArrayList();

    public Inventory() {
    }

    /**
     * This method adds a new part to inventory.
     * @param newPart - this newPart holds the new part being added.
     */
    public static void addPart(Part newPart) {

        allParts.add(newPart);
    }

    /**
     * This method adds a new part to inventory.
     * @param newProduct - This new product holds the new product being added.
     */
    public static void addProduct(Product newProduct) {

        allProducts.add(newProduct);
    }

    /**
     * This method looks up part id from the inventory.
     * @param partID - This part ID is used in the look up of a part from the inventory list.
     * @return - returns null.
     */
    public static Part lookUpPart(int partID) {
       if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getId() == partID) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * This method looks up product through search from inventory array.
     * @param productId - This product ID is used in the look up of a product from the inventory list.
     * @return - Returns null
     */
    public static Product lookUpProduct(int productId) {
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getProductId() == productId) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    /**
     * This Observable List parses the partName using the lookupPart() method.
     * @param partName - This partName is used in the looking up of a part from the inventory.
     * @return - Returns filteredParts.
     */
    public static ObservableList<Part> lookUpPart(String partName) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        for (Part part : allParts)
        {
            String name = part.getName().toLowerCase();
            if (name.contains(partName.toLowerCase())) {
                filteredParts.add(part);
            }
        }
        return filteredParts;
    }

    /**
     * This observable list parses the productName using the lookUpProduct() method.
     * @param productName - This productName is used in the looking up of a product from the inventory.
     * @return - Returns filteredProducts.
     */
    public static ObservableList<Product> lookUpProduct(String productName) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : allProducts)
        {
            String name = product.getName().toLowerCase();
            if (name.contains(productName.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    /**
     * This method updates an existing part in the inventory array.
     * @param index - This index is used for integer value when updating a part.
     * @param selectedPart - This selectedPart is the part that is selected when updating it.
     */
    public static void updatePart(int index, Part selectedPart) {

        Part updatePart = Inventory.lookUpPart(selectedPart.getId());
        Inventory.deletePart(updatePart);
        addPart(selectedPart);
    }

    /**
     * This method updates an existing product in the inventory array.
     * @param index - This index is used for integer value when updating a product.
     * @param newProduct - This newProduct is the product that is selected when updating it.
     */
    public static void updateProduct(int index, Product newProduct) {
        Product updateProduct = Inventory.lookUpProduct(newProduct.getId());
        Inventory.deleteProduct(updateProduct);
        addProduct(newProduct);
    }

    /**
     * This method deletes a part from the inventory array.
     * @param selectedPart - This selectedPart is used when deleting a selected part.
     * @return true - This returns true.
     */

    public static  boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    /**
     * This method deletes a product from the inventory array.
     * @param selectedProduct - This selectedProduct is used when deleting a selected product.
     */
    public static void deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
    }

    /**
     * This observable list gets all parts and returns the result.
     * @return allParts
     */
    public  static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This observable list gets all parts and returns the filtered results.
     * @return filteredParts
     */
    public  static ObservableList<Part> getAllFilteredParts() {
        return filteredParts;
    }

    /**
     * This observable list gets all products and returns the result.
     * @return allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * This method gets a partListSize and returns the size to the parts table
     * @return allParts.size()
     */
    public static int partListSize() {
        return allParts.size();
    }

    /**
     * This method gets a productListSize and returns the size to the products table
     * @return allProducts.size()
     */
    public static int productListSize() {
        return allProducts.size();
    }
}