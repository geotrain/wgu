package android.reserver.C868_greg_westmoreland.All.UI.Utilities;

/**
 * Import Statements
 */
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

public class Date_Picker_Fragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    // Declare TextView variable datePickerView
    private TextView datePickerView;
    // Declare Calender variable
    final Calendar calendar = Calendar.getInstance();

    /**
     * This method DatePickerFragment is used in Terms Edit Existing Terms java class
     * @param datePickerView
     */
    public Date_Picker_Fragment(TextView datePickerView) {
        this.datePickerView = datePickerView;
    }

    /**
     * This method is used to save the instance state once a date is selected
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    /**
     * This method once the date is sset saves everything to a string
     * @param view
     * @param year
     * @param month
     * @param day
     */
    public void onDateSet(DatePicker view, int year, int month, int day) {
        ++month;
        // Do something with the date chosen by the user
        String currentDateString = month + "/" + day + "/" + year;
        datePickerView.setText(currentDateString);
    }
}
