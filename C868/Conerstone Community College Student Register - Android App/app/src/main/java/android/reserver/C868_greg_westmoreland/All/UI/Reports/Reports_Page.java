package android.reserver.C868_greg_westmoreland.All.UI.Reports;

/**
 * Import statements
 */
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.List_Terms_Adapter;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class Reports_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    // Create array of strings and store the name of each report in the array
    String[] reports ={"Select Report", "Terms Report", "Courses Report", "Assessments Report", "Instructor Report"};

    /**
     * This method lists the items when the page loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_page);

        // Call Instance from repository and getAllTerms
        repository = new SchedulerRepository(getApplication());

        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Take instance of Spinner and apply OnItemsSelectedListener on it which will tell which item of spinner is clicked
        Spinner generateReport = (Spinner) findViewById(R.id.reportSpinner);
        generateReport.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter having the list of reports to generate
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.report_choice, android.R.layout.simple_spinner_item);

        // Set the simple layout resource file for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        generateReport.setAdapter(ad);

        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * This method inflates the menu and adds items to the action bar
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reports_list, menu);
        return true;
    }

    /**
     * This method is called when the backward arrow button for navigation is used. It is also used
     * when the users refreshes the screen
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.home_screen:
                Intent intent = new Intent(Reports_Page.this, Main_Activity_Home_Page.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method navigates to home screen
     * @param view
     */
    public void returnToTheHome(View view) {
        Intent intent = new Intent(Reports_Page.this, Main_Activity_Home_Page.class);
        startActivity(intent);
    }

    /**
     * On Item Selected for Reports Spinner
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Print out position string names from strings.xml based on position in the array
        System.out.println(parent.getItemAtPosition(0));
        System.out.println(parent.getItemAtPosition(1));
        System.out.println(parent.getItemAtPosition(2));
        System.out.println(parent.getItemAtPosition(3));
        System.out.println(parent.getItemAtPosition(4));

        // Set variables from the result of selection
        String spinnerResult = reports[position];
        System.out.println("The spinner selection is " + spinnerResult);

        if (spinnerResult.equals("Terms Report")) {
            System.out.println(parent.getItemAtPosition(1));
            Toast.makeText(getApplicationContext(), reports[position] + " was selected.", Toast.LENGTH_LONG).show();
            RecyclerView recyclerView = findViewById(R.id.reportsTermRecyclerView);
            final Reports_Terms_Adapter reportsAdapter = new Reports_Terms_Adapter(this);
            recyclerView.setAdapter(reportsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reportsAdapter.setTerms(repository.getAllTermsReport());

        } else if (spinnerResult.equals("Courses Report")) {
            System.out.println(parent.getItemAtPosition(2));
            Toast.makeText(getApplicationContext(), reports[position] + " was selected.", Toast.LENGTH_LONG).show();
            RecyclerView recyclerView = findViewById(R.id.reportsCourseRecyclerView);
            final Reports_Courses_Adapter reportsAdapter = new Reports_Courses_Adapter(this);
            recyclerView.setAdapter(reportsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reportsAdapter.setCourses(repository.getAllCoursesReport());

        } else if (spinnerResult.equals("Assessments Report")) {
            System.out.println(parent.getItemAtPosition(3));
            Toast.makeText(getApplicationContext(), reports[position] + " was selected.", Toast.LENGTH_LONG).show();
            RecyclerView recyclerView = findViewById(R.id.reportsAssessmentsRecyclerView);
            final Reports_Assessments_Adapter reportsAdapter = new Reports_Assessments_Adapter(this);
            recyclerView.setAdapter(reportsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reportsAdapter.setAssessments(repository.getAllAssessmentsReport());

        } else if (spinnerResult.equals("Instructor Report")) {
            System.out.println(parent.getItemAtPosition(4));
            Toast.makeText(getApplicationContext(), reports[position] + " was selected.", Toast.LENGTH_LONG).show();
            RecyclerView recyclerView = findViewById(R.id.reportsInstructorRecyclerView);
            final Reports_Instructors_Adapter reportsAdapter = new Reports_Instructors_Adapter(this);
            recyclerView.setAdapter(reportsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reportsAdapter.setInstructors(repository.getAllInstructorsReport());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "Nothing selected.", Toast.LENGTH_SHORT).show();
    }
}
