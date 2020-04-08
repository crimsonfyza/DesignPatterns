package sample.ui;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;

import sample.web.ui.SampleWebUiApplication;
import sample.web.ui.domain.Grade;


import static org.junit.Assert.assertEquals;

/**
 *
 * @author  Mark van Dalen
 *
 */

@WebAppConfiguration
public class GradeTests {

    @Test
    public void testGradeState() {

        // Arrange
        Grade grade1 = new Grade();
        Grade grade2 = new Grade();
        Grade grade3 = new Grade();
        // Act
        grade2.nextState();
        grade3.nextState();
        grade3.nextState();


        // Assert
        assertEquals("GradeStateNotEntered", grade1.getCurrentState());
        assertEquals("GradeStateConcept", grade2.getCurrentState());
        assertEquals("GradeStateFinal", grade3.getCurrentState());
    }

}
