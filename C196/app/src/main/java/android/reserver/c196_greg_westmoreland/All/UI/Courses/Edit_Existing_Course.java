package android.reserver.c196_greg_westmoreland.All.UI.Courses;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Assessments.Add_New_Assessment;
import android.reserver.c196_greg_westmoreland.R;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.c196_greg_westmoreland.All.UI.My_Receiver;
import android.reserver.c196_greg_westmoreland.All.UI.Utilities.Date_Picker_Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Edit_Existing_Course extends AppCompatActivity {

    /**
     * Declaration of variables used int he terms list details screen
     */
    public static int courseID = -1;

    int id;
    String existingCourseName;
    int termID;
    String existingCourseStartDate;
    String existingCourseEndDate;
    String dateFromScreen;
    String existingCourseStatus;
    String existingCourseInstructorName;
    String existingCourseInstructorPhone;
    String existingCourseInstructorEmail;
    String existingOptionalCourseNote;

    EditText editExistingCourseName;
    EditText editExistingCourseStartDate;
    EditText editExistingCourseEndDate;
    EditText editExistingCourseStatus;
    EditText editDate;
    EditText editExistingCourseInstructorName;
    EditText editExistingCourseInstructorPhone;
    EditText editExistingCourseInstructorEmail;
    EditText editExistingOptionalCourseNote;

    private SchedulerRepository repository;
    CoursesEntity currentCourse;
    public static int numAssessments;

    /**
     * This method is the actions taken when the terms list details page is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_existing_course);
        Button addAssessmentBtn = (Button) findViewById(R.id.addAssessment);

        /**
         * Fill in existing course name, assessment ID, start date, end date, course status, course instructor (name, phone, email)
         */
        id = getIntent().getIntExtra("courseID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);
        if (id == -1) {
            id = Edit_Existing_Course.courseID;
            id = courseID;
        }
        repository = new SchedulerRepository(getApplication());
        List<CoursesEntity> allCourses = repository.getAllCourses();

        for (CoursesEntity course : allCourses) {
            if (course.getCourseID() == id) {
                currentCourse = course;
            }
        }

        editExistingCourseName = findViewById(R.id.Existing_Course_Name);
        editExistingCourseStartDate = findViewById(R.id.Existing_Course_Start_Date);
        editExistingCourseEndDate = findViewById(R.id.Existing_Course_End_Date);
        editExistingCourseStatus = findViewById(R.id.Existing_Course_Status);
        editExistingCourseInstructorName = findViewById(R.id.Existing_Course_Instructor_Name);
        editExistingCourseInstructorPhone = findViewById(R.id.Existing_Course_Instructor_Phone);
        editExistingCourseInstructorEmail = findViewById(R.id.Existing_Course_Instructor_Email);
        editExistingOptionalCourseNote = findViewById(R.id.Existing_Optional_Course_Note);

        if (currentCourse != null) {
            existingCourseName = currentCourse.getCourseName();
            existingCourseStartDate = currentCourse.getCourseStartDate();
            existingCourseEndDate = currentCourse.getCourseEndDate();
            existingCourseStatus = currentCourse.getCourseStatus();
            existingCourseInstructorName = currentCourse.getCourseInstructorName();
            existingCourseInstructorPhone = currentCourse.getCourseInstructorPhone();
            existingCourseInstructorEmail = currentCourse.getCourseInstructorEmail();
            existingOptionalCourseNote = currentCourse.getOptionalCourseNote();
        } else {
            addAssessmentBtn.setVisibility(View.GONE);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        }

        if (id != -1) {
            editExistingCourseName.setText(existingCourseName);
            editExistingCourseStartDate.setText(existingCourseStartDate);
            editExistingCourseEndDate.setText(existingCourseEndDate);
            editExistingCourseStatus.setText(existingCourseStatus);
            editExistingCourseInstructorName.setText(existingCourseInstructorName);
            editExistingCourseInstructorPhone.setText(existingCourseInstructorPhone);
            editExistingCourseInstructorEmail.setText(existingCourseInstructorEmail);
            editExistingOptionalCourseNote.setText(existingOptionalCourseNote);
        }

        /**
         * Show associated assessments with an existing course that is being edited
         */
        RecyclerView recyclerView = findViewById(R.id.assessment_recyclerview);
        //RecyclerView recyclerView = findViewById(R.id.assessmentsListRecyclerView);
        final Edit_Existing_Course_Adapter adapter = new Edit_Existing_Course_Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new SchedulerRepository(getApplication());
        List<AssessmentsEntity> filteredAssessmentEntityList = new ArrayList<>();

        for(AssessmentsEntity assessment: repository.getAllAssessments()){
            if (assessment.getCourseID() == id) {
                filteredAssessmentEntityList.add(assessment);
            }
        }
        numAssessments = filteredAssessmentEntityList.size();
        adapter.setAssessments(filteredAssessmentEntityList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                repository.delete(adapter.getAssessmentAt(viewHolder.getAdapterPosition()));
                adapter.mAssessments.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar_course_edit), "Assessment was deleted from "
                        + existingCourseName + ".", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView);

        if (getIntent().getBooleanExtra("assessmentSaved", false))
            Toast.makeText(this,"Assessment Saved",Toast.LENGTH_LONG).show();

        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * This method is used when options are selected on the screen such as share, notify, or delete
     * @param item
     * @return
     */
    @Override // This method is called when the backward arrow -> navigation icon is selected
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, existingCourseName + " begins " + existingCourseStartDate +
                        " and ends on " + existingCourseEndDate);
                // Here you will be setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Share Information about " + existingCourseName);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify:
                dateFromScreen = editExistingCourseStartDate.getText().toString();
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(Edit_Existing_Course.this, My_Receiver.class);
                intent.putExtra("key", existingCourseName + " begins " + existingCourseStartDate +
                        " and ends on " + existingCourseEndDate);
                PendingIntent sender=PendingIntent.getBroadcast(Edit_Existing_Course.this,
                        ++Main_Activity_Home_Page.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.delete:
                for (CoursesEntity c : repository.getAllCourses()) {
                    if (c.getCourseID() == getIntent().getIntExtra("courseID", -1))
                        currentCourse = c;
                }
                // Variable false
                // boolean dontDelete = false;
                int id = item.getItemId();

                if (id == R.id.delete) {
                    if (numAssessments == 0) {
                        repository.delete(currentCourse);
                        intent = new Intent(Edit_Existing_Course.this, List_Courses.class);
                        startActivity(intent);
                        Toast.makeText(Edit_Existing_Course.this, "Course has been successfully " +
                                "deleted.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Edit_Existing_Course.this, "You cannot delete a course " +
                                "that has assessments associated with it", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method inflates the menu and adds items to the action bar
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_terms_list, menu);
        return true;
    }

    /**
     * This method takes the input data and saves it as a new term
     * @param view
     */
    public void saveCourse(View view) throws ParseException {
        String courseName = editExistingCourseName.getText().toString();
        String courseStartDate = editExistingCourseStartDate.getText().toString();
        String courseEndDate = editExistingCourseEndDate.getText().toString();
        String courseStatus = editExistingCourseStatus.getClass().toString();
        String courseInstructorName = editExistingCourseInstructorName.getText().toString();
        String courseInstructorPhone = editExistingCourseInstructorPhone.getText().toString();
        String courseInstructorEmail = editExistingCourseInstructorPhone.getText().toString();
        String optionalCourseNote = editExistingOptionalCourseNote.getText().toString();

        String startDateFromScreen = editExistingCourseStartDate.getText().toString();
        String endDateFromScreen = editExistingCourseEndDate.getText().toString();
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        // Check if Term End Date is before Term Start Date
        if (sdf.parse(endDateFromScreen).before(sdf.parse(startDateFromScreen))) {
            Toast.makeText(this, "The end date cannot be before the start date.", Toast.LENGTH_LONG).show();
            return;
        }

        // Check if term name, term start date, or term end date fields are empty
        if (editExistingCourseName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a term name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingCourseStartDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a start date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingCourseEndDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an end date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingCourseStatus.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course status before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingCourseInstructorName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course instructor name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingCourseInstructorPhone.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course instructor phone before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingCourseInstructorEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a course instructor email before saving.", Toast.LENGTH_LONG).show();
            return;
        } else {
            CoursesEntity newCourse = new CoursesEntity(id, courseName, termID, courseStartDate, courseEndDate,
                    courseStatus, courseInstructorName, courseInstructorPhone, courseInstructorEmail, optionalCourseNote);
            repository.update(newCourse);
            Intent intent = new Intent( Edit_Existing_Course.this, List_Courses.class);
            startActivity(intent);
        }
    }

    /**
     * This method is used for the date picker
     * @param view
     */
    public void addAssessmentToCourse(View view) {
        // Navigate to Add_New_Assessment class
        Intent intent = new Intent(Edit_Existing_Course.this, Add_New_Assessment.class);
        intent.putExtra("courseID", id);
        startActivity(intent);
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

    /**
     * This method returns to the Courses List screen used for navigation
     * @param view
     */
    public void seeCourses(View view) {
        Intent intent = new Intent(Edit_Existing_Course.this, List_Courses.class);
        startActivity(intent);
    }
}