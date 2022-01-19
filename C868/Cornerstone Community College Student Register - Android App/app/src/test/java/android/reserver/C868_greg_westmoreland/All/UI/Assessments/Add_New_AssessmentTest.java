package android.reserver.C868_greg_westmoreland.All.UI.Assessments;

/**
 * Import statements
 */
import static org.junit.Assert.assertEquals;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.AssessmentsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.text.ParseException;

@RunWith(MockitoJUnitRunner.class)

public class Add_New_AssessmentTest {

    @Mock
    SchedulerRepository repository;

    // Declare integer id for db
    int id = 0;
    int courseID = 0;

    /**
     * Unit Test
     */
    @Test
    public void testSaveAssessment_Success() {
        String assessmentName = "Test Assessment";
        String assessmentType = "Test Type";
        String assessmentStartDate = "01/01/2020";
        String assessmentEndDate = "01/02/2020";
        AssessmentsEntity newAssessment = new AssessmentsEntity(
                ++id,
                assessmentName,
                courseID,
                assessmentType,
                assessmentStartDate,
                assessmentEndDate);
        repository.insert(newAssessment);
        assertEquals(newAssessment.getAssessmentID(), id);
        assertEquals(newAssessment.getCourseID(), courseID);
        assertEquals(newAssessment.getAssessmentName(), assessmentName);
        assertEquals(newAssessment.getAssessmentType(), assessmentType);
        assertEquals(newAssessment.getAssessmentStartDate(), assessmentStartDate);
        assertEquals(newAssessment.getAssessmentEndDate(), assessmentEndDate);
    }

    /**
     * Unit Test
     */
    @Test
    public void testSaveAssessment_Failure_EmptyStartDate() {
        String assessmentName = "Assessment Name";
        String assessmentType = "Assessment Type";
        String assessmentStartDate = "";
        String assessmentEndDate = "Assessment End Date";
        AssessmentsEntity newAssessment = new AssessmentsEntity(++id, assessmentName, courseID, assessmentType,
                assessmentStartDate, assessmentEndDate);
        repository.insert(newAssessment);
        assertEquals(0, repository.getAllAssessments().size());
    }

    /**
     * Unit Test
     */
    @Test
    public void testSaveAssessment_Failure_EmptyEndDate() {
        String assessmentName = "Assessment Name";
        String assessmentType = "Assessment Type";
        String assessmentStartDate = "01/01/2020";
        String assessmentEndDate = "";
        AssessmentsEntity newAssessment = new AssessmentsEntity(
                ++id,
                assessmentName,
                courseID,
                assessmentType,
                assessmentStartDate,
                assessmentEndDate);
        repository.insert(newAssessment);
        assertEquals(repository.getAllAssessments().size(), 0);
    }

    /**
     * Unit Test
     */
    @Test
    public void testSaveAssessment_Failure_EmptyType() {
        String assessmentName = "Assessment Name";
        String assessmentType = "";
        String assessmentStartDate = "01/01/2020";
        String assessmentEndDate = "01/31/2020";
        AssessmentsEntity newAssessment = new AssessmentsEntity(
                ++id,
                assessmentName,
                courseID,
                assessmentType,
                assessmentStartDate,
                assessmentEndDate);
        repository.insert(newAssessment);
        assertEquals(repository.getAllAssessments().size(), 0);
    }

    /**
     * Unit Test
     */
    @Test
    public void testSaveAssessment_Failure_StartDateEqualsEndDate() throws ParseException {
        String assessmentName = "Assessment Name";
        String assessmentType = "Assessment Type";
        String assessmentStartDate = "01/01/2020";
        String assessmentEndDate = "01/01/2020";
        AssessmentsEntity newAssessment = new AssessmentsEntity(
                ++id,
                assessmentName,
                courseID,
                assessmentType,
                assessmentStartDate,
                assessmentEndDate);
        repository.insert(newAssessment);
        assertEquals(repository.getAllAssessments().size(), 0);
    }
}