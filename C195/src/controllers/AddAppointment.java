package controllers;

// Import statements
import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Contacts;
import models.Customers;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    // FX Ids for Labels
    @FXML private Label appointmentIdLabel;
    @FXML private Label customerLabel;
    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label locationLabel;
    @FXML private Label contactLabel;
    @FXML private Label contactIdLabel;
    @FXML private Label typeLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label customerIdLabel;
    @FXML private Label addAppointmentMessageLabel;

    // FX IDs for Text Fields
    @FXML private TextField appointmentIdTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField customerIdTextField;
    @FXML private TextField contactIdTextField;

    // FX Ids for DatePicker Time Selection
    @FXML private ChoiceBox<Integer> startMinuteChoiceBox;
    @FXML private ChoiceBox<Integer> startHourChoiceBox;
    @FXML private ChoiceBox<Integer> endMinuteChoiceBox;
    @FXML private ChoiceBox<Integer> endHourChoiceBox;
    @FXML private DatePicker startDatePicker;

    // FX Ids for the Buttons
    @FXML private Button saveButton;
    @FXML private Button closeButton;

    // FX Ids for Combo Boxes
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private ComboBox<Customers> customerComboBox;

    // Declare customerId object for Add Appointment Save Method
    private Customers customerId;

    // Observable List Combo Box for Meeting Types
    ObservableList<String> meetingType = FXCollections.observableArrayList(
            "Backlog Grooming",
            "De-Briefing",
            "Planning Session",
            "Sprint Planning",
            "Sprint Retrospective",
            "Staff",
            "Daily Scrum");
    // Observable List Combo Box for Contacts
    final ObservableList contactName = FXCollections.observableArrayList(DBContacts.getAllContacts());

    // Observable List Combo Box for Customers
    final ObservableList customerName = FXCollections.observableArrayList(DBCustomers.getAllCustomers());

    /**
    // Observable List Choice Box For Starting Hour
    ObservableList<LocalDateTime> startHourList = FXCollections.observableArrayList(08,09,10,11,12,13,14,15,16,17.18,19,20);

    // Observable List Choice Box For Starting Minute
    ObservableList<LocalDateTime> startMinuteList = FXCollections.observableArrayList(00,01,02,03,04,05,06,07,08,09,10,11,12,
            13,14,15,16,17.18,19,20,21,22,23,24,25,26,27.28,29,30,31,32,33,34,35,36,37,38,39,40,41.42,43.44,45,46,47.48,49,50,51,
        52,53,54,55,56,57,58,59);

    // Observable List Choice Box For Ending HourObservableList<LocalDateTime> endHourList = FXCollections.observableArrayList(08,09,10,11,12,13,14,15,16,17.18,19,20);
    ObservableList<LocalDateTime> endHourList = FXCollections.observableArrayList(08,09,10,11,12,13,14,15,16,17.18,19,20);

    // Observable List Choice Box For Ending Minute
    ObservableList<LocalDateTime> endMinuteList = FXCollections.observableArrayList(00,01,02,03,04,05,06,07,08,09,10,11,12,
            13,14,15,16,17.18,19,20,21,22,23,24,25,26,27.28,29,30,31,32,33,34,35,36,37,38,39,40,41.42,43.44,45,46,47.48,49,50,51,
            52,53,54,55,56,57,58,59);
    */

    /**
     * The displayCustomerId method is used to populate the customer ID text field once the customerComboBox has chosen
     * a customer from the customer data list
     * @param actionEvent
     */
    @FXML private void displayCustomerId(javafx.event.ActionEvent actionEvent) {
        this.customerIdTextField.setText(String.valueOf(customerComboBox.getValue().getCustomerID()));
    }

    /**
     * The displayCustomerId method is used to populate the customer ID text field once the customerComboBox has chosen
     * a customer from the customer data list
     * @param actionEvent
     */
    @FXML private void displayContactId(javafx.event.ActionEvent actionEvent) {
        this.contactIdTextField.setText(String.valueOf(contactComboBox.getValue().getContactID()));
    }

    /**
     * This method saves the added data to the appointments database and returns to the main controller
     * @param actionEvent
     */
    public void save(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {

        // Get Data From Add Customer Controller
       String title = titleTextField.getText();
       String description = descriptionTextField.getText();
       String location = locationTextField.getText();
       Integer contactId = contactComboBox.getValue().getContactID();
       String type = typeComboBox.getValue();
       LocalDate start = startDatePicker.getValue();
       LocalTime startTime = LocalTime.of(startHourChoiceBox.getValue(), startMinuteChoiceBox.getValue());
       LocalTime endTime = LocalTime.of(endHourChoiceBox.getValue(), endMinuteChoiceBox.getValue());
       Integer customerID = customerComboBox.getValue().getCustomerID();
       this.customerIdTextField.setText(String.valueOf(customerId.getCustomerID()));

       /*
       // Get Current  User Id from Global Variable currentUserId located in Login Controller
       String userId = DBUsers.getCurrentUserLoggedInId(currentUserId);
       */

       /**
       // Concatenate Start Date Picker, Start Hour, Start Minute
        public LocalDateTime atDate(start + " " + startTime);

        // Concatenate Start Date Picker, End Hour, End Minute
        public LocalDateTime atDate(start + " " + endTime);
        */

       if (customerID == null) {
           addAppointmentMessageLabel.setText("You must select a \"Customer\" before saving");
       } else if(titleTextField.getText().isEmpty()) {
           addAppointmentMessageLabel.setText("The \"Title\" text field cannot be blank.");
       } else if (descriptionTextField.getText().isEmpty()) {
           addAppointmentMessageLabel.setText("The \"Description\" text field cannot be blank.");
       } else if (locationTextField.getText().isEmpty()) {
           addAppointmentMessageLabel.setText("The \"Location\" text field cannot be blank.");
       } else if (contactComboBox.getValue() == null) {
           addAppointmentMessageLabel.setText("You must select a \"Contact\" before saving.");
       } else if (typeComboBox.getValue() == null) {
           addAppointmentMessageLabel.setText("You must select a \"Type\" before saving.");
       } else if (startDatePicker.getValue() == null) {
           addAppointmentMessageLabel.setText("You must select a \"Date\" before saving.");
       } else if (startHourChoiceBox == null) {
           addAppointmentMessageLabel.setText("You must select a \"Start Hour\" before saving.");
       } else if (startMinuteChoiceBox == null) {
           addAppointmentMessageLabel.setText("You must select a \"Start Minute\" before saving.");
       } else if (endHourChoiceBox == null) {
           addAppointmentMessageLabel.setText("You must select a \"End Hour\" before saving.");
       } else if (endMinuteChoiceBox == null) {
           addAppointmentMessageLabel.setText("You must select a \"End Minute\" before saving.");
       } else if (customerComboBox == null) {
           addAppointmentMessageLabel.setText("You must select a \"Customer\" before saving");
       } else {
           DBAppointments.addNewAppointment(title, description, location, contactId, type, customerId);
       }
    }

    /**
     * This method will close the add appointment controller without saving any data and returns to the main controller
     * @param actionEvent
     * @throws IOException
     */
    public void close(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1060, 875);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void close(ActionEvent event) throws IOException {
    }

    /**
     * This method initializes the page when loaded
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize Meeting Type Observable List
        typeComboBox.setItems(meetingType);

        // Initialize Contact Name Observable List
        contactComboBox.setItems(contactName);

        // ComboBox List for Customers populated from customers table
        customerComboBox.setItems(customerName);

        /**
        // ChoiceBox List for startingHourList
        startHourChoiceBox.setItems(startHourList);

        // ChoiceBox List for startingHourList
        startMinuteChoiceBox.setItems(startMinuteList);

        // ChoiceBox List for startingHourList
        endHourChoiceBox.setItems(endHourList);

        // ChoiceBox List for startingHourList
        endHourChoiceBox.setItems(endMinuteList);
        */
    }
}
