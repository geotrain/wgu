package controllers;

import DBAccess.DBAppointments;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Appointments;
import models.Countries;
import models.Customers;
import models.Users;
import utilities.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    /**
     * Initialize stage and scene variables for app navigation
     */
    private String errorHeader;
    private String errorTitle;
    private String errorText;
    Stage stage;
    Parent scene;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    public void logInButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Get Username And Password");
        String userName = usernameTextField.getText();
        String passWord = passwordTextField.getText();
        boolean result = DBUsers.getCurrentUser(userName, passWord);
        if (result) {
            System.out.println("This is a valid user");
            // Open up to new  scene
            Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene((Parent) root, 1060, 875);
            stage.setTitle("Welcome To Schedule Manager v 1.0");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("No Such Username or Password In Database");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("You Have Entered An Incorrect Value");
            alert.setContentText("Please Check Username/Password And Try Again.");
            alert.showAndWait();
            return;
        }
    }

    @FXML
    public void closeButton(ActionEvent actionEvent) {
        System.out.println("Close Button Selected");
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    // Unused Methods From Video Demonstrations
    /**
     TheLabel.setText("See Terminal For Countries Table Records.");
     ObservableList<Countries> countryList = DBCountries.getAllCountries();
     for(Countries C : countryList)
     System.out.println(
     "Country Id: " + C.getId() +
     " Name: " + C.getName() +
     " Create Date: " + C.getCreateDate() + " " + C.getCreateDateTime() +
     " Created By: " + C.getCreatedBy() +
     " Last Update: " + C.getLastUpdateDate() + " " + C.getLastUpdate() +
     " Last Updated By: " + C.getLastUpdatedBy() + "\n"
     );*/

    /**
     TheLabel.setText("See Terminal For Appointments Table Records.");
     ObservableList<Appointments> appointmentsList = DBAppointments.getAllAppointments();
     for(Appointments A : appointmentsList)
     System.out.println(
     "Appointment ID: " + A.getId() +
     " Title: " + A.getTitle() +
     " Description " + A.getDescription() +
     " Location: " + A.getLocation() +
     " Type: " + A.getType() +
     " Start: " + A.getStart() +
     " End: " + A.getEnd() +
     " Create Date: " + A.getCreateDate() +
     " Created By: " + A.setCreatedBy() +
     " Last Update: " + A.getLastUpdate() +
     " Last Updated By: " + A.getLastUpdatedBy() +
     " Customer ID: " + A.getCustomerId() +
     " User ID: " + A.getUserId() +
     " Contact ID: " + A.getContactId() + "\n"
     );*/
}