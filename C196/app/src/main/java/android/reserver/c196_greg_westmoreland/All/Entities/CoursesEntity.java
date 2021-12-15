package android.reserver.c196_greg_westmoreland.All.Entities;

/**
 * Import statements
 */
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Entity reference to the courses_table
 */
@Entity(tableName = "courses_table")
public class CoursesEntity {

    /**
     * @PrimaryKey reference to Auto-generate Primary Key and ID Variable For courses_table
     */
    @PrimaryKey(autoGenerate = true)
    private int courseID;

    /**
     * Declare the variables for the courses entity
     */
    private String courseName;
    private int termID;

    public CoursesEntity() {
    }

    @Override
    public String toString() {
        return "Courses{" +
                "courseName='" + courseName + '\'' +
                '}';
    }

    private String courseStartDate;
    private String courseEndDate;
    private String courseStatus;
    private String courseInstructorName;
    private String courseInstructorPhone;
    private String courseInstructorEmail;
    private String optionalCourseNote;

    /**
     * Constructor
     * @param courseID
     * @param courseName
     * @param termID
     * @param courseStartDate
     * @param courseEndDate
     * @param courseStatus
     * @param courseInstructorName
     * @param courseInstructorPhone
     * @param courseInstructorEmail
     * @param optionalCourseNote
     */
    public CoursesEntity(int courseID, String courseName, int termID, String courseStartDate, String courseEndDate,
                         String courseStatus, String courseInstructorName, String courseInstructorPhone,
                         String courseInstructorEmail, String optionalCourseNote) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.termID = termID;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseInstructorName = courseInstructorName;
        this.courseInstructorPhone = courseInstructorPhone;
        this.courseInstructorEmail = courseInstructorEmail;
        this.optionalCourseNote = optionalCourseNote;
    }

    /**
     * This method gets the course id from the courses_table
     * @return
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * This method sets the course id to the courses_table
     * @param courseID
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * This method gets the course name from the courses_table
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * This method sets the course name to the courses_table
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * This method gets the course start date from the courses_table
     * @return
     */
    public String getCourseStartDate() {
        return courseStartDate;
    }

    /**
     * This method sets the course start date to the courses_table
     * @param courseStartDate
     */
    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    /**
     * This method gets the course end date from the courses_table
     * @return
     */
    public String getCourseEndDate() {
        return courseEndDate;
    }

    /**
     * This method sets the course end date to the courses_table
     * @param courseEndDate
     */
    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    /**
     * This method gets the course status from the courses_table
     * @return
     */
    public String getCourseStatus() {
        return courseStatus;
    }

    /**
     * This method sets the course status to the courses_table
     * @param courseStatus
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * This method gets the course instructor's name the courses_table
     * @return
     */
    public String getCourseInstructorName() {
        return courseInstructorName;
    }

    /**
     * This method sets the course instructor's name to the courses_table
     * @param courseInstructorName
     */
    public void setCourseInstructorName(String courseInstructorName) {
        this.courseInstructorName = courseInstructorName;
    }

    /**
     * This method gets the course instructor's phone from the courses_table
     * @return
     */
    public String getCourseInstructorPhone() {
        return courseInstructorPhone;
    }

    /**
     * This method sets the course instructor's phone to the courses_table
     * @param courseInstructorPhone
     */
    public void setCourseInstructorPhone(String courseInstructorPhone) {
        this.courseInstructorPhone = courseInstructorPhone;
    }

    /**
     * This method gets the course instructor's email from the courses_table
     * @return
     */
    public String getCourseInstructorEmail() {
        return courseInstructorEmail;
    }

    /**
     * This method sets the course instructors email to the courses_table
     * @param courseInstructorEmail
     */
    public void setCourseInstructorEmail(String courseInstructorEmail) {
        this.courseInstructorEmail = courseInstructorEmail;
    }

    /**
     * This method gets the optional course note from the courses_table
     * @return
     */
    public String getOptionalCourseNote() {
        return optionalCourseNote;
    }

    /**
     * This method sets the optional course note to the courses_table
     * @param optionalCourseNote
     */
    public void setOptionalCourseNote(String optionalCourseNote) {
        this.optionalCourseNote = optionalCourseNote;
    }

    /**
     * This method gets the term id associated with a course
     * @return
     */
    public int getTermID() {
        return termID;
    }

    /**
     * This method sets the term id associated with a course
     * @param termID
     */
    public void setTermID(int termID) {
        this.termID = termID;
    }
}
