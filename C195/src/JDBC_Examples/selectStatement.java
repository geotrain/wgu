package JDBC_Examples;

import utilities.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class selectStatement {

    /**
     * This SELECT raw statement returns all results from the countries table
     * @return This is a return
     */
    public static String selectStatementRaw() {
        System.out.println("COUNTRY");
        String sql = "SELECT * FROM countries";
        return sql;
    }


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
     * This uses the selectStatement variable to SELECT countries record using place holders noted by the ?'s
     * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
     * setPreparedStatement() and getPreparedStatement().
     */
    /**
     // This the RAW SQL Statement saved in selectStatement .
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
     */

}
