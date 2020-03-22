package sample.web.ui.domain;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import sample.web.ui.repository.ExamRepository;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter

public class WrittenExam extends Exam {


    private boolean NotesAllowed = false;

    public void setNotedAllowed(boolean NotesAllowed) {
        this.NotesAllowed = NotesAllowed;

   }

    @Override
    public void createExam() {

        System.out.println("Create a new written exam.");
    }
}
