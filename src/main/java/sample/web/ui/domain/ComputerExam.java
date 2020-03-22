package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ComputerExam extends Exam {


    private Boolean OwnComputer = false;


    private Boolean internetAllowed = false;

    @Override
    public void createExam() {
        System.out.println("Create a new computer exam.");
    }
}
