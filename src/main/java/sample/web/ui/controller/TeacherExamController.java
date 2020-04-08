package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.domain.*;
import sample.web.ui.repository.ExamRepository;
import sample.web.ui.repository.GradeRepository;
import sample.web.ui.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

/**
 * Controller Teacher functions.
 *
 * @author  Mark van Dalen
 *
 */


@Controller
@RequestMapping("teacher/exam")
public class TeacherExamController {

    private final ExamRepository examRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    private UserService userService;

    public TeacherExamController(ExamRepository examRepository, GradeRepository gradeRepository) {
        this.examRepository = examRepository;
        this.gradeRepository = gradeRepository;
    }

    //GET all the exams from the current user
    @Transactional
    @GetMapping
    public ModelAndView list(Principal principal){
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    //GET a single exam
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Exam exam){
        return new ModelAndView("exams/view", "exam", exam);
    }

    // a view in spring boot does not accept an abstract class, because of this the form for exams are split in three separate forms
    // the exam factory design pattern has still been implemented in the @PostMapping from the different forms

    //GET the form for a computer exam
    @Transactional
    @GetMapping(value = "formComputer")
    public ModelAndView createFormComputer(@ModelAttribute ComputerExam exam){
        return new ModelAndView("exams/formComputer", "exam", exam);
    }

    //GET the form for an oral exam
    @Transactional
    @GetMapping(value = "formOral")
    public ModelAndView createFormOral(@ModelAttribute OralExam exam){
        return new ModelAndView("exams/formOral", "exam", exam);
    }

    //GET the form for a written exam
    @Transactional
    @GetMapping(value = "formWritten")
    public ModelAndView createFormWritten(@ModelAttribute WrittenExam exam){
        return new ModelAndView("exams/formWritten", "exam", exam);
    }

    //POST for the computer exam form
    @PostMapping(value = "formComputer")
    public ModelAndView createFormComputer(@Valid ComputerExam exam, BindingResult result, RedirectAttributes redirect, Principal principal){
        if(result.hasErrors()){
            return new ModelAndView("exams/formComputer", "formErrors", result.getAllErrors());
        }
        //get current user
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);

        //create a new Exam with type COMPUTER
        Exam newExam = ExamFactory.getExam(ExamType.COMPUTER);
        newExam.setName(exam.getName());
        newExam.setDate(exam.getDate());
        newExam.setTeacher(currentUser);
        newExam.setType(ExamType.COMPUTER);
        ((ComputerExam) newExam).setInternetAllowed(exam.getInternetAllowed());
        ((ComputerExam) newExam).setOwnComputer(exam.getOwnComputer());

        //save the new exam
        examRepository.save(newExam);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    //POST for the oral exam form
    @PostMapping(value = "formOral")
    public ModelAndView createFormOral(@Valid OralExam exam, BindingResult result, RedirectAttributes redirect, Principal principal){
        if(result.hasErrors()){
            return new ModelAndView("exams/formComputer", "formErrors", result.getAllErrors());
        }
        //get current user
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);

        //create a new Exam with type ORAL
        Exam newExam = ExamFactory.getExam(ExamType.ORAL);
        newExam.setName(exam.getName());
        newExam.setDate(exam.getDate());
        newExam.setTeacher(currentUser);
        ((OralExam) newExam).setNumberOfStudents(exam.getNumberOfStudents());
        newExam.setType(ExamType.ORAL);

        //save the new exam
        examRepository.save(newExam);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    //POST for the written exam form
    @PostMapping(value = "formWritten")
    public ModelAndView createFormWritten(@Valid WrittenExam exam, BindingResult result, RedirectAttributes redirect, Principal principal){
        if(result.hasErrors()){
            return new ModelAndView("exams/formComputer", "formErrors", result.getAllErrors());
        }
        //get current user
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);

        //create a new Exam with type WRITTEN
        Exam newExam = ExamFactory.getExam(ExamType.WRITTEN);
        newExam.setName(exam.getName());
        newExam.setDate(exam.getDate());
        newExam.setTeacher(currentUser);
        newExam.setType(ExamType.WRITTEN);
        ((WrittenExam) newExam).setNotesAllowed(exam.isNotesAllowed());

        //save the new exam
        examRepository.save(newExam);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    //DELETE a single exam
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        this.examRepository.deleteById(id);
        Iterable<Exam> exams = this.examRepository.findAll();
        return new ModelAndView("exams/list", "exams", exams);
    }

    //MODIFY an existing exan
    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Exam exam){
        return new ModelAndView("exam/form", "exam", exam);
    }

    //GET all the exam entries (grades) for an single exam
    @GetMapping(value = "{id}/grades")
    public ModelAndView grades(@PathVariable("id") Long id){
        Exam exam = new WrittenExam();
        exam.setId(id);
        Iterable<Grade> grades = gradeRepository.findAllByExam(exam);
        return new ModelAndView("grades/list", "grades", grades);
    }

    //GET a single grade so it can be updated
    @GetMapping(value = "/grades/{gradeId}")
    public ModelAndView grades(@PathVariable("gradeId") Grade grade){
        return new ModelAndView("grades/form", "grade", grade);
    }

    //POST for for updating a single grade
    @PostMapping(value = "/grades/{id}")
    public ModelAndView gradesPost(@PathVariable("id") Long id, Grade grade, Principal principal){
        Grade oldgrade = gradeRepository.findById(id).get();

        //State pattern, with the .nextState() the state of the grade will be updated
        //NotEntered -> Concept -> Final
        oldgrade.nextState();
        oldgrade.setResult(grade.getResult());

        //save the updated grade
        gradeRepository.save(oldgrade);

        //get current user
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }
}

