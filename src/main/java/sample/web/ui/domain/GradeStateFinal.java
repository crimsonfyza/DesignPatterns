package sample.web.ui.domain;

/**
 *
 * @author  Mark van Dalen
 *
 */

public class GradeStateFinal implements GradeState {
    @Override
    public void next(Grade grade) {
        System.out.println("This grade is already final.");
    }

    @Override
    public void prev(Grade grade) {
        grade.setState(new GradeStateConcept());
    }

    @Override
    public void printStatus() {
        System.out.println("This grade is final");
    }
}
