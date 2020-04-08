package sample.web.ui.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 *
 * @author  Mark van Dalen
 *
 */

public class GradeStateNotEntered implements GradeState {

    @Override
    public void next(Grade grade) {
        grade.setState(new GradeStateConcept());
    }

    @Override
    public void prev(Grade grade) {
        System.out.println("This grade is not entered.");
    }

    @Override
    public void printStatus() {
        System.out.println("This grade in not entered yet.");
    }
}
