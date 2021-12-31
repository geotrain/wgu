package android.reserver.C868_greg_westmoreland.All.DAO;

/**
 * Import statements
 */
import android.reserver.C868_greg_westmoreland.All.Entities.UsersEntity;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

/**
 * Dao reference
 */
@Dao
public interface UsersDao {
    /**
     * The insert statement for the terms entity
     * @param usersEntity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UsersEntity usersEntity);

    /**
     * The update statement for the terms entity
     * @param usersEntity
     */
    @Update
    void update(UsersEntity usersEntity);

    /**
     * The delete statement for the terms entity
     * @param usersEntity
     */
    @Delete
    void delete(UsersEntity usersEntity);

    /**
     * The insert all terms for the terms entity
     * @param usersEntity
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllUsers(List<UsersEntity> usersEntity);

    /**
     * Query to get all terms from the terms_table
     * @return
     */
    @Query("SELECT * FROM USERS_TABLE ORDER BY userID ASC")
    List<UsersEntity>getAllUsers();

    /**
     * Query to delete all terms from the terms table
     */
    @Query("DELETE FROM USERS_TABLE")
    void deleteAllUsers();

    /**
     * Query to check username and password from users table
     */
    @Query("SELECT * FROM USERS_TABLE")
    List<UsersEntity>checkUsernameAndPassword();

}
