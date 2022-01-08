package android.reserver.C868_greg_westmoreland.All.DAO;

/**
 * Import statements
 */
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * Referenced Dao
 */
@Dao
public interface CoursesDao {

    /**
     * Insert statement for course entity
     * @param coursesEntity
     */
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(CoursesEntity coursesEntity);

    /**
     * Update statement for the course entity
     * @param coursesEntity
     */
    @Update
    void update(CoursesEntity coursesEntity);

    /**
     * The delete statement for the course entity
     * @param courseEntity
     */
    @Delete
    void delete(CoursesEntity courseEntity);

    /**
     * The insert all courses for the course entity
     * @param coursesEntity
     */
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insertAllCourses(List<CoursesEntity> coursesEntity);

    /**
     * Query to get all courses from the courses_table
     * @return
     */
    @Query("SELECT * FROM COURSES_TABLE ORDER BY courseID ASC")
    List<CoursesEntity> getAllCourses();

    /**
     * Query to delete all courses from the courses_table
     */
    @Query("DELETE FROM COURSES_TABLE")
    void deleteAllCourses();

    /**
     * Query to get all courses from the courses_table
     * @return
     */
    @Query("SELECT courseID, courseInstructorName, courseInstructorPhone, courseInstructorEmail, termID " +
            "FROM COURSES_TABLE ORDER BY courseInstructorName ASC")
    List<CoursesEntity> getAllInstructorsReport();

    /**
     * Query to get all courses from the courses_table
     * @return
     */
    @Query("SELECT courseID, courseName, courseStartDate, courseEndDate, termID " +
            "FROM COURSES_TABLE ORDER BY courseName ASC")
    List<CoursesEntity> getAllCoursesReports();

    /**
     * Search using variable 'query' to get all course names, start dates, end dates from the courses_table
     * @return
     */
    @Query("SELECT courseID, courseName, courseStartDate, courseEndDate, termID FROM COURSES_TABLE " +
            "WHERE courseName LIKE :query OR courseStartDate LIKE :query OR courseEndDate LIKE :query")
    List<CoursesEntity>getAllCoursesSearch(String query);
}
