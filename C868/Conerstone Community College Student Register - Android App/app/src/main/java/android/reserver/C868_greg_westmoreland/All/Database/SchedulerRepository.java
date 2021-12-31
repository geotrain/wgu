package android.reserver.C868_greg_westmoreland.All.Database;

/**
 * Import statements
 */
import android.app.Application;
import android.reserver.C868_greg_westmoreland.All.DAO.AssessmentsDao;
import android.reserver.C868_greg_westmoreland.All.DAO.CoursesDao;
import android.reserver.C868_greg_westmoreland.All.DAO.TermsDao;
import android.reserver.C868_greg_westmoreland.All.DAO.UsersDao;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.UsersEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Utilities.LoginActivity;

import androidx.room.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private UsersDao mUsersDAO;
    private static int currentUserId;
    private List<TermsEntity> mAllTermsEntities;
    private List<CoursesEntity> mAllCoursesEntities;
    private List<AssessmentsEntity> mAllAssessmentsEntities;
    private List<UsersEntity> mAllUsersEntities;
    private static List<UsersEntity> mAllUserNamesAndPasswords;
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
        mUsersDAO = db.usersDao();
        UsersDao mUserNamesAndPasswords = db.usersDao();
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
            mAllAssessmentsEntities = mAssessmentsDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessmentsEntities;
    }

    /**
     * This method gets all users from the users_table
     * @return
     */
    public List<UsersEntity> getAllUsers() {
        databaseExecutor.execute(()-> {
            mAllUsersEntities = mUsersDAO.getAllUsers();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUsersEntities;
    }

    /**
     * This method checks username and password from the users_table
     * @return
     */
    /*
    public static boolean checkUsernameAndPassword(String uName, String password) {
            ResultSet rs = databaseExecutor.execute(()-> {
            SchedulerRepository mUserNamesAndPasswords = null;
            mAllUserNamesAndPasswords = mUserNamesAndPasswords.checkUsernameAndPassword();
        });
        try {
            //ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("User_Name"));
                if (rs.getString("User_Name").equals(uName) && rs.getString("password").equals(password)) {
                    LoginActivity.login_activity(uName, true);
                    currentUserId = rs.getInt("User_ID");
                    System.out.println("The value of global variable currentUserId is currently set to " + currentUserId);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoginActivity.login_activity(uName, false);
        return false;
    }*/


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
     * This method inserts all assessments in the users_table
     * @param usersEntity
     */
    public void insert(UsersEntity usersEntity) {
        databaseExecutor.execute(() -> {
            mUsersDAO.insert(usersEntity);
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
     * This method updates all assessments from the users table
     * @param usersEntity
     */
    public void update(UsersEntity usersEntity) {
        databaseExecutor.execute(() -> {
            mUsersDAO.update(usersEntity);
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

    /**
     * This method deletes all assessments from the users_table
     * @param usersEntity
     */
    public void delete(UsersEntity usersEntity) {
        databaseExecutor.execute(() -> {
            mUsersDAO.delete(usersEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
