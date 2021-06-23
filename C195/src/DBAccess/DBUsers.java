package DBAccess;

// Import statements
import utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.LoginActivity;
import java.sql.*;

public class DBUsers {

    /**
     * Declare a global static variable of currentUser
     */
    public static String currentUserId;

    /**
     * Checks to see if user and password are found in users table
     * @param uName
     * @param password
     * @return
     */
    public static boolean checkUserNameAndPass(String uName, String password) {

        String sql = "SELECT * FROM users";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("User_Name"));
                if (rs.getString("User_Name").equals(uName) && rs.getString("password").equals(password)) {
                    LoginActivity.login_activity(uName, true);
                    // Assign username to global variable currentUserId to see if they have any upcoming appointments within 15 minutes or less
                    currentUserId = uName;
                    System.out.println("The value of global variable currentUserId is currently set to " + currentUserId);
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        LoginActivity.login_activity(uName, false);
        return false;
    }

    /**
     * This getCurrentUserLoggedInId method will get the id of the current user logged in and is used to assign a userId
     * when adding a new appointment, modifying a new appointment, or deleting an appointment. It will also be used to
     * check if the user has an existing appointments within a 15 minute period when the user logs in.
     * @return
     */
    public static String getCurrentUserLoggedInId(String currentUserId) {
         return currentUserId;
    }



    /**
     * This ObservableList returns all the users from the users table the User_ID, User_Name, Password, Create_Date,
     * Created_By, Last_Update, Last_Updated_By. This is used in conjunction with the models/Users.java file that
     * contains the getId, getUserName, getPassword, getCreateDate, getCreateDateTime, getCreatedBy, getLastUpdateDate,
     * getLastUpdateTime, and getlastUpdatedBy methods.
     * @param <Users>
     * @return
     */
    public static <Users> ObservableList<Users> getAllUsers() {

        ObservableList<Users> ulist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Date createDateDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Date lastUpdateDate = rs.getDate("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");

                models.Users U = new models.Users(
                        userId,
                        userName,
                        password,
                        createDateDate,
                        createdBy,
                        lastUpdateDate,
                        lastUpdatedBy
                );
                ulist.add((Users) U);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return ulist;
    }
}

