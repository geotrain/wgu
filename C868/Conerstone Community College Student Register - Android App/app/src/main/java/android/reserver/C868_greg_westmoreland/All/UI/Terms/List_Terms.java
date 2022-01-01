package android.reserver.C868_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.List_Assessments;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Log_In_Page;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;

public class List_Terms extends AppCompatActivity {
    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    /**
     * This method loads the get all terms, adapter when the screen loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This tells which activity screen.xml this file is associated with
        setContentView(R.layout.activity_list_terms);
        // Call Instance from repository and getAllTerms
        repository = new SchedulerRepository(getApplication());
        // this is really just to set up the database if there isn't one on your device yet-otherwise
       // repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);

        final List_Terms_Adapter termsAdapter = new List_Terms_Adapter(this);
        recyclerView.setAdapter(termsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termsAdapter.setTerms(repository.getAllTerms());

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
        getMenuInflater().inflate(R.menu.menu_terms_list_recylceview, menu);
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
            case R.id.home_screen_from_terms_screen:
                returnToHomePage();
            //case R.id.terms_screen_to_add_new_term_screen:
                //addANewTerm();
            case R.id.refresh_terms:
                repository = new SchedulerRepository(getApplication());
                List<TermsEntity> allTerms = repository.getAllTerms();
                final List_Terms_Adapter termsAdapter = new List_Terms_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
                recyclerView.setAdapter(termsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termsAdapter.setTerms(allTerms);
                Toast.makeText(this, "Terms List Refreshed.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnToHomePage() {
        Intent intent = new Intent(List_Terms.this, Main_Activity_Home_Page.class);
        startActivity(intent);
    }

    private void addANewTerm() {
        Intent intent = new Intent(List_Terms.this, Add_New_Term.class);
        startActivity(intent);
    }

    /**
     * This method is used to navigate to the add a new terms screen
     * @param view
     */
    public void addNewTerm(View view) {
        Intent intent = new Intent(List_Terms.this, Add_New_Term.class);
        startActivity(intent);
    }

    /**
     * This method navigates to home screen
     * @param view
     */
    public void returnHome(View view) {
        Intent intent = new Intent(List_Terms.this, Main_Activity_Home_Page.class);
        startActivity(intent);
    }
}