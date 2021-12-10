package android.reserver.c196_greg_westmoreland.All.UI.Utilities;

/**
 * Import statments
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtility {

    /**
     * Date Time Formatter to format date input
     */
    public static DateTimeFormatter date_time_formatter = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US);

    /**
     * Setting local date and time
     * @param date
     * @return
     */
    public static LocalDate parseDate(String date) {
        String pattern = "M/d/yyyy";
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(pattern);
        LocalDate dateReturn = LocalDate.parse(date, sdf);
        return dateReturn;
    }
}