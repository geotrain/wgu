package android.reserver.c196_greg_westmoreland.All.UI.Courses;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Terms.Terms_List;
import android.reserver.c196_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import java.util.Objects;

public class CoursesList extends AppCompatActivity {

    public static int courseID;
    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    /**
     * This method loads the get all courses, adapter when the screen loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This tells which activity screen.xml this file is associated with
        setContentView(R.layout.activity_courses_list);
        // Call Instance from repository and getAllCourses
        repository = new SchedulerRepository(getApplication());
        // this is really just to set up the database if there isn't one on your device yet-otherwise
        repository.getAllCourses();
        RecyclerView recyclerView = findViewById(R.id.coursesListRecyclerView);

        final CoursesAdapter coursesAdapter = new CoursesAdapter(this);
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
                List<CoursesEntity> allCourses = repository.getAllCourses();
                final CoursesAdapter coursesAdapter = new CoursesAdapter(this);
                RecyclerView recyclerView = findViewById(R.id.coursesListRecyclerView);
                recyclerView.setAdapter(coursesAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                coursesAdapter.setCourses(allCourses);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNewCourse(View view) {
        Intent intent = new Intent(CoursesList.this, Courses_Add_New_Course.class);
        startActivity(intent);
    }
}