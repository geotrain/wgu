package controllers;

// Import statements
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    // FX Ids for Labels
    @FXML private Label appointmentIdLabel;
    @FXML private Label customerIdLabel;
    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label locationLabel;
    @FXML private Label contactLabel;
    @FXML private Label typeLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;
    @FXML private Label addAppointmentMessageLabel;

    // FX IDs for Text Fields
    @FXML private TextField appointmentIdTextField;
    @FXML private TextField customerIdTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;

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
    @FXML private ComboBox<String> contactComboBox;
    @FXML private ComboBox<String> typeComboBox;

    // Combo Boxes Observable Lists
    ObservableList<String> meetingType = FXCollections.observableArrayList("Backlog Grooming","De-Briefing","Planning Session",
            "Sprint Planning","Sprint Retrospective","Staff","Daily Scrum");

    ObservableList<String> contactName = FXCollections.observableArrayList("Anika Costa", "Daniel Garcia", "Li Lee");


    /**
     * This method saves the added data to the appointments database and returns to the main controller
     * @param actionEvent
     */
    public void save(javafx.event.ActionEvent actionEvent) {

        // Get Data From Add Customer Controller
       String title = titleTextField.getText();
       String description = descriptionTextField.getText();
       String location = locationTextField.getText();
       String contactId = contactComboBox.getValue();
       String type = typeComboBox.getValue();
       LocalDate start = startDatePicker.getValue();
       LocalTime startTime = LocalTime.of(startHourChoiceBox.getValue(), startMinuteChoiceBox.getValue());
       LocalTime endTime = LocalTime.of(endHourChoiceBox.getValue(), endMinuteChoiceBox.getValue());


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
    }
}
