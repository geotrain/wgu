package controllers;

// Import statements
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Countries;
import models.Division;
import utilities.DataProvider;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AddCustomer {

    // FX Ids for Labels
    @FXML private Label customerIdLabel;
    @FXML private Label customerNameLabel;
    @FXML private Label address1Label;
    @FXML private Label countryLabel;
    @FXML private Label zipCodeLabel;
    @FXML private Label phoneNumberLabel;
    @FXML private Label stateProvinceLabel;
    @FXML private Label addCustomerLabel;

    // FX Ids for Text Fields
    @FXML private TextField customerIdTextField;
    @FXML private TextField customerNameTextField;
    @FXML private TextField address1TextField;
    @FXML private TextField countryTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField phoneNumberTextField;

    // FX Ids for Choice Boxes
    @FXML private ComboBox<Countries> countryComboBox;
    @FXML private ComboBox<Division> stateProvinceComboBox;

    // FX IDs for Buttons
    @FXML private Button saveButton;
    @FXML private Button closeButton;

    // Observable List For Countries Drop Down Choice Box
    ObservableList<String> countriesList = FXCollections.observableArrayList("U.S", "UK", "Canada");

    // Observable List For State Provinces Drop Down Choice Box
    ObservableList<String> stateProvinceList = FXCollections.observableArrayList("Alabama","Alaska", "Arizona", "Arkansas", "California",
            "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
            "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
            "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",
            "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "Northwest Territories", "Alberta", "British Columbia",
            "Manitoba", "New Brunswick", "Nova Scotia", "Prince Edward Island", "Ontario", "Quebec", "Saskatchewan", "Nunavut", "Yukon",
            "Newfoundland and Labrador", "England", "Wales", "Scotland", "Northern Ireland");

    /**
     * The close() method exits the AddCustomer controller and returns to the MainController
     * @param actionEvent
     * @throws IOException
     */
    @FXML void close(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1090, 900);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This saveButton method saves input from the customer into the database.
     * @param actionEvent
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML Object saveButton(ActionEvent actionEvent) throws IOException, InterruptedException {

        // Get Data From Add Customer Controller
        String customerName = customerNameTextField.getText();
        String customerAddress = address1TextField.getText();
        String customerPostalCode = postalCodeTextField.getText();
        String customerPhoneNumber = phoneNumberTextField.getText();

        Integer divisionID = null;
        Division D = stateProvinceComboBox.getValue();
        if (D == null) {
            addCustomerLabel.setText("The \"Division\" must be selected to continue.");
            return null;
        }
        divisionID = D.getDivisionID();

        System.out.println("The state that was chosen was " + stateProvinceComboBox.getValue() + " and the value entered into the" +
                " database is " + divisionID);

        // Verify text fields cannot be empty
        if (customerNameTextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Customer Name\" text field cannot be blank.");
            return null;
        } else if (address1TextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Address\" text field cannot be blank.");
            return null;
        } else if (postalCodeTextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Postal Code\" text field cannot be blank.");
            return null;
        } else if (phoneNumberTextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Phone Number\" text field cannot be blank.");
            return null;
        } else {
            // Update Database
            DBCustomers.addNewCustomer(customerName, customerAddress, customerPostalCode,
                    customerPhoneNumber, divisionID);
        }

        // Label confirming save to database
        addCustomerLabel.setText("You Added A New Customer. Now click close button.");

        // Return to main screen controller
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1090, 900);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        return null;
    }

    /**
     * The Initialize method initializes the fields when the controller screen loads. e.g., it loads the observable
     * lists for the country selection choice box and stateProvince selection choice box.
     */
    @FXML private void initialize() {

        /**
         * Initialize the countryComboBox with the getAllCountries Method
         */
        countryComboBox.setItems(DataProvider.getAllCountries());
    }

    /**
     * The loadCountries is used to populate the state or province depending on which Country is selected.
     * @param actionEvent This is a parameter
     */
    public void loadCountries(ActionEvent actionEvent) {
        Countries C = countryComboBox.getValue();
        stateProvinceComboBox.setItems(DataProvider.getDivisionsByCountryId(C.getId()));
    }
}
