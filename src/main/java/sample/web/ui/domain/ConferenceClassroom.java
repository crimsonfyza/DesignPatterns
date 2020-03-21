package sample.web.ui.domain;

public class ConferenceClassroom implements Classroom {

    @Override
    public ClassroomObject assignValues(String name, int capacity){
        ClassroomObject classroomObject = new ClassroomObject(name, capacity, "conference", "");
        return classroomObject;
    }
}
