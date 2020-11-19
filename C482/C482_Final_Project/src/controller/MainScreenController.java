package controller;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

// Import statements
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the main screen where the user can add, modify, or delete parts or products.
 * Can exit the application from this screen.
 */
public class MainScreenController implements Initializable {

    // FX Ids For Anchor Pane
    @FXML private AnchorPane MainScreen;

    // FX Ids For VBox
    @FXML private VBox partsVBox;

    // FX Ids for Labels
    @FXML private Label mainTitleLabels;
    @FXML private Label partsTitleLabel;
    @FXML private Label errorLabel;
    @FXML private Label errorLabel2;
    @FXML private Label productsTitleLabel;


    // FX Ids For Tex Fields
    @FXML private TextField partSearchBox;
    @FXML private TextField productSearchBox;

    // FX Ids For Parts Table
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInventoryLevelColumn;
    @FXML private TableColumn<Part, Double> priceCostPerUnitColumn;

    // FX IDs For Product Table and Table Columns
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, Integer> productIdColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInventoryColumn;
    @FXML private TableColumn<Product, Double> productPricePerUnitColumn;

    // FX Ids for Buttons
    @FXML private Button addPartButton;
    @FXML private Button modifyPartButton;
    @FXML private Button deletePartButton;
    @FXML private Button addProductButton;
    @FXML private Button modifyProductButton;
    @FXML private Button deleteProductButton;
    @FXML private Button exitButton;

    /**
     * Initialize stage, scene, selectedPart, and selectedProduct variables for app navigation and modifying the inventory.
     */
    Stage stage;
    Parent scene;
    private static Part selectedPart;
    private static Product selectedProduct;

    /**
     * This method searches by part ID or part name from the parts table.
     * @param actionEvent - This event handler searches the parts either by ID or name from the parts table.
     * @throws IOException - This IO Exception is thrown if an error occurs.
     * @return - This returns the search results for a part ID or part Name
     */
    public ObservableList<Part> searchParts(ActionEvent actionEvent) throws IOException{
        // This clears the selection each time a new part name is entered into the search box
        partsTable.getSelectionModel().clearSelection();
        // This allows multiple selection for partial name searches
        partsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if(!partSearchBox.getText().trim().isEmpty())
            Inventory.getAllFilteredParts().clear();
        {
            try {
                int searchResults = Integer.parseInt(partSearchBox.getText());
                for(Part part : Inventory.getAllParts())
                {
                    if (part.getId() == searchResults) {
                        partsTable.getSelectionModel().select(part);
                        errorLabel.setText("");
                        return Inventory.getAllParts();
                    } else if (part.getName().contains(partSearchBox.getText())) {
                        partsTable.getSelectionModel().select(part);
                    }
                }
            } catch (NumberFormatException e) {
                // Catch exception error message
                System.out.println("Number error" + e.getMessage());
                // String search for part Search Box
                String search = partSearchBox.getText();
                for(Part name : Inventory.getAllParts())
                {
                    if (name.getName().contains(search)) {
                        partsTable.getSelectionModel().select(name);
                        Inventory.getAllFilteredParts().add(name);
                    }
                }
                if (Inventory.getAllFilteredParts().isEmpty()) {
                    errorLabel.setText("The part name you are searching for cannot be found.");
                    return Inventory.getAllParts();
                } else {
                    errorLabel.setText("");
                    return Inventory.getAllFilteredParts();
                }
            }
        }
        return null;
    }

    /**
     * This method switches to the add part screen.
     * @param actionEvent - This event handler opens up the add part screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    public void addPart(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Part Button Pushed");
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/add_part.fxml"));
        stage.setScene(new Scene(scene, 400, 415));
        stage.setTitle("Add Part Screen");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method populates modify part controller called in modifyPart Action event handler.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    public void updatePartScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/modify_part.fxml"));
        ModifyPartController controller = loader.getController();
        controller.stage.setScene(new Scene(scene, 415, 415));
        controller.stage.setTitle("Modify Part Screen");
        controller.stage.setResizable(false);
        controller.stage.show();
    }

    /**
     * This method switches to the modify part screen. ---
     *
     * COMPATIBLE FEATURE EXTENDING FUNCTIONALITY TO THE NEXT VERSION OF THE APPLICATION - In the next version, it would
     * be a great feature to introduce the ability for the end-user to be able to double click a part from the parts table
     * without first having to click the the modify part button. The existing way for the end user to be able to click
     * a part first and then click the modify part button would still exist in the new version. This new feature
     * would give the end user a faster, simpler way to modify a part by double clicking on a part from the parts
     * table without the need of clicking the modify part button first.
     *
     * @param event - This event handler opens up the modify part screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    @FXML void modifyPart(ActionEvent event) throws IOException {
        selectedPart = partsTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedPart);

        // Verify the user selects a part when the modify button is clicked
        if(selectedPart == null) {
            errorLabel.setText("You must select a part from the parts table before proceeding.");
            return;
        } else {
            System.out.println("Modify Part Button Pushed");
            errorLabel.setText("");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/modify_part.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 415, 415);
            ModifyPartController controller = fxmlLoader.getController();
            controller.setDataPart(partsTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Modify Part Screen");
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * This method deletes a selected part from the parts table.
     * @param actionEvent - This event handler deletes a selected part from the parts table.
     */
    public void deletePart(ActionEvent actionEvent) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        // Verify user selects a part when clicking the delete part button
        if(selectedPart == null) {
            errorLabel.setText("You must select a part from the parts table before deleting.");
            errorLabel.setTextFill(Color.RED);
            return;
        } else {
            errorLabel.setText("");
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to delete this part?");
        alert.setContentText("Select yes or no.");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton)
        {
            Inventory.deletePart(selectedPart);
            partsTable.setItems(Inventory.getAllParts());
            JDialog frame = null;
        }
        else if(result.get() == cancelButton)
        {
            actionEvent.consume();
        }
    }

    /**
     * This method searches by product ID or product name from the products table.
     * @param actionEvent - This event handler searches a product by ID or by name.
     * @throws IOException - This IO Exception occurs if an error occurs.
     * @return Inventory.getAllProducts() Method
     */
    public Object searchProduct(ActionEvent actionEvent) throws IOException{
        // This clears the selection each time a new part name is entered into the search box
        productsTable.getSelectionModel().clearSelection();
        // This allows multiple selection for partial name searches
        productsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Selection search box get text is not empty then run try catch block
        if(!productSearchBox.getText().trim().isEmpty())
        {
            try {
                int searchResults = Integer.parseInt(productSearchBox.getText());
                for(Product product : Inventory.getAllProducts())
                {
                    if (product.getId() == searchResults) {
                        productsTable.getSelectionModel().select(product);
                        errorLabel2.setText("");
                        return Inventory.getAllProducts();
                    }
                }
            } catch (NumberFormatException e) {
                // Catch exception error message
                System.out.println("Number error" + e.getMessage());
                String search = productSearchBox.getText();
                for (Product name : Inventory.getAllProducts())
                {
                   if (name.getName().contains(search)) {
                       errorLabel2.setText("");
                       productsTable.getSelectionModel().select(name);
                   } else if (Inventory.lookUpProduct(search).isEmpty()) {
                       errorLabel2.setText("The product name cannot be found.");
                   }
                }
            }
        }
        return null;
    }

    /**
     * This method switches to the add product screen.
     * @param event This event handler opens up the add product screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    @FXML void addProduct(ActionEvent event) throws IOException {
        System.out.println("Add Product Button Pushed");
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/addProduct.fxml"));
        stage.setScene(new Scene(scene, 875, 585));
        stage.setTitle("Add Product Screen");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method selects a specific part and then switches to the modify product screen for updating. ---
     *
     * COMPATIBLE FEATURE EXTENDING FUNCTIONALITY TO THE NEXT VERSION OF THE APPLICATION - In the next version, it would
     * be a great feature to introduce the ability for the end-user to be able to double click a product from the products table
     * without first having to click the the modify product button. The existing way for the end user to be able to click
     * a product first and then click the modify product button would still exist in the new version. This new feature
     * would give the end user a faster, simpler way to modify a product by double clicking on a product from the products
     * table without the need of clicking the modify product button first.
     *
     * @param event - This event handler opens the modify product screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    @FXML void modifyProduct(ActionEvent event) throws IOException {
        selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedProduct);

        // Verify the user has selected a product when clicking modify button
        if(selectedProduct == null) {
            errorLabel2.setText("You must select a product from the products table before proceeding.");
            return;
        } else {
            System.out.println("Modify Product Button Pushed");
            errorLabel2.setText("");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/modify_product.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 875, 585);
            ModifyProductController controller = fxmlLoader.getController();
            controller.setDataProduct(productsTable.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Modify Product Screen");
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * This method deletes a selected product from the products table view (only if it has no associated parts) assigned to it.
     * @param actionEvent - This event handler deletes a selected product from the products table.
     */
    public void deleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        // Check to verify that the user has selected a part before clicking the delete button
        if(selectedProduct == null) {
            errorLabel2.setText("You must select a product from the products table before deleting.");
            errorLabel2.setTextFill(Color.RED);
            return;
        }

        // Check to verify that the product selected to delete does not have any associated parts with it
        if (selectedProduct.getAssociatedParts().size() >= 1) {
            errorLabel2.setText("You cannot delete a product that have parts associated with it.");
            errorLabel2.setTextFill(Color.RED);
        } else {
            // Alert box warning you are about to delete a product from the products tables
            errorLabel2.setText("");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to delete this product?");
            alert.setContentText("Select yes or no.");
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(yesButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yesButton)
            {
                Inventory.deleteProduct(selectedProduct);
                productsTable.setItems(Inventory.getAllProducts());
                JDialog frame = null;
            }
            else if(result.get() == cancelButton)
            {
                actionEvent.consume();
            }
        }
    }

    /**
     * This method exits the Inventory Management System program.
     * @param event - This event handler exits the application.
     * @throws IOException - This throws an IOExcetion if an error occurs.
     */
    @FXML void exitButton(ActionEvent event) throws IOException {
        System.out.println("Exit Button Pushed");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inventory Management System Exit");
        alert.setHeaderText(null);
        alert.setContentText("You are now exiting the program.");
        alert.showAndWait();
        System.exit(0);
    }

    /**
     * This method updates the results when user changes the text.
     * @param partID - This partId is an integer passed into the update method to get all parts for the parts table.
     * @param Part - The Part is the InHouse part to be populated in the parts table.
     * @return - This returns true or false.
     */
    public boolean update(int partID, InHouse Part)
    {
        int index = -1;
        for(Part part : Inventory.getAllParts())
        {
            index++;

            if(part.getId() == partID) {
                Inventory.getAllParts().set(index, part);
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns filtered results from an Observable List in Inventory Class by Part Name.
     * @param name - This name is the string name of the parts from the observable array list.
     * @return - This returns the Inventory get all filtered parts for the parts table.
     */
    public ObservableList<Part> filter(String name)
    {
        if(!(Inventory.getAllFilteredParts().isEmpty()))
            Inventory.getAllFilteredParts().clear();

        for(Part part : Inventory.getAllParts())
        {
            if(part.getName().contains(name))
                Inventory.getAllFilteredParts().add(part);
        }
        if(Inventory.getAllFilteredParts().isEmpty())
            return Inventory.getAllParts();
        else
            return Inventory.getAllFilteredParts();
    }

    /**
     * This initializes the parts table on the main screen and populates it.
     * @param url - This url is used to initialize the parts and products table.
     * @param resourceBundle - This resourceBundle is used to assist in populating the parts and products table.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populates and initializes the parts table
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostPerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getAllParts());

        // Populates and initializes the products table
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        productPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(Inventory.getAllProducts());
    }
}