package android.reserver.c196_greg_westmoreland.All.UI.Assessments;

/**
 * Import statements
 */
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.reserver.c196_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.c196_greg_westmoreland.R;

public class Edit_Existing_Assessment extends AppCompatActivity {
    private SchedulerRepository repository;

    public static int courseIdAssessmentEditPage = -1;

    /**
     * The detailed assessments view when the screen loads
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asssesment_view);
    }
}