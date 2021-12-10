package android.reserver.c196_greg_westmoreland.All.Database;

/**
 * Import statements
 */
import android.content.Context;
import android.reserver.c196_greg_westmoreland.All.DAO.AssessmentsDao;
import android.reserver.c196_greg_westmoreland.All.DAO.CoursesDao;
import android.reserver.c196_greg_westmoreland.All.DAO.TermsDao;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.reserver.c196_greg_westmoreland.All.UI.Utilities.StartData;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The @Database references
 */
@Database(entities = {TermsEntity.class, CoursesEntity.class, AssessmentsEntity.class}, version = 12, exportSchema = false)

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

            // Database Write Executor
            databaseWriteExecutor.execute(() -> {

                // These delete all data upon starting the app
                //mTermsDao.deleteAllTerms();;
                //mCoursesDao.deleteAllCourses();
                //mAssessmentsDao.deleteAllAssessments();


                mTermsDao.insertAllTerms(StartData.getStartTerms());
                mCoursesDao.insertAllCourses(StartData.getStartCourses());
                mAssessmentsDao.insertAllAssessments(StartData.getStartAssessments());
            });
        }
    };
}
