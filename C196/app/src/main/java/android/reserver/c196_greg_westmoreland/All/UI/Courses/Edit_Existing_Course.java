package android.reserver.c196_greg_westmoreland.All.UI.Courses;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Entities.CourseStatusEntity;
import android.reserver.c196_greg_westmoreland.R;

import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    String existingCourseStartDate;
    String existingCourseEndDate;
    String dateFromScreen;
    CourseStatusEntity existingCourseStatus;
    String existingCourseInstructorName;
    String existingCourseInstructorPhone;
    String existingCourseInstructorEmail;

    EditText existingEditCourseName;
    EditText existingEditCourseStartDate;
    EditText existingEditCourseEndDate;
    CourseStatusEntity existingEditCourseStatus;
    EditText editDate;
    EditText existingEditCourseInstructorName;
    EditText existingEditCourseInstructorPhone;
    EditText existingEditCourseInstructorEmail;

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
        Button addCourseBtn = (Button) findViewById(R.id.addAssessment);

        /**
         * Fill in course name, course start date, and course end date
         */

        id = getIntent().getIntExtra("termID", -1);
        if (id == -1) {
            id = Edit_Existing_Course.courseID;
        }
        repository = new SchedulerRepository(getApplication());
        List<CoursesEntity> allCourses = repository.getAllCourses();

        for (CoursesEntity course:allCourses) {
            if (course.getCourseID() == id) {
                currentCourse = course;
            }
        }

        existingEditCourseName = findViewById(R.id.Existing_Course_Name);
        existingEditCourseStartDate = findViewById(R.id.Start_Date);
        existingEditCourseEndDate = findViewById(R.id.End_Date);

        if (currentCourse != null) {
            existingCourseName = currentCourse.getCourseName();
            existingCourseStartDate = currentCourse.getCourseStartDate();
            existingCourseEndDate = currentCourse.getCourseEndDate();
        } else {
            addCourseBtn.setVisibility(View.GONE);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        }

        if(id != -1){
            existingEditCourseName.setText(existingCourseName);
            existingEditCourseStartDate.setText(existingCourseStartDate);
            existingEditCourseEndDate.setText(existingCourseEndDate);
        }

        /**
         * Show associated assessments with an existing course that is being edited
         */
/*
        repository = new SchedulerRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.assessment_recyclerview);
        final CoursesEditExistingCourseAdapter adapter = new CoursesEditExistingCourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AssessmentsEntity> filteredAssessmentEntityList = new ArrayList<>();
        for(AssessmentsEntity assessment: repository.getAllAssessments()){
            if (assessment.getCourseID() == id) {
                filteredAssessmentEntityList.add(assessment);
            }
        }
        numAssessments = filteredAssessmentEntityList.size();
        adapter.setAssessments(filteredAssessmentEntityList);
        adapter.setAssessments(repository.getAllAssessments());

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
                Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar_termedit), "Assessment deleted",
                        Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView); */

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
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send");
                // Here you will be setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Send message title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify:
                dateFromScreen=editDate.getText().toString();
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate=null;
                try {
                    myDate=sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(Edit_Existing_Course.this, My_Receiver.class);
                intent.putExtra("key","message I want to see"); // <-- CHANGE THIS TO SEND COURSE ID, START, END DATES, ASSESSMENTS GOING FOR IT
                PendingIntent sender=PendingIntent.getBroadcast(Edit_Existing_Course.this,
                        ++Main_Activity_Home_Page.numAlert,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.delete:
                for (TermsEntity p:repository.getAllTerms()) {
                    //if (p.getTermID() == getIntent().getIntExtra("termId", -1))
                      //  currentCourse = p;
                }
                // Variable false
                boolean dontDelete = false;
                if (false) {
                    repository.delete(currentCourse);
                } else {
                    Toast.makeText(Edit_Existing_Course.this, "Can't delete a course " +
                            "that has assessments associated with it", Toast.LENGTH_LONG).show();
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

    public void saveCourse(View view) {
        String courseName = existingEditCourseName.getText().toString();
        String courseStartDate = existingEditCourseStartDate.getText().toString();
        String courseEndDate = existingEditCourseEndDate.getText().toString();
        String courseStatus = existingEditCourseStatus.getClass().toString();
        String courseInstructorName = existingEditCourseInstructorName.getText().toString();
        String courseInstructorPhone = existingEditCourseInstructorPhone.getText().toString();
        String courseInstructorEmail = existingEditCourseInstructorEmail.getText().toString();

        //CoursesEntity newCourse = new CoursesEntity(++id, courseName, courseStartDate, courseEndDate,
                //courseStatus, courseInstructorName, courseInstructorPhone, courseInstructorEmail);
        //repository.update(newCourse);
        Intent intent = new Intent( Edit_Existing_Course.this, List_Courses.class);
        startActivity(intent);
    }

    /**
     * This method is used for the date picker
     * @param view
     */

    public void addAssessmentToCourse(View view) {
        // Associate Course To Term
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