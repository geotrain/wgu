package controllers;

//Import statements
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
import models.Customers;
import models.Division;
import utilities.DataProvider;
import java.io.IOException;

public class ModifyCustomer {

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
    @FXML private ComboBox<Countries> countriesComboBox;
    @FXML private ComboBox<Division> stateProvinceComboBox;

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
        Scene scene = new Scene((Parent) root, 1090, 900);
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

        Integer divisionID = null;
        Division D = stateProvinceComboBox.getValue();
        if (D == null) {
            addCustomerLabel.setText("The \"Division\" must be selected to continue.");
            return null;
        }
        divisionID = D.getDivisionID();

        System.out.println("The state that was chosen was " + stateProvinceComboBox.getValue() +
                " and the value entered into the" + " database is " + divisionID);

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
            DBCustomers.updateCustomer(customer_id, customerName, customerAddress, customerPostalCode,
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
        stateProvinceComboBox.setItems(DataProvider.getAllDivisions());
        countriesComboBox.setItems(DataProvider.getAllCountries());
        for (Division division:DataProvider.getAllDivisions()) {
            if (division.getDivisionID() == customer.getDivisionID()) {
                stateProvinceComboBox.getSelectionModel().select(division);
                break;
            }
        }
        for (Countries country:DataProvider.getAllCountries()) {
            if (country.getId() == stateProvinceComboBox.getSelectionModel().getSelectedItem().getCountryID()) {
                countriesComboBox.getSelectionModel().select(country);
                break;
            }
        }
    }

    /**
     * This method initializes the observable list arrays to populate the countries and the states and provinces.
     */
    @FXML private void initialize() {

        /**
         * Initialize the countryComboBox with the getAllCountries Method
         */
        countriesComboBox.setItems(DataProvider.getAllCountries());
    }
    /**
     * The loadCountries is used to populate the state or province depending on which Country is selected.
     * @param actionEvent This is a parameter
     */
    public void loadCountries(ActionEvent actionEvent) {
        Countries C = countriesComboBox.getValue();
        stateProvinceComboBox.setItems(DataProvider.getDivisionsByCountryId(C.getId()));
    }
}
