package android.reserver.c196_greg_westmoreland.All.Database;

/**
 * The import statements
 */
import android.reserver.c196_greg_westmoreland.All.Entities.AssessmentTypeEntity;
import android.text.TextUtils;
import androidx.room.TypeConverter;

public class SchedulerAssessmentTypeConverter {
    /**
     * The method that takes the assessment type and converts it to a string
     * @param assessmentType
     * @return
     */
    @TypeConverter
    public static String fromAssessmentTypeToString(AssessmentTypeEntity assessmentType) {
        if (assessmentType == null) {
            return null;
        }
        return assessmentType.name();
    }

    /**
     * The method that takes a string and converts it to an assessment type
     * @param assessmentType
     * @return
     */
    @TypeConverter
    public static AssessmentTypeEntity fromStringToAssessmentType(String assessmentType) {
        if (TextUtils.isEmpty(assessmentType)) {
            return AssessmentTypeEntity.Obj_Assessment;
        }
        return AssessmentTypeEntity.valueOf(assessmentType);
    }
}
