package android.reserver.C868_greg_westmoreland.All.UI.Terms;

/*
/**
 * Import statements
 */
/*
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.DAO.TermsDao;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Terms_Search_Results extends Activity {

    /**
     * Declare Database Repository
     */
/*
    private SchedulerRepository repository;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println(query);
            if (query != null) {

                final List_Terms_Adapter termsAdapter = new List_Terms_Adapter(this);
                RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
                recyclerView.setAdapter(termsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termsAdapter.setTerms(repository.getAllTermsSearch(query));
            }
        }
    }
}*/
