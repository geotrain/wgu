package JDBC_Examples;

// Import statements
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static utilities.DBQuery.statement;

public class insertStatement {

    /**
     * Create a Raw SQL INSERT statement for the countries table. Inserting France country record.
     * This line of code is commented out.
     */
    String insertStatement3 = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES('France', " +
            "'2021-05-09 15:45:09', 'admin', 'admin')";

    /**
     * Create a SQL INSERT statement for the countries table using variables . Inserting France country record.
     * This comment is commented out.
     */
    /**
     String Country = "France";
     String Create_Date = "2021-05-09 15:45:09";
     String Created_By = "admin";
     String Last_Updated_By = "admin";
     String insertStatement2 = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By)" +
     "VALUES( " +
     "'" + Country + "'," +
     "'" + Create_Date + "'," +
     "'" + Created_By + "'," +
     "'" + Last_Updated_By + "'" +
     ")";

     // Execute SQL INSERT Statement. Will return a false boolean statement since it is using a INSERT Statement
     //statement.execute(insertStatement3);
    state

     // Confirm rows affected by Raw SLQ INSERT statement into countries table.
     if(statement.getUpdateCount() > 0)
        System.out.println(statement.getUpdateCount() + " rows affected.");
     else
        System.out.println("No changes were made and no rows were updated.");
     */

    /**
     * This uses the insertStatement variable to INSERT INTO countries table using place holders noted by the ?'s
     * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
     * setPreparedStatement() and getPreparedStatement().
     */
    /**
     // This the RAW SQL Statement saved in insertStatement String variable.
     String insertStatement4 = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) VALUES(?, ?, ?, ?)";

     // Create setPreparedStatement Object
     DBQuery.setPreparedStatement(conn, insertStatement4);

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

     String insertStatement5 = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By)" +
     "VALUES( " +
     "'" + Country + "'," +
     "'" + Create_Date + "'," +
     "'" + Created_By + "'," +
     "'" + Last_Updated_By + "'" +
     ")";
     // Try Catch Block Exception Handling
     try {
     statement.execute(insertStatement5);
     if(statement.getUpdateCount() > 0)
     System.out.println(statement.getUpdateCount() + " row(s) affected.");
     else
     System.out.println("No changes were made.");
     } catch(Exception e) {
     System.out.println("Error: " + e.getMessage());
     }
     */
}

