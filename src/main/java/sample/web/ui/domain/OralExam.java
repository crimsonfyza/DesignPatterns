package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Component
public class OralExam extends Exam {

    private int NumberOfStudents = 1;

    public OralExam(){}

    @Override
    public void createExam() {
        System.out.println("Create a new oral exam.");
    }
}
