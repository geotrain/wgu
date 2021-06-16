package controllers;

/**
 * Import statements
 */
import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import com.mysql.cj.jdbc.MysqlSQLXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import models.Appointments;
import models.Customers;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
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

    // FX Ids for Labels
    @FXML private Label errorLabel;

    /**
     * This method inditializes the customers and appointments tables
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
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

    /**
     * This method will open the add appointment controller
     * @param actionEvent
     * @throws IOException
     */
    @FXML public void addAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/addAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 400);
        stage.setTitle("Add Appointment");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML public void modifyAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/modifyAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 400);
        stage.setTitle("Modify Appointment");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteAppointment(ActionEvent actionEvent) {
    }

    /**
     * This method opens up the AddCustomer Controller to add a new customer to the database.
     * @param actionEvent
     * @throws IOException
     */
    @FXML public void addCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/addCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 400, 450);
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method modifies an existing customer record in the ModifyCustomer controller.
     * @param actionEvent
     * @throws IOException
     */

    @FXML public void modifyCustomer(ActionEvent actionEvent) throws IOException {
        Customers selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();

        // Verify user selects a customer when clicking the modify customer button
        if (selectedCustomer == null) {
            errorLabel.setText("You must select a customer from the customers table before modifying.");
            errorLabel.setTextFill(Color.RED);
            return;
        } else {
            errorLabel.setText("");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../views/modifyCustomer.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 400, 450);
            ModifyCustomer controller = fxmlLoader.getController();
            controller.setCustomerData(customersTableView.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Modify Customer");
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * This deleteCustomer method deletes selected customer from customers table.
     * @param actionEvent
     */
    @FXML public void deleteCustomer(ActionEvent actionEvent) {
        Customers selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();

        // Verify user selects a customer when clicking the delete customer button
        if (selectedCustomer == null) {
            errorLabel.setText("You must select a customer from the customers table before deleting.");
            errorLabel.setTextFill(Color.RED);
            return;
        } else {
            errorLabel.setText("");
        }

        // Alert Message Confirming That They Indeed Want To Delete Selected Customer
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete Customer Warning");
        alert.setHeaderText("Are you sure you want to delete " + selectedCustomer.getCustomerName() + " ?");
        alert.setContentText("Select yes or no.");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton)
        {
            DBCustomers.deleteCustomer(selectedCustomer.getCustomerID());
            customersTableView.setItems(DBCustomers.getAllCustomers());
            JDialog frame = null;
        }
        else if(result.get() == cancelButton)
        {
            actionEvent.consume();
        }
    }

    // Controls Methods
    @FXML public void viewScheduleByContact(ActionEvent actionEvent) {
    }

    @FXML public void viewCustomerAppointmentsByMonth(ActionEvent actionEvent) {
    }

    @FXML public void contactEmailList(ActionEvent actionEvent) {
    }

    @FXML public void viewCustomerAppointmentsByType(ActionEvent actionEvent) {
    }

    /**
     * This method exits out of the Main Controller and returns to the Login Controller for a new user to log in.
     * @param actionEvent
     * @throws IOException
     */
    @FXML public void logOff(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root, 602, 257);
        stage.setTitle("Appointment User Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method exits the program completely.
     * @param actionEvent
     */
    @FXML public void exitProgram(ActionEvent actionEvent) {
        System.out.println("Exit Button Selected");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Schedule Manager Exit");
        alert.setHeaderText(null);
        alert.setContentText("You are now exiting the program.");
        alert.showAndWait();
        System.exit(0);
    }
}
