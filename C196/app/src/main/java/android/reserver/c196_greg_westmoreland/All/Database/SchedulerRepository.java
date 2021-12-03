package android.reserver.c196_greg_westmoreland.All.Database;

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

public class SchedulerRepository {
    private TermsDao mTermsDAO;
    private CoursesDao mCoursesDAO;
    private AssessmentsDao mAssessmentsDAO;
    private List<TermsEntity> mAllTermsEntities;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Constructor
    public SchedulerRepository(Application application) {
        SchedulerDatabaseBuilder db= SchedulerDatabaseBuilder.getDatabase(application);
        mTermsDAO = db.termsDao();
    }

    // Get All Terms
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

    // Insert Statements
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

    public void insert(AssessmentsDao assessmentsEntity) {
        databaseExecutor.execute(() -> {
            mAssessmentsDAO.insert(assessmentsEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Update Statements
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

    public void update(AssessmentsDao assessmentsDao) {
        databaseExecutor.execute(() -> {
            mAssessmentsDAO.update(assessmentsDao);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Delete Statements
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
