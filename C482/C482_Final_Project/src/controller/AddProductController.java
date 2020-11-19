package controller;

/**
 * @author Greg Westmoreland
 * C482 Class Project
 */

// Import statements
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;
import model.Part;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * This class is the add product controller allowing the user to add a new product to the inventory.
 */
public class AddProductController implements Initializable {

    // FX Ids for Labels
    @FXML private Label mainLabel;
    @FXML private Label errorLabel;

    // FX Ids For Text Fields
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField count;
    @FXML private TextField price;
    @FXML private TextField max;
    @FXML private TextField min;
    @FXML private TextField searchPartsTextBox;

    // FX Ids For Parts Table
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInventoryLevelColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;

    // FX IDs For Products Table
    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> associatedPartIdColumn;
    @FXML private TableColumn<Part, String> associatedPartNameColumn;
    @FXML private TableColumn<Part, Integer> associatedPartInventoryColumn;
    @FXML private TableColumn<Part, Double> associatedPartPriceColumn;

    // FX IDs For Buttons
    @FXML private Button addButton;
    @FXML private Button removeAssociatedPartButton;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    // ID Label Not Able To Edit
    @FXML void idFieldNotAbleToEdit(MouseEvent event) {
        errorLabel.setText("You Cannot edit the ID field.");
    }

    /**
     * Initialize stage, scene, addSelectedPart, addSelectedAssociatedPart, and productSelection variables for app
     * navigation and moving parts to and from the associated parts table.
     */
    Stage stage;
    Parent scene;
    private Part addSelectedPart;
    private Part addSelectedAssociatedPart;
    private Product productSelection;

    /**
     * Observable list to hold the data when a part is moved from the allPartsTable Table to the associatedPartsTable
     */
    Inventory inv = new Inventory();
    Product addProduct = new Product(0, "",0.00,0,0, 0);
    ObservableList<Part> temp = FXCollections.observableArrayList();
    Random rand = new Random();


    /**
     * This method searches by part ID or part name from the parts table.
     * @param actionEvent - This event handler searches the parts either by ID or name from the parts table.
     * @throws IOException - This IO Exception is thrown if an error occurs.
     * @return - This returns the search results for a part ID or part Name
     */
    public ObservableList<Part> searchParts(ActionEvent actionEvent) throws IOException{
        // This clears the selection each time a new part name is entered into the search box
        allPartsTable.getSelectionModel().clearSelection();
        // This allows multiple selection for partial name searches
        allPartsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if(!searchPartsTextBox.getText().trim().isEmpty())
            Inventory.getAllFilteredParts().clear();
        {
            try {
                int searchResults = Integer.parseInt(searchPartsTextBox.getText());
                for(Part part : Inventory.getAllParts())
                {
                    if (part.getId() == searchResults) {
                        allPartsTable.getSelectionModel().select(part);
                        errorLabel.setText("");
                        return Inventory.getAllParts();
                    } else if (part.getName().contains(searchPartsTextBox.getText())) {
                        allPartsTable.getSelectionModel().select(part);
                    }
                }
            } catch (NumberFormatException e) {
                // Catch exception error message
                System.out.println("Number error" + e.getMessage());
                // String search for part Search Box
                String search = searchPartsTextBox.getText();
                for(Part name : Inventory.getAllParts())
                {
                    if (name.getName().contains(search)) {
                        allPartsTable.getSelectionModel().select(name);
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
     * This method adds a part from the parts table and associates it with the products table.
     * @param event - This event handler adds a new part to the associated parts table.
     */
    @FXML
    void addPart(ActionEvent event) {
        Part addSelectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if(addSelectedPart == null) {
            errorLabel.setText("Select a part before associating it with a product.");
            return;
        } else {
            errorLabel.setText("");
            addProduct.addAssociatedPart(addSelectedPart);
            temp.add(addSelectedPart);
            System.out.println("Selected Part Associated To Product");
            associatedPartsTable.setItems(addProduct.getAssociatedParts());
        }
    }

    /**
     * This method removes an associated part from the product table and puts it back into the parts table
     * @param event - This event handler removes a part from the associated parts table.
     * @throws IOException - Returns IO Exception if an error occurs.
     */
    @FXML void removeAssociatedPart(ActionEvent event) throws IOException {
        // Part selection when user clicks associated part to remove
        Part addSelectedAssociatedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

        // Throw error if no associated part is selected prior to clicking remove associated parts button
        if (addSelectedAssociatedPart == null) {
            errorLabel.setText("Select a part before removing the association from a product.");
            errorLabel.setTextFill(Color.RED);
        } else {
            // Alert confirming user if the user wants to remove associated part from associated parts table
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you want to remove this associated part from this product?");
            alert.setContentText("Select yes or no.");
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(yesButton, cancelButton);
            Optional<ButtonType> result = alert.showAndWait();
            // If user clicks yes, it will remove the associated part and return to the add product controller screen
            if (result.get() == yesButton) {
                errorLabel.setText("");
                addProduct.deleteAssociatedPart(addSelectedAssociatedPart);
                System.out.println("Selected Part Unassociated From Product");
                JDialog frame = null;
            // If user clicks cancel button it will return to the add product controller screen with no action taken
            } else if(result.get() == cancelButton) {
                event.consume();
            }
        }
    }

    /**
     * This method saves a new product in the products table in the main screen controller. ---
     *
     * RUN TIME ERROR DOCUMENTATION: I was having a run time error when clicking the save button when trying to add a new
     * product. It was giving me a null pointer exception. when adding the "Product AddTemptProduct = new Product();"
     * I was passing in an object productSelection I through was holding all of the data when adding a new product. See Line 321 ---
     *
     * RUN TIME ERROR CORRECTION: I placed a red dot next to the line number 200 at the top of the method and
     * then clicked on the debug icon in intelliJ. By doing this I was able to step over and step into the logic of the
     * method until I was able to determine exactly where I was getting the null value while trying to save a new product.
     * The correct action was to change productSelection to in the new Product to "productID, productName, productPrice,
     * productStock, productMax, and productMin). After making this correction the new product was saved to the inventory. See Line 321
     *
     * @param actionEvent - This event handler saves a new product and associated parts to the inventory.
     * @throws IOException - This IOException is thrown if an error occurs.
     */
    @FXML public void saveProduct(ActionEvent actionEvent) throws IOException{
        // Randomly generated product ID - text field is uneditable
        int productId = Integer.parseInt((id.getText()));

        // Verify name text field is not blank
        if (name.getText().isEmpty()) {
            errorLabel.setText("The \"name\" text field cannot be blank.");
            return;
        }
        String productName = name.getText();

        // Verify count text field is not blank
        if(count.getText().isEmpty()) {
            errorLabel.setText("The \"Inventory\" text field cannot be blank.");
            return;
            // Verify count text field is not a double or floating point
        } else if (!tryIntegerValue(count.getText())) {
            errorLabel.setText("The inventory number must be an integer value.");
            return;
        }
        int productStock = Integer.parseInt(count.getText());

        // Verify price text field is not blank
        if (price.getText().isEmpty()) {
            errorLabel.setText("The \"price\" text field cannot be blank.");
            return;
        // Verify price text field is not a string
        } else if (!isPriceNumeric(price.getText())) {
            return;
        }
        double productPrice = Double.parseDouble(price.getText());

        // Verify max text field is not blank
        if (max.getText().isEmpty()) {
            errorLabel.setText("The \"max\" text field cannot be blank.");
            return;
            // Verify max text field is not a double or floating point
        } else if (!tryIntegerValue(max.getText())) {
            errorLabel.setText("The max number must be an integer value.");
            return;
        }
        int productMax = Integer.parseInt(max.getText());

        // Verify min text field is not blank
        if(min.getText().isEmpty()) {
            errorLabel.setText("The \"min\" text field cannot be blank.");
        //  Verify min text field cannot be larger than max text field
        } else if (!tryIntegerValue(min.getText())) {
            errorLabel.setText("The min number must be an integer value.");
            return;
        }
        int productMin = Integer.parseInt(min.getText());

        // Verify count text field is larger than min text field and smaller than max text field
        if (Integer.parseInt(count.getText()) > Integer.parseInt(max.getText())) {
            errorLabel.setText("Inventory must be smaller than or equal to the max.");
            return;
            // Verify count text field is larger than the min level
        } else if (Integer.parseInt(count.getText()) < Integer.parseInt(min.getText())) {
            errorLabel.setText("Inventory must be larger than or equal to the min.");
            return;
            // Verify that the max text field is larger than the min text field
        } else if (Integer.parseInt(max.getText()) < Integer.parseInt(min.getText())) {
            errorLabel.setText("Max must be larger than the min.");
        }

        // Verify that at least one associated part is added to the associated parts table before saving
        if (temp.isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Dialog Box");
            alert.setHeaderText("An error has occurred.");
            alert.setContentText("You must associate at least one part before saving new product.");
            Exception noSelectedPart = new IOException("You must associate at least one part before saving new product.");
            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            noSelectedPart.printStackTrace(pw);
            String exceptionText = sw.toString();
            Label label = new Label("The exception stacktrace was:");
            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
            return;
        }

        // Save new product
        errorLabel.setText("");
        /**
         * Run Time Error Documentation: I was having a run time error when clicking the save button when trying to add a new
         * product. It was giving me a null pointer exception. when adding the "Product AddTemptProduct = new Product();"
         * I was passing in an object productSelection I through was holding all of the data when adding a new product.
         *
         * Run Time Correction Documentation: I placed a red dot next to the line number 200 at the top of the method and
         * then clicked on the debug icon in intelliJ. By doing this I was able to step over and step into the logic of the
         * method until I was able to determine exactly where I was getting the null value while trying to save a new product.
         * The correct action was to change productSelection to in the new Product to "productID, productName, productPrice,
         * productStock, productMax, and productMin). After making this correction the new product was saved to the inventory.
         */
        Product addTempProduct = new Product(productId, productName, productPrice, productStock, productMax, productMin);

        // For loop adding selected parts to be associated parts
        for (int i=0; i < temp.size(); i++) {
            Part part = temp.get(i);
            addTempProduct.addAssociatedPart(part);
        }
        // Add new product to inventory
        Inventory.addProduct(addTempProduct);

        // Return to main screen after save button is selected.
        System.out.println("Save Button Pushed");
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene, 900, 525));
        stage.setTitle("Inventory Management System");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method returns to the main screen Inventory Management System.
     * @param event - This event handler cancels all actions taken and returns to the main screen.
     * @throws IOException - This IO Exception is thrown if an error occurs.
     */
    @FXML void cancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel and Return To Main Screen");
        alert.setHeaderText("Are you sure you want to return to the main screen?");
        alert.setContentText("Click OK to continue.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("Cancel Button Pushed");
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
            stage.setScene(new Scene(scene, 900, 525));
            stage.setTitle("Inventory Management System");
            stage.setResizable(false);
            stage.show();
        } else {
            errorLabel.setText("You have returned to the add product screen.");
        }
    }

    /**
     * Initialize variable Random to hold an automated productId that is called in the saveButton method
     * Initialize variable result to hold the final result from the random() function called in the saveButton method
     */
    private static Random random = new Random();
    private static int result;

    /**
     * This method initializes the parts table and the products table
     * @param url - This URL is used in the initialize method to populate the parts and associated parts tables.
     * @param resourceBundle - This resourceBundle is used in the initialize method to assist with populating tables.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // Auto-Generate part id with random() method.
        int nextId = random.nextInt(100) + 1;
        result += nextId;
        System.out.println("New Product Id Generated Is: " + result);
        id.setText(Integer.toString(result));

        // This initializes the parts table
        partIdColumn.setCellValueFactory((new PropertyValueFactory<>("id")));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTable.setItems(Inventory.getAllParts());

        // This initializes the associated parts table
        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.setItems(addProduct.getAssociatedParts());
    }

    /**
     * Method checks to see if price text field value entered is a numeric value only.
     * @param strNum - This string is used to verify that the price text field is a double and not a string used for validation purposes.
     * @return - This returns true if it is a double and returns false if it is a string.
     */
    public boolean isPriceNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
            errorLabel.setText("");
            return true;
        } catch (NumberFormatException nfe) {
            errorLabel.setText("Change \"Price\" text field from a string to an double.");
            System.out.println("isPriceNumeric returns false");
            return false;
        }
    }

    /**
     * This method checks if the integer values for inventory, min, and max text fields are integers and not doubles or floats
     * @param strNum - This string verifies that the value is an integer and not a double or floating point used for validation purposes.
     * @return - This returns true if it is an integer and false if it is a double or floating point.
     */
    public boolean tryIntegerValue(String strNum) {
        try {
            Integer.parseInt(strNum);
            errorLabel.setText("");
            return true;
        } catch (NumberFormatException e) {
            errorLabel.setText("Enter an integer value instead of a number with a decimal.");
            System.out.println("tryIntegerValue returns false");
            return false;
        }
    }
}
