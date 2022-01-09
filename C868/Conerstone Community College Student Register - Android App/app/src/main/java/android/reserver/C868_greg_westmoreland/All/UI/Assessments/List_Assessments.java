package android.reserver.C868_greg_westmoreland.All.UI.Assessments;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.List_Courses;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Log_In_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.List_Terms_Adapter;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class List_Assessments extends AppCompatActivity {

    // Declare variables
    public static int assessmentID;

    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    // Variables for Search
    private List<AssessmentsEntity> mSearchTerms;
    private RecyclerView recyclerView;

    /**
     * This Assessments List is generated when the user loads this screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_assessments);
        repository = new SchedulerRepository(getApplication());
        repository.getAllAssessments();

        setTitle("Assessments List");
        final Assessments_Adapter assessmentsAdapter = new Assessments_Adapter(this);
        recyclerView = findViewById(R.id.assessmentsListRecyclerView);
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
        getMenuInflater().inflate(R.menu.menu_assessments_list_recycleview, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.assessmentsSearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconified(true);
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Search Assessments");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             * onQueryTextSubmit method
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return true;
            }

            /**
             * onQueryTextChange
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextChange(String query) {
                //search(query);
                return false;
            }
        });
        return true;
    }

    /**
     * Method used for string search
     * @param query
     */
    public void search(String query) {
        List<AssessmentsEntity> searchResults = repository.getAllAssessmentsSearch(query);
        System.out.println(query);
        if (query != null) {
            mSearchTerms = searchResults;
            final Assessments_Adapter assessmentsAdapter = new Assessments_Adapter(this);
            recyclerView.setAdapter(assessmentsAdapter);
        }
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
            case R.id.home_screen_from_assessments_screen:
                returnToHomePage();
                return true;
            case R.id.refresh_assessments:
                repository = new SchedulerRepository(getApplication());
                List<AssessmentsEntity> allAssessments = repository.getAllAssessments();
                final Assessments_Adapter assessmentsAdapter = new Assessments_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.assessmentsListRecyclerView);
                recyclerView.setAdapter(assessmentsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                assessmentsAdapter.setAssessments(allAssessments);
                Toast.makeText(this, "Assessments List Refreshed.", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called from the navigational menu
     */
    private void returnToHomePage() {
        Intent intent = new Intent(List_Assessments.this, Main_Activity_Home_Page.class);
        startActivity(intent);
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