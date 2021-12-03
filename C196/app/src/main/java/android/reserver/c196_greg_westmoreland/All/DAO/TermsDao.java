package android.reserver.c196_greg_westmoreland.All.DAO;

import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TermsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermsEntity termsEntity);

    @Update
    void update(TermsEntity termsEntity);

    @Delete
    void delete(TermsEntity termsEntity);

    @Query("SELECT * FROM TERMS_TABLE ORDER BY termID ASC")
    List<TermsEntity> getAllTerms();

}
