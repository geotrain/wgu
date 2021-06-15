package controllers;

// Import Statements
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
import javafx.scene.paint.Color;
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
import java.time.ZoneId;
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

    // FX Ids for the text fields used for the login screen
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    // FX Ids for labels
    @FXML private Label messageLabel;
    @FXML private Label zoneIdLabel;

    /**
     * The loginButton method checks if a username is correct or not then logs into the main controller
     * @param actionEvent
     * @throws IOException
     */
    @FXML public void logInButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Get Username And Password");
        String userName = usernameTextField.getText();
        String passWord = passwordTextField.getText();
        // Verify username or password is not an empty string
        if (userName.isEmpty()) {
            messageLabel.setText("You must enter a username before clicking the login button.");
            messageLabel.setTextFill(Color.RED);
        } else if (passWord.isEmpty()) {
            messageLabel.setText("You must enter a password before clicking the login button.");
            messageLabel.setTextFill(Color.RED);
        } else if (!(userName.isEmpty() && passWord.isEmpty())) {
            boolean result = DBUsers.getCurrentUser(userName, passWord);
            if (result) {
                System.out.println("This is a valid user");
                // Open up to new scene
                Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene((Parent) root, 1060, 900);
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
    }

    /**
     * The closeButton method exits the program
     * @param actionEvent
     */
    @FXML public void closeButton(ActionEvent actionEvent) {
        System.out.println("Close Button Selected");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Schedule Manager Exit");
        alert.setHeaderText(null);
        alert.setContentText("You are now exiting the program.");
        alert.showAndWait();
        System.exit(0);
    }

    /**
     * The initialize method loads the screen
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");

        // Display local zone id to a label called zoneIdLabel
        zoneIdLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }
}