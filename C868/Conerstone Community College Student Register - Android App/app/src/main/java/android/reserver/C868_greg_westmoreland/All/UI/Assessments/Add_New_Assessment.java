package android.reserver.C868_greg_westmoreland.All.UI.Assessments;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.Add_New_Course;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.Edit_Existing_Course;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.List_Courses;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.List_Terms;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Add_New_Assessment extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    /**
     * Declaration of variables used int the terms list details screen
     */
    int id;
    String assessmentName;
    int courseID;
    static int id3;
    String assessmentType;
    String assessmentStartDate;
    String assessmentEndDate;

    EditText editCourseId;
    EditText editAssessmentName;
    EditText editAssessmentType;
    EditText editAssessmentStartDate;
    EditText editAssessmentEndDate;

    SchedulerRepository repository;
    AssessmentsEntity currentAssessment;

    /**
     * This method is the actions taken when the assessments list details page is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_assessment);

        id = getIntent().getIntExtra("assessmentID",-1);
        courseID = getIntent().getIntExtra("courseID", -1);
        assessmentName = getIntent().getStringExtra("Assessment_Name");
        assessmentType = getIntent().getStringExtra("Assessment_Type");
        assessmentStartDate = getIntent().getStringExtra("Start_Date");
        assessmentEndDate = getIntent().getStringExtra("End_Date");
        id3 = courseID;

        repository = new SchedulerRepository(getApplication());
        List<AssessmentsEntity> allAssessments = repository.getAllAssessments();

        id = allAssessments.get(allAssessments.size()-1).getAssessmentID(); // This keeps id from being -1
        editAssessmentName = findViewById(R.id.Assessment_Name);
        editAssessmentType = findViewById(R.id.Assessment_Type);
        editAssessmentStartDate = findViewById(R.id.Start_Date);
        editAssessmentEndDate = findViewById(R.id.End_Date);

        /**
        if (id != -1) {
            editAssessmentName.setText(assessmentName);
            editAssessmentStartDate.setText(assessmentStartDate);
            editAssessmentEndDate.setText(assessmentEndDate);
            editAssessmentType.setText((CharSequence) assessmentType);
        }*/

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
            case R.id.home_screen_from_add_new_assessment_screen:
                Intent intent = new Intent( Add_New_Assessment.this, Main_Activity_Home_Page.class);
                startActivity(intent);
            case R.id.assessments_screen_from_add_new_assessment_screen:
                Intent intent2 = new Intent( Add_New_Assessment.this, List_Assessments.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method inflates the menu and adds items to the action bar
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new_assessment, menu);
        return true;
    }

    /**
     * This method takes the input data and saves it as a new term
     * @param view
     */
    public void saveAssessment(View view) throws ParseException {
        String assessmentName = editAssessmentName.getText().toString();
        String assessmentType = editAssessmentType.getText().toString();
        String assessmentStartDate = editAssessmentStartDate.getText().toString();
        String assessmentEndDate = editAssessmentEndDate.getText().toString();

        try {
            String startDateFromScreen = editAssessmentStartDate.getText().toString();
            String endDateFromScreen = editAssessmentEndDate.getText().toString();

            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            Date start = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(startDateFromScreen);
            Date end = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(endDateFromScreen);

            // Check if Term End Date is before Term Start Date
            if (sdf.parse(endDateFromScreen).before(sdf.parse(startDateFromScreen))) {
                Toast.makeText(this, "The end date cannot be before the start date.", Toast.LENGTH_LONG).show();
                return;
            } else if (sdf.parse(startDateFromScreen).equals(sdf.parse(endDateFromScreen))) {
                Toast.makeText(this, "The start date and end date cannot the same date.", Toast.LENGTH_LONG).show();
                return;
            } else if (start.compareTo(end) > 31) {
            Toast.makeText(this, "The start and end dates must be 30 days or less.",
                    Toast.LENGTH_LONG).show();
            return;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Check if term name, term start date, or term end date fields are empty
        if (editAssessmentName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a assessment name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editAssessmentStartDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a start date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editAssessmentEndDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an end date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editAssessmentType.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an assessment type before saving.", Toast.LENGTH_LONG).show();
            return;
        } else {
            AssessmentsEntity newAssessment = new AssessmentsEntity(++id, assessmentName, courseID, assessmentType,
                    assessmentStartDate, assessmentEndDate);
            repository.insert(newAssessment);
            Intent intent = new Intent( Add_New_Assessment.this, Edit_Existing_Course.class);
            intent.putExtra("courseID", courseID);
            startActivity(intent);
        }
    }

    /**
     * This method takes action when an item is selected on the screen
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * This method is used when nothing is selected when the screen loads
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
