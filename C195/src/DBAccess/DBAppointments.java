package DBAccess;

// Import statements
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
                String start = rs.getString("Start");
                String end = rs.getString("End");
                String createDate = rs.getString("Create_Date");
                String createdBy = rs.getString("Created_By");
                String lastUpdate = rs.getString("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");

                LocalDateTime convertStart = LocalDateTime.parse(start, datetimeDTF);
                LocalDateTime convertEnd = LocalDateTime.parse(end, datetimeDTF);
                LocalDateTime convertcreateDate = LocalDateTime.parse(createDate, datetimeDTF);
                LocalDateTime convertlastUpdate = LocalDateTime.parse(lastUpdate, datetimeDTF);
                models.Appointments A = new models.Appointments(
                        appointmentId,
                        title,
                        description,
                        location,
                        type,
                        convertStart,
                        convertEnd,
                        convertcreateDate,
                        createdBy,
                        convertlastUpdate,
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
     * addNewAppointment adds a new appointment to the appointments table, it records userId, customerId, and contactId
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param customerId
     * @return
     */
    public static boolean addNewAppointment(String title, String description, String location, Integer contactId,
                                            String type, Customers customerId) {
        {
            try {
                Statement statement = DBConnection.getConnection().createStatement();
                String addQuery = "INSERT INTO appointments SET Title='" + title
                        + "', Description='" + description
                        + "', Location='" + location
                        + "', Type='" + type
                        + "', Contact_ID=" + contactId
                        + "', Customer_ID=" + customerId;
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
    }
}
