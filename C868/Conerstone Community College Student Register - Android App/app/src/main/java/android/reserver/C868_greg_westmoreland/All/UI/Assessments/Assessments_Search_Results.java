package android.reserver.C868_greg_westmoreland.All.UI.Assessments;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.List_Terms_Adapter;
import android.reserver.C868_greg_westmoreland.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Assessments_Search_Results extends Activity {

    /**
     * Declare Database Repository
     */
    private SchedulerRepository repository;

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    /**
     * onNewIntent method
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    /**
     * handleIntent method
     * @param intent
     */
    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println(query);
            if (query != null) {
                final Assessments_Adapter assessmentsAdapter = new Assessments_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.assessmentsListRecyclerView);
                recyclerView.setAdapter(assessmentsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                assessmentsAdapter.setAssessments(repository.getAllAssessmentsSearch(query));
            }
        }
    }
}
