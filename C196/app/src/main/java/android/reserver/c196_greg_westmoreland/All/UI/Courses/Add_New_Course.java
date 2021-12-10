package android.reserver.c196_greg_westmoreland.All.UI.Courses;

/**
 * Import statements
 */
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Terms.List_Terms_Adapter;
import android.reserver.c196_greg_westmoreland.All.UI.Terms.List_Terms;
import android.reserver.c196_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Objects;

public class Add_New_Course extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Declaration of variables used int the terms list details screen
     */
    int id;
    String courseName;
    int termID;
    String courseStartDate;
    String courseEndDate;
    String courseStatusEntity;
    String courseInstructorName;
    String courseInstructorPhone;
    String courseInstructorEmail;

    EditText editCourseName;
    EditText editCourseStartDate;
    EditText editCourseEndDate;
    String editCourseStatusEntity;
    EditText editCourseInstructorName;
    EditText editCourseInstructorPhone;
    EditText editCourseInstructorEmail;

    SchedulerRepository repository;
    CoursesEntity currentCourse;

    //public static int termId = null;

    /**
     * This method is the actions taken when the terms list details page is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        id = getIntent().getIntExtra("courseID",-1);
        termID = getIntent().getIntExtra("termID", -1);
        courseName = getIntent().getStringExtra("Course_Name");
        courseStartDate = getIntent().getStringExtra("Start_Date");
        courseEndDate = getIntent().getStringExtra("End_Date");
        //courseStatusEntity = getIntent().getStringExtra("Course_Status");
        courseInstructorName = getIntent().getStringExtra("Course_Instructor_Name");
        courseInstructorPhone = getIntent().getStringExtra("Course_Instructor_Phone");
        courseInstructorEmail = getIntent().getStringExtra("Course_Instructor_Email");

        repository = new SchedulerRepository(getApplication());
        List<CoursesEntity> allCourses = repository.getAllCourses();

        id = allCourses.get(allCourses.size()-1).getTermID(); // This keeps id from being -1

        editCourseName = findViewById(R.id.Course_Name);
        editCourseStartDate = findViewById(R.id.Start_Date);
        editCourseEndDate = findViewById(R.id.End_Date);
        //editCourseStatusEntity = findViewById(R.id.Course_status);
        editCourseInstructorName = findViewById(R.id.Course_Instructor_Name);
        editCourseInstructorPhone = findViewById(R.id.Course_Instructor_Phone);
        editCourseInstructorEmail = findViewById(R.id.Course_Instructor_Email);

        // Possible take out
        if (currentCourse != null) {
            courseName = currentCourse.getCourseName();
            courseStartDate = currentCourse.getCourseStartDate();
            courseEndDate = currentCourse.getCourseEndDate();
            //courseStatusEntity = currentCourse.getCourseStatus();
            courseInstructorName = currentCourse.getCourseInstructorName();
            courseInstructorPhone = currentCourse.getCourseInstructorPhone();
            courseInstructorEmail = currentCourse.getCourseInstructorEmail();
        }

        repository = new SchedulerRepository(getApplication());

        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * This method is used when options are selected on the screen such refresh
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
                final List_Terms_Adapter termsAdapter = new List_Terms_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
                recyclerView.setAdapter(termsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termsAdapter.setTerms(allTerms);
        }
        return super.onOptionsItemSelected(item);
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
     * This method takes the input data and saves it as a new term
     * @param view
     */
    public void saveCourse(View view) {
        String courseName = editCourseInstructorName.getText().toString();
        String courseStartDate = editCourseStartDate.getText().toString();
        String courseEndDate = editCourseEndDate.getText().toString();
        //CourseStatusEntity courseStatusEntity= editCourseStatusEntity.getClass().toString();
        String courseInstructorName = editCourseInstructorName.getText().toString();;
        String courseInstructorPhone = editCourseInstructorPhone.getText().toString();;
        String courseInstructorEmail = editCourseInstructorEmail.getText().toString();;

        CoursesEntity newCourse = new CoursesEntity(++id, courseName, termID, courseStartDate,
                courseEndDate, courseStatusEntity, courseInstructorName, courseInstructorPhone, courseInstructorEmail );
        repository.insert(newCourse);
        Intent intent = new Intent( Add_New_Course.this, List_Terms.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
