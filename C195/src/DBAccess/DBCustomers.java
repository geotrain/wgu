package DBAccess;

// Import statements
import com.mysql.cj.xdevapi.InsertStatement;
import models.Customers;
import utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DBCustomers {

    /** This ObservableList returns all the customers from the customers table. This is
     * used in conjunction with the models/Customers.java file that contains the getId and getName methods
     * @param <Customers>
     * @return
     */
    public static <Customers> ObservableList<Customers> getAllCustomers() {

        ObservableList<Customers> clist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                Date customerCreateDate = rs.getDate("Create_Date");
                String customerCreatedBy = rs.getString("Created_By");
                Timestamp customerLastUpdate = rs.getTimestamp("Last_Update");
                String customerLastUpdatedBy = rs.getString("Last_Updated_By");
                int customerDivisionId = rs.getInt("Division_ID");

                models.Customers C = new models.Customers(
                        customerID,
                        customerName,
                        customerAddress,
                        customerPostalCode,
                        customerPhone,
                        customerCreateDate,
                        customerCreatedBy,
                        customerLastUpdate,
                        customerLastUpdatedBy,
                        customerDivisionId
                );
                clist.add((Customers) C);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return clist;
    }

    /**
     * This addNewCustomer method adds data entered into the customers table.
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param divisionID
     * @return
     */
    public static boolean addNewCustomer(String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber, Integer divisionID)
    {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String addQuery = "INSERT INTO customers SET Customer_Name='" + customerName
                    + "', Address='" + customerAddress
                    + "', Phone='" + customerPhoneNumber
                    + "', Postal_Code='" + customerPostalCode
                    + "', Division_ID=" + divisionID;
            statement.execute(addQuery);
            if(statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + " row(s) affected.");
            else
                System.out.println("No changes were made.");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }

    /**
     * The deleteCustomer method deletes a selected user from the customers table located on the MainController.
     * @param id
     * @return
     */
    public static boolean deleteCustomer(int id)
    {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String deleteQuery = "DELETE FROM customers WHERE Customer_ID=" + id;
            statement.execute(deleteQuery);
            if(statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + " row(s) affected.");
            else
                System.out.println("No changes were made.");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }

    /**
     * This addNewCustomer method adds data entered into the customers table.
     *
     * @param customerIdTextField
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhoneNumber
     * @param divisionID
     * @return
     */
    public static boolean updateCustomer(String customerIdTextField, String customerName, String customerAddress, String customerPostalCode,
                                         String customerPhoneNumber, Integer divisionID)
    {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String updateQuery = "UPDATE customers SET Customer_Name='" + customerName
                    + "', Address='" + customerAddress
                    + "', Phone='" + customerPhoneNumber
                    + "', Postal_Code='" + customerPostalCode
                    + "', Division_ID='" + divisionID
                    + "'WHERE Customer_ID=" + customerIdTextField;
            statement.execute(updateQuery);
            if(statement.getUpdateCount() > 0)
                System.out.println(statement.getUpdateCount() + " row(s) affected.");
            else
                System.out.println("No changes were made.");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
}
