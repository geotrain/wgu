package main;

// Import statements
import DBAccess.DBCountries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.DBConnection;
import utilities.DBQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Main extends Application {

    private static Object ResultSet;

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
         * This demonstrates a SQL Injection Attack utilizing the scanner object allowing code to be entered.
         * Entering a 1=1 will cause all records to show instead of just the records for the country the user enters.
         * This block of code is commented out due to it preventing the user interface from loading.
         */
        /**
        String country;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a country: ");
        country = keyboard.nextLine();
        String selectStatement3 = "SELECT * FROM countries WHERE " + country;
        // Try Catch Block Exception Handling
        try {
            statement.execute(selectStatement3);
            ResultSet rs = statement.getResultSet();
            // Forward Scroll ResultSet
            while (rs.next()) {
                int Country_ID2 = rs.getInt("Country_ID");
                String Country2 = rs.getString("Country");
                LocalDate Create_Date2 = rs.getDate("Create_Date").toLocalDate();
                LocalTime Create_Time2 = rs.getTime("Create_Date").toLocalTime();
                String Created_By2 = rs.getString("Created_By");
                LocalDateTime Last_Update_2 = rs.getTimestamp("Last_Update").toLocalDateTime();
                String Last_Updated_By2 = rs.getString("Last_Updated_By");
                // Display Result Set
                System.out.println(Country_ID2 + " | " + Country2 + " | " + Create_Date2 + " | " + Create_Time2 + " | " +
                        Created_By2 + " | " + Last_Update_2 + " | " + Last_Updated_By2);
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }*/

        /**
         * This demonstrates a SQL Insert Statement using the Scanner Object to enter a country name to create a new
         * record in the countries table. We are only creating the Country, Create_Date, Created_By, and
         * Last_Updated_By values. This insert statement has been commented out.
         */
        /**
         String Country, Create_Date, Created_By, Last_Updated_By;
         Scanner keyboard = new Scanner(System.in);
         System.out.print("Enter a country: ");
         Country = keyboard.nextLine();
         Create_Date = "2020-05-09 23:18:46";
         Created_By = "admin";
         Last_Updated_By = "admin";

         // Replace any entries that contain a single quote when users enters a country like: 'country'
         if(Country.contains("'"))
             Country = Country.replace("'", "\\'");

         String insertStatement2 = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By)" +
                 "VALUES( " +
                 "'" + Country + "'," +
                 "'" + Create_Date + "'," +
                 "'" + Created_By + "'," +
                 "'" + Last_Updated_By + "'" +
                 ")";
         // Try Catch Block Exception Handling
         try {
            statement.execute(insertStatement2);
             if(statement.getUpdateCount() > 0)
                 System.out.println(statement.getUpdateCount() + " row(s) affected.");
             else
                 System.out.println("No changes were made.");
         } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
         }
         */

        /**
         * Create a Raw SQL INSERT statement for the countries table. Inserting France country record.
         * This line of code is commented out.
         */
        //String insertStatement3 = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES('France', '2021-05-09 15:45:09', 'admin', 'admin')";

        /**
         * Create a SQL INSERT statement for the countries table using variables . Inserting France country record.
         * This comment is commented out.
         */

        String Country = "France";
        String Create_Date = "2021-05-09 15:45:09";
        String Created_By = "admin";
        String Last_Updated_By = "admin";
        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By)" +
                "VALUES( " +
                "'" + Country + "'," +
                "'" + Create_Date + "'," +
                "'" + Created_By + "'," +
                "'" + Last_Updated_By + "'" +
                ")";

        // Execute SQL INSERT Statement. Will return a false boolean statement since it is using a INSERT Statement
        statement.execute(insertStatement);
        // Confirm rows affected by Raw SLQ INSERT statement into countries table.
        if(statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + " rows affected.");
        else
            System.out.println("No changes were made and no rows were updated.");


        /**
         * Create a Raw SQL UPDATE statement for the countries table. Updating country record to be changed to Japan.
         */
        String updateStatement = "UPDATE countries SET Country = 'Japan' WHERE Country_ID = 100;";
        statement.execute(updateStatement);
        if(statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + " record updated.");
        else
            System.out.println("No changes were made.");

        /**
         * Create a Raw SQL DELETE statement for the countries table. Deleting country record.
         */
        String deleteStatement = "DELETE FROM countries WHERE Country_ID = 100000000;";
        statement.execute(deleteStatement);
        if(statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + " record updated.");
        else
            System.out.println("No changes were made.");

        /**
         * ResultSet Object from the countries table to store all records in selectStatement2 variable 
         */
        String selectStatement2 = "SELECT * FROM countries;";
        // Try Catch Block Exception Handling
        try {
            statement.execute(selectStatement2);
            ResultSet rs = statement.getResultSet();
            // Forward Scroll ResultSet
            while (rs.next()) {
                int Country_ID2 = rs.getInt("Country_ID");
                String Country2 = rs.getString("Country");
                LocalDate Create_Date2 = rs.getDate("Create_Date").toLocalDate();
                LocalTime Create_Time2 = rs.getTime("Create_Date").toLocalTime();
                String Created_By2 = rs.getString("Created_By");
                LocalDateTime Last_Update_2 = rs.getTimestamp("Last_Update").toLocalDateTime();
                String Last_Updated_By2 = rs.getString("Last_Updated_By");
                // Display Result Set
                System.out.println(Country_ID2 + " | " + Country2 + " | " + Create_Date2 + " | " + Create_Time2 + " | " +
                        Created_By2 + " | " + Last_Update_2 + " | " + Last_Updated_By2);
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        /**
         * This will check the database connection upon startup of the the program and do a conversion of the Create_Date
         * UTC Date/Timestamp and convert it to the user's local time zone on their system using the application. The
         * method checkDatabaseConversion() is located in the DBAccess package in the DBCountries.java class.
         */
        DBAccess.DBCountries.checkDatabaseConversion();

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
