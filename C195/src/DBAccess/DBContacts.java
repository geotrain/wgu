package DBAccess;

// Import statement
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Countries;
import utilities.DBConnection;
import utilities.DataProvider;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class DBContacts {

    /**
     * This ObservableList returns all the contacts from the contacts table the Contact ID and Contact Name. This is
     * used in conjunction with the models/Contacts.java file that contains the getContactId, getContactName, and
     * getContactEmail methods.
     *
     * @param <Contacts> This is a parameter
     * @return This is a return statement
     */
    public static <Contacts> ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> contactlist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                models.Contacts C = new models.Contacts(
                        contactID,
                        contactName,
                        contactEmail
                );
                contactlist.add((Contacts) C);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return contactlist;
    }

    /**
     * This getContactsEmailList method returns all emails from the contacts table and is pulled from the ContactEmail Controller
     * @param <Contacts> This is a parameter
     * @return This is a return statement
     */
    public static <Contacts> ObservableList<Contacts> getContactsEmailList() {

        ObservableList<Contacts> contactEmailList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts;";
            System.out.println("agetContactEmailList SQL statement --> " + sql); // Print out SQL Statement

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                models.Contacts CE = new models.Contacts(
                        contactID,
                        contactName,
                        contactEmail
                );
                contactEmailList.add((Contacts) CE);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return contactEmailList;
    }

}
