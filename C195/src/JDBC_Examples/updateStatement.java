package JDBC_Examples;

public class updateStatement {

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
     * This uses the updateStatement variable to UPDATE countries table using place holders noted by the ?'s
     * separated by commas known as key value mapping.. This will use the utilities package DBQuery.java.class methods
     * setPreparedStatement() and getPreparedStatement().
     */
    /**
     // This the RAW SQL Statement saved in updateStatement or updateStatement String variable.
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


}
