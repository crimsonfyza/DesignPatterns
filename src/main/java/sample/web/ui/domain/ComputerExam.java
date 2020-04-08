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
@NoArgsConstructor
@Component
public class ComputerExam extends Exam {


    private Boolean ownComputer = false;


    private Boolean internetAllowed = false;

    @Override
    public void createExam() {
        System.out.println("Create a new computer exam.");
    }
}
