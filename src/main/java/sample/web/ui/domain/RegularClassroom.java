package sample.web.ui.domain;

public class RegularClassroom implements Classroom {

    @Override
    public ClassroomObject assignValues(String name, int capacity){
        ClassroomObject classroomObject = new ClassroomObject(name, capacity, "regular", "");
        return classroomObject;
    }
}
