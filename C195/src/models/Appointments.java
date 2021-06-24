package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Appointments {
    /**
     * Declare int id and String name variables to be used in the DBCountries.java class
     */
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    /**
     * The Appointments method with the columns from the appointment table
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerId
     * @param userId
     * @param contactId
     */
    public Appointments(int id,
                        String title,
                        String description,
                        String location,
                        String type,
                        LocalDateTime start,
                        LocalDateTime end,
                        LocalDateTime createDate,
                        String createdBy,
                        LocalDateTime lastUpdate,
                        String lastUpdatedBy,
                        int customerId,
                        int userId,
                        int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * The getId method that gets the Appointments (Appointment_ID) from the appointments table.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * The setId method that sets the Appointments (Appointment_ID) to the appointments table. This is auto-incremented
     * so we will not use this method for this project.
     * @return
     */
    public int setId() {
        return this.id = id;
    }

    /**
     * The getName method that gets the Appointments (Title) from the appointments table.
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * The setTitle method that sets the Appointments (Title) to the appointments table.
     * @return
     */
    public String setTitle() {
        return this.title = title;
    }

    /**
     * The getDescription method that gets the Appointment (Description) from the appointments table.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * This setDescription method that sets the Appointments (Description) to the appointments table.
     * @return
     */
    public String setDescription() {
        return this.description = description;
    }

    /**
     * The getLocation method that gets the Appointments (Location) from the appointments table.
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * The setLocation method that gets the Appointments (Location) to the appointments table.
     * @return
     */
    public String setLocation() {
        return this.location = location;
    }

    /**
     * The getType method that gets the Appointments (Type) from the appointments table.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * The setType method that gets the Appointments (Type) to the appointments table.
     * @return
     */
    public String setType() {
        return this.type = type;
    }

    /**
     * This getStart method that gets the Appointments (Start) from the appointments table.
     * @return
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * This setStart method that gets the Appointments (Start) to the appointments table.
     * @return
     */
    public LocalDateTime setStart() {
        return this.start = start;
    }

    /**
     * This getEnd method that gets the Appointments (End) from the appointments table
     * @return
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * This setEnd method that gets the Appointments (End) to the appointments table
     * @return
     */
    public LocalDateTime setEnd() {
        return this.end = end;
   }

    /**
     * This getCreateDate method that gets the Appointments (Create_Date) from the appointments table.
     * @return
     */
    public LocalDateTime getCreateDate() {
       return createDate;
    }

    /**
     * This setCreateDate method that sets the Appointments (Create_Date) to the appointments table.
     * @return
     */
    public LocalDateTime setCreateDate() {
        return this.createDate = createDate;
    }

    /**
     * This getCreatedBy method that gets the Appointments (Created_By) from the appointments table.
     * @return
     */
    public String getCreatedBy()  {
        return createdBy;
    }

    /**
     * This setCreatedBy method that sets the Appointments (Created_By) to the appointments table.
     * @return
     */
    public String setCreatedBy()  {
        return this.createdBy = createdBy;
    }

    /**
     * This getLastUpdate method that gets the Appointments (Last_Update) from the appointments table.
     * @return
     */
    public LocalDateTime getLastUpdate() {
       return lastUpdate;
    }

    /**
     * This setLastUpdate method that sets the Appointments (Last_Update) to the appointments table.
     * @return
     */
    public LocalDateTime setLastUpdate() {
        return this.lastUpdate = lastUpdate;
    }

    /**
     * This getLastUpdatedBy method that gets the Appointments (Last_Updated_By) from the appointments table.
     * @return
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This setLastUpdatedBy method that sets the Appointments (Last_Updated_By) to the appointments table.
     * @return
     */
    public String setLastUpdatedBy() {
        return this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This getCustomerId method that gets the Appointments (Customer_ID) from the appointments table.
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * This setCustomerId method that sets the Appointments (Customer_ID) to the appointments table.
     * @return
     */
    public int setCustomerId() {
        return this.customerId = customerId;
    }

    /**
     * This getUserId method that gets the Appointments (User_ID) from the appointments table.
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This setUserId method that sets the Appointments (User_ID) to the appointments table.
     * @return
     */
    public int setUserId() {
        return this.userId = userId;
    }

    /**
     * This getContactId method that gets the Appointments (ContactID) from the appointments table.
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * This setContactId method that sets the Appointments (ContactID) to the appointments table.
     * @return
     */
    public int setContactId() {
        return this.userId = userId;
    }

    /**
     * Override to print type to string
     * @return
     */
    @Override public String toString() {
        return type;
    }

    /**
     * Tis method is used to preload the previously saved contact for appointments and is being called in the
     * setAppointmentDate() method in the ModifyAppointment controller.
     * @param contactId
     * @return
     */
    public static Contacts getContactName(int contactId) {

        ObservableList<Contacts> contactlist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Contact_Name FROM contacts where Contact_ID =" + contactId;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String contactName = rs.getString("Contact_Name");
                models.Contacts C = new models.Contacts(contactName);
                contactlist.add((Contacts) C);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        System.out.println("The contact selected was "+ contactlist);
        return contactlist.get(0);
    }

    /**
     * This method is used to preload the previously saved customer for appointments and is being called in the
     * setAppointmentDate() method in the ModifyAppointment controller.
     * @param customerId
     * @return
     */
    public static Customers getCustomerName(int customerId) {
        ObservableList<Customers> customerList = FXCollections.observableArrayList();

        try {
            String sql2 = "SELECT Customer_Name FROM customers where Customer_ID =" + customerId;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql2);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String customerName = rs.getString(("Customer_Name"));
                models.Customers CU = new models.Customers(customerName);
                customerList.add((Customers) CU);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        System.out.println("The customer selected was " + customerList);
        return customerList.get(0);
    }

    /**
     * This method is used to preload the previously saved user for appointments and is being called in the
     * setAppointmentDate() method in the ModifyAppointment controller.
     * @param userId
     * @return
     */
    public static Users getUserName(int userId) {
        ObservableList<Users> userList = FXCollections.observableArrayList();

        try {
            String sql3 = "SELECT User_Name FROM users where User_ID =" + userId;

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql3);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String userName = rs.getString(("User_Name"));
                models.Users U = new models.Users(userName);
                userList.add((Users) U);
            }
        } catch (Exception throwables){
                throwables.printStackTrace();
        }
        System.out.println("The user selected was " + userList);
        return userList.get(0);
    }
}
