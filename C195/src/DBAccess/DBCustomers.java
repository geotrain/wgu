package DBAccess;

// Import statements
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

}
