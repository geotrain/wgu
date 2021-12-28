package android.reserver.c196_greg_westmoreland.All.Database;

/**
 * Import statements
 */
import androidx.room.TypeConverter;
import java.util.Date;

public class SchedulerDateConverter {
    /**
     * @Typeconverter that takes the Date to Long
     * @param timestamp
     * @return
     */
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    /**
     * @Typeconverter that takes a String to Date
     * @param date
     * @return
     */
    @TypeConverter
    public static String toTimestamp(Date date) {
        return String.valueOf(date == null ? null : date.getTime());
    }
}
