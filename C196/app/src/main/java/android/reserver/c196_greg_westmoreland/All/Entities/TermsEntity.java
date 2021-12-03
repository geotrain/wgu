package android.reserver.c196_greg_westmoreland.All.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity (tableName = "terms_table")
public class TermsEntity {

    // Auto-generate Primary Key / ID Variable For terms_table
    @PrimaryKey(autoGenerate = true)
    private int termID;

    // Terms Name e.g., Term 1, Spring Term Variable
    private String termName;

    @Override
    public String toString() {
        return "Terms{" +
                "termName='" + termName + '\'' +
                '}';
    }

    // Term Start Date Variable
    private String termStartDate; // <-- Problem here

    // Term End Date Variable
    private String termEndDate; // <-- Problem here

    public TermsEntity(int termID, String termName, String termStartDate, String termEndDate) {
        this.termID = termID;
        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }
}
