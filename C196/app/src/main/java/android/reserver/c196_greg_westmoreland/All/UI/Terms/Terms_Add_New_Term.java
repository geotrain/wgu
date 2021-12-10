package android.reserver.c196_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Utilities.DatePickerFragment;
import android.reserver.c196_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class Terms_Add_New_Term extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        setContentView(R.layout.activity_terms_add_new_term);
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
            case R.id.refresh:
                repository = new SchedulerRepository(getApplication());
                List<TermsEntity> allTerms = repository.getAllTerms();
                final TermsListAdapter termsAdapter = new TermsListAdapter(this);
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
    public void saveTerm(View view) {
        String termName = editTermName.getText().toString();
        String termStartDate = editTermStartDate.getText().toString();
        String termEndDate = editTermEndDate.getText().toString();


        TermsEntity newTerm = new TermsEntity(++id, termName, termStartDate, termEndDate);
        repository.insert(newTerm);
        Intent intent = new Intent( Terms_Add_New_Term.this, Terms_List.class);
        startActivity(intent);

    }

    /**
     * This method will display a date picker to choose from when selecting dates
     * @param view
     */
    public void showDatePicker(View view) {
        int viewID = view.getId();
        TextView datePickerView = findViewById(viewID);
        DialogFragment newFragment = new DatePickerFragment(datePickerView);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}