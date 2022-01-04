package android.reserver.C868_greg_westmoreland.All.UI.Main;

/**
 * Import statements
 */
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.Assessments_Adapter;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.List_Assessments;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class Main_Activity_Reports_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    // Create array of strings and store the name of each report in the array
    String[] reports ={"Terms Report", "Courses Report", "Assessments Report", "Instructor Report"};

    /**
     * This method lists the items when the page loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reports_page);

        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Take instance of Spinner and apply OnItemsSelectedListener on it which will tell which item of spinner is clicked
        Spinner generateReport = (Spinner) findViewById(R.id.reportSpinner);
        generateReport.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter having the list of reports to generate
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, reports);

        // Set the simple layout resource file for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        generateReport.setAdapter(ad);
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
                Intent intent = new Intent(Main_Activity_Reports_Page.this, Main_Activity_Home_Page.class);
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
        Intent intent = new Intent(Main_Activity_Reports_Page.this, Main_Activity_Home_Page.class);
        startActivity(intent);
    }

    /**
     * On Item Selected for Reports Spinner
     * @param spinner
     * @param arg1
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> spinner, View arg1, int position, long id) {
        // Write code to generate report selected from reports spinner
        if (R.id.reportSpinner == 0) {
            // make toast of name of report that is selected in the reports spinner
            Toast.makeText(getApplicationContext(), "You selected " + reports[position], Toast.LENGTH_LONG).show();
        } else if (R.id.reportSpinner == 1) {
            // make toast of name of report that is selected in the reports spinner
            Toast.makeText(getApplicationContext(), "You selected " + reports[position], Toast.LENGTH_LONG).show();
        } else if (R.id.reportSpinner == 2) {
            // make toast of name of report that is selected in the reports spinner
            Toast.makeText(getApplicationContext(), "You selected " + reports[position], Toast.LENGTH_LONG).show();
        } else if ((R.id.reportSpinner == 3)) {
            // make toast of name of report that is selected in the reports spinner
            Toast.makeText(getApplicationContext(), "You selected " + reports[position], Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
