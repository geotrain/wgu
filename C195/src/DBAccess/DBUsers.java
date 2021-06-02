package DBAccess;

// Import statements
import models.Users;
import utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.logging.Logger;

public class DBUsers {

    // Declare Private Variables
    private static Users currentUser;

    public static Users getCurrentUser(String uName) {

        String sql = "SELECT * FROM users WHERE User_Name = '"+uName + "'";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String user = rs.getString("User_Name");
                String password = rs.getString("Password");
                System.out.println(user + " " + password);
                return new Users(rs.getInt("User_ID"),rs.getString("User_Name"),
                        rs.getString("Password"),rs.getDate("Create_Date"),
                        rs.getString("Created_By"), rs.getDate("Last_Update"),
                        rs.getString("Last_Updated_By"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentUser;
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

