package android.reserver.c196_greg_westmoreland.All.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.R;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterHere(View view) {
        // Add Code for when user clicks enter button
        Intent intent = new Intent(MainActivity.this, TermsList.class);
        startActivity(intent);
    }
}