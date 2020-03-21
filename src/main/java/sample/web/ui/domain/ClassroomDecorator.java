package sample.web.ui.domain;

public abstract class ClassroomDecorator implements Classroom {
    protected Classroom decoratedClassroom;

    public ClassroomDecorator(Classroom decoratedClassroom){
        this.decoratedClassroom = decoratedClassroom;
    }

    public ClassroomObject assignValues(String name, int capacity){
        return decoratedClassroom.assignValues(name, capacity);
    }
}
