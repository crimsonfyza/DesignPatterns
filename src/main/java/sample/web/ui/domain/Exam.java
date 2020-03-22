package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Exam {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name is required.")
    private String name = null;


    //@NotEmpty(message = "Type is required.")
    private ExamType type = null;

    //@NotEmpty(message = "Date is required.")
    private Date date = null;

    public abstract void createExam();
}
