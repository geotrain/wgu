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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;
import model.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * This class is used to modify values of an existing product, associate and disassociated parts to a product from inventory.
 */
public class ModifyProductController implements Initializable {

    // Observable Lists
    private ObservableList<Part> partsInventory = FXCollections.observableArrayList();
    private ObservableList<Part> partsInventorySearch = FXCollections.observableArrayList();
    private ObservableList<Part> assocPartList = FXCollections.observableArrayList();

    // FX IDs for Labels
    @FXML private Label mainLabel;
    @FXML private Label errorLabel;

    // FX IDs for Text Fields
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField count;
    @FXML private TextField cost;
    @FXML private TextField max;
    @FXML private TextField min;
    @FXML private TextField searchParts;

    // FX IDs For Parts Table & Parts Columns
    @FXML private TableView<Part> allPartsTable;
    @FXML private TableColumn<Part, Integer> partIdColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInventoryColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;

    // FX IDs for Associated Parts Table and Associated Parts Columns
    @FXML private TableView<Part> associatedPartsTable;
    @FXML private TableColumn<Part, Integer> associatedPartIdColumn;
    @FXML private TableColumn<Part, String> associatedPartNameColumn;
    @FXML private TableColumn<Part, Integer> associatedPartInventoryColumn;
    @FXML private TableColumn<Part, String> associatedPricePartColumn;

    // FX IDs for Buttons
    @FXML private Button addButton;
    @FXML private Button removeAssociatedPartButton;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    // ID Label Not Able To Edit
    @FXML void idFieldNotAbleToEdit(MouseEvent event) {
        errorLabel.setText("Cannot edit the ID field. Select Radio Button To Populate");
    }

    /**
     * Initialize stage, scene, variables for app navigation and addSelectedPart, addSelectedAssociatedPart, and newProduct variables.
     */
    Stage stage;
    Parent scene;
    private Part addSelectedPart;
    private Part addSelectedAssociatedPart;
    private Product newProduct;
    private Product productSelection;

    /**
     * Observable list to hold the data when a part is moved from the allPartsTable Table to the associatedPartsTable.
     */
    Inventory inv = new Inventory();
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
        if(!searchParts.getText().trim().isEmpty())
            Inventory.getAllFilteredParts().clear();
        {
            try {
                int searchResults = Integer.parseInt(searchParts.getText());
                for(Part part : Inventory.getAllParts())
                {
                    if (part.getId() == searchResults) {
                        allPartsTable.getSelectionModel().select(part);
                        errorLabel.setText("");
                        return Inventory.getAllParts();
                    } else if (part.getName().contains(searchParts.getText())) {
                        allPartsTable.getSelectionModel().select(part);
                    }
                }
            } catch (NumberFormatException e) {
                // Catch exception error message
                System.out.println("Number error" + e.getMessage());
                // String search for part Search Box
                String search = searchParts.getText();
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
     * @param event - This event handler adds a part to the associated parts table.
     */
    @FXML void addPart(ActionEvent event) {
        Part addSelectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        if(addSelectedPart == null) {
            errorLabel.setText("Select a part before associating it with a product.");
            return;
        } else {
            errorLabel.setText("");
            productSelection.addAssociatedPart(addSelectedPart);
            System.out.println("Selected Part Associated To Product");
            associatedPartsTable.setItems(productSelection.getAssociatedParts());
        }
    }

    /**
     * This method removes an associated part from the product table and puts it back into the parts table.
     * @param event - This event handler removes an associated part from the associated parts table.
     */
    @FXML void removeAssociatedPart(ActionEvent event) {
        Part addSelectedAssociatedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (addSelectedAssociatedPart == null) {
            errorLabel.setText("Select a part before removing the association from a product.");
            errorLabel.setTextFill(Color.RED);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Are you sure you want to remove this associated part from this product?");
        alert.setContentText("Select yes or no.");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            errorLabel.setText("");
            productSelection.deleteAssociatedPart(addSelectedAssociatedPart);
            System.out.println("Selected Part Unassociated From Product");
        } else if(result.get() == cancelButton) {
            event.consume();
        }
    }

    /**
     * This method received selected product from main screen controller when modify button is selected.
     * @param product - This product is used to recieve product values from the main screen controller when switching screens.
     */
    public void setDataProduct(Product product) {
        productSelection = product;
        this.id.setText(String.valueOf(productSelection.getId()));
        this.name.setText(String.valueOf(productSelection.getName()));
        this.cost.setText(String.valueOf(productSelection.getPrice()));
        this.count.setText(String.valueOf(productSelection.getInStock()));
        this.min.setText(String.valueOf(productSelection.getMin()));
        this.max.setText(String.valueOf(productSelection.getMax()));
        associatedPartsTable.setItems(productSelection.getAssociatedParts());
        }

    /**
     * his method saves a modified product
     * @param event - This event handler is used to save a modified product and return to the main screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
     */
    @FXML void save(ActionEvent event) throws IOException {
        // Product value for modified product
        productSelection.setId(Integer.parseInt(id.getText()));

        // Verify name text field is not blank
        if (name.getText().isEmpty()) {
            errorLabel.setText("The \"Name\" text field cannot be blank.");
            return;
        } else {
            productSelection.setName(name.getText());
        }

        // Verify count text field is not blank
        if (count.getText().isEmpty()) {
            errorLabel.setText("The \"Inventory\" text field cannot be blank.");
            return;
            // Verify count text field is not a double or a float
        } else if (!tryIntegerValue(count.getText())) {
            errorLabel.setText("The inventory number must be an integer value.");
            return;
        } else {
            productSelection.setInStock(Integer.parseInt(count.getText()));
        }

        // Verify cost text field is not blank
        if (cost.getText().isEmpty()) {
            errorLabel.setText("The \"Price\" text field cannot be blank.");
            return;
            // Verify cost text field is not a string
        } else if (!isPriceNumeric(cost.getText())) {
            return;
        } else {
            productSelection.setPrice(Double.parseDouble(cost.getText()));
        }

        // Verify max text field is not blank
        if (max.getText().isEmpty()) {
            errorLabel.setText("The \"Max\" text field cannot be blank.");
            return;
            // Verify max text field is not a double or a float
        } else if (!tryIntegerValue(max.getText())) {
            errorLabel.setText("The max number must be an integer value.");
            return;
        } else {
            productSelection.setMax(Integer.parseInt(max.getText()));
        }

        // Verify min text field is not blank
        if (min.getText().isEmpty()) {
            errorLabel.setText("The \"Min\" text field cannot be blank.");
            return;
        // Verify max text field is not a double or a float
        } else if (!tryIntegerValue(min.getText())) {
            errorLabel.setText("The min number must be an integer value.");
            return;
        } else {
            productSelection.setMin(Integer.parseInt(min.getText()));
        }

        // Verify count text field is larger than min text field and smaller than max text field
        if (Integer.parseInt(count.getText()) < Integer.parseInt(min.getText())) {
            errorLabel.setText("Inventory must be larger than or equal to the min.");
            return;
        // Verify count text field is smaller than max text field
        } else if (Integer.parseInt(count.getText()) > Integer.parseInt(max.getText())) {
            errorLabel.setText("Inventory must be smaller than or equal to the max.");
            return;
        // Verify count text field is larger than min text field
        } else if (Integer.parseInt(max.getText()) < Integer.parseInt(min.getText())) {
            errorLabel.setText("Max must be larger than the min.");
            return;
        }

        // Verify that at least one associated part is required before saving modified product
        if (associatedPartsTable.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Dialog Box");
            alert.setHeaderText("An error has occurred.");
            alert.setContentText("You must associate at least one part before saving modified product.");
            Exception noSelectedPart = new IOException("You must associate at least one part before saving modified product.");
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

        // Save modified product
        errorLabel.setText("");
        Inventory.updateProduct(Integer.parseInt(id.getText()),productSelection);

        // Return to main screen after save button is selected.
        System.out.println("Save Button Pushed");
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        stage.setScene(new Scene(scene, 900, 525));
        stage.setTitle("Inventory Management System");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method returns to the main screen Inventory Management System.
     * @param event - This event handler is used to cancel all actions and return to the main screen.
     * @throws IOException - This IO Exception occurs if an error occurs.
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
            errorLabel.setText("You have returned to the modify product screen.");
        }
    }

    /**
     * Initialize variable Random to hold an automated productId that is called in the saveButton method
     * Initialize variable result to hold the final result from the random() function called in the saveButton method
     */
    public static Random random = new Random();
    public static int result;

    /**
     * This method initializes the parts and products table.
     * @param url - This url is used to help initialize the parts and associated parts table.
     * @param resourceBundle - This resourceBundle assists in populating the parts and associated parts table.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // Auto-Generate part id with random() method.
        int nextId = random.nextInt(100) + 1;
        result += nextId;
        System.out.println("New Product Id Generated Is: " + result);
        id.setText(Integer.toString(result));

        // Parts Table
        partIdColumn.setCellValueFactory((new PropertyValueFactory<>("id")));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTable.setItems(Inventory.getAllParts());

        // Products Table
        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPricePartColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.setItems(Inventory.getAllParts());
    }

    /**
     * Method checks to see if price text field value entered is a numeric value only.
     * @param strNum - This String is used to verify the price is a double and not a string for validation purposes.
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
     * @param strNum - This is used to verify that an integer value is entered and not a double or floating point for validation purposes.
     * @return - This returns true if it is an integer and false if it is a double or a floating point.
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
