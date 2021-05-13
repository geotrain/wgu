package models;

import java.sql.Date;
import java.sql.Time;

public class Appointments {
    /**
     * Declare int id and String name variables to be used in the DBCountries.java class
     */
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;
    private Date createDate;
    private String createdBy;
    private Time lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    /**
     * The Appointments method with the columns from the appointment table
     * @param id
     * @param name
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
    public Appointments(int id, String name, String description, String location, String type, Date start, Date end,
                        Date createDate, String createdBy, Time lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        this.id = id;
        this.title = name;
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
    public Date getStart() {
        return start;
    }

    /**
     * This setStart method that gets the Appointments (Start) to the appointments table.
     * @return
     */
    public Date setStart() {
        return this.start = start;
    }

    /**
     * This getEnd method that gets the Appointments (End) from the appointments table
     * @return
     */
    public Date getEnd() {
        return end;
    }

    /**
     * This setEnd method that gets the Appointments (End) to the appointments table
     * @return
     */
    public Date setEnd() {
        return this.end = end;
    }

    /**
     * This getCreateDate method that gets the Appointments (Create_Date) from the appointments table.
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This setCreateDate method that sets the Appointments (Create_Date) to the appointments table.
     * @return
     */
    public Date setCreateDate() {
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
    public Time getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This setLastUpdate method that sets the Appointments (Last_Update) to the appointments table.
     * @return
     */
    public Time setLastUpdate() {
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
}