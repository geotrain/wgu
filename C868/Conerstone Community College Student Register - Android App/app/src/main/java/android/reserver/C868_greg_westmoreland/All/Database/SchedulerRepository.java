package android.reserver.C868_greg_westmoreland.All.Database;

/*
  Import statements
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
import java.util.ArrayList;
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
    private ArrayList<AssessmentsEntity> mAllAssessmentsSearchEntities;
    private List<UsersEntity> mAllUsersEntities;
    private static List<UsersEntity> mAllUserList;
    private static List<UsersEntity> mAllUserPass;
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

    public boolean checkUsernameAndPassword(String uName, String password) {
            databaseExecutor.execute(()-> {
            mAllUserList = mUsersDAO.getAllUsers();
            mAllUserPass = mUsersDAO.getAllUsers();
        });
        System.out.println(getAllUsers().toString());
        try {
            // For loop for iterating over the List
            for (int i = 0; i < mAllUserList.size(); i++) {
                // Print Usernames and passwords from the database in logs
                System.out.println(mAllUserList.get(i));
                if (mAllUserList.get(i).getUsername().equals(uName) && mAllUserList.get(i)
                        .getPassword().equals(password)) {
                    // Print username and passwords found
                    System.out.println("Found username " + mAllUserList.get(i).getUsername() +
                            "with password " + mAllUserList.get(i).getPassword());
                    return true;
                } else {
                    // Print username and passwords not found
                    System.out.println("Did not find username " + mAllUserList.get(i).getUsername() +
                            "with password " + mAllUserList.get(i).getPassword());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginActivity.login_activity(uName, false);
        return false;
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

    /**
     * This method gets all courses from the courses_table
     * @return
     */
    public List<CoursesEntity> getAllInstructorsReport() {
        databaseExecutor.execute(()-> {
            mAllCoursesEntities = mCoursesDAO.getAllInstructorsReport();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCoursesEntities;
    }

    /**
     * This method gets all courses from the courses_table
     * @return
     */
    public List<CoursesEntity> getAllCoursesReport() {
        databaseExecutor.execute(()-> {
            mAllCoursesEntities = mCoursesDAO.getAllCoursesReports();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCoursesEntities;
    }

    /**
     * This method gets all terms from the terms_table
     * @return
     */
    public List<TermsEntity> getAllTermsReport() {
        databaseExecutor.execute(()-> {
            mAllTermsEntities = mTermsDAO.getAllTermsReport();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTermsEntities;
    }

    /**
     * This method gets all assessments from the assessments_table
     * @return
     */
    public List<AssessmentsEntity> getAllAssessmentsReport() {
        databaseExecutor.execute(()-> {
            mAllAssessmentsEntities = mAssessmentsDAO.getAllAssessmentsReport();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessmentsEntities;
    }

    public List<AssessmentsEntity> searchAllAssessments() {
        databaseExecutor.execute(()-> {
            mAllAssessmentsEntities = mAssessmentsDAO.getAllAssessmentsSearch();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessmentsEntities;
    }

    /**
     * This method gets all terms from the terms_table
     * @return
     */
    public List<TermsEntity> getAllTermsSearch(String query) {
        databaseExecutor.execute(()-> {
            mAllTermsEntities = mTermsDAO.getAllTermsSearch(query);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTermsEntities;
    }

    /**
     * This method gets all terms from the terms_table
     * @return
     */
    public List<CoursesEntity> getAllCoursesSearch(String query) {
        databaseExecutor.execute(()-> {
            mAllTermsEntities = mCoursesDAO.getAllCoursesSearch(query);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCoursesEntities;
    }
}
