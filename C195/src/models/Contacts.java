package models;

public class Contacts {
    /**
     * Declare variables to be used in the DBContacts.java class
     */
    private int contactID;
    private String contactName;
    private String contactEmail;

    /**
     * Constructor for Contacts Table
     * @param contactID
     * @param contactName
     * @param contactEmail
     */
    public Contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public Contacts(String contactName) {

        this.contactName = contactName;

    }

    /**
     * The getContactID method gets the Contacts (Contact_ID) from the contacts table.
     * @return
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * The setContactID method sets the Contacts (Contact_ID) to the contacts table. This is auto-incremented
     * so we will not use this method for this project.
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * The getContactName method gets the Contacts (Contact_Name) from the contacts table.
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * The setContactName method sets the Contacts (Contact_Name) to the contacts table.
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * The getContactEmail method gets the Contacts (Email) from the contacts table.
     * @return
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * The setContactEmail method sets the Contacts (Email) to the contacts table.
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Override to print customerName to string
     * @return
     */
    @Override public String toString() {
        return contactName;
    }
}
