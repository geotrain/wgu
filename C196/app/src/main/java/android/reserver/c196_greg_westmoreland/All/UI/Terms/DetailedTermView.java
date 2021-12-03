package android.reserver.c196_greg_westmoreland.All.UI.Terms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.c196_greg_westmoreland.R;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DetailedTermView extends AppCompatActivity {

    String termName;
    EditText editTermName;
    String termStartDate;
    EditText editTermStartDate;
    String termEndDate;
    EditText editTermEndDate;
    int id;
    SchedulerRepository repository;
    private Object TermsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term_view);
        // Add backward navigation to action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Get termName, termStartDate, and termEndDate from activity_parts_list.xml
        termName = getIntent().getStringExtra((termName));
        editTermName = findViewById(R.id.Term_Name);
        editTermName.setText(termName);
        termStartDate = getIntent().getStringExtra(termStartDate);
        editTermStartDate = findViewById(R.id.Start_Date);
        editTermStartDate.setText(termStartDate);
        termEndDate = getIntent().getStringExtra(termEndDate);
        editTermEndDate = findViewById(R.id.End_Date);
        editTermEndDate.setText(termEndDate);
        repository = new SchedulerRepository(getApplication());
    }

    @Override // This method is called when the backward arrow -> navigation icon is selected
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.refresh:
                repository = new SchedulerRepository(getApplication());
                List<TermsEntity> allThings = repository.getAllTerms();
                final TermsAdapter termsAdapter = new TermsAdapter(this);
                RecyclerView recyclerView = findViewById(R.id.termsListRecyclerView);
                recyclerView.setAdapter(termsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                termsAdapter.setThings(allThings);
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveTerm(View view) {
        String termName = editTermName.getText().toString();
        if(termName == null) {
            TermsEntity newTerm = new TermsEntity(++id, termName, termStartDate, termEndDate);
            repository.insert(newTerm);
        } else if (termName == this.getText(Integer.parseInt(termName))) { // <-- NEED HELP HERE
            TermsEntity oldTerm = new TermsEntity(getIntent().getIntExtra("termID",1),
                    termName, termStartDate, termEndDate);
            repository.delete(oldTerm);
        } else {
            TermsEntity oldTerm = new TermsEntity(getIntent().getIntExtra("termID",-1),
                    termName, termStartDate, termEndDate);
            repository.update(oldTerm);
        }
    }

    public void date(View view) {
        // Used for Date Picker
    }
}