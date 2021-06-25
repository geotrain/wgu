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
import java.util.Locale;
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

    // FX Ids for the Text Fields used for the login screen
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    // FX Ids for Labels
    @FXML private Label messageLabel;
    @FXML private Label zoneIdLabel;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label userZoneIdLabel;

    // FX ID's Buttons
    public Button loginButton;
    public Button closeButton;

    // Set Resource Bundle Object
    ResourceBundle rb = ResourceBundle.getBundle("main/login", Locale.getDefault());

    /**
     * The loginButton method checks if a username is correct or not then logs into the main controller
     * @param actionEvent This is a parameter
     * @throws IOException This is an exception
     */
    @FXML public void logInButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Get Username And Password");
        String userName = usernameTextField.getText();
        String passWord = passwordTextField.getText();
        // Verify username or password is not an empty string
        if (userName.isEmpty()) {
            messageLabel.setText(rb.getString("usernameEmpty"));
            messageLabel.setTextFill(Color.RED);
        } else if (passWord.isEmpty()) {
            messageLabel.setText(rb.getString("passwordEmpty"));
            messageLabel.setTextFill(Color.RED);
        } else if (!(userName.isEmpty() && passWord.isEmpty())) {
            boolean result = DBUsers.checkUserNameAndPass(userName, passWord);
            if (result) {
                System.out.println("This is a valid user");
                // Open up to new scene
                Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene((Parent) root, 1090, 900);
                stage.setTitle("Welcome To Schedule Manager v 1.0");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("No Such Username or Password In Database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("incorrectUserPassTitleText"));
                alert.setHeaderText(rb.getString("incorrectUserPassHeaderText"));
                alert.setContentText(rb.getString("incorrectUserPassContentText"));
                alert.setResizable(true);
                alert.showAndWait();
                return;
            }
        }
    }

    /**
     * The closeButton method exits the program
     * @param actionEvent This is a parameter
     */
    @FXML public void closeButton(ActionEvent actionEvent) {
        System.out.println("Close Button Selected");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(rb.getString("closeAlertTitleText"));
        alert.setHeaderText(null);
        alert.setContentText(rb.getString("closeAlertContentText"));
        alert.showAndWait();
        System.exit(0);
    }

    /**
     * The initialize method loads the screen
     * @param url This is a parameter
     * @param resourceBundle This is a parameter
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");

        // Display local zone id to a label called zoneIdLabel
        zoneIdLabel.setText(String.valueOf(ZoneId.systemDefault()));
        userZoneIdLabel.setText(rb.getString("zoneid"));
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        loginButton.setText(rb.getString("signin"));
        closeButton.setText(rb.getString("close"));

    }
}