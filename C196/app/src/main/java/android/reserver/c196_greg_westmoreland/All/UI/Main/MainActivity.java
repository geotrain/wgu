package android.reserver.c196_greg_westmoreland.All.UI.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.All.Entities.TermsEntity;
import android.reserver.c196_greg_westmoreland.All.UI.Terms.TermsList;
import android.reserver.c196_greg_westmoreland.R;
import android.view.View;
import java.time.LocalDate;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Object TermsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make New Repository
        SchedulerRepository schedulerRepository = new SchedulerRepository(getApplication());

        // Add Data To Terms Table in Schedule Repository
        //Date DATE = new Date();
        TermsEntity termsEntitySpring = new TermsEntity(1, "Spring Term", "String", "String");
        TermsEntity termsEntitySummer = new TermsEntity(2, "Summer Term", "String", "String");
        TermsEntity termsEntityFall = new TermsEntity(3, "Fall Term", "String", "String");

        schedulerRepository.insert(termsEntitySpring);
        schedulerRepository.insert(termsEntitySummer);
        schedulerRepository.insert(termsEntityFall);

         }

    public void enterHere(View view) {
        // Add Code for when user clicks enter button
        Intent intent = new Intent(MainActivity.this, TermsList.class);
        startActivity(intent);
    }
}