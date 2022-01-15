package android.reserver.C868_greg_westmoreland.All.Database;

/**
 * Import statements
 */
import android.content.Context;
import android.reserver.C868_greg_westmoreland.All.DAO.AssessmentsDao;
import android.reserver.C868_greg_westmoreland.All.DAO.CoursesDao;
import android.reserver.C868_greg_westmoreland.All.DAO.TermsDao;
import android.reserver.C868_greg_westmoreland.All.DAO.UsersDao;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.reserver.C868_greg_westmoreland.All.Entities.UsersEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Utilities.Starting_Data_For_Database;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The @Database references
 */
@Database(entities = {TermsEntity.class, CoursesEntity.class, AssessmentsEntity.class, UsersEntity.class},
        version = 20, exportSchema = false)

/**
 * The @Types Converter references
 */
@TypeConverters({SchedulerDateConverter.class, SchedulerAssessmentTypeConverter.class, SchedulerCourseStatusTypeConverter.class})

/**
 * The references to the dao's
 */
public abstract class SchedulerDatabase extends RoomDatabase {
    public abstract TermsDao termsDao();
    public abstract CoursesDao coursesDao();
    public abstract AssessmentsDao assessmentsDao();
    public abstract UsersDao usersDao();
    private static final int NUMBER_OF_THREADS = 4;

    /**
     * The Database write executor.
     */
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * The Instance for the scheduler database
     */
    private static volatile SchedulerDatabase INSTANCE;

    /**
     * This method gets the database and returns its instance
     * @param context
     * @return
     */
    static SchedulerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SchedulerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SchedulerDatabase.class,
                            "scheduler_database").fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * This rooms.database call back reference
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        /**
         * On Open method
         * @param db
         */
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            TermsDao mTermsDao = INSTANCE.termsDao();
            CoursesDao mCoursesDao = INSTANCE.coursesDao();
            AssessmentsDao mAssessmentsDao = INSTANCE.assessmentsDao();
            UsersDao mUsersDao = INSTANCE.usersDao();

            // Database Write Executor
            databaseWriteExecutor.execute(() -> {

                // These delete all data upon starting the app, comment these out for database persistence
                /*
                mTermsDao.deleteAllTerms();;
                mCoursesDao.deleteAllCourses();
                mAssessmentsDao.deleteAllAssessments();
                mUsersDao.deleteAllUsers();
                */

                mTermsDao.insertAllTerms(Starting_Data_For_Database.getStartTerms());
                mCoursesDao.insertAllCourses(Starting_Data_For_Database.getStartCourses());
                mAssessmentsDao.insertAllAssessments(Starting_Data_For_Database.getStartAssessments());
                mUsersDao.insertAllUsers(Starting_Data_For_Database.getStartUsers());
            });
        }
    };
}
