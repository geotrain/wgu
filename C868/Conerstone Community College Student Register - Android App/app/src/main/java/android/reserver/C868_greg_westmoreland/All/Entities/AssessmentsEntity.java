package android.reserver.C868_greg_westmoreland.All.Entities;

/**
 * Import statements
 */
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Entity reference to the assessment_table
 */
@Entity(tableName = "assessments_table")
public class AssessmentsEntity {

    /**
     * This @PrimaryKey reference Auto-generate the Primary Key and ID Variable For assessments_table
     */
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    /**
     * Declaration of variables uses
     */
    private String assessmentName;
    private int courseID;

    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentName='" + assessmentName + '\'' +
                '}';
    }

    private String assessmentType;
    private String assessmentStartDate;
    private String assessmentEndDate;


    /**
     * Constructor
     * @param assessmentID
     * @param assessmentName
     * @param courseID
     * @param assessmentType
     * @param assessmentStartDate
     * @param assessmentEndDate
     */
    public AssessmentsEntity(int assessmentID, String assessmentName, int courseID,
                             String assessmentType, String assessmentStartDate,
                             String assessmentEndDate) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.courseID = courseID;
        this.assessmentType = assessmentType;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
    }

    /**
     * This method gets the assessment id from the assessments_table
     * @return
     */
    public int getAssessmentID() {
        return assessmentID;
    }

    /**
     * This method sets the assessment id to the assessments_table
     * @param assessmentID
     */
    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    /**
     * Thie method gets the assessment name from the assessments_table
     * @return
     */
    public String getAssessmentName() {

        return assessmentName;
    }

    /**
     * This method sets the assessment name to the assessments_table
     * @param assessmentName
     */
    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    /**
     * This method gets the assessment type from the assessments_table
     * @return
     */
    public String getAssessmentType() {
        return assessmentType;
    }

    /**
     * This method sets the assessment type to the assessments_table
     * @param assessmentType
     */
    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    /**
     * This methos gets the assessment start date from the assessments_table
     * @return
     */
    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    /**
     * This method sets the start date to the assessments_table
     * @param assessmentStartDate
     */
    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    /**
     * This method gets the assessment end date from the assessments_table
     * @return
     */
    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    /**
     * This method sets the assessment end date to the assessments_table
     * @param assessmentEndDate
     */
    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }

    /**
     * This method gets the course id associated with an assessment
     * @return
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * This method sets the course id associated with an assessment
     * @param courseID
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
