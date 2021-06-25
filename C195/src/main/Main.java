package main;

// Import statements
import DBAccess.DBCountries;
import DBAccess.DBDivisions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.DBConnection;
import utilities.DBQuery;
import java.sql.*;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application {

    private static Object ResultSet;

    /**
     * This start method brings up the initial user login screen for a user to enter a username and password to begin
     * using the program. It will throw an error if a user attempts to log in without the correct username and/or password.
     * All log in attempts will be logged in the ../logs/login_activity.txt file.
     * @param primaryStage  This is a parameter
     * @throws Exception This is an exception
     */
    @Override public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        primaryStage.setTitle("Appointment User Login");
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This is the main method () that launches the program
     * @param args This is a parameter
     * @throws SQLException This is an exception
     */
    public static void main(String[] args) throws SQLException {

        /**
         * These are used to to test in French "fr", Spanish "es", and German "de". To test in a specific language
         * simply uncomment the line corresponding to the language you want to test.
         */
        // Locale.setDefault(new Locale("en"));
        // Locale.setDefault(new Locale("fr"));
        // Locale.setDefault(new Locale("de"));
        // Locale.setDefault(new Locale("es"));

        // Convert this to a string for login controller toString
        System.out.println(ZoneId.systemDefault());

        /**
         * This will make the initial connection to the uCertify database. The method startConnection() is located in
         * the utilities package in the DBConnection.java class.
         */
        DBConnection.startConnection();

        /**
         * This will will establish a JDBC API Connection Interface that will allow a communication with the database.
         * The method getConnection() is located in the utilities package in the DBConnection.java class.
         */
        Connection conn = DBConnection.getConnection();

        /**
         * This will create the Statement object. The setStatement() method is located in the utilities package in the
         * DBQuery.java class.
         */
        DBQuery.setStatement(conn);

        /**
         * This is a Statement reference using the DBQuery.java class with the getStatement() method
         */
        Statement statement = DBQuery.getStatement();

        /**
         * This will check the database connection upon startup of the the program and do a conversion of the Create_Date
         * UTC Date/Timestamp and convert it to the user's local time zone on their system using the application. The
         * method checkDatabaseConversion() is located in the DBAccess package in the DBCountries.java class.
         */
        DBAccess.DBCountries.checkDatabaseConversion();

        /**
         * This sets all the DB Division ID's Right After the Database Connection Is Made.
         */
        DBDivisions.selectAllDivisions();

        /**
         * This sets all the DB Country ID's Right After the Database Connection Is Made.
         */
        DBCountries.selectAllCountries();

        /**
         *  This starts the program in the main method launch(args)
         */
        launch(args);

        /**
         * This will close the database connection when the program exits. The method closeConnection() is located in the
         * utilities package in the DBConnection.java class.
         */
        DBConnection.closeConnection();
    }
}