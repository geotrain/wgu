package JDBC_Examples;

public class deleteStatement {

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
     * This uses the deleteStatement variable to DELETE countries record using place holders noted by the ?'s
     * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
     * setPreparedStatement() and getPreparedStatement().
     */
    /**
     // This the RAW SQL Statement saved in deleteStatement
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

}
