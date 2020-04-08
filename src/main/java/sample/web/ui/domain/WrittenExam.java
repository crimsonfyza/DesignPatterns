package sample.web.ui.domain;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import sample.web.ui.repository.ExamRepository;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Component
public class WrittenExam extends Exam {


    private boolean notesAllowed = false;


    @Override
    public void createExam() {

        System.out.println("Create a new written exam.");
    }
}
