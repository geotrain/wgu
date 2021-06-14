package models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Countries {
    /**
     * Declare int id and String name variables to be used in the DBCountries.java class
     */
    private int id;
    private String name;
    private Date createDate;
    private Time createDateTime;
    private String createdBy;
    private Date lastUpdateDate;
    private Time lastUpdate;
    private String lastUpdatedBy;

    /**
     * The Countries method with the country id and name from the countries tables
     * @param id
     * @param name
     * @param createDate
     * @param createDateTime
     * @param createdBy
     * @param lastUpdateDate
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public Countries(int id, String name, Date createDate, Time createDateTime, String createdBy, Date lastUpdateDate, Time lastUpdate, String lastUpdatedBy) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.createDateTime = createDateTime;
        this.createdBy = createdBy;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * The getId method that gets the Countries (Country_ID) from the countries table.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * The setId method that sets the Countries (Country_ID) to the countries table. This is auto-incremented
     * so we will not use this method for this project.
     * @return
     */
    public int setId() {
        return this.id = id;
    }

    /**
     * The getName method that gets the Countries (Country) from the countries table.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This setName method that sets the Countries (Country) to the countries table.
     * @return
     */
    public String setName() {
        return this.name = name;
    }

    /**
     * The getCreateDate method that gets the Countries (Create_Date) from the countries table.
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * The setCreateDate method that sets the Countries (Create_Date) to the countries table.
     * @return
     */
    public Date setCreateDate() {
        return this.createDate = createDate;
    }

    /**
     * The getCreateDateTime method that gets teh Countries (Create_Date) from the countries table.
     * @return
     */
    public Time getCreateDateTime() {
        return createDateTime;
    }

    /**
     * The setCreateDateTime method that sets teh Countries (Create_Date) to the countries table.
     * @return
     */
    public Time setCreateDateTime() {
        return this.createDateTime = createDateTime;
    }

    /**
     * The getCreatedBy method that gets the Countries (Created_By) from the countries table.
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This setCreatedBy method that sets the Countries (Created_By) to the countries table.
     * @return
     */
    public String setCreatedBy() {
        return this.createdBy = createdBy;
    }

    /**
     * This getLastUpdateDate method that gets the Countries (Last_Update) from the countries table.
     * @return
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * This setLastUpdateDate method that sets the Countries (Last_Update) to the countries table.
     * @return
     */
    public Date setLastUpdateDate() {
        return this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * This getLastUpdate method that gets the Countries (Last_Update) from the countries table.
     * @return
     */
    public Time getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This setLastUpdate method that sets the Countries (Last_Update) to the countries table.
     * @return
     */
    public Time setLastUpdate() {
        return this.lastUpdate = lastUpdate;
    }

    /**
     * This getLastUpdatedBy method that gets the Countries (Last_Updated_By) from the countries table.
     * @return
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This setLastUpdatedBy method that sets the Countries (Last_Updated_By) to the countries table.
     * @return
     */
    public String setLastUpdatedBy() {
        return this.lastUpdatedBy = lastUpdatedBy;
    }
}