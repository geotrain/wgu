package android.reserver.c196_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Assessments.AssessmentsList;
import android.reserver.c196_greg_westmoreland.All.UI.Assessments.Assessments_Add_New_Assessment;
import android.reserver.c196_greg_westmoreland.All.UI.Courses.CoursesList;
import android.reserver.c196_greg_westmoreland.All.UI.Courses.Courses_Add_New_Course;
import android.reserver.c196_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import java.util.Objects;

public class Terms_List extends AppCompatActivity {
    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    /**
     * This method loads the get all terms, adapter when the screen loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This tells which activity screen.xml this file is associated with
        setContentView(R.layout.activity_terms_list);
        // Call Instance from repository and getAllTerms
        repository = new SchedulerRepository(getApplication());
        // this is really just to set up the database if there isn't one on your device yet-otherwise
        repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);

        final TermsListAdapter termsAdapter = new TermsListAdapter(this);
        recyclerView.setAdapter(termsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termsAdapter.setTerms(repository.getAllTerms());

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
                List<TermsEntity> allTerms = repository.getAllTerms();
                final TermsListAdapter termsAdapter = new TermsListAdapter(this);
                RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
                recyclerView.setAdapter(termsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termsAdapter.setTerms(allTerms);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is used to navigate to the add a new terms screen
     * @param view
     */
    public void addNewTerm(View view) {
        Intent intent = new Intent(Terms_List.this, Terms_Add_New_Term.class);
        startActivity(intent);
    }

    /**
     * This method is used to navigate to the add a new assessment screen
     * @param view
     */
    public void addNewAssessment(View view) {
        Intent intent = new Intent(Terms_List.this, Assessments_Add_New_Assessment.class);
        startActivity(intent);
    }
}