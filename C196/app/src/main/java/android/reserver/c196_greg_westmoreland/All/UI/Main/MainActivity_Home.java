package android.reserver.c196_greg_westmoreland.All.UI.Main;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.UI.Assessments.AssessmentsList;
import android.reserver.c196_greg_westmoreland.All.UI.Courses.CoursesList;
import android.reserver.c196_greg_westmoreland.All.UI.Terms.Terms_List;
import android.reserver.c196_greg_westmoreland.R;
import android.view.View;

public class MainActivity_Home extends AppCompatActivity {

    /**
     * This variable sets the numAlert variable for the notify in the terms list detail screen
     */
    public static int numAlert;

    /**
     * This method when the main screen (home screen) is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
    }

    /**
     * This method allows the user to enter the app when the enter button is pressed
     * @param view
     */
    public void seeAllTerms(View view) {
        Intent intent = new Intent(MainActivity_Home.this, Terms_List.class);
        startActivity(intent);
    }

    /**
     * This method is used to navigate to the assessment list screen
     * @param view
     */
    public void seeAllAssessments(View view) {
        Intent intent = new Intent(MainActivity_Home.this, AssessmentsList.class);
        startActivity(intent);
    }

    /**
     * This method is used to navigate to the courses list screen
     * @param view
     */
    public void seeAllCourses(View view) {
        Intent intent = new Intent(MainActivity_Home.this, CoursesList.class);
        startActivity(intent);
    }
}