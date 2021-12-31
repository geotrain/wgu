package android.reserver.C868_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.Add_New_Assessment;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Utilities.Date_Picker_Fragment;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Add_New_Term extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Declaration of variables used int he terms list details screen
     */
    int id;
    String termName;
    String termEndDate;
    String termStartDate;

    EditText editTermName;
    EditText editTermStartDate;
    EditText editTermEndDate;

    SchedulerRepository repository;
    TermsEntity currentTerm;

    //public static int termId = null;

    /**
     * This method is the actions taken when the terms list details page is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_term);
        id = getIntent().getIntExtra("termID",-1);
        termName = getIntent().getStringExtra("Term_Name");
        termStartDate = getIntent().getStringExtra("Start_Date");
        termEndDate = getIntent().getStringExtra("End_Date");

        repository = new SchedulerRepository(getApplication());
        List<TermsEntity> allTerms = repository.getAllTerms();

        id = allTerms.get(allTerms.size()-1).getTermID(); // This keeps id from being -1

        editTermName = findViewById(R.id.Term_Name);
        editTermStartDate = findViewById(R.id.Start_Date);
        editTermEndDate = findViewById(R.id.End_Date);

        // Possible take out
        if (currentTerm != null) {
            termName = currentTerm.getTermName();
            termStartDate = currentTerm.getTermStartDate();
            termEndDate = currentTerm.getTermEndDate();
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
            case R.id.home_screen_from_add_new_term_screen:
                Intent intent = new Intent( Add_New_Term.this, Main_Activity_Home_Page.class);
                startActivity(intent);
            case R.id.terms_screen_from_add_new_term_screen:
                Intent intent2 = new Intent( Add_New_Term.this, List_Terms.class);
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
        getMenuInflater().inflate(R.menu.menu_add_new_term, menu);
        return true;
    }

    /**
     * This method takes the input data and saves it as a new term
     * @param view
     */
    public void saveTerm(View view) throws ParseException {

        String termName = editTermName.getText().toString();
        String termStartDate = editTermStartDate.getText().toString();
        String termEndDate = editTermEndDate.getText().toString();

        String startDateFromScreen = editTermStartDate.getText().toString();
        String endDateFromScreen = editTermEndDate.getText().toString();
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        // Check if Term End Date is before Term Start Date
        if (sdf.parse(endDateFromScreen).before(sdf.parse(startDateFromScreen))) {
            Toast.makeText(this, "The end date cannot be before the start date.", Toast.LENGTH_LONG).show();
            return;
        }

        // Check if term name, term start date, or term end date fields are empty
        if (editTermName == null || editTermName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a term name before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editTermStartDate == null || editTermStartDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a start date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else if (editTermEndDate == null || editTermEndDate.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply an end date before saving.", Toast.LENGTH_LONG).show();
            return;
        } else {
            TermsEntity newTerm = new TermsEntity(++id, termName, termStartDate, termEndDate);
            repository.insert(newTerm);
            Intent intent = new Intent( Add_New_Term.this, List_Terms.class);
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
     * This method has the code that deals when an item is selected on the screen
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * This method has the code when nothing is selected on the screen
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}