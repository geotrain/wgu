package android.reserver.C868_greg_westmoreland.All.Entities;

/**
 * Import statements
 */
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Entity references the terms_table
 */
@Entity (tableName = "terms_table")
public class TermsEntity {

    /**
     * The @PrimaryKey Auto-generate Primary Key and ID Variable For terms_table
     */
    @PrimaryKey(autoGenerate = true)
    private int termID;

    /**
     * Declaratoin of variables uses for terms_table
     */
    private String termName;

    @Override
    public String toString() {
        return "Terms{" +
                "termName='" + termName + '\'' +
                '}';
    }

    private String termStartDate;
    private String termEndDate;

    /**
     * Constructor
     * @param termID
     * @param termName
     * @param termStartDate
     * @param termEndDate
     */
    public TermsEntity(int termID, String termName, String termStartDate, String termEndDate) {
        this.termID = termID;
        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    /**
     * This method gets all term ids from the terms_table
     * @return
     */
    public int getTermID() {
        return termID;
    }

    /**
     * This method sets all term ids to the terms_table
     * @param termID
     */
    public void setTermID(int termID) {
        this.termID = termID;
    }

    /**
     * This method gets term names from the terms_table
     * @return
     */
    public String getTermName() {
        return termName;
    }

    /**
     * This method sets term names to the terms_table
     * @param termName
     */
    public void setTermName(String termName) {
        this.termName = termName;
    }

    /**
     * This method gets the term start date from the terms_table
     * @return
     */
    public String getTermStartDate() {
        return termStartDate;
    }

    /**
     * This method sets the term start date to the terms_table
     * @param termStartDate
     */
    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    /**
     * This method gets the term end date from the terms_table
     * @return
     */
    public String getTermEndDate() {
        return termEndDate;
    }

    /**
     * This method sets the term end date to the terms_table
     * @param termEndDate
     */
    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }
}
