package android.reserver.C868_greg_westmoreland.All.DAO;

/**
 * Import statements
 */
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;

/**
 * Import statements
 */
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao reference
 */
@Dao
public interface TermsDao {

    /**
     * The insert statement for the terms entity
     * @param termsEntity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermsEntity termsEntity);

    /**
     * The update statement for the terms entity
     * @param termsEntity
     */
    @Update
    void update(TermsEntity termsEntity);

    /**
     * The delete statement for the terms entity
     * @param termsEntity
     */
    @Delete
    void delete(TermsEntity termsEntity);

    /**
     * The insert all terms for the terms entity
     * @param termsEntity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllTerms(List<TermsEntity> termsEntity);

    /**
     * Query to get all terms from the terms_table
     * @return
     */
    @Query("SELECT * FROM TERMS_TABLE ORDER BY termID ASC")
    List<TermsEntity>getAllTerms();

    /**
     * Query to delete all terms from the terms table
     */
    @Query("DELETE FROM TERMS_TABLE")
    void deleteAllTerms();

    /**
     * Query to get all terms from the terms_table
     * @return
     */
    @Query("SELECT termID, termName, termStartDate, termEndDate FROM TERMS_TABLE ORDER BY termName ASC")
    List<TermsEntity>getAllTermsReport();

    /**
     * Search using variable 'query' to get all term names, start dates, end dates from the terms_table
     * @return
     */
    @Query("SELECT termID, termName, termStartDate, termEndDate FROM TERMS_TABLE WHERE termName LIKE '%' " +
            "|| :query || '%' OR termStartDate LIKE '%' || :query || '%' OR termEndDate LIKE '%' || :query || '%'")
    List<TermsEntity>getAllTermsSearch(String query);


}
