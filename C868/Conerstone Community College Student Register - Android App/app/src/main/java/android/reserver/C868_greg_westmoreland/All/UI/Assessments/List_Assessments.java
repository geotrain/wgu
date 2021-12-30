package android.reserver.C868_greg_westmoreland.All.UI.Assessments;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Log_In_Page;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import java.util.Objects;

public class List_Assessments extends AppCompatActivity {

    // Declare variables
    public static int assessmentID;

    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    /**
     * This Assessments List is generated when the user loads this screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This tells which activity screen.xml this file is associated with
        setContentView(R.layout.activity_list_assessments);
        // Call Instance from repository and getAllAssessments
        repository = new SchedulerRepository(getApplication());
        // this is really just to set up the database if there isn't one on your device yet-otherwise
        repository.getAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.assessmentsListRecyclerView);

        final Assessments_Adapter assessmentsAdapter = new Assessments_Adapter(this);
        recyclerView.setAdapter(assessmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentsAdapter.setAssessments(repository.getAllAssessments());

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
        getMenuInflater().inflate(R.menu.menu_terms_list_recylceview, menu);
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
            case R.id.refresh:
                repository = new SchedulerRepository(getApplication());
                List<AssessmentsEntity> allAssessments = repository.getAllAssessments();
                final Assessments_Adapter assessmentsAdapter = new Assessments_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.assessmentsListRecyclerView);
                recyclerView.setAdapter(assessmentsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                assessmentsAdapter.setAssessments(allAssessments);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method navigates to home screen
     * @param view
     */
    public void returnToTheHome(View view) {
        Intent intent = new Intent(List_Assessments.this, Main_Activity_Log_In_Page.class);
        startActivity(intent);
    }
}