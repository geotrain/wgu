package android.reserver.C868_greg_westmoreland.All.DAO;

/**
 * Import statements
 */
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * Dao Reference
 */
@Dao
public interface AssessmentsDao {

    /**
     * Insert Statement for assessment entity
     * @param assessmentsEntity
     */
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(AssessmentsEntity assessmentsEntity);

    /**
     * Update statement for assessment entity
     * @param assessmentEntity
     */
    @Update
    void update(AssessmentsEntity assessmentEntity);

    /**
     * Delete statement for assessment entity
     * @param assessmentEntity
     */
    @Delete
    void delete(AssessmentsEntity assessmentEntity);

    /**
     * Insert all assessments for assessments entity
     * @param assessmentsEntity
     */
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insertAllAssessments(List<AssessmentsEntity> assessmentsEntity);

    /**
     * Query to get all assessments from the assessments_table
     * @return
     */
    @Query("SELECT * FROM ASSESSMENTS_TABLE ORDER BY assessmentID ASC")
    List<AssessmentsEntity> getAllAssessments();

    /**
     * Query to delete everything from the assessments_table
     */
    @Query("DELETE FROM ASSESSMENTS_TABLE")
    void deleteAllAssessments();

    /**
     * Query to get all assessments from the assessments_table
     * @return
     */
    @Query("SELECT assessmentID, assessmentName, assessmentStartDate, assessmentEndDate, courseID FROM " +
            "ASSESSMENTS_TABLE ORDER BY assessmentID ASC")
    List<AssessmentsEntity> getAllAssessmentsReport();
}
