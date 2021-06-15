package controllers;

/**
 * Import statements
 */
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
    @FXML private ChoiceBox<String> countriesChoiceBox;
    @FXML private ChoiceBox<String> stateProvinceChoiceBox;

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
        Scene scene = new Scene((Parent) root, 1060, 900);
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
        String customerCountry = countriesChoiceBox.getValue();
        Integer divisionID = null;

        if (stateProvinceChoiceBox.getValue() == "Alabama") {
             divisionID = 1;
        } else if (stateProvinceChoiceBox.getValue() == "Arizona") {
             divisionID = 2;
        } else if (stateProvinceChoiceBox.getValue() == "Arkansas") {
            divisionID = 3;
        } else if (stateProvinceChoiceBox.getValue() == "California") {
             divisionID = 4;
        } else if (stateProvinceChoiceBox.getValue() == "Colorado") {
             divisionID = 5;
        } else if (stateProvinceChoiceBox.getValue() == "Connecticut") {
             divisionID = 6;
        } else if (stateProvinceChoiceBox.getValue() == "Delaware") {
             divisionID = 7;
        } else if (stateProvinceChoiceBox.getValue() == "District of Columbia") {
             divisionID = 8;
        } else if (stateProvinceChoiceBox.getValue() == "Florida") {
             divisionID = 9;
        } else if (stateProvinceChoiceBox.getValue() == "Georgia") {
             divisionID = 10;
        } else if (stateProvinceChoiceBox.getValue() == "Idaho") {
             divisionID = 11;
        } else if (stateProvinceChoiceBox.getValue() == "Illinois") {
             divisionID = 12;
        } else if (stateProvinceChoiceBox.getValue() == "Indiana") {
             divisionID = 13;
        } else if (stateProvinceChoiceBox.getValue() == "Iowa") {
             divisionID = 14;
        } else if (stateProvinceChoiceBox.getValue() == "Kansas") {
             divisionID = 15;
        } else if (stateProvinceChoiceBox.getValue() == "Kentucky") {
             divisionID = 16;
        } else if (stateProvinceChoiceBox.getValue() == "Louisiana") {
             divisionID = 17;
        } else if (stateProvinceChoiceBox.getValue() == "Maine") {
             divisionID = 18;
        } else if (stateProvinceChoiceBox.getValue() == "Maryland") {
             divisionID = 19;
        } else if (stateProvinceChoiceBox.getValue() == "Massachusetts") {
             divisionID = 20;
        } else if (stateProvinceChoiceBox.getValue() == "Michigan") {
             divisionID = 21;
        } else if (stateProvinceChoiceBox.getValue() == "Minnesota") {
             divisionID = 22;
        } else if (stateProvinceChoiceBox.getValue() == "Mississippi") {
             divisionID = 23;
        } else if (stateProvinceChoiceBox.getValue() == "Missouri") {
             divisionID = 24;
        } else if (stateProvinceChoiceBox.getValue() == "Montana") {
             divisionID = 25;
        } else if (stateProvinceChoiceBox.getValue() == "Nebraska") {
             divisionID = 26;
        } else if (stateProvinceChoiceBox.getValue() == "Nevada") {
             divisionID = 27;
        } else if (stateProvinceChoiceBox.getValue() == "New Hampshire") {
             divisionID = 28;
        } else if (stateProvinceChoiceBox.getValue() == "New Jersey") {
             divisionID = 29;
        } else if (stateProvinceChoiceBox.getValue() == "New Mexico") {
             divisionID = 30;
        } else if (stateProvinceChoiceBox.getValue() == "New York") {
             divisionID = 31;
        } else if (stateProvinceChoiceBox.getValue() == "North Carolina") {
             divisionID = 32;
        } else if (stateProvinceChoiceBox.getValue() == "North Dakota") {
             divisionID = 33;
        } else if (stateProvinceChoiceBox.getValue() == "Ohio") {
             divisionID = 34;
        } else if (stateProvinceChoiceBox.getValue() == "Oklahoma") {
             divisionID = 35;
        } else if (stateProvinceChoiceBox.getValue() == "Oregon") {
             divisionID = 36;
        } else if (stateProvinceChoiceBox.getValue() == "Pennsylvania") {
             divisionID = 37;
        } else if (stateProvinceChoiceBox.getValue() == "Rhode Island") {
             divisionID = 38;
        } else if (stateProvinceChoiceBox.getValue() == "South Carolina") {
             divisionID = 39;
        } else if (stateProvinceChoiceBox.getValue() == "South Dakota") {
             divisionID = 40;
        } else if (stateProvinceChoiceBox.getValue() == "Tennessee") {
             divisionID = 41;
        } else if (stateProvinceChoiceBox.getValue() == "Texas") {
             divisionID = 42;
        } else if (stateProvinceChoiceBox.getValue() == "Utah") {
             divisionID = 43;
        } else if (stateProvinceChoiceBox.getValue() == "Vermont") {
             divisionID = 44;
        } else if (stateProvinceChoiceBox.getValue() == "Virginia") {
             divisionID = 45;
        } else if (stateProvinceChoiceBox.getValue() == "Washington") {
             divisionID = 46;
        } else if (stateProvinceChoiceBox.getValue() == "West Virginia") {
             divisionID = 47;
        } else if (stateProvinceChoiceBox.getValue() == "Wisconsin") {
             divisionID = 48;
        } else if (stateProvinceChoiceBox.getValue() == "Wyoming") {
             divisionID = 49;
        } else if (stateProvinceChoiceBox.getValue() == "Hawaii") {
             divisionID = 52;
        } else if (stateProvinceChoiceBox.getValue() == "Alaska") {
             divisionID = 54;
        } else if (stateProvinceChoiceBox.getValue() == "Northwest Territories") {
             divisionID = 60;
        } else if (stateProvinceChoiceBox.getValue() == "Alberta") {
             divisionID = 61;
        } else if (stateProvinceChoiceBox.getValue() == "British Columbia") {
             divisionID = 62;
        } else if (stateProvinceChoiceBox.getValue() == "Manitoba") {
             divisionID = 63;
        } else if (stateProvinceChoiceBox.getValue() == "New Brunswick") {
             divisionID = 64;
        } else if (stateProvinceChoiceBox.getValue() == "Nova Scotia") {
             divisionID = 65;
        } else if (stateProvinceChoiceBox.getValue() == "Prince Edward Island") {
             divisionID = 66;
        } else if (stateProvinceChoiceBox.getValue() == "Ontario") {
             divisionID = 67;
        } else if (stateProvinceChoiceBox.getValue() == "Quebec") {
             divisionID = 68;
        } else if (stateProvinceChoiceBox.getValue() == "Saskatchewan") {
             divisionID = 69;
        } else if (stateProvinceChoiceBox.getValue() == "Nunavut") {
             divisionID = 70;
        } else if (stateProvinceChoiceBox.getValue() == "Yukon") {
             divisionID = 71;
        } else if (stateProvinceChoiceBox.getValue() == "Newfoundland and Labrador") {
             divisionID = 72;
        } else if (stateProvinceChoiceBox.getValue() == "England") {
             divisionID = 101;
        } else if (stateProvinceChoiceBox.getValue() == "Wales") {
             divisionID = 102;
        } else if (stateProvinceChoiceBox.getValue() == "Scotland") {
             divisionID = 103;
        } else if (stateProvinceChoiceBox.getValue() == "Northern Ireland") {
             divisionID = 104;
        }

        System.out.println("The state that was chosen was " + stateProvinceChoiceBox.getValue() + " and the value entered into the" +
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
            DBCustomers.addNewCustomer(customerName, customerAddress, customerPostalCode, customerPhoneNumber, divisionID);
        }

        // Label confirming save to database
        addCustomerLabel.setText("You Added A New Customer. Now click close button.");

        // Return to main screen controller
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1060, 900);
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
        // Observable list for countries choice box, default value is "U.S"
        countriesChoiceBox.setValue("U.S");
        countriesChoiceBox.setItems(countriesList);

        // Observable list for state / provinces choice box, default value is "Alabama"
        stateProvinceChoiceBox.setValue("Alabama");
        stateProvinceChoiceBox.setItems(stateProvinceList);
    }
}
