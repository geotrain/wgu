package android.reserver.C868_greg_westmoreland.All.UI.Assessments;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.Edit_Existing_Course;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Log_In_Page;
import android.reserver.C868_greg_westmoreland.All.UI.My_Receiver;
import android.reserver.C868_greg_westmoreland.All.UI.Utilities.Date_Picker_Fragment;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Edit_Existing_Assessment extends AppCompatActivity {

    // Declare Variables
    public static int assessmentID = -1;

    int id;
    String existingAssessmentName;
    int courseID;
    String existingAssessmentType;
    String existingAssessmentStartDate;
    String existingAssessmentEndDate;
    String dateFromScreen;

    EditText editExistingAssessmentName;
    EditText editExistingCourseID;
    EditText editExistingAssessmentType;
    EditText editExistingAssessmentStartDate;
    EditText editExistingAssessmentEndDate;
    EditText editDate;

    private SchedulerRepository repository;
    AssessmentsEntity currentAssessment;
    public static int numAssessments;

    /**
     * The detailed assessments view when the screen loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_existing_assessment);

        /**
         * Fill in assessment name, associated courseID, assessment type, assessment start date, and assessment end date
         */
        id = getIntent().getIntExtra("assessmentID", -1);
        if (id == -1) {
            id = Edit_Existing_Assessment.assessmentID;
        }
        repository = new SchedulerRepository(getApplication());
        List<AssessmentsEntity> allAssessments = repository.getAllAssessments();

        for (AssessmentsEntity assessment:allAssessments) {
            if (assessment.getAssessmentID() == id) {
                currentAssessment = assessment;
            }
        }

        editExistingAssessmentName = findViewById(R.id.Existing_Assessment_Name);
        editExistingAssessmentType = findViewById(R.id.Existing_Assessment_Type);
        editExistingAssessmentStartDate = findViewById(R.id.Existing_Assessment_Start_Date);
        editExistingAssessmentEndDate = findViewById(R.id.Existing_Assessment_End_Date);

        if (currentAssessment != null) {
            existingAssessmentName = currentAssessment.getAssessmentName();
            existingAssessmentType = currentAssessment.getAssessmentType();
            existingAssessmentStartDate = currentAssessment.getAssessmentStartDate();
            existingAssessmentEndDate = currentAssessment.getAssessmentEndDate();
        } else {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        }

        if(id != -1){
            editExistingAssessmentName.setText(existingAssessmentName);
            editExistingAssessmentType.setText(existingAssessmentType);
            editExistingAssessmentStartDate.setText(existingAssessmentStartDate);
            editExistingAssessmentEndDate.setText(existingAssessmentEndDate);
        }

        if (getIntent().getBooleanExtra("assessmentSaved", false))
            Toast.makeText(this,"Assessment has been saved",Toast.LENGTH_LONG).show();

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
                sendIntent.putExtra(Intent.EXTRA_TEXT, existingAssessmentName + " begins " + existingAssessmentStartDate +
                        " and ends on " + existingAssessmentEndDate);
                // Here you will be setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Share Information about " + existingAssessmentName);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifyStartDate:
                dateFromScreen = editExistingAssessmentStartDate.getText().toString();
                String myStartFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf_start = new SimpleDateFormat(myStartFormat, Locale.US);
                Date myStartDate = null;
                try {
                    myStartDate=sdf_start.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long triggerStart = myStartDate.getTime();
                Intent intentStart = new Intent(Edit_Existing_Assessment.this, My_Receiver.class);
                intentStart.putExtra("key", existingAssessmentName + " begins on " + existingAssessmentStartDate);
                PendingIntent senderStart=PendingIntent.getBroadcast(Edit_Existing_Assessment.this,
                        ++Main_Activity_Log_In_Page.numAlert,intentStart,0);
                AlarmManager alarmManagerStart=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManagerStart.set(AlarmManager.RTC_WAKEUP, triggerStart, senderStart);
                return true;
            case R.id.notifyEndDate:
                dateFromScreen = editExistingAssessmentEndDate.getText().toString();
                String myEndFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf_end = new SimpleDateFormat(myEndFormat, Locale.US);
                Date myEndDate = null;
                try {
                    myEndDate = sdf_end.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long triggerEnd = myEndDate.getTime();
                Intent intentEnd = new Intent(Edit_Existing_Assessment.this, My_Receiver.class);
                intentEnd.putExtra("key", existingAssessmentName + " ends on " + existingAssessmentEndDate);
                PendingIntent senderEnd = PendingIntent.getBroadcast(Edit_Existing_Assessment.this,
                        ++Main_Activity_Log_In_Page.numAlert, intentEnd, 0);
                AlarmManager alarmManagerEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManagerEnd.set(AlarmManager.RTC_WAKEUP, triggerEnd, senderEnd);
                return true;
            case R.id.delete:
                for (AssessmentsEntity a : repository.getAllAssessments()) {
                    if (a.getAssessmentID() == getIntent().getIntExtra("assessmentId", -1))
                      currentAssessment = a;
                }
                // Variable false
                //boolean dontDelete = false;
                int id = item.getItemId();
                if (id == R.id.delete) {
                    AlertDialog alertDialog = new AlertDialog.Builder(this)
                            .setTitle("Alert")
                            .setMessage("Are you sure you want to delete the assessment?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteExistingAssessment();
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
                    Toast.makeText(Edit_Existing_Assessment.this, "We were unable to " +
                            "delete the assessment.", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.home:
                intentStart = new Intent(Edit_Existing_Assessment.this, Main_Activity_Home_Page.class);
                startActivity(intentStart);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method deletes an assessment when user clicks on delete icon in navigation menu
     */
    public void deleteExistingAssessment() {
        repository.delete(currentAssessment);
        Intent intentStart = new Intent(Edit_Existing_Assessment.this, List_Assessments.class);
        startActivity(intentStart);
        Toast.makeText(Edit_Existing_Assessment.this, "Assessment has been successfully " +
                "deleted.", Toast.LENGTH_LONG).show();
    }

    /**
     * This method inflates the menu and adds items to the action bar
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessments_list, menu);
        return true;
    }

    /**
     * This method takes the input data and saves it as a new term
     * @param view
     */
    public void saveAssessment(View view) throws ParseException {
        int courseID = currentAssessment.getCourseID();
        String assessmentName = editExistingAssessmentName.getText().toString();
        String assessmentTypeEntity = editExistingAssessmentType.getText().toString();
        String assessmentStartDate = editExistingAssessmentStartDate.getText().toString();
        String assessmentEndDate = editExistingAssessmentEndDate.getText().toString();

        try {
            String startDateFromScreen = editExistingAssessmentStartDate.getText().toString();
            String endDateFromScreen = editExistingAssessmentEndDate.getText().toString();

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
        if (editExistingAssessmentName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an assessment name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingAssessmentStartDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a start date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editExistingAssessmentEndDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an end date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else {
            AssessmentsEntity newAssessment = new AssessmentsEntity(id, assessmentName, courseID, assessmentTypeEntity,
                    assessmentStartDate, assessmentEndDate);
            repository.update(newAssessment);
            Toast.makeText(this, "Assessment saved.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent( Edit_Existing_Assessment.this, Edit_Existing_Course.class);
            intent.putExtra("courseID", courseID);
            startActivity(intent);
        }
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
     * This method returns to the Assessments List screen used for navigation
     * @param view
     */
    public void seeAssessments(View view) {
        Intent intent = new Intent(Edit_Existing_Assessment.this, List_Assessments.class);
        startActivity(intent);
    }
}