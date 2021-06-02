package models;

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
     * @param id
     * @param username
     * @param password
     * @param createDateDate
     * @param createdBy
     * @param lastUpdateDate
     * @param lastUpdatedBy
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
     * The getId method that gets the Users (User_ID) from the users table.
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * The setId method that sets the Users (User_ID) to the users table. This is auto-incremented so we will not use
     * this method for this project.
     * @return
     */
    public int setId() {
        return this.id = id;
    }

    /**
     * The getUsername method that gets the Users (User_Name) from the users table.
     * @return
     */
    public String getUserName() {
        return this.username;
    }

    /**
     * The setUserName method that sets the Users (User_Name) to the users table.
     * @return void
     */
    public void setUserName(String username) {
         this.username = username;
    }

    /**
     * The getPassword method that gets the Users (Password) from the users table.
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * The setPassword method that sets the Users (Password) to the users table.
     * @return void
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The getCreateDateDate method that gets the Users (Create_Date) from the users table.
     * @return
     */
    public Date getCreateDateDate() {
        return this.createDateDate;
    }

    /**
     * The setCreateDateDate method that sets the Users (Create_Date) to the users table.
     * @return void
     */
    public void setCreateDateDate(Date createDateDate) {
        this.createDateDate = createDateDate;
    }

    /**
     * The getCreatedBy method that gets the Users (Created_By) from the users table.
     * @return
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * The setCreatedBy method that sets the Users (Created_By) to the users table.
     * @return void
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * The getLastUpdateDate method that gets the Users (Last_Update) from the users table.
     * @return
     */
    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    /**
     * The setLastUpdateDate method that sets the Users (Last_Update) to the users table.
     * @return
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * The getLastUpdatedBy method that gets the Users (Last_Updated_By) from the users table.
     * @return
     */
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    /**
     * The setLastUpdatedBy method that sets the Users (Last_Updated_By) to the users table.
     * @return void
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
