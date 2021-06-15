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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customers;
import models.Division;
import utilities.DataProvider;

import java.io.IOException;

public class ModifyCustomer {

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

    // Declare customerId object for Modify Customer Method
    private Customers customerId;

    // FX Ids for Labels
    @FXML private Label customerIdLabel;
    @FXML private Label customerNameLabel;
    @FXML private Label address1Label;
    @FXML private Label cityLabel;
    @FXML private Label countryLabel;
    @FXML private Label zipCodeLabel;
    @FXML private Label phoneNumberLabel;
    @FXML private Label staveProvinceLabel;
    @FXML private Label addCustomerLabel;

    // FX Ids for Text Fields
    @FXML private TextField customerIdTextField;
    @FXML private TextField customerNameTextField;
    @FXML private TextField address1TextField;
    @FXML private TextField countryTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField phoneNumberTextField;

    // FIX Ids for Choice Boxes
    @FXML private ChoiceBox<String> countriesChoiceBox;
    @FXML private ChoiceBox<Division> stateProvinceChoiceBox;

    // FX Ids for buttons
    @FXML private Button saveButton;
    @FXML private Button closeButton;

    /**
     * This method closes the screen and returns to the main screen
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
     * This save button method saes the modified customer data to the database.
     * @param actionEvent
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @FXML Object save(ActionEvent actionEvent) throws IOException, InterruptedException {
        // Get Data From Add Customer Controller
        String customer_id = customerIdTextField.getText();
        String customerName = customerNameTextField.getText();
        String customerAddress = address1TextField.getText();
        String customerPostalCode = zipCodeTextField.getText();
        String customerPhoneNumber = phoneNumberTextField.getText();
        String customerCountry = countriesChoiceBox.getValue();
        Integer divisionID = null;



        System.out.println("The state that was chosen was " + stateProvinceChoiceBox.getValue() + " and the value entered into the" +
                " database is " + divisionID);

        // Verify text fields cannot be empty
        if (customerNameTextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Customer Name\" text field cannot be blank.");
            return null;
        } else if (address1TextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Address\" text field cannot be blank.");
            return null;
        } else if (zipCodeTextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Postal Code\" text field cannot be blank.");
            return null;
        } else if (phoneNumberTextField.getText().isEmpty()) {
            addCustomerLabel.setText("The \"Phone Number\" text field cannot be blank.");
            return null;
        } else {
            // Update Database
            DBCustomers.updateCustomer(customer_id, customerName, customerAddress, customerPostalCode, customerPhoneNumber, divisionID);
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
     * Set Customer Data method recieves customer data from the main controller and then populates the text fields to be modified.
     * @param customer - This Customer is used to set teh customer data to the modify screen
     */
    public void setCustomerData(Customers customer) {
        customerId = customer;
        this.customerIdTextField.setText(String.valueOf(customerId.getCustomerID()));
        this.customerNameTextField.setText(String.valueOf(customerId.getCustomerName()));
        this.address1TextField.setText(String.valueOf(customerId.getCustomerAddress()));
        this.zipCodeTextField.setText(String.valueOf(customerId.getCustomerPostalCode()));
        this.phoneNumberTextField.setText(String.valueOf(customerId.getCustomerPhone()));
        stateProvinceChoiceBox.setItems(DataProvider.getAllDivisions());

    }

    /**
     * This method initializes the observable list arrays to populate the countries and the states and provinces.
     */
    @FXML private void initialize() {
        // This initializes the countries choice box
        countriesChoiceBox.setValue("U.S");
        countriesChoiceBox.setItems(countriesList);

    }

}
