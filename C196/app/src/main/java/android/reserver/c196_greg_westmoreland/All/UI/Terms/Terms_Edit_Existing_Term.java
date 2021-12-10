package android.reserver.c196_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Assessments.Assessments_Edit_Existing_Assessment;
import android.reserver.c196_greg_westmoreland.All.UI.Courses.Courses_Edit_Existing_Course;
import android.reserver.c196_greg_westmoreland.All.UI.Main.MainActivity_Home;
import android.reserver.c196_greg_westmoreland.All.UI.MyReceiver;
import android.reserver.c196_greg_westmoreland.All.UI.Utilities.DatePickerFragment;
import android.reserver.c196_greg_westmoreland.R;
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

public class Terms_Edit_Existing_Term extends AppCompatActivity {

    /**
     * Declaration of variables used int he terms list details screen
     */
    public static int termID = -1;

    int id;
    String existingTermName;
    String existingTermStartDate;
    String existingTermEndDate;
    String dateFromScreen;

    EditText existingEditTermName;
    EditText existingEditTermStartDate;
    EditText existingEditTermEndDate;
    EditText editDate;

    EditText courseName;
    EditText courseStartDate;
    EditText courseEndDate;
    EditText courseStatus;
    EditText courseInstructorName;
    EditText courseInstructorPhone;
    EditText courseInstructorEmail;

    private SchedulerRepository repository;
    TermsEntity currentTerm;
    public static int numCourses;

    /**
     * This method is the actions taken when the terms list details page is loaded
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_edit_existing_term);
        Button addCourseBtn = (Button) findViewById(R.id.addCourse);

        /**
         * Fill in existing term name, existing term start date, and existing term end date
         */
        id = getIntent().getIntExtra("termID", -1);
        if (id == -1) {
            id = Terms_Edit_Existing_Term.termID;
        }
        repository = new SchedulerRepository(getApplication());
        List<TermsEntity> allTerms = repository.getAllTerms();

        for (TermsEntity term : allTerms) {
            if (term.getTermID() == id) {
                currentTerm = term;
            }
        }

        existingEditTermName = findViewById(R.id.Existing_Term_Name);
        existingEditTermStartDate = findViewById(R.id.Start_Date);
        existingEditTermEndDate = findViewById(R.id.End_Date);

        if (currentTerm != null) {
            existingTermName = currentTerm.getTermName();
            existingTermStartDate = currentTerm.getTermStartDate();
            existingTermEndDate = currentTerm.getTermEndDate();
        } else {
            addCourseBtn.setVisibility(View.GONE);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        }

        if (id != -1) {
            existingEditTermName.setText(existingTermName);
            existingEditTermStartDate.setText(existingTermStartDate);
            existingEditTermEndDate.setText(existingTermEndDate);
        }

        /**
         * Show associated courses with an existing term that is being edited
         */

        RecyclerView recyclerView = findViewById(R.id.course_recyclerview);
        final TermsEditExistingTermAdapter adapter = new TermsEditExistingTermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new SchedulerRepository(getApplication());
        List<CoursesEntity> filteredCourseEntityList = new ArrayList<>();

        for (CoursesEntity course : repository.getAllCourses()) {
            if (course.getTermID() == id) {
                filteredCourseEntityList.add(course);
            }
        }

        numCourses = filteredCourseEntityList.size();
        adapter.setCourses(filteredCourseEntityList);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //int myId = adapter.getCourseAt(viewHolder.getLayoutPosition()).getCourseID();
                String myName = adapter.getCourseAt(viewHolder.getLayoutPosition()).getCourseName();
                repository.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                adapter.mCourses.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar_termedit),
                        "Associated course " + myName + " was deleted.", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }).attachToRecyclerView(recyclerView);

        if (getIntent().getBooleanExtra("courseSaved", false))
            Toast.makeText(this, "Associated course added to term.", Toast.LENGTH_LONG).show();

        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * This method is used when options are selected on the screen such as share, notify, or delete
     *
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
                dateFromScreen = existingEditTermStartDate.getText().toString();
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(Terms_Edit_Existing_Term.this, MyReceiver.class);
                intent.putExtra("key", "message I want to see"); // <-- CHANGE THIS TO SEND COURSE ID, START, END DATES, ASSESSMENTS GOING FOR IT
                PendingIntent sender = PendingIntent.getBroadcast(Terms_Edit_Existing_Term.this,
                        ++MainActivity_Home.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.delete:
                for (TermsEntity p : repository.getAllTerms()) {
                    if (p.getTermID() == getIntent().getIntExtra("termId", -1))
                        currentTerm = p;
                }
                // Variable false
                boolean dontDelete = false;
                if (false) {
                    repository.delete(currentTerm);
                } else {
                    Toast.makeText(Terms_Edit_Existing_Term.this, "Can't delete a term " +
                            "that has courses associated with it", Toast.LENGTH_LONG).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method inflates the menu and adds items to the action bar
     *
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_terms_list, menu);
        return true;
    }

    /**
     * This method will display a date picker to choose from when selecting dates
     *
     * @param view
     */
    public void showDatePicker(View view) {
        int viewID = view.getId();
        TextView datePickerView = findViewById(viewID);
        DialogFragment newFragment = new DatePickerFragment(datePickerView);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * This method takes existing data from the term and saves the changes to the database
     * @param view
     */
    public void saveTerm(View view) {
        String termName = existingEditTermName.getText().toString();
        String termStartDate = existingEditTermStartDate.getText().toString();
        String termEndDate = existingEditTermEndDate.getText().toString();

        TermsEntity newTerm = new TermsEntity(id, termName, termStartDate, termEndDate);
        repository.update(newTerm);
        Intent intent = new Intent(Terms_Edit_Existing_Term.this, Terms_List.class);
        startActivity(intent);
    }

    /**
     * This method adds a new course to an existing term from the edit terms page
     * @param view
     */
    public void addCourseToTerm(View view) {
        // Navigate to Courses_Edit_Existing_Course class
        Intent intent = new Intent(Terms_Edit_Existing_Term.this, Courses_Edit_Existing_Course.class);
        intent.putExtra("termID", id);
        Assessments_Edit_Existing_Assessment.courseIdAssessmentEditPage = -1;
        startActivity(intent);
    }
}