package sample.ui;

import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import sample.web.ui.domain.Exam;
import sample.web.ui.domain.ExamFactory;
import sample.web.ui.domain.ExamType;
import sample.web.ui.domain.Grade;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author  Mark van Dalen
 *
 */

@WebAppConfiguration
public class ExamTests {
    @Test
    public void testExamTypes() {

        // Arrange
        Exam exam1 = ExamFactory.getExam(ExamType.COMPUTER);
        Exam exam2 = ExamFactory.getExam(ExamType.ORAL);
        Exam exam3 = ExamFactory.getExam(ExamType.WRITTEN);
        // Act

        // Assert
        assertEquals("ComputerExam", exam1.getClass().getSimpleName());
        assertEquals("OralExam", exam2.getClass().getSimpleName());
        assertEquals("WrittenExam", exam3.getClass().getSimpleName());
    }

}
