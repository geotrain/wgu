package android.reserver.C868_greg_westmoreland.All.UI.Terms;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.DAO.TermsDao;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Main.Main_Activity_Home_Page;
import android.reserver.C868_greg_westmoreland.R;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class List_Terms extends AppCompatActivity implements View.OnClickListener {
    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    // Variables for Search
    private List<TermsEntity> mSearchTerms;
    private RecyclerView recyclerView;


    /**
     * This method loads the get all terms, adapter when the screen loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_terms);
        repository = new SchedulerRepository(getApplication());

        setTitle("Terms List");
        final List_Terms_Adapter termsAdapter = new List_Terms_Adapter(this);
        recyclerView = findViewById(R.id.termsListRecyclerView);
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_terms_list_recylceview, menu);

        // Associate searchable configuration with the SearchView
        SearchView searchView = (SearchView) menu.findItem(R.id.termsSearch).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconified(true);
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Search Terms");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             * onQueryTextSubmit method
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            /**
             * onQueryTextChange
             * @param query
             * @return
             */
            @Override
            public boolean onQueryTextChange(String query) {
                search(query);
                return false;
            }
        });
        return true;
    }

    /**
     * Method used for string search
     * @param query
     */
    public void search(String query) {
        List<TermsEntity> searchResults = repository.getAllTermsSearch(query);
        System.out.println(query);
        if (query != null) {
            mSearchTerms = searchResults;
            final List_Terms_Adapter termsAdapter = new List_Terms_Adapter(this);
            recyclerView.setAdapter(termsAdapter);
        }
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
            case R.id.list_terms_to_home:
                returnToHomePage();
                return true;
            case R.id.list_terms_add_new_term:
                addANewTerm();
                return true;
            case R.id.termsSearch:
                // Write code to search terms list for recycleView
                Toast.makeText(this, "Terms List Search.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.refresh_terms:
                repository = new SchedulerRepository(getApplication());
                List<TermsEntity> allTerms = repository.getAllTerms();
                final List_Terms_Adapter termsAdapter = new List_Terms_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
                recyclerView.setAdapter(termsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termsAdapter.setTerms(allTerms);
                Toast.makeText(this, "Terms List Refreshed.", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method returns to home page and is called from the navigational menu
     */
    private void returnToHomePage() {
        Intent intent = new Intent(List_Terms.this, Main_Activity_Home_Page.class);
        startActivity(intent);
    }

    /**
     * This method navigates to add a new term and is called from the navigational menu
     */
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
     * onClick method
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * onPointerCaptureChanged method
     * @param hasCapture
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}