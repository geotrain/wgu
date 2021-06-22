package DBAccess;

// Import statements
import javafx.scene.control.TextField;
import models.Customers;
import utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                models.Appointments A = new models.Appointments(
                        appointmentId,
                        title,
                        description,
                        location,
                        type,
                        start.toLocalDateTime(),
                        end.toLocalDateTime(),
                        createDate.toLocalDateTime(),
                        createdBy,
                        lastUpdate.toLocalDateTime(),
                        lastUpdatedBy,
                        customerId,
                        userId,
                        contactId);
                appointmentsList.add((Appointments) A);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return appointmentsList;
    }

    /**
     * addNewAppointment adds a new appointment to the appointments table, it records userId, customerId, and contactId, etc.
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @return
     */
    public static boolean addNewAppointment(
            String title,
            String description,
            String location,
            Integer contactId,
            String type,
            LocalDateTime start,
            LocalDateTime end,
            Integer customerId,
            Integer userId)
    {
        {
            try {
                String addQuery = "INSERT INTO appointments(" + // NEED NEW METHOD for updateAppoint() called in modify apointment controller
                        "Title, " +
                        "Description, " +
                        "Location, " +
                        "Type, " +
                        "Start, " +
                        "End, " +
                        "Contact_ID, " +
                        "Customer_ID, " +
                        "User_ID) " +
                        "VALUES(?,?,?,?,?,?,?,?,?)";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(addQuery);
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setString(3, location);
                ps.setString(4, type);
                ps.setTimestamp(5, Timestamp.valueOf(start));
                ps.setTimestamp(6, Timestamp.valueOf(end));
                ps.setInt(7, contactId);
                ps.setInt(8, customerId);
                ps.setInt(9, userId);

                ps.execute();
                if(ps.getUpdateCount() > 0)
                    System.out.println(ps.getUpdateCount() + " row(s) affected.");
                else
                    System.out.println("No changes were made.");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
            return false;
        }
    }

    /**
     * The deleteCustomer method deletes a selected appointment from the appointments table located on the MainController.
     * @param id
     * @return
     */
    public static boolean deleteAppointment(int id)
    {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String deleteQuery = "DELETE FROM appointments WHERE Appointment_ID=" + id;
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
     * updateAppointment updates an existing appointment to the appointments table, it records userId, customerId, and contactId, etc.
     *
     * @param appointment_id
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param start
     * @param end
     * @param customerId
     * @param userId
     * @return
     */
    public static boolean updateAppointment(
            String appointment_id,
            String title,
            String description,
            String location,
            Integer contactId,
            String type,
            LocalDateTime start,
            LocalDateTime end,
            Integer customerId,
            Integer userId)
    {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String updateQuery = "UPDATE appointments SET Title='" + title
                    + "', Description='" + description
                    + "', Location='" + location
                    + "', Contact_ID='" + contactId
                    + "', Type='" + type
                    + "', Start='" + Timestamp.valueOf(start)
                    + "', End='" + Timestamp.valueOf(end)
                    + "', Customer_ID='" + customerId
                    + "', User_ID='" + userId
                    + "'WHERE Appointment_ID=" + appointment_id;
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
