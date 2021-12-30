package android.reserver.C868_greg_westmoreland.All.UI.Main;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.List_Assessments;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.List_Courses;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.List_Terms;
import android.reserver.C868_greg_westmoreland.R;
import android.view.View;

public class Main_Activity_Log_In_Page extends AppCompatActivity {

    private SchedulerRepository repository;

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
        setContentView(R.layout.activity_main_log_in_page);

        // Creates DB upon the main screen loading
        repository = new SchedulerRepository(getApplication());
        repository.getAllTerms();
    }

    public void logIn(View view) {
        Intent intent = new Intent(Main_Activity_Log_In_Page.this, Main_Activity_Home_Page.class);
        startActivity(intent);
    }
}