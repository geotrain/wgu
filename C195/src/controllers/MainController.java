package controllers;

import com.mysql.cj.jdbc.MysqlSQLXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // Main Controller Buttons
    @FXML private Button viewAllCustomerAppointmentsButton;
    @FXML private Button viewAllAppointmentsByType;
    @FXML private Button viewAllContactsSchedulesButton;
    @FXML private Button viewContactsEmailListButton;
    @FXML private Button logOffButton;
    @FXML private Button exitProgramButton;
    @FXML private Button addAppointmentButton;
    @FXML private Button modifyAppointmentButton;
    @FXML private Button deleteAppointmentButton;
    @FXML private Button addCustomerButton;
    @FXML private Button modifyCustomerButton;
    @FXML private Button deleteCustomerButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Initialized");
    }

    @FXML
    public void addAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/addAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 500);
        stage.setTitle("Add Appointment");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void modifyAppointment(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteAppointment(ActionEvent actionEvent) {
    }

    // Add | Modify | Customer
    @FXML
    public void addCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void modifyCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteCustomer(ActionEvent actionEvent) {
    }


    // Controls Methods
    @FXML
    public void viewAllContactsSchedules(ActionEvent actionEvent) {
    }

    @FXML
    public void viewAllCustomerAppointments(ActionEvent actionEvent) {
    }

    @FXML
    public void viewContactsEmailList(ActionEvent actionEvent) {
    }

    @FXML
    public void logOff(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 602, 257);
        stage.setTitle("Appointment User Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitProgram(ActionEvent actionEvent) {
        System.out.println("Exit Button Selected");
        System.exit(0);
    }


    public void viewAllAppointmentsByType(ActionEvent actionEvent) {
    }
}
