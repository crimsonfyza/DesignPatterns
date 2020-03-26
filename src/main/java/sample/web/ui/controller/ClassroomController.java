package sample.web.ui.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.domain.*;
import sample.web.ui.repository.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
@RequestMapping("classroom")
public class ClassroomController {
    private final ClassroomRepository classroomRepository;

    public ClassroomController(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Transactional
    @GetMapping
    public ModelAndView list(){
        Iterable<ClassroomObject> classrooms = classroomRepository.findAll();
        return new ModelAndView("classrooms/list", "classrooms", classrooms);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") ClassroomObject classroom){
        return new ModelAndView("classrooms/view", "classroom", classroom);
    }

    @Transactional
    @GetMapping(params = "form")
    public ModelAndView createForm(@ModelAttribute ClassroomObject classroom){
        return new ModelAndView("classrooms/form", "classroom", classroom);
    }

    @PostMapping
    public ModelAndView create(@Valid ClassroomObject classroom, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return new ModelAndView("classrooms/form", "formErrors", result.getAllErrors());
        }

        Classroom newClassroom;

        switch (classroom.getType()){
            case "conference":
                newClassroom = new ConferenceClassroom();
                break;

            case "computer":
                newClassroom = new ComputerClassroom();
                break;

            default:
                newClassroom = new RegularClassroom();
        }
        saveClassroom(newClassroom, classroom.getBoard(), classroom.getName(), classroom.getCapacity());

        redirect.addFlashAttribute("globalMessage", "Successfully created a new classroom");
        return new ModelAndView("redirect:/classroom/", "classroom", classroom);
    }

    public void saveClassroom(Classroom classroom, String board, String name, int capacity){
        switch (board){
            case "whiteboard":
                WhiteboardClassroomDecorator whiteboardClassroom = new WhiteboardClassroomDecorator(classroom);
                classroomRepository.save(whiteboardClassroom.assignValues(name, capacity));
                break;

            case "blackboard":
                BlackboardClassroomDecorator blackboardClassroom = new BlackboardClassroomDecorator(classroom);
                classroomRepository.save(blackboardClassroom.assignValues(name, capacity));
                break;

            default:
                classroomRepository.save(classroom.assignValues(name, capacity));
                break;
        }
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        this.classroomRepository.deleteById(id);
        Iterable<ClassroomObject> classrooms = this.classroomRepository.findAll();
        return new ModelAndView("classrooms/list", "classrooms", classrooms);
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") ClassroomObject classroom){
        return new ModelAndView("classrooms/form", "classrooms", classroom);
    }
}
