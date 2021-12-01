package android.reserver.c196_greg_westmoreland.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.R;
import android.view.MenuItem;
import android.view.View;

public class DetailedTermView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term_view);
        // Add backward navigation to action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override // This method is called when the backward arrow -> navigation icon is selected
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveTerm(View view) {
        // Write code to save term to database
    }
}