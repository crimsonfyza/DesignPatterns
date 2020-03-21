package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClassroomObject {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int capacity;

    private String type;

    private String board;

    public ClassroomObject(String name, int capacity, String type, String board){
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.board = board;
    }

    public ClassroomObject(ClassroomObject c){
        this.name = c.name;
        this.capacity = c.capacity;
        this.type = c.type;
        this.board = c.board;
    }
}
