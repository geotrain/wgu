package controllers;
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
import models.Appointments;
import models.Contacts;
import models.Customers;
import models.Users;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class ModifyAppointment implements Initializable {
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
    @FXML private Label userLabel;
    @FXML private Label userIdLabel;
    @FXML private Label modifyAppointmentMessageLabel;

    // FX IDs for Text Fields
    @FXML private TextField appointmentIdTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField customerIdTextField;
    @FXML private TextField contactIdTextField;
    @FXML private TextField userIdTextField;

    // FX Ids for DatePicker Time Selection
    @FXML private ChoiceBox<String> startMinuteChoiceBox;
    @FXML private ChoiceBox<String> startHourChoiceBox;
    @FXML private ChoiceBox<String> endMinuteChoiceBox;
    @FXML private ChoiceBox<String> endHourChoiceBox;
    @FXML private DatePicker startDatePicker;

    // FX Ids for the Buttons
    @FXML private Button saveButton;
    @FXML private Button closeButton;

    // FX Ids for Combo Boxes
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private ComboBox<Customers> customerComboBox;
    @FXML private ComboBox<Users> userComboBox;

    // Declare customerId object for Add Appointment Save Method
    private Appointments appointmentId;

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

    // Observable List Combo Box for Users
    final ObservableList usersName = FXCollections.observableArrayList(DBUsers.getAllUsers());

    // Observable List Choice Box For Starting Hour
    ObservableList<String> startHourList = FXCollections.observableArrayList("05","06","07","08","09","10","11",
            "12","13","14","15","16","17","18","19","20","21","22","23");

    // Observable List Choice Box For Starting Minute
    ObservableList<String> startMinuteList = FXCollections.observableArrayList("00","15","30","45");

    // Observable List Choice Box For Ending Hour
    ObservableList<String> endHourList = FXCollections.observableArrayList("05","06","07","08","09","10","11","12",
            "13","14","15","16","17","18","19","20","21","22","23");

    // Observable List Choice Box For Ending Minute
    ObservableList<String> endMinuteList = FXCollections.observableArrayList("00","15","30","45");

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
     * The displayUserId method is used to populate the user ID text field once the userComboBox has chosen
     * a user from the user data list
     * @param actionEvent
     */
    @FXML private void displayUserId(javafx.event.ActionEvent actionEvent) {
        this.userIdTextField.setText(String.valueOf(userComboBox.getValue().getId()));
    }

    @FXML
    void save (javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException, ParseException {

        if (customerComboBox.getValue() == null) {
            modifyAppointmentMessageLabel.setText("You must select a \"Customer\" before saving");
        } else if(titleTextField.getText().isEmpty()) {
            modifyAppointmentMessageLabel.setText("The \"Title\" text field cannot be blank.");
        } else if (descriptionTextField.getText().isEmpty()) {
            modifyAppointmentMessageLabel.setText("The \"Description\" text field cannot be blank.");
        } else if (locationTextField.getText().isEmpty()) {
            modifyAppointmentMessageLabel.setText("The \"Location\" text field cannot be blank.");
        } else if (contactComboBox.getValue() == null) {
            modifyAppointmentMessageLabel.setText("You must select a \"Contact\" before saving.");
        } else if (typeComboBox.getValue() == null) {
            modifyAppointmentMessageLabel.setText("You must select a \"Type\" before saving.");
        } else if (startDatePicker.getValue() == null || startDatePicker.getValue().equals(0)) {
            modifyAppointmentMessageLabel.setText("You must select a \"Date\" before saving.");
        } else if (startHourChoiceBox.getSelectionModel().isEmpty()) {
            modifyAppointmentMessageLabel.setText("You must select a \"Start Hour\" before saving.");
        } else if (startMinuteChoiceBox.getSelectionModel().isEmpty()) {
            modifyAppointmentMessageLabel.setText("You must select a \"Start Minute\" before saving.");
        } else if (endHourChoiceBox.getSelectionModel().isEmpty()) {
            modifyAppointmentMessageLabel.setText("You must select a \"End Hour\" before saving.");
        } else if (endMinuteChoiceBox.getSelectionModel().isEmpty()) {
            modifyAppointmentMessageLabel.setText("You must select a \"End Minute\" before saving.");
        } else if (customerComboBox == null) {
            modifyAppointmentMessageLabel.setText("You must select a \"Customer\" before saving");
        } else {
            // Get Data From Add Customer Controller
            String appointment_id = appointmentIdTextField.getText();
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            String location = locationTextField.getText();
            String contactIdNameString = contactComboBox.getValue().toString();
            Integer contactId = DBCustomers.getContactId(contactIdNameString);
            System.out.println("Contact ID is " + contactComboBox.getValue());
            String type = typeComboBox.getValue().toString();
            LocalDate start = startDatePicker.getValue();
            LocalTime startTime = LocalTime.of(Integer.parseInt(startHourChoiceBox.getValue()), Integer.parseInt(startMinuteChoiceBox.getValue()));
            LocalTime endTime = LocalTime.of(Integer.parseInt(endHourChoiceBox.getValue()), Integer.parseInt(endMinuteChoiceBox.getValue()));
            String customerIdNameString = customerComboBox.getValue().toString();
            Integer customerID = DBCustomers.getCustomerId(customerIdNameString);
            String userIdNameString = userComboBox.getValue().toString();
            Integer userID = DBCustomers.getUserId(userIdNameString);

            // Concatenate Start Date Picker, Start Hour, Start Minute
            LocalDateTime startDateTime = LocalDateTime.of(start, startTime);

            // Concatenate Start Date Picker, End Hour, End Minute
            LocalDateTime endDateTime = LocalDateTime.of(start, endTime);

            // Check if endDateTime isBefore() startDateTime
            if (endDateTime.isBefore(startDateTime)) {
                modifyAppointmentMessageLabel.setText("You must select a Start Time That Comes Before End Time.");
                return;
            }

            // Check if startDateTime and endDateTime are the same
            if (startDateTime.isEqual(endDateTime)) {
                modifyAppointmentMessageLabel.setText("The Start Time And The End Time Cannot Be The Same.");
                return;
            }

            // Check if startDateTime and endDateTime have any overlaps with existing appointments TODO Need to Check Logic
            LocalDateTime existingAppointmentStartTime = DBAppointments.getAllAppointmentStartTimes(Appointments.getStart());
            LocalDateTime existingAppointmentEndTime = DBAppointments.getAllAppointmentEndTimes(Appointments.getEnd());
            if (endDateTime.isBefore(existingAppointmentStartTime) && endDateTime.isAfter(existingAppointmentEndTime)) {
                modifyAppointmentMessageLabel.setText("You are overlapping an existing appointment. Please change times and try again.");
                return;
            } else if (startDateTime.isAfter(existingAppointmentStartTime) && startDateTime.isBefore(existingAppointmentEndTime)) {
                modifyAppointmentMessageLabel.setText("You are overlapping an existing appointment. Please change times and try again.");
                return;
            } else if (startDateTime.isBefore(existingAppointmentStartTime) && endDateTime.isBefore(existingAppointmentEndTime)) {
                modifyAppointmentMessageLabel.setText("You are overlapping an existing appointment. Please change times and try again.");
                return;
            }

            System.out.println("Start Time is " + startDateTime);
            System.out.println("End Time is " + endDateTime);

            // Put Time Zone Conversation From Local Time To EST Time Zone then see if local time piece fits within the EST
            // between 8 am - 10 p.m.
            ZoneId userTimeZone = ZoneId.systemDefault();
            ZoneId easternTimeZoneId = ZoneId.of("America/New_York");
            System.out.println("The current user's time zone is: " +userTimeZone);
            System.out.println("The current user's time zone in ET is: " +easternTimeZoneId);

            ZonedDateTime localStart = startDateTime.atZone(userTimeZone);
            ZonedDateTime estStart = localStart.withZoneSameInstant(easternTimeZoneId);
            ZonedDateTime localEnd = endDateTime.atZone(userTimeZone);
            ZonedDateTime estEnd = localEnd.withZoneSameInstant(easternTimeZoneId);

            /**
             * This checks if the local time zone of the user against the eastern time zone then does a comparison to
             * make sure it falls between 08:00 and 22:00 EST (Office Hours In New York)
             */
            if (estStart.toLocalTime().isBefore(LocalTime.of(8,0)) ||
                    estEnd.toLocalTime().isAfter(LocalTime.of(22,0)) ||
                    estStart.toLocalTime().isAfter(LocalTime.of(22,0)) ||
                    estEnd.toLocalTime().isBefore(LocalTime.of(8,0))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Schedule Time");
                alert.setHeaderText("You Must Select A Time Zone Between 08:00 - 22:00 ET");
                alert.setContentText("You will need to change the beginning and/or end time of your appointment to between 8 a.m. and 10 p.m. ET");
                alert.showAndWait();
                return;
            }

            // Update the database
            DBAppointments.updateAppointment(appointment_id,title,description,location,contactId,type,startDateTime,endDateTime,customerID,userID);

            // Label confirming save to database
            modifyAppointmentMessageLabel.setText("You Added A New Appointment. Now click close button.");

            // Return to main screen controller
            Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene((Parent) root, 1090, 900);
            stage.setTitle("Welcome To Schedule Manager v 1.0");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
        return;
    }

    public void close(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1090, 875);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void close(ActionEvent event) throws IOException {
    }

    /**
     * Set Appointment Data method receives appointment data from the main controller and then populates the text fields to be modified.
     * @param appointment - This Appointment is used to set the appointment data to the modify screen
     */
    public void setAppointmentData(Appointments appointment) {
        appointmentId = appointment;
        this.appointmentIdTextField.setText(String.valueOf(appointmentId.getId()));
        this.titleTextField.setText(String.valueOf(appointmentId.getTitle()));
        this.descriptionTextField.setText(String.valueOf(appointmentId.getDescription()));
        this.locationTextField.setText(String.valueOf(appointmentId.getLocation()));
        this.contactIdTextField.setText(String.valueOf(appointmentId.getContactId()));
        this.contactComboBox.setValue(appointmentId.getContactName(appointmentId.getContactId()));
        this.typeComboBox.setValue(appointment.getType());
        startDatePicker.setValue(LocalDate.from(appointmentId.getStart()));

        /** This compares what the startHourChoiceBox value is and if its 5-9 it adds a leading zero
         * as this returns only a single digit and does not populate the choice box in the modifyAppointment controller.
         */
        String hourStart = String.valueOf(LocalTime.from(appointmentId.getStart()).getHour());
        if (hourStart.length() == 1) {
            hourStart = "0" + LocalTime.from(appointmentId.getStart()).getHour();
            this.startHourChoiceBox.setValue(hourStart);
        } else {
            hourStart = String.valueOf(LocalTime.from(appointmentId.getStart()).getHour());
            this.startHourChoiceBox.setValue(hourStart);
        }

        /**
         * This compares what the minuteStartChoiceBox value is and if its 5-9 it adds a leading zero
         * as this returns only a single digit and does not populate the choice box in the modifyAppointment controller.
         */
        String minuteStart = String.valueOf(LocalTime.from(appointmentId.getStart()).getMinute());
        if (minuteStart.length() == 1) {
            minuteStart = "0" + LocalTime.from(appointmentId.getStart()).getMinute();
            this.startMinuteChoiceBox.setValue(minuteStart);
        } else {
            minuteStart = String.valueOf(LocalTime.from(appointmentId.getStart()).getMinute());
            this.startMinuteChoiceBox.setValue(minuteStart);
        }

        /** This compares what the endHourChoiceBox value is and if its 5-9 it adds a leading zero
         * as this returns only a single digit and does not populate the choice box in the modifyAppointment controller.
         */
        String hourEnd = String.valueOf(LocalTime.from(appointmentId.getEnd()).getHour());
        if (hourEnd.length() == 1) {
            hourEnd = "0" + LocalTime.from(appointmentId.getEnd()).getHour();
            this.endHourChoiceBox.setValue(hourEnd);
        } else {
            hourEnd = String.valueOf(LocalTime.from(appointmentId.getEnd()).getHour());
            this.endHourChoiceBox.setValue(hourEnd);
        }

        /**
         * This compares what the minuteStartChoiceBox value is and if its 5-9 it adds a leading zero
         * as this returns only a single digit and does not populate the choice box in the modifyAppointment controller.
         */
        String minuteEnd = String.valueOf(LocalTime.from(appointmentId.getEnd()).getMinute());
        if (minuteEnd.length() == 1) {
            minuteEnd = "0" + LocalTime.from(appointmentId.getEnd()).getMinute();
            this.endMinuteChoiceBox.setValue(minuteEnd);
        } else {
            minuteEnd = String.valueOf(LocalTime.from(appointmentId.getEnd()).getMinute());
            this.endMinuteChoiceBox.setValue(minuteEnd);
        }

        this.customerIdTextField.setText(String.valueOf(appointmentId.getCustomerId()));
        this.customerComboBox.setValue(appointmentId.getCustomerName(appointmentId.getCustomerId()));
        this.userIdTextField.setText(String.valueOf(appointmentId.getUserId()));
        this.userComboBox.setValue(appointmentId.getUserName(appointmentId.getUserId()));
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

        // ComboBox List for Users populated from the users table
        userComboBox.setItems((usersName));

        // ChoiceBox List for startingHourList
        startHourChoiceBox.setItems(startHourList);

        // ChoiceBox List for startingHourList
        startMinuteChoiceBox.setItems(startMinuteList);

        // ChoiceBox List for startingHourList
        endHourChoiceBox.setItems(endHourList);

        // ChoiceBox List for startingHourList
        endMinuteChoiceBox.setItems(endMinuteList);
    }
}
