package android.reserver.C868_greg_westmoreland.All.UI.Terms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import android.reserver.C868_greg_westmoreland.All.Database.SchedulerRepository;
import android.reserver.C868_greg_westmoreland.All.Entities.TermsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.text.ParseException;


@RunWith(MockitoJUnitRunner.class)

public class Add_New_Term_Test {

    @Mock
    SchedulerRepository repository;

    // Declare integer id for db
    int id = 0;

    @Test
    public void testSaveTerm_Failure() {
        TermsEntity newTerm = new TermsEntity(
                ++id,
                "",
                "",
                "");
        repository.insert(newTerm);
        assertEquals(1, newTerm.getTermID());
    }

    @Test
    public void testSaveTerm_Failure_EmptyTermName() {
        String termName = "";
        String termStartDate = "01/01/2020";
        String termEndDate = "01/31/2020";
        TermsEntity newTerm = new TermsEntity(
                ++id,
                termName,
                termStartDate,
                termEndDate);
        repository.insert(newTerm);
        assertEquals("", newTerm.getTermName());
    }

    @Test
    public void testSaveTerm_Failure_EmptyStartDate() {
        String termName = "Term Name";
        String termStartDate = "";
        String termEndDate = "12/31/2020";
        TermsEntity newTerm = new TermsEntity(
                ++id,
                termName,
                termStartDate,
                termEndDate);
        assertEquals(termName, newTerm.getTermName());
        assertEquals(termStartDate, newTerm.getTermStartDate());
        assertEquals(termEndDate, newTerm.getTermEndDate());
    }

    @Test
    public void testSaveTerm_Success() {
        TermsEntity newTerm = new TermsEntity(
                ++id,
                "Fall 2019",
                "09/01/2019",
                "12/31/2019");
        repository.insert(newTerm);
    }

    @Test
    public void testSaveTerm_Failure_EmptyEndDate() {
        String termName = "Term Name";
        String termStartDate = "01/01/2020";
        String termEndDate = "";
        TermsEntity newTerm = new TermsEntity(
                ++id,
                termName,
                termStartDate,
                termEndDate);
        assertEquals(termName, newTerm.getTermName());
        assertEquals(termStartDate, newTerm.getTermStartDate());
        assertEquals(termEndDate, newTerm.getTermEndDate());
    }

    @Test
    public void testSaveTerm_Failure_StartDateAfterEndDate() {
        String termName = "Term Name";
        String termStartDate = "01/01/2020";
        String termEndDate = "01/02/2020";
        TermsEntity newTerm = new TermsEntity(
                ++id,
                termName,
                termStartDate,
                termEndDate);
        assertEquals(termName, newTerm.getTermName());
        assertEquals(termStartDate, newTerm.getTermStartDate());
        assertEquals(termEndDate, newTerm.getTermEndDate());
    }

    @Test
    public void testSaveTerm_Failure_StartDateEqualsEndDate() throws ParseException {
        String termName = "Term Name";
        String termStartDate = "01/01/2020";
        String termEndDate = "01/01/2020";
        TermsEntity newTerm = new TermsEntity(
                ++id,
                termName,
                termStartDate,
                termEndDate);
        repository.insert(newTerm);
        assertEquals(1, newTerm.getTermID());
        assertEquals("Term Name", newTerm.getTermName());
        assertEquals("01/01/2020", newTerm.getTermStartDate());
        assertEquals("01/01/2020", newTerm.getTermEndDate());
    }
}