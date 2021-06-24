package controllers;
// Import Statements
import DBAccess.DBAppointments;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

    // FX ID's Buttons
    @FXML private Button closeButton;

    // FX ID's Table View and Table Columns
    @FXML private TableView<Appointments> customerAppointmentByTypeAndMonthTableView;
    @FXML private TableColumn<Appointments, Date> monthColumn;
    @FXML private TableColumn<Appointments, String> typeColumn;
    @FXML private TableColumn<Appointments, Integer> countColumn;

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
     * This initialize method will generate the Appointments By Type and Month Table View
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populates and initializes ALL of the appointments by type and month
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        //countColumn.getCellValueFactory(new PropertyValueFactory<>("count"))
        customerAppointmentByTypeAndMonthTableView.setItems(DBAppointments.getAllAppointments());
    }
}
