package models;

// Import statements
import java.sql.Date;
import java.sql.Time;

public class Users {
    /**
     * Declare int id and String name variables to be used in the DBCountries.java class
     */
    private int id;
    private String username;
    private String password;
    private Date createDateDate;
    private String createdBy;
    private Date lastUpdateDate;
    private String lastUpdatedBy;

    /**
     * The Users method with the columns from the users table
     * @param id This is a parameter
     * @param username This is a parameter
     * @param password This is a parameter
     * @param createDateDate This is a parameter
     * @param createdBy This is a parameter
     * @param lastUpdateDate This is a parameter
     * @param lastUpdatedBy This is a parameter
     */
    public Users(int id, String username,String password, Date createDateDate,  String createdBy,
                 Date lastUpdateDate,  String lastUpdatedBy) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createDateDate = createDateDate;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Constructor used for getUserName() method in the Appointments.java and will be called in the ModifyAppointment controller.
     * @param username This is a parameter
     */
    public Users(String username) {
        this.username = username;
    }

    /**
     * The getId method that gets the Users (User_ID) from the users table.
     * @return This is a return statement
     */
    public int getId() {
        return this.id;
    }

    /**
     * The setId method that sets the Users (User_ID) to the users table. This is auto-incremented so we will not use
     * this method for this project.
     * @return This is a return statement
     */
    public int setId() {
        return this.id = id;
    }

    /**
     * The getUsername method that gets the Users (User_Name) from the users table.
     * @return This is a return statement
     */
    public String getUserName() {
        return this.username;
    }

    /**
     * The setUserName method that sets the Users (User_Name) to the users table.
     * @param username This is a parameter
     */
    public void setUserName(String username) {
         this.username = username;
    }

    /**
     * The getPassword method that gets the Users (Password) from the users table.
     * @return This is a return statement
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * The setPassword method that sets the Users (Password) to the users table.
     * @param password This is a parameter
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The getCreateDateDate method that gets the Users (Create_Date) from the users table.
     * @return This is a return statement
     */
    public Date getCreateDateDate() {
        return this.createDateDate;
    }

    /**
     * The setCreateDateDate method that sets the Users (Create_Date) to the users table.
     * @param createDateDate This is a parameter
     */
    public void setCreateDateDate(Date createDateDate) {
        this.createDateDate = createDateDate;
    }

    /**
     * The getCreatedBy method that gets the Users (Created_By) from the users table.
     * @return This is a return statement
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * The setCreatedBy method that sets the Users (Created_By) to the users table.
     * @param createdBy This is a parameter
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * The getLastUpdateDate method that gets the Users (Last_Update) from the users table.
     * @return This is a return statement
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * The setLastUpdateDate method that sets the Users (Last_Update) to the users table.
     * @param lastUpdateDate This is a parameter
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * The getLastUpdatedBy method that gets the Users (Last_Updated_By) from the users table.
     * @return This is a return statement
     */
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    /**
     * The setLastUpdatedBy method that sets the Users (Last_Updated_By) to the users table.
     * @param lastUpdatedBy This is a parameter
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Override to print username to string
     * @return This is a return statement
     */
    @Override public String toString() {
        return username;
    }
}
