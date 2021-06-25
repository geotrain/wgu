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
     * @param contactID This is a parameter
     * @param contactName This is a parameter
     * @param contactEmail This is a parameter
     */
    public Contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * This method returns a string of the contact Name
     * @param contactName This is a parameter
     */
    public Contacts(String contactName) {
        this.contactName = contactName;
    }

    /**
     * The getContactID method gets the Contacts (Contact_ID) from the contacts table.
     * @return This is a return statement
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * The setContactID method sets the Contacts (Contact_ID) to the contacts table. This is auto-incremented
     * so we will not use this method for this project.
     * @param contactID This is a parameter
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * The getContactName method gets the Contacts (Contact_Name) from the contacts table.
     * @return This is a return statement
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * The setContactName method sets the Contacts (Contact_Name) to the contacts table.
     * @param contactName This is a parameter
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * The getContactEmail method gets the Contacts (Email) from the contacts table.
     * @return This is a return statement
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * The setContactEmail method sets the Contacts (Email) to the contacts table.
     * @param contactEmail This is a parameter
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Override to print customerName to string
     * @return This is a return statement
     */
    @Override public String toString() {
        return contactName;
    }
}
