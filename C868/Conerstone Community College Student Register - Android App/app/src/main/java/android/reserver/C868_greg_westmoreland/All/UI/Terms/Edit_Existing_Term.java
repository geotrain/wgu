package android.reserver.C868_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.CoursesEntity;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.Add_New_Course;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Log_In_Page;
import android.reserver.C868_greg_westmoreland.All.UI.My_Receiver;
import android.reserver.C868_greg_westmoreland.All.UI.Utilities.Date_Picker_Fragment;
import android.reserver.C868_greg_westmoreland.R;
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
import java.util.concurrent.TimeUnit;

public class Edit_Existing_Term extends AppCompatActivity {

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
        setContentView(R.layout.activity_edit_existing_term);
        Button addCourseBtn = (Button) findViewById(R.id.addCourse);

        /**
         * Fill in existing term name, existing term start date, and existing term end date
         */
        id = getIntent().getIntExtra("termID", -1);
        if (id == -1) {
            id = Edit_Existing_Term.termID;
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
        final Edit_Existing_Term_Adapter adapter = new Edit_Existing_Term_Adapter(this);
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
            /**
             * This method holds the recycle view when moved
             * @param recyclerView
             * @param viewHolder
             * @param target
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            /**
             * This method swipes to delete a term
             * @param viewHolder
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                String myName = adapter.getCourseAt(viewHolder.getLayoutPosition()).getCourseName();
                repository.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                adapter.mCourses.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Snackbar snackbar = Snackbar.make(findViewById(R.id.snackbar_termedit),
                         myName + " course was deleted from " + existingTermName + ".", Snackbar.LENGTH_LONG);
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
            case R.id.home_screen_from_edit_term_screen:
                returnToHome();
                return true;
            case R.id.add_new_course:
                addCourseToExistingTerm();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, existingTermName + " begins " + existingTermStartDate +
                        " and ends on " + existingTermEndDate);
                // Here you will be setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Share Information about " + existingTermName);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifyStartDate:
                dateFromScreen = existingEditTermStartDate.getText().toString();
                String myStartFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf_start = new SimpleDateFormat(myStartFormat, Locale.US);
                Date myStartDate = null;
                try {
                    myStartDate = sdf_start.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long triggerStart = myStartDate.getTime();
                Intent intentStart = new Intent(Edit_Existing_Term.this, My_Receiver.class);
                intentStart.putExtra("key", existingTermName + " begins on " + existingTermStartDate);
                PendingIntent senderStart = PendingIntent.getBroadcast(Edit_Existing_Term.this,
                        ++Main_Activity_Log_In_Page.numAlert, intentStart, 0);
                AlarmManager alarmManagerStart = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManagerStart.set(AlarmManager.RTC_WAKEUP, triggerStart, senderStart);
                return true;
            case R.id.notifyEndDate:
                dateFromScreen = existingEditTermEndDate.getText().toString();
                String myEndFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf_end = new SimpleDateFormat(myEndFormat, Locale.US);
                Date myEndDate = null;
                try {
                    myEndDate = sdf_end.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long triggerEnd = myEndDate.getTime();
                Intent intentEnd = new Intent(Edit_Existing_Term.this, My_Receiver.class);
                intentEnd.putExtra("key", existingTermName + " ends on " + existingTermEndDate);
                PendingIntent senderEnd = PendingIntent.getBroadcast(Edit_Existing_Term.this,
                        ++Main_Activity_Log_In_Page.numAlert, intentEnd, 0);
                AlarmManager alarmManagerEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManagerEnd.set(AlarmManager.RTC_WAKEUP, triggerEnd, senderEnd);
                return true;
            case R.id.delete:
                for (TermsEntity t : repository.getAllTerms()) {
                    if (t.getTermID() == getIntent().getIntExtra("termId", -1))
                        currentTerm = t;
                }
                // Variable false
                //boolean dontDelete = false;
                int id = item.getItemId();
                if (id == R.id.delete) {
                    if (numCourses == 0) {
                        AlertDialog alertDialog = new AlertDialog.Builder(this)
                                .setTitle("Alert")
                                .setMessage("Are you sure you want to delete the term?")
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        deleteExistingTerm();
                                        return;
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .create();
                        alertDialog.setCancelable(true);
                        alertDialog.show();
                    } else {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        intentStart = new Intent(Edit_Existing_Term.this, List_Terms.class);
                        startActivity(intentStart);
                        Toast.makeText(Edit_Existing_Term.this, "You cannot delete a term " +
                                "that has courses associated with it", Toast.LENGTH_LONG).show();
                    }
                }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is used for deleting a term
     */
    private void deleteExistingTerm() {
        repository.delete(currentTerm);
        Intent intentStart = new Intent(Edit_Existing_Term.this, List_Terms.class);
        startActivity(intentStart);
        Toast.makeText(Edit_Existing_Term.this, "Term has been successfully " +
                "deleted.", Toast.LENGTH_LONG).show();
    }

    /**
     * This method is used to add a course to the term
     */
    private void addCourseToExistingTerm() {
        // Navigate to Courses_Add_New_Course class
        Intent intent = new Intent(Edit_Existing_Term.this, Add_New_Course.class);
        intent.putExtra("termID", id);
        startActivity(intent);
    }

    /**
     * This method is used to navigate back to the home page
     */
    private void returnToHome() {
        Intent intent = new Intent(Edit_Existing_Term.this, Main_Activity_Home_Page.class);
        startActivity(intent);
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
        DialogFragment newFragment = new Date_Picker_Fragment(datePickerView);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * This method takes existing data from the term and saves the changes to the database
     * @param view
     */
    public void saveTerm(View view) throws ParseException {

        String termName = existingEditTermName.getText().toString();
        String termStartDate = existingEditTermStartDate.getText().toString();
        String termEndDate = existingEditTermEndDate.getText().toString();

        try {
            String startDateFromScreen = existingEditTermStartDate.getText().toString();
            String endDateFromScreen = existingEditTermEndDate.getText().toString();

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
        if (existingEditTermName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a term name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (existingEditTermStartDate.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please supply a start date before saving.", Toast.LENGTH_LONG).show();
                return;
        } else if (existingEditTermEndDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an end date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else {
            TermsEntity newTerm = new TermsEntity(id, termName, termStartDate, termEndDate);
            repository.update(newTerm);
            Intent intent = new Intent(Edit_Existing_Term.this, List_Terms.class);
            startActivity(intent);
        }
    }

    /**
     * This method adds a new course to an existing term from the edit terms page
     * @param view
     */
    public void addCourseToTerm(View view) {
        // Navigate to Courses_Add_New_Course class
        Intent intent = new Intent(Edit_Existing_Term.this, Add_New_Course.class);
        intent.putExtra("termID", id);
        startActivity(intent);
    }

    /**
     * This method returns to the Terms List screen used for navigation
     * @param view
     */
    public void seeAllTerms(View view) {
        Intent intent = new Intent(Edit_Existing_Term.this, List_Terms.class);
        startActivity(intent);
    }
}