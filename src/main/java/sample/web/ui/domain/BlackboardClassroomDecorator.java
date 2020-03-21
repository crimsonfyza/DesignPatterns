package sample.web.ui.domain;

public class BlackboardClassroomDecorator extends ClassroomDecorator {
    public BlackboardClassroomDecorator(Classroom decoratedClassroom){
        super(decoratedClassroom);
    }

    @Override
    public ClassroomObject assignValues(String name, int capacity){
        return setBoard(decoratedClassroom.assignValues(name, capacity));
    }

    private ClassroomObject setBoard(ClassroomObject classroom){
        classroom.setBoard("blackboard");
        return classroom;
    }
}