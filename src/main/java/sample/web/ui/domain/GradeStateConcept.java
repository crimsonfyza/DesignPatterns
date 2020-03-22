package sample.web.ui.domain;

public class GradeStateConcept implements GradeState {
    @Override
    public void next(Grade grade) {
        grade.setState(new GradeStateFinal());
    }

    @Override
    public void prev(Grade grade) {
        grade.setState(new GradeStateNotEntered());
    }

    @Override
    public void printStatus() {
        System.out.println("This grade is in concept.");
    }
}
