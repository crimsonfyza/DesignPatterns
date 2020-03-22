package sample.web.ui.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue
    private Long id;

    private double result;

    @Transient
    private GradeState state = new GradeStateNotEntered();

    @OneToOne(cascade = {CascadeType.MERGE})
    private Exam exam;

    //student nog eraan koppelen!!!!!

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        state.printStatus();
    }


}
