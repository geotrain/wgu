package android.reserver.c196_greg_westmoreland.All.UI.Utilities;

/**
 * Import statements
 */
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
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
        startTerm.add(new TermsEntity(4, "Term 4", "04/01/2021", "04/30/2021"));
        startTerm.add(new TermsEntity(5, "Term 5", "05/01/2021", "05/31/2021"));
        startTerm.add(new TermsEntity(6, "Term 6", "06/01/2021", "06/30/2021"));
        return startTerm;
    }

    /**
     * This Array List holds start data to populate the courses_table with 4 courses when the app is launched
     * @return
     */
    public static List<CoursesEntity> getStartCourses() {
        List<CoursesEntity> startCourse = new ArrayList<>();
        startCourse.add(new CoursesEntity(1, "Algebra", 1,
                "01/01/2021", "01/31/2021", "Plan to take",
                "John Smith", "210-123-1234",
                "jsmith@wgu.edu", ""));
        startCourse.add(new CoursesEntity(2, "History", 2,
                "02/01/2021", "02/28/201", "In Progress",
                "Tyler Love", "210-345-5678",
                "tloveh@wgu.edu", ""));
        startCourse.add(new CoursesEntity(3, "Chemistry", 3,
                "03/01/2021", "03/31/2021", "Dropped",
                "Susan Jones", "210-321-4321",
                "sjones@wgu.edu", ""));
        startCourse.add(new CoursesEntity(4, "Music", 4,
                "04/01/2021", "04/30/2021", "Completed",
                "Mary Johnson", "210-468-9752",
                "mjohnson@wgu.edu", ""));
        startCourse.add(new CoursesEntity(5, "Art", 1,
                "04/01/2021", "04/30/2021", "Completed",
                "Pete Black", "210-468-1232",
                "pblack@wgu.edu", ""));
        startCourse.add(new CoursesEntity(6, "Literature", 1,
                "04/01/2021", "04/30/2021", "Plan to take",
                "Cindy Pikes", "210-468-3522",
                "cpikes@wgu.edu", ""));
        startCourse.add(new CoursesEntity(7, "Spanish I", 2,
                "04/01/2021", "04/30/2021", "In progress",
                "Danny Tyson", "210-468-3327",
                "dtyson@wgu.edu", ""));
        startCourse.add(new CoursesEntity(8, "Spanish II", 2,
                "04/01/2021", "04/30/2021", "In progress",
                "Todd Marks", "210-468-2234",
                "tmarks@wgu.edu", ""));
        startCourse.add(new CoursesEntity(9, "Biology", 1,
                "04/01/2021", "04/30/2021", "Completed",
                "Kyle Morris", "210-468-2334",
                "kmorris@wgu.edu", ""));
        startCourse.add(new CoursesEntity(10, "Government", 2,
                "04/01/2021", "04/30/2021", "Completed",
                "Andrew Simmons", "210-468-2374",
                "asimmons@wgu.edu", ""));
        return startCourse;
    }

    /**
     * This Array List holds start data to populate the assessments_table with 4 terms when the app is launched
     * @return
     */
    public static List<AssessmentsEntity> getStartAssessments() {
        List<AssessmentsEntity> startAssessment = new ArrayList<>();
        startAssessment.add(new AssessmentsEntity(1, "Objective 1", 1,
                "Objective", "01/01/2021", "01/31/2021"));
        startAssessment.add(new AssessmentsEntity(2, "Objective 2", 2,
                "Objective", "02/01/2021", "02/28/2021"));
        startAssessment.add(new AssessmentsEntity(3, "Performance 1", 3,
                "Performance", "03/01/2021", "03/31/2021"));
        startAssessment.add(new AssessmentsEntity(4, "Performance 2", 4,
                "Performance", "04/01/2021", "04/30/2021"));
        startAssessment.add(new AssessmentsEntity(5, "Objective 3", 1,
                "Objective", "04/01/2021", "04/30/2021"));
        startAssessment.add(new AssessmentsEntity(6, "Objective 4", 2,
                "Performance", "04/01/2021", "04/30/2021"));
        startAssessment.add(new AssessmentsEntity(7, "Performance 3", 1,
                "Performance", "04/01/2021", "04/30/2021"));
        startAssessment.add(new AssessmentsEntity(8, "Performance 4", 2,
                "Performance", "04/01/2021", "04/30/2021"));
        return startAssessment;
    }
}
