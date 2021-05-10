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
import java.sql.*;
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
         * This uses the insertStatement variable to INSERT INTO countries table using place holders noted by the ?'s
         * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
         * setPreparedStatement() and getPreparedStatement().
         */
        /**
        // This the RAW SQL Statement saved in insertStatement String variable.
        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES(?, ?, ?, ?)";

        // Create setPreparedStatement Object
        DBQuery.setPreparedStatement(conn, insertStatement);

        // preparedStatementReference
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // Declare variables for insertStatement
        String countryName;
        String createDate = "2021-05-10 01:04:01";
        String createdBy = "admin";
        String lastUpdatedBy = "admin";

        // Get keyboard input
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter a country: ");
        countryName = keyboard.nextLine();


        // Key-Value Mappings to the ?'s
        ps.setString(1, countryName);
        ps.setString(2, createDate);
        ps.setString(3, createdBy);
        ps.setString(4, lastUpdatedBy);

        // Execute() SQL PreparedStatement
        ps.execute();

        // Check row(s) that were affected.
        if (ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " row(s) affected.");
        else
            System.out.println("No changes were made.");
         */

        /**
         * This uses the updateStatement variable to UPDATE countries table using place holders noted by the ?'s
         * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
         * setPreparedStatement() and getPreparedStatement().
         */
        /**
        // This the RAW SQL Statement saved in insertStatement or updateStatement String variable.
        String updateStatement = "UPDATE countries SET Country = ?, Created_By = ? WHERE Country = ?";

        // Create setPreparedStatement Object
        DBQuery.setPreparedStatement(conn, updateStatement);

        // preparedStatementReference
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // Declare variables for updateStatement
        String country_name_to_be_replaced, new_country, created_by;

        // Get keyboard input
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter a country to update: ");
        country_name_to_be_replaced = keyboard.nextLine();

        System.out.print("Please enter a new country: ");
        new_country = keyboard.nextLine();

        System.out.print("Please enter user: ");
        created_by = keyboard.nextLine();

        // Key-Value Mappings to the ?'s
        ps.setString(1, new_country);
        ps.setString(2, created_by);
        ps.setString(3, country_name_to_be_replaced);

        // Execute() SQL PreparedStatement
        ps.execute();

        // Check row(s) that were affected.
        if (ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " row(s) affected.");
        else
            System.out.println("No changes were made.");
        */

        /**
         * This uses the deleteStatement variable to DELETE countries record using place holders noted by the ?'s
         * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
         * setPreparedStatement() and getPreparedStatement().
         */
        /**
         // This the RAW SQL Statement saved in insertStatement or updateStatement String variable.
         String deleteStatement = "DELETE FROM countries WHERE Country = ?";

         // Create setPreparedStatement Object
         DBQuery.setPreparedStatement(conn, deleteStatement);

         // preparedStatementReference
         PreparedStatement ps = DBQuery.getPreparedStatement();

         // Declare variables for updateStatement
         String country_to_delete, new_country, created_by;

         // Get keyboard input
         Scanner keyboard = new Scanner(System.in);
         System.out.print("Please enter a country to delete: ");
         country_to_delete = keyboard.nextLine();

         // Key-Value Mappings to the ?'s
         ps.setString(1, country_to_delete);

         // Execute() SQL PreparedStatement
         ps.execute();

         // Check row(s) that were affected.
         if (ps.getUpdateCount() > 0)
         System.out.println(ps.getUpdateCount() + " row(s) affected.");
         else
         System.out.println("No changes were made.");
         */

        /**
         * This uses the selectStatement variable to SELECT countries record using place holders noted by the ?'s
         * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
         * setPreparedStatement() and getPreparedStatement().
         */
        // This the RAW SQL Statement saved in insertStatement or updateStatement String variable.
        String selectStatement = "SELECT * FROM countries";

        // Create setPreparedStatement Object
        DBQuery.setPreparedStatement(conn, selectStatement);

        // preparedStatementReference
        PreparedStatement ps = DBQuery.getPreparedStatement();

        // Execute() SQL PreparedStatement
        ps.execute();

        ResultSet rs = ps.getResultSet();

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
        /**
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
        */

        /**
         * Create a Raw SQL UPDATE statement for the countries table. Updating country record to be changed to Japan.
         * This is commented out.
         */
        /**
        String updateStatement = "UPDATE countries SET Country = 'Japan' WHERE Country_ID = 100;";
        statement.execute(updateStatement);
        if(statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + " record updated.");
        else
            System.out.println("No changes were made.");
         */

        /**
         * Create a Raw SQL DELETE statement for the countries table. Deleting country record. This is commented out.
         */
        /**
        String deleteStatement = "DELETE FROM countries WHERE Country_ID = 100000000;";
        statement.execute(deleteStatement);
        if(statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + " record updated.");
        else
            System.out.println("No changes were made.");
         */

        /**
         * ResultSet Object from the countries table to store all records in selectStatement2 variable. This is commented out.
         */
        /**
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
         */

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
