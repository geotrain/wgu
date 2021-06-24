package controllers;
// Import Statments
import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import models.Appointments;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ContactSchedule implements Initializable {

    // FX ID's Labels
    @FXML private Label contactsEmailListLabel;
    @FXML private Label chooseContactLabel;

    // FX ID's Buttons'
    @FXML private Button closeButton;

    // FX ID's Table View and Table Columns
    @FXML private TableView<Appointments> contactAppointmentTableView;
    @FXML private TableColumn<Appointments, Integer> appointmentIdColumn;
    @FXML private TableColumn<Appointments, String> titleColumn;
    @FXML private TableColumn<Appointments, String> typeColumn;
    @FXML private TableColumn<Appointments, String> descriptionColumn;
    @FXML private TableColumn<Appointments, Date> startColumn;
    @FXML private TableColumn<Appointments, Date> endColumn;
    @FXML private TableColumn<Appointments, Integer> customerIdColumn;

    // FX ID's Radio Buttons
    @FXML private RadioButton contactOneRadioButton;
    @FXML private RadioButton contactTwoRadioButton;
    @FXML private RadioButton contactThreeRadioButton;

    @FXML
    private ToggleGroup contactToggleGroup;

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
     * This method returns only appointments for contactOneSelection radio button one
     * @param actionEvent
     */
    public void contactOneSelection(javafx.event.ActionEvent actionEvent) {
        // Populates and initializes the appointments table list table view set by contactOneSelection default
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        //contactAppointmentTableView.setItems(DBAppointments.getContactOneAppointments(contactId)); //TODO not working
    }

    /**
     * This method returns only appointments for contactOneSelection radio button two
     * @param actionEvent
     */
    public void contactTwoSelection(javafx.event.ActionEvent actionEvent) {
        // Populates and initializes the appointments table list table view set by contactOneSelection default
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        //contactAppointmentTableView.setItems(DBAppointments.getContactTwoAppointments(contactId); //TODO not working
    }

    /**
     * This method returns only appointments for contactOneSelection radio button three
     * @param actionEvent
     */
    public void contactThreeSelection(javafx.event.ActionEvent actionEvent) {
        // Populates and initializes the appointments table list table view set by contactOneSelection default
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        //contactAppointmentTableView.setItems(DBAppointments.getContactThreeAppointments(contactId)); TODO not working
    }

}
