package sample.web.ui.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.web.ui.domain.CreateExam;
import sample.web.ui.domain.ExamObject;
import sample.web.ui.repository.ExamRepository;

@Controller
@RequestMapping("/")

public class ExamController {

    private final ExamRepository examRepository;

    @Autowired
    public ExamController (
            ExamRepository examRepository
    ) {
            this.examRepository = examRepository;
    }

    @Autowired
    public void TEST() {

        //
        String examName = "Wiskune";
        String type = "Schriftelijk";
        //

        CreateExam createExam = new CreateExam();
        ExamObject examObject = createExam.createExam(examName, type);

        examRepository.save(examObject);

    }

}
