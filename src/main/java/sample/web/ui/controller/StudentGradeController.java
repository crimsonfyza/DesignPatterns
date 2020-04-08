package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import sample.web.ui.domain.Exam;
import sample.web.ui.domain.Grade;
import sample.web.ui.domain.User;
import sample.web.ui.domain.WrittenExam;
import sample.web.ui.repository.ExamRepository;
import sample.web.ui.repository.GradeRepository;
import sample.web.ui.service.UserService;

import java.security.Principal;

/**
 * Controller student functions.
 *
 * @author  Mark van Dalen
 *
 */


@Controller
@RequestMapping("student/grade")
public class StudentGradeController {


    private final GradeRepository gradeRepository;
    private final ExamRepository examRepository;

    @Autowired
    private UserService userService;

    public StudentGradeController(GradeRepository gradeRepository, ExamRepository examRepository) {
        this.gradeRepository = gradeRepository;
        this.examRepository = examRepository;
    }

    //GET a list of all the grades of the current user
    @Transactional
    @GetMapping
    public ModelAndView list(Principal principal){
        long studentId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(studentId);
        Iterable<Grade> grades = gradeRepository.findAllByStudent(currentUser);
        return new ModelAndView("grades/list", "grades", grades);
    }

    //GET a list off al the Exams available
    @Transactional
    @GetMapping(value = "/exams")
    public ModelAndView listExams(){
        Iterable<Exam> exams = examRepository.findAll();
        return new ModelAndView("exams/list", "exams", exams);
    }

    //GET a single exaM and make a new exam entry (grade) from the exam
    @Transactional
    @GetMapping(value = "/exams/{id}")
    public ModelAndView getExam(@PathVariable("id") Long id, Principal principal){
        long studentId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(studentId);
        Exam exam = new WrittenExam();
        exam.setId(id);
        Grade newGrade = new Grade();
        newGrade.setExam(exam);
        newGrade.setStudent(currentUser);
        gradeRepository.save(newGrade);

        Iterable<Exam> exams = examRepository.findAll();
        return new ModelAndView("exams/list", "exams", exams);
    }
}
