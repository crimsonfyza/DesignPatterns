package sample.web.ui.domain;

public class WhiteboardClassroomDecorator extends ClassroomDecorator {
    public WhiteboardClassroomDecorator(Classroom decoratedClassroom){
        super(decoratedClassroom);
    }

    @Override
    public ClassroomObject assignValues(String name, int capacity){
        return setBoard(decoratedClassroom.assignValues(name, capacity));
    }

    private ClassroomObject setBoard(ClassroomObject classroom){
        classroom.setBoard("whiteboard");
        return classroom;
    }
}
