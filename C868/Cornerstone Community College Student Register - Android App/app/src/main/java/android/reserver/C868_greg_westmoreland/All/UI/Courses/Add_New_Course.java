package android.reserver.C868_greg_westmoreland.All.UI.Courses;

/**
 * Import statements
 */
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.Edit_Existing_Term;
import android.reserver.C868_greg_westmoreland.All.UI.Utilities.Date_Picker_Fragment;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Add_New_Course extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Declaration of variables used int the terms list details screen
     */
    int id;
    int termID;
    static int id2;
    String courseName;
    String courseStartDate;
    String courseEndDate;
    String courseStatusEntity;
    String courseInstructorName;
    String courseInstructorPhone;
    String courseInstructorEmail;
    String optionalCourseNote;

    EditText editTermId;
    EditText editCourseName;
    EditText editCourseStartDate;
    EditText editCourseEndDate;
    EditText editCourseStatusEntity;
    EditText editCourseInstructorName;
    EditText editCourseInstructorPhone;
    EditText editCourseInstructorEmail;
    EditText editOptionalCourseNote;

    SchedulerRepository repository;
    CoursesEntity currentCourse;

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
        courseStatusEntity = getIntent().getStringExtra("Course_Status");
        courseInstructorName = getIntent().getStringExtra("Course_Instructor_Name");
        courseInstructorPhone = getIntent().getStringExtra("Course_Instructor_Phone");
        courseInstructorEmail = getIntent().getStringExtra("Course_Instructor_Email");
        optionalCourseNote = getIntent().getStringExtra("Optional_Course_Note");
        id2 = termID;

        repository = new SchedulerRepository(getApplication());
        List<CoursesEntity> allCourses = repository.getAllCourses();

        id = allCourses.get(allCourses.size()-1).getCourseID(); // This keeps id from being -1
        editCourseName = findViewById(R.id.Course_Name);
        editCourseStartDate = findViewById(R.id.Start_Date);
        editCourseEndDate = findViewById(R.id.End_Date);
        editCourseStatusEntity = findViewById(R.id.Course_status);
        editCourseInstructorName = findViewById(R.id.Course_Instructor_Name);
        editCourseInstructorPhone = findViewById(R.id.Course_Instructor_Phone);
        editCourseInstructorEmail = findViewById(R.id.Course_Instructor_Email);
        editOptionalCourseNote = findViewById(R.id.Optional_Course_Note);

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
            case R.id.home_screen_from_add_new_term_screen:
                Intent intent = new Intent( Add_New_Course.this, Main_Activity_Home_Page.class);
                startActivity(intent);
                return true;
            case R.id.courses_screen_from_add_new_course_screen:
                Intent intent2 = new Intent( Add_New_Course.this, List_Courses.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method inflates the menu and adds items to the action bar
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new_course, menu);
        return true;
    }

    /**
     * This method takes the input data and saves it as a new term
     * @param view
     */
    public void saveCourse(View view) throws ParseException {
        String courseName = editCourseName.getText().toString();
        String courseStartDate = editCourseStartDate.getText().toString();
        String courseEndDate = editCourseEndDate.getText().toString();
        String courseStatusEntity= editCourseStatusEntity.getText().toString();
        String courseInstructorName = editCourseInstructorName.getText().toString();;
        String courseInstructorPhone = editCourseInstructorPhone.getText().toString();
        String courseInstructorEmail = editCourseInstructorEmail.getText().toString();
        String optionalCourseNote = editOptionalCourseNote.getText().toString();

        try {
            String startDateFromScreen = editCourseStartDate.getText().toString();
            String endDateFromScreen = editCourseEndDate.getText().toString();

            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            Date start = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(startDateFromScreen);
            Date end = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(endDateFromScreen);

            long diff = end.getTime() - start.getTime();

            TimeUnit time = TimeUnit.DAYS;
            long difference = time.convert(diff, TimeUnit.MILLISECONDS);
            System.out.println("The difference in days is : "+ difference);

            // Check if Term End Date is before Term Start Date
            if (sdf.parse(endDateFromScreen).before(sdf.parse(startDateFromScreen))) {
                Toast.makeText(this, "The end date cannot be before the start date.", Toast.LENGTH_LONG).show();
                return;
            } else if (sdf.parse(startDateFromScreen).equals(sdf.parse(endDateFromScreen))) {
                Toast.makeText(this, "The start date and end date cannot the same date.", Toast.LENGTH_LONG).show();
                return;
            } else if (difference >= 31) {
                Toast.makeText(this, "The start and end dates must be 30 days or less.",
                        Toast.LENGTH_LONG).show();
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Check if term name, term start date, or term end date fields are empty
        if (editCourseInstructorName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editCourseStartDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a start date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editCourseEndDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an end date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editCourseInstructorName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course instructor name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editCourseInstructorPhone.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course instructor phone before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editCourseInstructorEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course instructor email before saving.", Toast.LENGTH_LONG).show();
            return;
        } else {
            CoursesEntity newCourse = new CoursesEntity(++id, courseName, termID, courseStartDate,
                    courseEndDate, courseStatusEntity, courseInstructorName, courseInstructorPhone,
                    courseInstructorEmail, optionalCourseNote);
            repository.insert(newCourse);
            Intent intent = new Intent( Add_New_Course.this, Edit_Existing_Term.class);
            intent.putExtra("termID", termID);
            startActivity(intent);
        }
    }

    /**
     * This method deals with the code with items selected on the screen
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * This method deals with the code when nothing is selected on the screen
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * This method will display a date picker to choose from when selecting dates
     * @param view
     */
    public void showDatePicker(View view) {
        int viewID = view.getId();
        TextView datePickerView = findViewById(viewID);
        DialogFragment newFragment = new Date_Picker_Fragment(datePickerView);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
