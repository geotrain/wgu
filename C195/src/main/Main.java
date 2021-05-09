package main;

// Import statements
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.DatabaseConnection;
import javafx.application.Application;
import java.sql.SQLException;

public class Main extends Application {

    /**
     * This start method brings up the initial user login screen for a user to enter a username and password to begin
     * using the program. It will throw an error if a user attempts to log in without the correct username and/or password.
     * All log in attempts will be logged in the ../logs/login_activity.txt file.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        primaryStage.setTitle("Appointment User Login");
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {

        /**
         * This will make the initial connection to the uCertify database. Connection can be found in the
         * DatabaseConnection.java class
         */
        DatabaseConnection.startConnection();

        /**
         * The "DBAccess.DBCountries.checkDatabaseConversion()" will check the database connection upon startup of the
         * the program and do a conversion of the Create_Date UTC Date/Timestamp and convert it to the user's local time
         * zone on their system using the application.
         */
        DBAccess.DBCountries.checkDatabaseConversion();

        // This starts the program in the main method launch(args)
        launch(args);

        /**
         * This will close the database connection when the program exits
         */
        DatabaseConnection.closeConnection();
    }

}
