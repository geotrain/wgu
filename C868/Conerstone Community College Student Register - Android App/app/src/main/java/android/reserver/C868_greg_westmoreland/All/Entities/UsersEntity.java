package android.reserver.C868_greg_westmoreland.All.Entities;

/**
 * Import statements
 */
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Entity references the terms_table
 */
@Entity (tableName = "users_table")
public class UsersEntity {

    /**
     * The @PrimaryKey Auto-generate Primary Key and ID Variable For terms_table
     */
    @PrimaryKey(autoGenerate = true)
    private int userID;

   // Declare variables
    private String username;
    private String password;

    /**
     * Covert to string
     * @return
     */
    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                "password='" + password + '\'' +
                '}';
    }

    /**
     * Constructor
     * @param userID
     * @param username
     * @param password
     */
    public UsersEntity(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    /**
     * This method gets the userID from the users_table
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This method sets the userID to the users_table
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * This method gets the username from the users_table
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the username to the users_table
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method gets the password from the users_table
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the password to the users_table
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
