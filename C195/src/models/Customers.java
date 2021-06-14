package models;

import DBAccess.DBCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customers {

    /**
     * Declare int id and String name variables to be used in the DBCustomers.java class
     */
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private Date customerCreateDate;
    private String customerCreatedBy;
    private Timestamp customerLastUpdate;
    private String customerLastUpdatedBy;
    private int customerDivisionId;

    /**
     * The Customers Constructor with the columns from the countries table
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param customerCreateDate
     * @param customerCreatedBy
     * @param customerLastUpdate
     * @param customerLastUpdatedBy
     * @param customerDivisionId
     */
    public Customers(int customerID,
                     String customerName,
                     String customerAddress,
                     String customerPostalCode,
                     String customerPhone,
                     Date customerCreateDate,
                     String customerCreatedBy,
                     Timestamp customerLastUpdate,
                     String customerLastUpdatedBy,
                     int customerDivisionId) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerCreateDate = customerCreateDate;
        this.customerCreatedBy = customerCreatedBy;
        this.customerLastUpdate = customerLastUpdate;
        this.customerLastUpdatedBy = customerLastUpdatedBy;
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * The getCustomerID method gets the Users (Customer_ID) from the users table.
     * @return
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * The setCustomerID method sets the Users (Customer_ID) to the users table. This is auto-incremented
     * so we will not use this method for this project.
     * @return
     */
    public int setCustomerID() {
        return this.customerID = customerID;
    }

    /**
     * The getCustomerName method gets the Users (Customer_Name) from the users table.
     * @return
     */
    public String getCustomerName() {
       return customerName;
    }

    /**
     * The setCustomerName method sets the Users (Customer_Name) to the users table.
     * @return
     */
    public String setCustomerName() {
        return this.customerName = customerName;
    }

    /**
     * The getCustomerAddress method gets the Users (Address) from the users table.
     * @return
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * The setCustomerAddress method sets the Users (Address) to the users table.
     * @return
     */
    public String setCustomerAddress() {
        return this.customerAddress = customerAddress;
    }

    /**
     * The getCustomerPostalCode method gets the Users (Postal_Code) from the users table.
     * @return
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * The setCustomerPostalCode method sets the Users (Address) to the users table.
     * @return
     */
    public String setCustomerPostalCode() {
        return this.customerPostalCode = customerPostalCode;
    }

    /**
     * The getCustomerPhone method gets the Users (Phone) from the users table.
     * @return
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * The setCustomerPhone method sets the Users (Phone) to the users table.
     * @return
     */
    public String setCustomerPhone() {
        return this.customerPhone = customerPhone;
    }

    /**
     * The getCustomerCreateDate method gets the Users (Create_Date) from the users table.
     * @return
     */
    public Date getCustomerCreateDate() {
        return customerCreateDate;
    }

    /**
     * The setCustomerCreateDate method gets the Users (Create_Date) from the users table. This is auto-incremented
     * so we will not use this method for this project.
     * @return
     */
    public Date setCustomerCreateDate() {
        return this.customerCreateDate = customerCreateDate;
    }

    /**
     * The getCustomerCreatedBy method gets the Users (Created_By) from the users table.
     * @return
     */
    public String getCustomerCreatedBy() {
        return customerCreatedBy;
    }

    /**
     * The setCustomerCreatedBy method sets the Users (Created_By) to the users table.
     * @return
     */
    public String setCustomerCreatedBy() {
        return this.customerCreatedBy = customerCreatedBy;
    }

    /**
     * The getCustomerLastUpdate method gets the Users (Last_Update) from the users table.
     * @return
     */
    public Timestamp getCustomerLastUpdate() {
        return customerLastUpdate;
    }

    /**
     * The setCustomerLastUpdate method gets the Users (Last_Update) from the users table. This is auto-incremented
     * so we will not use this method for this project.
     * @return
     */
    public Timestamp setCustomerLastUpdate() {
        return this.customerLastUpdate = customerLastUpdate;
    }

    /**
     * The getCustomerLastUpdatedBy method gets the Users (Last_Updated_By) from the users table.
     * @return
     */
    public String getCustomerLastUpdatedBy() {
        return customerLastUpdatedBy;
    }

    /**
     * The setCustomerLastUpdatedBy method sets the Users (Last_Updated_By) to the users table.
     * @return
     */
    public String setCustomerLastUpdatedBy() {
        return this.customerLastUpdatedBy = customerLastUpdatedBy;
    }

    /**
     * The getDivisionID method gets the Users (Division_ID) from the users table.
     * @return
     */
    public int getDivisionID() {
        return customerDivisionId;
    }

    /**
     * The setDivisionID method sets the Users (Division_ID) to the users table.
     * @return
     */
    public int setDivisionID() {
        return this.customerDivisionId = customerDivisionId;
    }
}
