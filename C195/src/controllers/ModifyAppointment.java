package controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ModifyAppointment {
    @FXML private Label appointmentIdLabel;
    @FXML private Label userIdLabel;
    @FXML private Label customerIdLabel;
    @FXML private Label titleLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label locationLabel;
    @FXML private Label contactLabel;
    @FXML private Label typeLabel;
    @FXML private Label urlLabel;
    @FXML private Label dateLabel;
    @FXML private Label startTimeLabel;
    @FXML private Label endTimeLabel;

    @FXML private TextField appointmentIdTextField;
    @FXML private TextField customerIdTextField;
    @FXML private TextField userIdTextField;
    @FXML private TextField titleTextField;
    @FXML private TextField descriptionTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField contactTextField;
    @FXML private TextField typeTextField;
    @FXML private TextField urlTextField;

    @FXML private DatePicker datePicker;

    @FXML private ChoiceBox<?> startAmPmChoiceBox;
    @FXML private ChoiceBox<?> startMinuteChoiceBox;
    @FXML private ChoiceBox<?> startHourChoiceBox;

    @FXML private ChoiceBox<?> endAmPmChoiceBox;
    @FXML private ChoiceBox<?> endMinuteChoiceBox;
    @FXML private ChoiceBox<?> endHourChoiceBox;

    @FXML private Button saveButton;
    @FXML private Button closeButton;

    @FXML private Label addAppointmentMessageLabel;

    public void save(javafx.event.ActionEvent actionEvent) {
    }

    public void close(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1060, 875);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void close(ActionEvent event) throws IOException {
    }
}
