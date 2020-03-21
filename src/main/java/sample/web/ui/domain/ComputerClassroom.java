package sample.web.ui.domain;

public class ComputerClassroom implements Classroom {

    @Override
    public ClassroomObject assignValues(String name, int capacity){
        ClassroomObject classroomObject = new ClassroomObject(name, capacity, "computer", "");
        return classroomObject;
    }
}
