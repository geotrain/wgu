package android.reserver.C868_greg_westmoreland.All.UI.Courses;

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
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Log_In_Page;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.List;
import java.util.Objects;

public class List_Courses extends AppCompatActivity {

    // Declare variables
    public static int courseID;
    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    // Variables for Search
    private List<CoursesEntity> mSearchCourses;
    private RecyclerView recyclerView;

    /**
     * This method loads the get all courses, adapter when the screen loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);
        repository = new SchedulerRepository(getApplication());
        repository.getAllCourses();

        setTitle("Courses List");
        final Courses_Adapter coursesAdapter = new Courses_Adapter(this);
        recyclerView = findViewById(R.id.coursesListRecyclerView);
        recyclerView.setAdapter(coursesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        coursesAdapter.setCourses(repository.getAllCourses());

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_courses_list_recylceview, menu);

        // Associate searchable configuration with the SearchView
        SearchView searchView = (SearchView) menu.findItem(R.id.coursesSearch).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconified(true);
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Search Courses");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            /**
             * onQueryTextSubmit method
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            /**
             * onQueryTextChange
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextChange(String query) {
                search(query);
                return true;
            }
        });
        return true;
    }

    /**
     * Method used for string search
     * @param query
     */
    public void search(String query) {
        List<CoursesEntity> searchResults = repository.getAllCoursesSearch(query);
        System.out.println(query);
        if (query != null) {
            mSearchCourses = searchResults;
            final Courses_Adapter coursesAdapter = new Courses_Adapter(this);
            recyclerView = findViewById(R.id.coursesListRecyclerView);
            recyclerView.setAdapter(coursesAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            coursesAdapter.setCourses(searchResults);
            coursesAdapter.setCourses(mSearchCourses);
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
            case R.id.home_screen_from_courses_screen:
                returnToHomePage();
                return true;
            case R.id.refresh_courses:
                repository = new SchedulerRepository(getApplication());
                List<CoursesEntity> allCourses = repository.getAllCourses();
                final Courses_Adapter coursesAdapter = new Courses_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.coursesListRecyclerView);
                recyclerView.setAdapter(coursesAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                coursesAdapter.setCourses(allCourses);
                Toast.makeText(this, "Courses List Refreshed.", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Returns to home page used in navigational menu
     */
    private void returnToHomePage() {
        Intent intent = new Intent(List_Courses.this, Main_Activity_Home_Page.class);
        startActivity(intent);
    }

    /**
     * This method navigates to home screen
     * @param view
     */
    public void returnToHome(View view) {
        Intent intent = new Intent(List_Courses.this, Main_Activity_Log_In_Page.class);
        startActivity(intent);
    }
}