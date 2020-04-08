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

    @Transactional
    @GetMapping
    public ModelAndView list(Principal principal){
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Exam exam){
        return new ModelAndView("exams/view", "exam", exam);
    }

    @Transactional
    @GetMapping(value = "formComputer")
    public ModelAndView createFormComputer(@ModelAttribute ComputerExam exam){
        return new ModelAndView("exams/formComputer", "exam", exam);
    }

    @Transactional
    @GetMapping(value = "formOral")
    public ModelAndView createFormOral(@ModelAttribute OralExam exam){
        return new ModelAndView("exams/formOral", "exam", exam);
    }

    @Transactional
    @GetMapping(value = "formWritten")
    public ModelAndView createFormWritten(@ModelAttribute WrittenExam exam){
        return new ModelAndView("exams/formWritten", "exam", exam);
    }

    @PostMapping(value = "formComputer")
    public ModelAndView createFormComputer(@Valid ComputerExam exam, BindingResult result, RedirectAttributes redirect, Principal principal){
        if(result.hasErrors()){
            return new ModelAndView("exams/formComputer", "formErrors", result.getAllErrors());
        }
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);

        Exam newExam = ExamFactory.getExam(ExamType.COMPUTER);
        newExam.setName(exam.getName());
        newExam.setDate(exam.getDate());
        newExam.setTeacher(currentUser);
        newExam.setType(ExamType.COMPUTER);
        ((ComputerExam) newExam).setInternetAllowed(exam.getInternetAllowed());
        ((ComputerExam) newExam).setOwnComputer(exam.getOwnComputer());
        examRepository.save(newExam);

        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    @PostMapping(value = "formOral")
    public ModelAndView createFormOral(@Valid OralExam exam, BindingResult result, RedirectAttributes redirect, Principal principal){
        if(result.hasErrors()){
            return new ModelAndView("exams/formComputer", "formErrors", result.getAllErrors());
        }
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);

        Exam newExam = ExamFactory.getExam(ExamType.ORAL);
        newExam.setName(exam.getName());
        newExam.setDate(exam.getDate());
        newExam.setTeacher(currentUser);
        ((OralExam) newExam).setNumberOfStudents(exam.getNumberOfStudents());

        newExam.setType(ExamType.ORAL);
        examRepository.save(newExam);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    @PostMapping(value = "formWritten")
    public ModelAndView createFormWritten(@Valid WrittenExam exam, BindingResult result, RedirectAttributes redirect, Principal principal){
        if(result.hasErrors()){
            return new ModelAndView("exams/formComputer", "formErrors", result.getAllErrors());
        }
        long teacherId = userService.findByUsername(principal.getName()).getId();
        User currentUser = new User();
        currentUser.setId(teacherId);

        Exam newExam = ExamFactory.getExam(ExamType.WRITTEN);
        newExam.setName(exam.getName());
        newExam.setDate(exam.getDate());
        newExam.setTeacher(currentUser);
        newExam.setType(ExamType.WRITTEN);
        ((WrittenExam) newExam).setNotesAllowed(exam.isNotesAllowed());
        examRepository.save(newExam);
        Iterable<Exam> exams = examRepository.findAllByTeacher(currentUser);
        return new ModelAndView("exams/list", "exams", exams);
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        this.examRepository.deleteById(id);
        Iterable<Exam> exams = this.examRepository.findAll();
        return new ModelAndView("exams/list", "exams", exams);
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Exam exam){
        return new ModelAndView("exam/form", "exam", exam);
    }

    @GetMapping(value = "{id}/grades")
    public ModelAndView grades(@PathVariable("id") Long id){
        Exam exam = new WrittenExam();
        exam.setId(id);
        Iterable<Grade> grades = gradeRepository.findAllByExam(exam);
        return new ModelAndView("grades/list", "grades", grades);
    }
}

