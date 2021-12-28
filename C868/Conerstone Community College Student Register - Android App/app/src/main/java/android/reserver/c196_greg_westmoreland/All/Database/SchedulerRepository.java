package android.reserver.c196_greg_westmoreland.All.Database;

/**
 * Import statements
 */
import android.app.Application;
import android.reserver.c196_greg_westmoreland.All.DAO.AssessmentsDao;
import android.reserver.c196_greg_westmoreland.All.DAO.CoursesDao;
import android.reserver.c196_greg_westmoreland.All.DAO.TermsDao;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Schedule Repository
 */
public class SchedulerRepository {
    /**
     * Decleration of the variables used for the repository
     */
    private TermsDao mTermsDAO;
    private CoursesDao mCoursesDAO;
    private AssessmentsDao mAssessmentsDAO;
    private List<TermsEntity> mAllTermsEntities;
    private List<CoursesEntity> mAllCoursesEntities;
    private List<AssessmentsEntity> mAllAssesssmentsEntities;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Constructer
     * @param application
     */
    public SchedulerRepository(Application application) {
        SchedulerDatabase db= SchedulerDatabase.getDatabase(application);
        mTermsDAO = db.termsDao();
        mCoursesDAO = db.coursesDao();
        mAssessmentsDAO = db.assessmentsDao();
    }

    /**
     * This method gets all terms from the terms_table
     * @return
     */
    public List<TermsEntity> getAllTerms() {
        databaseExecutor.execute(()-> {
            mAllTermsEntities = mTermsDAO.getAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTermsEntities;
    }

    /**
     * This method gets all courses from the courses_table
     * @return
     */
    public List<CoursesEntity> getAllCourses() {
        databaseExecutor.execute(()-> {
            mAllCoursesEntities = mCoursesDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCoursesEntities;
    }

    /**
     * This method gets all assessments from the assessments_table
     * @return
     */
    public List<AssessmentsEntity> getAllAssessments() {
        databaseExecutor.execute(()-> {
            mAllAssesssmentsEntities = mAssessmentsDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssesssmentsEntities;
    }

    /**
     * This method inserts all terms in the terms_table
     * @param termsEntity
     */
    public void insert(TermsEntity termsEntity) {
        databaseExecutor.execute(() -> {
            mTermsDAO.insert(termsEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method inserts all courses in the courses_table
     * @param coursesEntity
     */
    public void insert(CoursesEntity coursesEntity) {
        databaseExecutor.execute(() -> {
            mCoursesDAO.insert(coursesEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method inserts all assessments in the assessments_table
     * @param assessmentsEntity
     */
    public void insert(AssessmentsEntity assessmentsEntity) {
        databaseExecutor.execute(() -> {
            mAssessmentsDAO.insert(assessmentsEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates all terms in the terms_table
     * @param termsEntity
     */
    public void update(TermsEntity termsEntity) {
        databaseExecutor.execute(() -> {
            mTermsDAO.update(termsEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates all courses in the courses_table
     * @param coursesEntity
     */
    public void update(CoursesEntity coursesEntity) {
        databaseExecutor.execute(() -> {
            mCoursesDAO.update(coursesEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates all assessments from the assessments table
     * @param assessmentsEntity
     */
    public void update(AssessmentsEntity assessmentsEntity) {
        databaseExecutor.execute(() -> {
            mAssessmentsDAO.update(assessmentsEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deletes all terms from the terms_table
     * @param termsEntity
     */
    public void delete(TermsEntity termsEntity) {
        databaseExecutor.execute(() -> {
            mTermsDAO.delete(termsEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deltes all courses from the courses_table
     * @param courseEntity
     */
    public void delete(CoursesEntity courseEntity) {
        databaseExecutor.execute(() -> {
            mCoursesDAO.delete(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deletes all assessments from the assessments_table
     * @param assessmentEntity
     */
    public void delete(AssessmentsEntity assessmentEntity) {
        databaseExecutor.execute(() -> {
            mAssessmentsDAO.delete(assessmentEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
