package controllers;
// Import Statments
import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Appointments;
import models.Contacts;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ContactSchedule implements Initializable {

    // FX ID's Labels
    @FXML private Label contactsEmailListLabel;
    @FXML private Label chooseContactLabel;
    @FXML private Label contactIdSelectedLabel;

    // FX ID's Buttons
    @FXML private Button closeButton;

    // FX ID's ComboBoxes
    @FXML private ComboBox<Contacts> contactComboBox;

    // FX ID's Table View and Table Columns
    @FXML private TableView<Appointments> contactAppointmentTableView;
    @FXML private TableColumn<Appointments, Integer> appointmentIdColumn;
    @FXML private TableColumn<Appointments, String> titleColumn;
    @FXML private TableColumn<Appointments, String> typeColumn;
    @FXML private TableColumn<Appointments, String> descriptionColumn;
    @FXML private TableColumn<Appointments, Date> startColumn;
    @FXML private TableColumn<Appointments, Date> endColumn;
    @FXML private TableColumn<Appointments, Integer> customerIdColumn;

    // FX ID's Text Fields
    @FXML private TextField contactIdTextField;

    // Observable List Combo Box for Contacts
    final ObservableList contactName = FXCollections.observableArrayList(DBContacts.getAllContacts());

    /**
     * This method will close the Contact Email controller
     * @param actionEvent
     * @throws IOException
     */
    public void close(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1090, 900);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This initialize method will generate the Contact Schedule Table View Upon Radio Button Selection
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize Contact Name Observable List
        contactComboBox.setItems(contactName);

        // Populates and initializes ALL of the contact appointments table list table view
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactAppointmentTableView.setItems(DBAppointments.getAllAppointments());
    }

    /**
     * The displayContactId method is used to populate the customer ID text field once the customerComboBox has chosen
     * a customer from the customer data list
     * @param actionEvent
     */
    @FXML private void displayContactId(javafx.event.ActionEvent actionEvent) {
        contactIdTextField.setText(String.valueOf(contactComboBox.getValue().getContactID()));
        contactAppointmentTableView.setItems(DBAppointments.getContactScheduleByContactId(Integer.parseInt(contactIdTextField.getText())));

    }
}
