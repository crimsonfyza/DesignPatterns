package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import sample.web.ui.domain.*;
import sample.web.ui.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ClassroomController {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomController(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Autowired
    public void addClassroom(){
        WhiteboardClassroomDecorator whiteboardClassroom = new WhiteboardClassroomDecorator(new ConferenceClassroom());
        classroomRepository.save(whiteboardClassroom.assignValues("AB101", 30));

        BlackboardClassroomDecorator blackboardClassroom = new BlackboardClassroomDecorator(new RegularClassroom());
        classroomRepository.save(blackboardClassroom.assignValues("AB102", 40));

        ComputerClassroom computerClassroom1 = new ComputerClassroom();
        classroomRepository.save(computerClassroom1.assignValues("AB103", 20));
    }
}
