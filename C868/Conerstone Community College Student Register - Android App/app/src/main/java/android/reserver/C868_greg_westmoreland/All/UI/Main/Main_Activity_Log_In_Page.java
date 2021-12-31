package android.reserver.C868_greg_westmoreland.All.UI.Main;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.C868_greg_westmoreland.All.DAO.UsersDao;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.UsersEntity;
import android.reserver.C868_greg_westmoreland.All.UI.Assessments.List_Assessments;
import android.reserver.C868_greg_westmoreland.All.UI.Courses.List_Courses;
import android.reserver.C868_greg_westmoreland.All.UI.Terms.List_Terms;
import android.reserver.C868_greg_westmoreland.R;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Main_Activity_Log_In_Page extends AppCompatActivity {

    private SchedulerRepository repository;
    String username;
    String password;
    EditText editUsername;
    EditText editPassword;

    /**
     * This variable sets the numAlert variable for the notify in the terms list detail screen
     */
    public static int numAlert;

    /**
     * This method when the main screen (home screen) is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log_in_page);
        username = getIntent().getStringExtra("loginUsername");
        password = getIntent().getStringExtra("loginPassword");

        // Creates DB upon the main screen loading
        repository = new SchedulerRepository(getApplication());
        repository.getAllTerms();

        // Get user input for username and password
        editUsername = findViewById(R.id.loginUsername);
        editPassword = findViewById(R.id.loginPassword);
    }

    public void logIn(View view) throws Exception{

        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        // Check if username and password are empty or missing values
        if (editUsername == null || editUsername.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a username.",
                    Toast.LENGTH_LONG).show();
            return;
        } else if (editPassword == null || editPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please supply a password.",
                    Toast.LENGTH_LONG).show();
        } else if (!(editUsername.getText().toString().trim().isEmpty() && editPassword.getText()
                .toString().trim().isEmpty())) {
            //boolean result = repository.checkUsernameAndPassword(username, password);
            //if (result) {
            if (true) {
                Intent intent = new Intent(Main_Activity_Log_In_Page.this,
                        Main_Activity_Home_Page.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "You have entered an incorrect username: "
                        + editUsername + " or password: " + editPassword, Toast.LENGTH_LONG).show();
            }
        }
    }
}