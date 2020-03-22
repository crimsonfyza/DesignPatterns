package sample.web.ui.domain;


public abstract class ExamFactory {
    public static Exam getExam(ExamType type) {
        Exam exam = null;
        switch (type) {
            case WRITTEN:
                exam = new WrittenExam();
                break;
            case COMPUTER:
                exam = new ComputerExam();
                break;
            case ORAL:
                exam = new OralExam();
                break;
        }
        return exam;
    }
}
