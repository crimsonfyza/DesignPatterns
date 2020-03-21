package sample.web.ui.domain;

public class CreateExam {

    public CreateExam() {

    }

    public ExamObject createExam (String examName, String type) {
        ExamObject examObject = new ExamObject(examName, type);
        return examObject;
    }
}
