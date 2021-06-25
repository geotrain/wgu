package controllers;

// Import Statements
import DBAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Appointments;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ViewAllCustomerAppointmentsByTpeAndMonth implements Initializable {

    // FX ID's Labels
    @FXML private AnchorPane customerReportByTypeAndMonthLabel;
    @FXML private Label monthLabel;
    @FXML private Label typeLabel;
    @FXML private Label errorLabel;
    @FXML private Label countLabel;

    // FX ID's Text Fields
    @FXML private TextField countTextField;

    // FX ID's Combo Boxes
    @FXML private ComboBox<String> monthComboBox;
    @FXML private ComboBox<String> typeComboBox;

    // FX ID's Buttons
    @FXML private Button closeButton;

    // Declare global variables to share with DBAppointments.getCustomerTypeAndMonthReport() method
    private static String monthSelected;
    private static String typeSelected;

    // Observable List Combo Box for Meeting Types
    ObservableList<String> meetingType = FXCollections.observableArrayList(
            "Backlog Grooming",
            "De-Briefing",
            "Planning Session",
            "Sprint Planning",
            "Sprint Retrospective",
            "Staff",
            "Daily Scrum");

    // Observable List Combo Box for Meeting Types
    ObservableList<String> monthName = FXCollections.observableArrayList(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December");

    /**
     * This method will close the ViewAllCustomerAppointmentsByTypeAndMonth Controller and Return To Main Controller
     * @param actionEvent
     * @throws IOException
     */
    @FXML void close(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 1090, 875);
        stage.setTitle("Welcome To Schedule Manager v 1.0");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method will close the ViewAllCustomerAppointmentsByTypeAndMonth Controller and Return To Main Controller
     * @param actionEvent This is a parameter
     * @throws IOException This is a return statement
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
     * This initialize method will generate the Appointments By Type and Month Table View
     * @param url This is a parameter
     * @param resourceBundle This is a parameter
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set label to clear
        errorLabel.setText("");

        // Initialize Meeting Type Observable List
        typeComboBox.setItems(meetingType);

        // Initialize Meeting Type Observable List
        monthComboBox.setItems(monthName);
    }

    /**
     * This selectMonth action will choose a type generated from an Observable List
     * @param actionEvent This is a parameter
     */
    public void selectMonthType(javafx.event.ActionEvent actionEvent) {
        monthSelected = monthComboBox.getValue();
        typeSelected = typeComboBox.getValue();
        System.out.println("The selections were " + monthSelected + " for month and " + typeSelected + " for type.");
        if (monthSelected == null) {
            errorLabel.setText("Please select a month before selecting type.");
            errorLabel.setTextFill(Color.RED);
            return;
        } else {
            // Set label to clear
            errorLabel.setText("");
            Integer monthTypeCount = DBAppointments.getCustomerTypeAndMonthReport(typeSelected, monthSelected);
            System.out.println("This is monthTypeCount " + monthTypeCount);
            countLabel.setText("Type And Month Count");
            countTextField.setText(String.valueOf(monthTypeCount));
            //countLabel.setText("Count : " + monthTypeCount);
        }
    }

    /**
     * This clearErroLabel will set the error label to empty once a selection is made
     * @param actionEvent This is a parameter
     */
    public void clearErrorLabel(javafx.event.ActionEvent actionEvent) {
        // Set label to clear
        errorLabel.setText("");
        typeComboBox.getItems().clear();
        countTextField.clear();
        countLabel.setText("Type And Month Count");
        // Observable List Combo Box for Meeting Types
        ObservableList<String> meetingType = FXCollections.observableArrayList(
                "Backlog Grooming",
                "De-Briefing",
                "Planning Session",
                "Sprint Planning",
                "Sprint Retrospective",
                "Staff",
                "Daily Scrum");
        // Initialize Meeting Type Observable List
        typeComboBox.setItems(meetingType);
    }
}
