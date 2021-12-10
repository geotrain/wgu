package android.reserver.c196_greg_westmoreland.All.Database;

/**
 * The import statements
 */
import android.reserver.c196_greg_westmoreland.All.Entities.CourseStatusEntity;
import android.text.TextUtils;
import androidx.room.TypeConverter;

public class SchedulerCourseStatusTypeConverter {

    /**
     * The method that takes the course status and converts it to a string
     * @param courseStatus
     * @return
     */
    @TypeConverter
    public static String fromCourseStatusToString(CourseStatusEntity courseStatus) {
        if(courseStatus == null) {
            return null;
        }
        return courseStatus.name();
    }

    /**
     * The method that takes the string and converts it to a course status
     * @param courseStatus
     * @return
     */
    @TypeConverter
    public static CourseStatusEntity fromStringToCourseStatus(String courseStatus) {
        if(TextUtils.isEmpty(courseStatus)) {
            return CourseStatusEntity.IN_PROGRESS;
        }
        return CourseStatusEntity.valueOf(courseStatus);
    }
}
