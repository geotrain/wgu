package DBAccess;

// Import statements
import utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DBAppointments {

    /**
     * This ObservableList returns all the appointments from the appointments table including the Appointment_ID, Title,
     * Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID,
     * User_ID, Contact_ID. This is used in conjunction with the models/Appointments.java file that contains the
     * Get methods to be used in the query.
     * @param <Appointments>
     * @return
     */
    public static <Appointments> ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Date start = rs.getDate("Start");
                Date end = rs.getDate("End");
                Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Time lastUpdate = rs.getTime("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                models.Appointments A = new models.Appointments(appointmentId,title, description,location,type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId);
                appointmentsList.add((Appointments) A);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return appointmentsList;
    }

}