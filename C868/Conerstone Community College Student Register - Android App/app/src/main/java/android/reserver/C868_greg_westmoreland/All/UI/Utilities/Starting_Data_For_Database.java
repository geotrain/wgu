package android.reserver.C868_greg_westmoreland.All.UI.Utilities;

/**
 * Import statements
 */
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import java.util.ArrayList;
import java.util.List;

public class Starting_Data_For_Database {

    /**
     * This Array List holds start data to populate the terms_table with 3 terms when the app is launched
     * @return
     */
    public static List<TermsEntity> getStartTerms() {
        List<TermsEntity> startTerm = new ArrayList<>();
        startTerm.add(new TermsEntity(1, "Term 1", "01/01/2021", "01/31/2021"));
        startTerm.add(new TermsEntity(2, "Term 2", "02/01/2021", "02/28/2021"));
        startTerm.add(new TermsEntity(3, "Term 3", "03/01/2021", "03/31/2021"));
        return startTerm;
    }

    /**
     * This Array List holds start data to populate the courses_table with 4 courses when the app is launched
     * @return
     */
    public static List<CoursesEntity> getStartCourses() {
        List<CoursesEntity> startCourse = new ArrayList<>();
        startCourse.add(new CoursesEntity(1, "Algebra", 1,
                "12/01/2021", "12/08/2021", "Plan to take",
                "John Smith", "210-123-1234",
                "jsmith@wgu.edu", "Very Hard Class"));
        startCourse.add(new CoursesEntity(2, "History", 2,
                "12/09/2021", "12/17/2021", "In Progress",
                "Tyler Love", "210-345-5678",
                "tloveh@wgu.edu", "Take First Semester"));
        startCourse.add(new CoursesEntity(3, "Chemistry", 3,
                "12/18/2021", "12/26/2021", "Dropped",
                "Susan Jones", "210-321-4321",
                "sjones@wgu.edu", "Do Not Take With Algebra"));
        return startCourse;
    }

    /**
     * This Array List holds start data to populate the assessments_table with 4 terms when the app is launched
     * @return
     */
    public static List<AssessmentsEntity> getStartAssessments() {
        List<AssessmentsEntity> startAssessment = new ArrayList<>();
        startAssessment.add(new AssessmentsEntity(1, "Objective 1", 1,
                "Objective", "12/01/2021", "12/07/2021"));
        startAssessment.add(new AssessmentsEntity(2, "Performance 1", 1,
                "Objective", "12/07/2021", "12/08/2021"));
        startAssessment.add(new AssessmentsEntity(3, "Objective 2", 2,
                "Objective", "12/09/2021", "12/16/2021"));
        startAssessment.add(new AssessmentsEntity(4, "Performance 2", 2,
                "Objective", "12/16/2021", "12/17/2021"));
        startAssessment.add(new AssessmentsEntity(5, "Objective 3", 3,
                "Objective", "12/18/2021", "12/25/2021"));
        startAssessment.add(new AssessmentsEntity(6, "Performance 3", 3,
                "Performance", "12/25/2021", "12/26/2021"));
        return startAssessment;
    }
}
