package controllers;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import com.mysql.cj.jdbc.MysqlSQLXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import models.Appointments;
import models.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // FX Ids For Buttons
    @FXML private Button viewCustomerAppointmentsByMonthButton;
    @FXML private Button viewCustomerAppointmentsByTypeButton;
    @FXML private Button viewScheduleByContactButton;
    @FXML private Button contactEmailListButton;
    @FXML private Button logOffButton;
    @FXML private Button exitProgramButton;
    @FXML private Button addAppointmentButton;
    @FXML private Button modifyAppointmentButton;
    @FXML private Button deleteAppointmentButton;
    @FXML private Button addCustomerButton;
    @FXML private Button modifyCustomerButton;
    @FXML private Button deleteCustomerButton;

    // FX Ids for Appointments Table
    @FXML private TableView<Appointments> appointmentsTableView;
    @FXML private TableColumn<Appointments, Integer> appointmentIDColumn;
    @FXML private TableColumn<Appointments, Integer> appointmentUserIDColumn;
    @FXML private TableColumn<Appointments, Integer> appointmentCustomerIDColumn;
    @FXML private TableColumn<Appointments, String> appointmentDescriptionColumn;
    @FXML private TableColumn<Appointments, String> appointmentTypeColumn;
    @FXML private TableColumn<Appointments, Date> appointmentStartColumn;
    @FXML private TableColumn<Appointments, Date> appointmentEndColumn;

    // FX Ids For Customers Table
    @FXML private TableView<Customers> customersTableView;
    @FXML private TableColumn<Customers, Integer> customerIDColumn;
    @FXML private TableColumn<Customers, String> customerNameColumn;
    @FXML private TableColumn<Customers, String> customerAddressColumn;
    @FXML private TableColumn<Customers, String> customerPostalCodeColumn;
    @FXML private TableColumn<Customers, String> customerPhoneColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("The Main Controller Is Initialized");

        // Populates and initializes the appointments table
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointmentUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appointmentCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentsTableView.setItems(DBAppointments.getAllAppointments());

        // Populates and initializes the customers table
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customersTableView.setItems(DBCustomers.getAllCustomers());
    }

    @FXML public void addAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/addAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 500);
        stage.setTitle("Add Appointment");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML public void modifyAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/modifyAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 500);
        stage.setTitle("Modify Appointment");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteAppointment(ActionEvent actionEvent) {
    }

    // Add | Modify | Customer
    @FXML
    public void addCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/addCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 450);
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void modifyCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/modifyCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 450);
        stage.setTitle("Modify Customer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteCustomer(ActionEvent actionEvent) {
    }


    // Controls Methods
    @FXML
    public void viewScheduleByContact(ActionEvent actionEvent) {
    }

    @FXML
    public void viewCustomerAppointmentsByMonth(ActionEvent actionEvent) {
    }

    @FXML
    public void contactEmailList(ActionEvent actionEvent) {
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


    public void viewCustomerAppointmentsByType(ActionEvent actionEvent) {
    }
}
