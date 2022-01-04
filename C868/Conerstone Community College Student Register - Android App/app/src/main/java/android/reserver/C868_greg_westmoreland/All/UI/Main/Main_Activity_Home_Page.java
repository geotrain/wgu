package android.reserver.C868_greg_westmoreland.All.UI.Main;

/**
 * Import statements
 */
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.Assessments_Adapter;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.List_Assessments;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.List_Courses;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.List_Terms;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class Main_Activity_Home_Page extends AppCompatActivity {

    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    /**
     * This method when the main screen (home screen) is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_page);

        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * This method inflates the menu and adds items to the action bar
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_screen_recycleview, menu);
        return true;
    }

    /**
     * This method is called when the backward arrow button for navigation is used. It is also used
     * when the users refreshes the screen
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.log_in_screen:
                Intent intent = new Intent(Main_Activity_Home_Page.this, Main_Activity_Log_In_Page.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method allows the user to enter the app when the enter button is pressed
     * @param view
     */
    public void seeAllTerms(View view) {
        Intent intent = new Intent(Main_Activity_Home_Page.this, List_Terms.class);
        startActivity(intent);
    }

    /**
     * This method is used to navigate to the assessment list screen
     * @param view
     */
    public void seeAllAssessments(View view) {
        Intent intent = new Intent(Main_Activity_Home_Page.this, List_Assessments.class);
        startActivity(intent);
    }

    /**
     * This method is used to navigate to the courses list screen
     * @param view
     */
    public void seeAllCourses(View view) {
        Intent intent = new Intent(Main_Activity_Home_Page.this, List_Courses.class);
        startActivity(intent);
    }

    /**
     * This method is used to navigate to the reports list screen
     * @param view
     */
    public void seeAllReports(View view) {
        Intent intent = new Intent(Main_Activity_Home_Page.this, Main_Activity_Reports_Page.class);
        startActivity(intent);
    }

}
